package fr.inria.spirals.features.extractor;

import fr.inria.spirals.features.DiffAnalyzer;
import gumtree.spoon.AstComparator;
import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.DeleteOperation;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.MoveOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.operations.UpdateOperation;
import spoon.Launcher;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.compiler.VirtualFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AstExtractor {

	public static final String CSV_SEPARATOR = "\t";

	private String oldSourcePath;
	private String diffPath;
	private String project;
	private String bugId;

	public AstExtractor(String oldSourcePath, String diffPath) {
		this.oldSourcePath = oldSourcePath;
		this.diffPath = diffPath;
	}

	public void extract() {
		DiffAnalyzer diffAnalyzer = new DiffAnalyzer(diffPath);

		Map<String, List<String>> originalFiles = diffAnalyzer.getOriginalFiles(oldSourcePath);
		Map<String, List<String>> patchedFiles = diffAnalyzer.getPatchedFiles(oldSourcePath);

		Launcher oldSpoon = initSpoon(originalFiles);
		Launcher newSpoon = initSpoon(patchedFiles);

		getAstDiff(oldSpoon, newSpoon);
	}



	private void getAstDiff(Launcher oldSpoon, Launcher newSpoon) {
		AstComparator diff = new AstComparator();

		Diff editScript = diff.compare(oldSpoon.getFactory().getModel().getRootPackage(), newSpoon.getFactory().getModel().getRootPackage());
		for (int i = 0; i < editScript.getRootOperations().size(); i++) {
			Operation operation = editScript.getRootOperations().get(i);
			if (operation instanceof MoveOperation) {
				operation.getDstNode().putMetadata("isMoved", true);
			}
		}

		for (int i = 0; i < editScript.getRootOperations().size(); i++) {
			Operation operation = editScript.getRootOperations().get(i);
			if (operation instanceof InsertOperation || operation instanceof DeleteOperation ) {
				CtElement dstNode = operation.getSrcNode();
				System.out.println(operation.getClass().getSimpleName());
				System.out.println(printElement(newSpoon, dstNode));
			} else if(operation instanceof UpdateOperation) {
				CtElement srcNode = operation.getSrcNode();
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

	}

	private String printElement(Launcher newSpoon, CtElement element) {
		DefaultJavaPrettyPrinter print = new DefaultJavaPrettyPrinter(newSpoon.getEnvironment()) {
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

	public void setProject(String project) {
		this.project = project;
	}

	public String getProject() {
		return project;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getBugId() {
		return bugId;
	}
}