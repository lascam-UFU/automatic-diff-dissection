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

	private static final String OUTPUT_DIRECTORY = "outputDirectory";
	private static final String OLD_SOURCE_DIRECTORY = "oldSourceDirectory";
	private static final String NEW_SOURCE_DIRECTORY = "newSourceDirectory";
	private static final String DIFF_PATCH = "diffPath";
	private static final String PROJECT = "project";
	private static final String BUG_ID = "bugID";
	private static final String MODE = "MODE";


	private static JSAP jsap = new JSAP();

	public static void main(String[] args) throws Exception {
		initJSAP();
		JSAPResult arguments = parseArguments(args);
		if (arguments == null) {
			return;
		}
		String mode = arguments.getString(MODE);
		switch (mode.toLowerCase()) {
		case "diff":
			DiffExtractor extractor = new DiffExtractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(NEW_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));
			extractor.setProject(arguments.getString(PROJECT));
			extractor.setBugId(arguments.getString(BUG_ID));

			ExtractorResults extractorResults = extractor.extract();
			if (extractorResults != null) {
				extractorResults.setProject(arguments.getString(PROJECT));
				extractorResults.setBugId(arguments.getString(BUG_ID));
				System.out.println(extractorResults.toCSV());
			}
			break;
		case "ast":
			AstExtractor astExtractor = new AstExtractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));
			astExtractor.setProject(arguments.getString(PROJECT));
			astExtractor.setBugId(arguments.getString(BUG_ID));

			astExtractor.extract();
			break;
		case "limit":
			PositionExtractor limitExtractor = new PositionExtractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(NEW_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));
			limitExtractor.setProject(arguments.getString(PROJECT));
			limitExtractor.setBugId(arguments.getString(BUG_ID));

			limitExtractor.getLimitOfPatch();
			break;
		case "spreading":
			PositionExtractor spreadingExtractor = new PositionExtractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(NEW_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));
			spreadingExtractor.setProject(arguments.getString(PROJECT));
			spreadingExtractor.setBugId(arguments.getString(BUG_ID));

			spreadingExtractor.spreading2();
			break;
		case "position":
			PositionExtractor positionExtractor = new PositionExtractor(
					arguments.getString(OLD_SOURCE_DIRECTORY),
					arguments.getString(NEW_SOURCE_DIRECTORY),
					arguments.getString(DIFF_PATCH));
			positionExtractor.setProject(arguments.getString(PROJECT));
			positionExtractor.setBugId(arguments.getString(BUG_ID));

			positionExtractor.countAddRemoveModify();
			break;
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

		FlaggedOption bugIdOpt = new FlaggedOption(BUG_ID);
		bugIdOpt.setRequired(true);
		bugIdOpt.setAllowMultipleDeclarations(false);
		bugIdOpt.setLongFlag("id");
		bugIdOpt.setShortFlag('i');
		bugIdOpt.setUsageName("");
		bugIdOpt.setDefault("normal");
		bugIdOpt.setStringParser(JSAP.STRING_PARSER);
		bugIdOpt.setHelp("The bug id of the defects4j dataset");
		jsap.registerParameter(bugIdOpt);

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


		FlaggedOption modeOpt = new FlaggedOption(MODE);
		modeOpt.setRequired(true);
		modeOpt.setAllowMultipleDeclarations(false);
		modeOpt.setLongFlag("mode");
		modeOpt.setShortFlag('m');
		modeOpt.setUsageName("");
		modeOpt.setStringParser(JSAP.STRING_PARSER);
		modeOpt.setHelp("The extraction mode");
		jsap.registerParameter(modeOpt);
	}
}