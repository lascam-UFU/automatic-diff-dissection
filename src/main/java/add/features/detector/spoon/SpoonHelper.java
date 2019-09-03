package add.features.detector.spoon;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import add.main.Constants;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.compiler.VirtualFile;

/**
 * Created by fermadeiral
 */
public class SpoonHelper {
    private static Logger LOGGER = LoggerFactory.getLogger(SpoonHelper.class);

    public static Launcher initSpoon(Map<String, List<String>> files) {
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setNoClasspath(true);
        spoon.getEnvironment().setAutoImports(false);
        spoon.getEnvironment().setCommentEnabled(false);
        for (String path : files.keySet()) {
            String fileContent = String.join(Constants.LINE_BREAK, files.get(path))
                    .replace("import javax.annotation.Nullable", "// import javax.annotation.Nullable")
                    .replace("import javax.annotation.CheckForNull", "// import javax.annotation.CheckForNull");
            VirtualFile virtualFile = new VirtualFile(fileContent, new File(path).getName());
            spoon.getModelBuilder().addInputSource(virtualFile);
        }
        spoon.buildModel();

        return spoon;
    }

    public static Diff getAstDiff(Launcher oldSpoon, Launcher newSpoon) {
        AstComparator diff = new AstComparator();
        return diff.compare(oldSpoon.getFactory().getModel().getRootPackage(),
                newSpoon.getFactory().getModel().getRootPackage());
    }

    public static void printInsertOrDeleteOperation(Environment env, CtElement element, Operation operation) {
        DefaultJavaPrettyPrinter print = new DefaultJavaPrettyPrinter(env) {
            @Override
            public DefaultJavaPrettyPrinter scan(CtElement e) {
                if (e != null && e.getMetadata("isMoved") == null) {
                    return super.scan(e);
                }
                return this;
            }
        };
        print.scan(element);
        // LOGGER.debug(operation.getClass().getSimpleName());
        // LOGGER.debug(print.getResult()+Constants.LINE_BREAK);
    }

    public static void printUpdateOperation(CtElement srcNode, CtElement dstNode, UpdateOperation operation) {
        // LOGGER.debug(UpdateOperation.class.getSimpleName());
        // LOGGER.debug(srcNode.toString());
        // LOGGER.debug("to");
        if (dstNode != null) {
            // LOGGER.debug(dstNode+Constants.LINE_BREAK);
        } else {
            // LOGGER.debug(operation.getAction().getValue()+Constants.LINE_BREAK);
        }
    }

}
