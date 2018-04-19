package fr.inria.spirals.features.detector.spoon;

import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.compiler.VirtualFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by fermadeiral
 */
public class SpoonHelper {

    public static Launcher initSpoon(Map<String, List<String>> files) {
        Launcher spoon = new Launcher();
        spoon.getEnvironment().setNoClasspath(true);
        spoon.getEnvironment().setAutoImports(true);
        spoon.getEnvironment().setCommentEnabled(false);
        for (String path : files.keySet()) {
            VirtualFile virtualFile = new VirtualFile(String.join("\n", files.get(path)), new File(path).getName());
            spoon.getModelBuilder().addInputSource(virtualFile);
        }
        spoon.buildModel();
        return spoon;
    }

    public static Diff getAstDiff(Launcher oldSpoon, Launcher newSpoon) {
        AstComparator diff = new AstComparator();
        return diff.compare(oldSpoon.getFactory().getModel().getRootPackage(), newSpoon.getFactory().getModel().getRootPackage());
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
        System.out.println(operation.getClass().getSimpleName());
        System.out.println(print.getResult()+"\n");
    }

    public static void printUpdateOperation(CtElement srcNode, CtElement dstNode, UpdateOperation operation) {
        System.out.println(UpdateOperation.class.getSimpleName());
        System.out.println(srcNode);
        System.out.println("to");
        if (dstNode != null) {
            System.out.println(dstNode+"\n");
        } else {
            System.out.println(operation.getAction().getValue()+"\n");
        }
    }

}
