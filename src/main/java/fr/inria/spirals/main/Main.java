package fr.inria.spirals.main;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import fr.inria.spirals.features.Extractor;
import fr.inria.spirals.features.ExtractorResults;

import java.util.Iterator;

/**
 * The main class of the project
 */
public class Main {

	private static final String OUTPUT_DIRECTORY = "outputDirectory";
	private static final String OLD_SOURCE_DIRECTORY = "oldSourceDirectory";
	private static final String NEW_SOURCE_DIRECTORY = "newSourceDirectory";
	private static final String DIFF_PATCH = "diffPath";
	private static final String PROJECT = "project";
	private static final String BUG_ID = "bugID";


	private static JSAP jsap = new JSAP();

	public static void main(String[] args) {
		try {
			initJSAP();
			JSAPResult arguments = parseArguments(args);
			if (arguments == null) {
				return;
			}

			Extractor extractor = new Extractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(NEW_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));

			ExtractorResults extractorResults = extractor.extract();
			extractorResults.setProject(arguments.getString(PROJECT));
			extractorResults.setBugId(arguments.getString(BUG_ID));
			System.out.println(extractorResults.toCSV());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}


	private static void showUsage() {
		System.err.println();
		System.err.println("Usage: java -jar patchclustering.jar");
		System.err.println("                          " + jsap.getUsage());
		System.err.println();
		System.err.println(jsap.getHelp());
	}

	private static JSAPResult parseArguments(String[] args) {
		JSAPResult config = jsap.parse(args);
		if (!config.success()) {
			System.err.println();
			for (Iterator<?> errs = config.getErrorMessageIterator(); errs
					.hasNext(); ) {
				System.err.println("Error: " + errs.next());
			}
			showUsage();
			return null;
		}

		return config;
	}

	private static void initJSAP() throws JSAPException {
		FlaggedOption projectOpt = new FlaggedOption(PROJECT);
		projectOpt.setRequired(true);
		projectOpt.setAllowMultipleDeclarations(false);
		projectOpt.setLongFlag("project");
		projectOpt.setShortFlag('p');
		projectOpt.setUsageName("math,lang,time,chart,closure");
		projectOpt.setStringParser(JSAP.STRING_PARSER);
		projectOpt.setHelp("The project name");
		jsap.registerParameter(projectOpt);

		FlaggedOption modeOpt = new FlaggedOption(BUG_ID);
		modeOpt.setRequired(true);
		modeOpt.setAllowMultipleDeclarations(false);
		modeOpt.setLongFlag("id");
		modeOpt.setShortFlag('i');
		modeOpt.setUsageName("");
		modeOpt.setDefault("normal");
		modeOpt.setStringParser(JSAP.STRING_PARSER);
		modeOpt.setHelp("The bug id of the defects4j dataset");
		jsap.registerParameter(modeOpt);

		FlaggedOption outputDirectoryOpt = new FlaggedOption(OUTPUT_DIRECTORY);
		outputDirectoryOpt.setRequired(false);
		outputDirectoryOpt.setAllowMultipleDeclarations(false);
		outputDirectoryOpt.setLongFlag("output");
		outputDirectoryOpt.setShortFlag('o');
		outputDirectoryOpt.setUsageName("");
		outputDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		outputDirectoryOpt.setHelp("The path to the evaluation output directory.");
		//jsap.registerParameter(outputDirectoryOpt);

		FlaggedOption sourceDirectoryOpt = new FlaggedOption(OLD_SOURCE_DIRECTORY);
		sourceDirectoryOpt.setRequired(true);
		sourceDirectoryOpt.setAllowMultipleDeclarations(false);
		sourceDirectoryOpt.setLongFlag("oldSource");
		sourceDirectoryOpt.setShortFlag('s');
		sourceDirectoryOpt.setUsageName("");
		sourceDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		sourceDirectoryOpt.setHelp("The path to the old source directory of the project.");
		jsap.registerParameter(sourceDirectoryOpt);


		FlaggedOption newSourceDirectoryOpt = new FlaggedOption(NEW_SOURCE_DIRECTORY);
		newSourceDirectoryOpt.setRequired(true);
		newSourceDirectoryOpt.setAllowMultipleDeclarations(false);
		newSourceDirectoryOpt.setLongFlag("newSource");
		newSourceDirectoryOpt.setShortFlag('x');
		newSourceDirectoryOpt.setUsageName("");
		newSourceDirectoryOpt.setStringParser(JSAP.STRING_PARSER);
		newSourceDirectoryOpt.setHelp("The path to the new source directory of the project.");
		jsap.registerParameter(newSourceDirectoryOpt);

		FlaggedOption diffPathOpt = new FlaggedOption(DIFF_PATCH);
		diffPathOpt.setRequired(true);
		diffPathOpt.setAllowMultipleDeclarations(true);
		diffPathOpt.setLongFlag("diff");
		diffPathOpt.setShortFlag('d');
		diffPathOpt.setUsageName("");
		diffPathOpt.setStringParser(JSAP.STRING_PARSER);
		diffPathOpt.setHelp("The path to the diff.");
		jsap.registerParameter(diffPathOpt);
	}
}