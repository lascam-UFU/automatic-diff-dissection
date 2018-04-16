package fr.inria.spirals.main;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import fr.inria.spirals.features.ExtractorResults;
import fr.inria.spirals.features.extractor.AstExtractor;
import fr.inria.spirals.features.extractor.DiffExtractor;
import fr.inria.spirals.features.extractor.PositionExtractor;

import java.util.Iterator;

/**
 * Created by tdurieux
 */
public class Main {

	private Config config;

	public Main(String[] args) throws JSAPException {
		JSAP jsap = this.initJSAP();
		JSAPResult arguments = this.parseArguments(args, jsap);
		if (arguments == null) {
			return;
		}
		this.initConfig(arguments);
	}

	private void showUsage(JSAP jsap) {
		System.err.println();
		System.err.println("Usage: java -jar patchclustering.jar");
		System.err.println("                          " + jsap.getUsage());
		System.err.println();
		System.err.println(jsap.getHelp());
	}

	private JSAPResult parseArguments(String[] args, JSAP jsap) {
		JSAPResult config = jsap.parse(args);
		if (!config.success()) {
			System.err.println();
			for (Iterator<?> errs = config.getErrorMessageIterator(); errs.hasNext();) {
				System.err.println("Error: " + errs.next());
			}
			this.showUsage(jsap);
			return null;
		}
		return config;
	}

	private JSAP initJSAP() throws JSAPException {
		JSAP jsap = new JSAP();

		FlaggedOption projectOpt = new FlaggedOption("project");
		projectOpt.setRequired(true);
		projectOpt.setAllowMultipleDeclarations(false);
		projectOpt.setLongFlag("project");
		projectOpt.setShortFlag('p');
		projectOpt.setUsageName("math,lang,time,chart,closure");
		projectOpt.setStringParser(JSAP.STRING_PARSER);
		projectOpt.setHelp("The project name");
		jsap.registerParameter(projectOpt);

		FlaggedOption bugIdOpt = new FlaggedOption("bugId");
		bugIdOpt.setRequired(true);
		bugIdOpt.setAllowMultipleDeclarations(false);
		bugIdOpt.setLongFlag("id");
		bugIdOpt.setShortFlag('i');
		bugIdOpt.setUsageName("");
		bugIdOpt.setDefault("normal");
		bugIdOpt.setStringParser(JSAP.STRING_PARSER);
		bugIdOpt.setHelp("The bug id of the defects4j dataset");
		jsap.registerParameter(bugIdOpt);

		FlaggedOption outputDirectoryOpt = new FlaggedOption("outputDirectory");
		outputDirectoryOpt.setRequired(false);
		outputDirectoryOpt.setAllowMultipleDeclarations(false);
		outputDirectoryOpt.setLongFlag("output");
		outputDirectoryOpt.setShortFlag('o');
		outputDirectoryOpt.setUsageName("");
		outputDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		outputDirectoryOpt.setHelp("The path to the evaluation output directory.");
		//jsap.registerParameter(outputDirectoryOpt);

		FlaggedOption sourceDirectoryOpt = new FlaggedOption("buggySourceDirectory");
		sourceDirectoryOpt.setRequired(true);
		sourceDirectoryOpt.setAllowMultipleDeclarations(false);
		sourceDirectoryOpt.setLongFlag("oldSource");
		sourceDirectoryOpt.setShortFlag('s');
		sourceDirectoryOpt.setUsageName("");
		sourceDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		sourceDirectoryOpt.setHelp("The path to the old source directory of the project.");
		jsap.registerParameter(sourceDirectoryOpt);

		FlaggedOption newSourceDirectoryOpt = new FlaggedOption("fixedSourceDirectory");
		newSourceDirectoryOpt.setRequired(true);
		newSourceDirectoryOpt.setAllowMultipleDeclarations(false);
		newSourceDirectoryOpt.setLongFlag("newSource");
		newSourceDirectoryOpt.setShortFlag('x');
		newSourceDirectoryOpt.setUsageName("");
		newSourceDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		newSourceDirectoryOpt.setHelp("The path to the new source directory of the project.");
		jsap.registerParameter(newSourceDirectoryOpt);

		FlaggedOption diffPathOpt = new FlaggedOption("diffPath");
		diffPathOpt.setRequired(true);
		diffPathOpt.setAllowMultipleDeclarations(true);
		diffPathOpt.setLongFlag("diff");
		diffPathOpt.setShortFlag('d');
		diffPathOpt.setUsageName("");
		diffPathOpt.setStringParser(JSAP.STRING_PARSER);
		diffPathOpt.setHelp("The path to the diff.");
		jsap.registerParameter(diffPathOpt);

		FlaggedOption modeOpt = new FlaggedOption("launcherMode");
		modeOpt.setRequired(true);
		modeOpt.setAllowMultipleDeclarations(false);
		modeOpt.setLongFlag("mode");
		modeOpt.setShortFlag('m');
		modeOpt.setUsageName("");
		modeOpt.setStringParser(JSAP.STRING_PARSER);
		modeOpt.setHelp("The extraction mode");
		jsap.registerParameter(modeOpt);

		return jsap;
	}

	private void initConfig(JSAPResult arguments) {
		this.config = Config.getInstance();
		this.config.setProject(arguments.getString("project"));
		this.config.setBugId(arguments.getString("bugId"));
		this.config.setOutputDirectoryPath(arguments.getString("outputDirectory"));
		this.config.setBuggySourceDirectoryPath(arguments.getString("buggySourceDirectory"));
		this.config.setFixedSourceDirectoryPath(arguments.getString("fixedSourceDirectory"));
		this.config.setDiffPath(arguments.getString("diffPath"));
		this.config.setLauncherMode(arguments.getString("launcherMode"));
	}

	private void execute() {
		switch (this.config.getLauncherMode().toLowerCase()) {
			case "diff":
				DiffExtractor extractor = new DiffExtractor(
						this.config.getBuggySourceDirectoryPath(),
						this.config.getFixedSourceDirectoryPath(),
						this.config.getDiffPath());
				extractor.setProject(this.config.getProject());
				extractor.setBugId(this.config.getBugId());

				ExtractorResults extractorResults = extractor.extract();
				if (extractorResults != null) {
					extractorResults.setProject(this.config.getProject());
					extractorResults.setBugId(this.config.getBugId());
					System.out.println(extractorResults.toCSV());
				}
				break;
			case "ast":
				AstExtractor astExtractor = new AstExtractor(
						this.config.getBuggySourceDirectoryPath(),
						this.config.getDiffPath());
				astExtractor.setProject(this.config.getProject());
				astExtractor.setBugId(this.config.getBugId());

				astExtractor.extract();
				break;
			case "limit":
				PositionExtractor limitExtractor = new PositionExtractor(
						this.config.getBuggySourceDirectoryPath(),
						this.config.getFixedSourceDirectoryPath(),
						this.config.getDiffPath());
				limitExtractor.setProject(this.config.getProject());
				limitExtractor.setBugId(this.config.getBugId());

				limitExtractor.getLimitOfPatch();
				break;
			case "spreading":
				PositionExtractor spreadingExtractor = new PositionExtractor(
						this.config.getBuggySourceDirectoryPath(),
						this.config.getFixedSourceDirectoryPath(),
						this.config.getDiffPath());
				spreadingExtractor.setProject(this.config.getProject());
				spreadingExtractor.setBugId(this.config.getBugId());

				spreadingExtractor.spreading2();
				break;
			case "position":
				PositionExtractor positionExtractor = new PositionExtractor(
						this.config.getBuggySourceDirectoryPath(),
						this.config.getFixedSourceDirectoryPath(),
						this.config.getDiffPath());
				positionExtractor.setProject(this.config.getProject());
				positionExtractor.setBugId(this.config.getBugId());

				positionExtractor.countAddRemoveModify();
				break;
		}
	}

	public static void main(String[] args) throws Exception {
		Main m = new Main(args);
		m.execute();
	}

}
