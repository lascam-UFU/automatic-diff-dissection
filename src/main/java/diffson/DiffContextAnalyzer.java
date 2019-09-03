package diffson;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import com.github.gumtreediff.tree.ITree;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import add.entities.PatternInstance;
import add.entities.RepairPatterns;
import add.features.detector.EditScriptBasedDetector;
import add.features.detector.repairpatterns.MappingAnalysis;
import add.features.detector.repairpatterns.RepairPatternDetector;
import add.main.Config;
import add.main.TimeChrono;
import fr.inria.coming.codefeatures.Cntx;
import fr.inria.coming.codefeatures.CodeFeatureDetector;
import fr.inria.coming.utils.MapList;
import gumtree.spoon.AstComparator;
import gumtree.spoon.builder.Json4SpoonGenerator;
import gumtree.spoon.builder.SpoonGumTreeBuilder;
import gumtree.spoon.builder.jsonsupport.NodePainter;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.DiffImpl;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtSwitch;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtElement;

/**
 * 
 * @author Matias Martinez
 *
 */
public class DiffContextAnalyzer {
    File out = null;

    public DiffContextAnalyzer() {
        super();
        PDDConfigurationProperties.properties.setProperty("maxdifftoanalyze", "5");

        out = new File("/tmp/");
        out.mkdirs();
    }

    public DiffContextAnalyzer(String outfile) {
        super();

        out = new File(outfile);
        out.mkdirs();
    }

    private Logger log = Logger.getLogger(this.getClass());
    int error = 0;
    int zero = 0;
    int withactions = 0;

    @SuppressWarnings("unchecked")
    public void run(String path) throws Exception {

        error = 0;
        zero = 0;
        withactions = 0;

        File dir = new File(path);

        beforeStart();

        for (File difffile : dir.listFiles()) {
            
            TimeChrono cr = new TimeChrono();
            cr.start();
            Map<String, Diff> diffOfcommit = new HashMap();

            if (difffile.isFile() || difffile.listFiles() == null)
                continue;

            if (!acceptFile(difffile)) {
                continue;
            }

            processDiff(difffile, diffOfcommit);

            // here, at the end, we compute the Context
            atEndCommit(difffile, diffOfcommit);
            
        }
        
        log.info("Final Results: ");
        log.info("----");
        log.info("Withactions " + withactions);
        log.info("Zero " + zero);
        log.info("Error " + error);

        beforeEnd();
    }

    @SuppressWarnings("unchecked")
    public void processDiff(File difffile, Map<String, Diff> diffOfcommit) {
        for (File fileModif : difffile.listFiles()) {
            int i_hunk = 0;

            if (".DS_Store".equals(fileModif.getName()))
                continue;

//            if (PDDConfigurationProperties.getPropertyBoolean("excludetests")
//                    && (fileModif.getName().toLowerCase().indexOf("test")!=-1))
//                continue;
            
            if (fileModif.getName().toLowerCase().indexOf("test")!=-1)
                continue;

            String pathname = fileModif.getAbsolutePath() + File.separator + difffile.getName() + "_"
                    + fileModif.getName();

            File previousVersion = new File(pathname + "_s.java");
            if (!previousVersion.exists()) {
                pathname = pathname + "_" + i_hunk;
                previousVersion = new File(pathname + "_s.java");
                if (!previousVersion.exists())
                    // break;
                    continue;
            }

            File postVersion = new File(pathname + "_t.java");
            i_hunk++;
            try {
                Diff diff = getdiffFuture(previousVersion, postVersion);

                String key = fileModif.getParentFile().getName() + "_" + fileModif.getName();
                diffOfcommit.put(key, diff);

                if (diff.getAllOperations().size() > 0) {

                    withactions++;

                } else {
                    zero++;
                }

            } catch (Throwable e) {
                e.printStackTrace();
                error++;
            }

        }
    }

    public void beforeEnd() {
        // Do nothing
    }

    public void beforeStart() {
        // Do nothing
    }

    private Future<Diff> getfutureResult(ExecutorService executorService, File left, File right) {

        Future<Diff> future = executorService.submit(() -> {

            AstComparator comparator = new AstComparator();
            return comparator.compare(left, right);

        });
        return future;
    }

    public Diff getdiffFuture(File left, File right) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Diff> future = getfutureResult(executorService, left, right);

        Diff resukltDiff = null;
        try {
            resukltDiff = future.get(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("job was interrupted");
        } catch (ExecutionException e) {
            log.error("caught exception: " + e.getCause());
        } catch (TimeoutException e) {
            log.error("timeout");
        }

        executorService.shutdown();
        
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            } 
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        
        return resukltDiff;

    }

    protected boolean acceptFile(File fileModif) {
        File f = new File(out.getAbsolutePath() + File.separator + fileModif.getName() + ".json");
        return !f.exists();
    }

    @SuppressWarnings("unchecked")
    public JsonObject atEndCommit(File difffile, Map<String, Diff> diffOfcommit) {
        try {

            JsonObject statsjsonRoot = getContextFuture(difffile.getName(), diffOfcommit);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            boolean savePerFile = false;// testing
            if (savePerFile) {

                JsonArray filesa = statsjsonRoot.getAsJsonArray("affected_files");
                for (JsonElement jsonElement : filesa) {

                    String name = jsonElement.getAsJsonObject().getAsJsonPrimitive("file").getAsString();

                    FileWriter fw = new FileWriter(out.getAbsolutePath() + File.separator + name + ".json");

                    String prettyJsonString = gson.toJson(jsonElement);
                    fw.write(prettyJsonString);

                    fw.flush();
                    fw.close();
                }

            } else {
                FileWriter fw = new FileWriter(out.getAbsolutePath() + File.separator + difffile.getName() + ".json");

                // Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String prettyJsonString = gson.toJson(statsjsonRoot);
                fw.write(prettyJsonString);
                // System.out.println(prettyJsonString);
                fw.flush();
                fw.close();
            }
            return statsjsonRoot;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Future<JsonObject> getContextInFeature(ExecutorService executorService, String id,
            Map<String, Diff> diffOfcommit) {

        Future<JsonObject> future = executorService.submit(() -> {
            JsonObject statsjsonRoot = calculateCntxJSON(id, diffOfcommit);
            return statsjsonRoot;
        });
        return future;
    }

    public JsonObject getContextFuture(String id, Map<String, Diff> operations) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<JsonObject> future = getContextInFeature(executorService, id, operations);

        JsonObject resukltDiff = null;
        try {
            resukltDiff = future.get(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error("job was interrupted");
        } catch (ExecutionException e) {
            log.error("caught exception: " + e.getCause());
        } catch (TimeoutException e) {
            log.error("timeout context analyzed.");
        }

        executorService.shutdown();
        
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            } 
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        
        return resukltDiff;
    }

    /////// ---------=-=-=-=--=-=-=-

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public JsonObject calculateCntxJSON(String id, Map<String, Diff> operations) {

        JsonObject statsjsonRoot = new JsonObject();
        statsjsonRoot.addProperty("diffid", id);
        JsonArray filesArray = new JsonArray();
        statsjsonRoot.add("affected_files", filesArray);

        for (String modifiedFile : operations.keySet()) {
            MapList<Operation, String> patternsPerOp = new MapList<>();
            MapList<Operation, String> repairactionPerOp = new MapList<>();
            List<PatternInstance> patternInstances = new ArrayList<>();

            Diff diff = operations.get(modifiedFile);
            List<Operation> operationsFromFile = diff.getRootOperations();

            log.info("Diff file " + modifiedFile + " " + operationsFromFile.size());
            // Patterns:
          //  System.out.println(diff.getRootOperations().size());
            if(diff.getRootOperations().size()<=10) {
               JsonObject fileModified = new JsonObject();

               fileModified.addProperty("file", modifiedFile);
               fileModified.addProperty("nr_root_ast_changes", diff.getRootOperations().size());
               filesArray.add(fileModified);

               Config config = new Config();
               EditScriptBasedDetector.preprocessEditScript(diff);
               TimeChrono cr = new TimeChrono();
               cr.start();
               RepairPatternDetector detector = new RepairPatternDetector(config, diff);
               RepairPatterns rp = detector.analyze();

               for (List<PatternInstance> pi : rp.getPatternInstances().values()) {
                  patternInstances.addAll(pi);
               }
               cr.start();

               JsonArray ast_arrays = calculateJSONAffectedStatementList(diff, operationsFromFile, patternsPerOp,
                    repairactionPerOp, patternInstances);
            // fileModified.add("faulty_stmts_ast", ast_arrays);
               fileModified.add("pattern_instances", ast_arrays);

               includeAstChangeInfoInJSon(diff, operationsFromFile, fileModified);
           }
        }

        return statsjsonRoot;

    }

    public void includeAstChangeInfoInJSon(Diff diff, List<Operation> operationsFromFile, JsonObject fileModified) {
        JsonArray ast_changes_arrays = new JsonArray();
        // Here include optionality

        for (Operation op : operationsFromFile) {

            JsonObject astNode = new JsonObject();
            astNode.addProperty("change_type", op.getClass().getSimpleName());
            astNode.addProperty("entity_type",
                    op.getSrcNode().getClass().getSimpleName().replaceAll("Ct", "").replaceAll("Impl", ""));

            ast_changes_arrays.add(astNode);
        }
        fileModified.add("ast_changes", ast_changes_arrays);
    }

    /**
     * 
     * @param operation
     * @param cresolver
     * @param opContext
     * @param diff
     */

    @SuppressWarnings({ "unchecked", "unused", "rawtypes" })
    private void seInformation(Operation operation, CodeFeatureDetector cresolver, JsonObject opContext, Diff diff, CtElement affectedelement
            , ReturnTypePainter painter) {

        Cntx bugContext = new Cntx<>();

        CtElement affectedCtElement = null;

        if (operation instanceof MoveOperation || operation instanceof DeleteOperation || operation instanceof UpdateOperation) {

            CtElement affected = operation.getSrcNode();
            affectedCtElement = affected;
        } else if (operation instanceof InsertOperation){
            CtElement oldLocation = ((InsertOperation) operation).getParent();
            affectedCtElement = oldLocation;
        } 

        if (affectedCtElement != null) {
            Cntx iContext = cresolver.analyzeFeatures(affectedelement, painter.getAllExpressions(),
                    painter.getRootLogicalExpressions(), painter.getAllBinaryOperators());
            opContext.add("cntx", iContext.toJSON());
        }
        
    }

    CodeFeatureDetector cresolver = new CodeFeatureDetector();

    /**
     *
     * @param diff
     * @param operations
     * @param patternsPerOp
     * @param repairactionPerOp
     * @param patternInstances
     * @return
     */
    public JsonArray calculateJSONAffectedStatementList(Diff diff, List<Operation> operations,
            MapList<Operation, String> patternsPerOp, MapList<Operation, String> repairactionPerOp,
            List<PatternInstance> patternInstancesOriginal) {

        Json4SpoonGenerator jsongen = new Json4SpoonGenerator();

        JsonArray ast_affected = new JsonArray();

        List<PatternInstance> patternInstancesMerged = merge(patternInstancesOriginal);

        for (PatternInstance patternInstance : patternInstancesMerged) {
            Set<ITree> allTreeparents = new HashSet<>();
            Operation opi = patternInstance.getOp();

            List<CtElement> faulties = null;

            CtElement getAffectedCtElement = patternInstance.getFaultyLine();
            
            if(whetherDiscardElement(getAffectedCtElement))
                   continue;
            
            ITree faultyTree = patternInstance.getFaultyTree();
            if (faultyTree != null) {

                faultyTree = MappingAnalysis.getFormatedTreeFromControlFlow(faultyTree,
                        (CtElement) faultyTree.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT));

                allTreeparents.add(faultyTree);
            } else {

                if (getAffectedCtElement != null) {
                    faulties = new ArrayList<>();
                    faulties.add(getAffectedCtElement);
                } else {
                    if (patternInstance.getFaulty() != null)
                        faulties = patternInstance.getFaulty();
                    else {

                    }
                }

                for (CtElement faulty : faulties) {
                    ITree nodeFaulty = (ITree) faulty.getMetadata("gtnode");

                    if (nodeFaulty != null) {

                        ITree transformedTree = MappingAnalysis.getFormatedTreeFromControlFlow(nodeFaulty,
                                (CtElement) faulty.getMetadata(SpoonGumTreeBuilder.SPOON_OBJECT));

                        allTreeparents.add(transformedTree);
                    } else {
                    }
                }
            }

            List<NodePainter> painters = new ArrayList();
            painters.add(new FaultyElementPatternPainter(patternInstancesOriginal));
            
            ReturnTypePainter painterforreturn = new ReturnTypePainter(getAffectedCtElement);
            painters.add(painterforreturn);

            JsonObject jsonInstance = new JsonObject();
            JsonArray affected = new JsonArray();
            for (ITree iTree : allTreeparents) {
                JsonObject jsonT = jsongen.getJSONwithCustorLabels(((DiffImpl) diff).getContext(), iTree, painters);
                affected.add(jsonT);
            }
            
            jsonInstance.add("faulty_ast", affected);

            ast_affected.add(jsonInstance);

            JsonObject opContext = getContextInformation(diff, cresolver, opi, getAffectedCtElement, painterforreturn);

            jsonInstance.add("context", opContext);
        }

        return ast_affected;
    }
    
    private boolean whetherDiscardElement(CtElement orginalelement) {
        
        CtElement tostudy = retrieveElementToStudy(orginalelement);
        
        List<CtBinaryOperator> allbinaporators = tostudy.getElements(e -> (e instanceof CtBinaryOperator)).stream()
                .map(CtBinaryOperator.class::cast).collect(Collectors.toList());
        
        if(allbinaporators.size()>=3) {
            
            for (CtBinaryOperator anbinaryoperator: allbinaporators) {
                
                int numberString=0;
                
                CtElement parent = anbinaryoperator;
                
                do {
                    numberString+=getNumberOfStringInBinary((CtBinaryOperator)parent);
                    parent=parent.getParent();
                    
                } while (parent instanceof CtBinaryOperator &&
                        ((CtBinaryOperator) parent).getKind().equals(BinaryOperatorKind.PLUS));
                
                if(numberString>=4)
                    return true;
            }
        }
        
        return false;
    }
    
    private int getNumberOfStringInBinary (CtBinaryOperator binarytostudy) {
        
        int stringnumber=0;
        
        if(binarytostudy.getKind().equals(BinaryOperatorKind.PLUS)) {
            
           if(binarytostudy.getLeftHandOperand() instanceof CtLiteral && 
                ((CtLiteral) binarytostudy.getLeftHandOperand()).toString().trim().startsWith("\""))
             stringnumber++;
        
           if(binarytostudy.getRightHandOperand() instanceof CtLiteral && 
                ((CtLiteral) binarytostudy.getRightHandOperand()).toString().trim().startsWith("\""))
            stringnumber++;
           
        }
        
        return stringnumber;
    }
    
//    private boolean wheterBinaryofString (CtBinaryOperator binarytostudy) {
//        
//        if(binarytostudy.getKind().equals(BinaryOperatorKind.PLUS)) {
//            
//            if(binarytostudy.getLeftHandOperand().toString().trim().startsWith("\"") 
//                    || ((binarytostudy.getLeftHandOperand() instanceof CtBinaryOperator) && 
//                ((CtBinaryOperator) binarytostudy.getLeftHandOperand()).getKind().equals(BinaryOperatorKind.PLUS))) {
//                
//                if(binarytostudy.getRightHandOperand().toString().trim().startsWith("\"") 
//                        || ((binarytostudy.getRightHandOperand() instanceof CtBinaryOperator) && 
//                    ((CtBinaryOperator) binarytostudy.getRightHandOperand()).getKind().equals(BinaryOperatorKind.PLUS)))
//                    
//                    return true;
//            }
//        }
//        
//        return false;
//    }
    
    private CtElement retrieveElementToStudy(CtElement element) {

        if (element instanceof CtIf) {
            return (((CtIf) element).getCondition());
        } else if (element instanceof CtWhile) {
            return (((CtWhile) element).getLoopingExpression());
        } else if (element instanceof CtFor) {
            return (((CtFor) element).getExpression());
        } else if (element instanceof CtDo) {
            return (((CtDo) element).getLoopingExpression());
        } else if (element instanceof CtForEach) {
            return (((CtForEach) element).getExpression());
        } else if (element instanceof CtSwitch) {
            return (((CtSwitch) element).getSelector());
        } else
            return (element);
    }

    public JsonObject getContextInformation(Diff diff, CodeFeatureDetector cresolver, Operation opi,
            CtElement getAffectedCtElement, ReturnTypePainter painter) {

        JsonObject opContext = new JsonObject();

        seInformation(opi, cresolver, opContext, diff, getAffectedCtElement, painter);

        return opContext;
    }

    private List<PatternInstance> merge(List<PatternInstance> patternInstancesOriginal) {
        
        List<PatternInstance> patternInstancesMerged = new ArrayList<>();
        Map<CtElement, PatternInstance> cacheFaultyLines = new HashMap<>();

        for (PatternInstance patternInstance : patternInstancesOriginal) {
            if (!cacheFaultyLines.containsKey(patternInstance.getFaultyLine())) {
                cacheFaultyLines.put(patternInstance.getFaultyLine(), patternInstance);
                patternInstancesMerged.add(patternInstance);
            }
        }
        return patternInstancesMerged;
    }
}
