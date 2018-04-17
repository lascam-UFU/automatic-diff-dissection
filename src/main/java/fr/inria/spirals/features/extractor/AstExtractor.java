package fr.inria.spirals.features.extractor;

import fr.inria.spirals.entities.RepairActions;
import fr.inria.spirals.features.analyzer.DiffAnalyzer;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.*;
import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.compiler.VirtualFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by tdurieux
 */
public class AstExtractor extends AbstractExtractor {

	public AstExtractor(String oldSourcePath, String diffPath) {
		super(oldSourcePath, diffPath);
	}

	public RepairActions extract() {
		DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);

		Map<String, List<String>> originalFiles = diffAnalyzer.getOriginalFiles(buggySourcePath);
		Map<String, List<String>> patchedFiles = diffAnalyzer.getPatchedFiles(buggySourcePath);

		Launcher oldSpoon = initSpoon(originalFiles);
		Launcher newSpoon = initSpoon(patchedFiles);

		return getAstDiff(oldSpoon, newSpoon);
	}



	private RepairActions getAstDiff(Launcher oldSpoon, Launcher newSpoon) {
		AstComparator diff = new AstComparator();

		Diff editScript = diff.compare(oldSpoon.getFactory().getModel().getRootPackage(), newSpoon.getFactory().getModel().getRootPackage());
		return getRepairActions(editScript);
	}

	private RepairActions getRepairActions(Diff editScript) {
		final RepairActions repairActions = new RepairActions();
		for (int i = 0; i < editScript.getAllOperations().size(); i++) {
			Operation operation = editScript.getAllOperations().get(i);
			if (operation instanceof MoveOperation) {
				operation.getSrcNode().putMetadata("isMoved", true);
				operation.getDstNode().putMetadata("isMoved", true);
			}
		}

		for (int i = 0; i < editScript.getRootOperations().size(); i++) {
			Operation operation = editScript.getRootOperations().get(i);
			CtElement srcNode = operation.getSrcNode();
			if (operation instanceof InsertOperation || operation instanceof DeleteOperation ) {
				System.out.println(operation.getClass().getSimpleName());
				System.out.println(printElement(srcNode.getFactory().getEnvironment(), srcNode));
			} else if(operation instanceof UpdateOperation) {
				CtElement dstNode = operation.getDstNode();
				System.out.println(operation.getClass().getSimpleName());
				System.out.println(srcNode);
				System.out.println("to");
				if (dstNode != null) {
					System.out.println(dstNode);
				} else {
					System.out.println(((UpdateOperation) operation).getAction().getValue());
				}
			}
		}

		return repairActions;
	}

	private String printElement(Environment env, CtElement element) {
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
		return print.getResult();
	}

	private Launcher initSpoon(Map<String, List<String>> files) {
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

}
