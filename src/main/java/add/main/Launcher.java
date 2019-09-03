package add.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.stringparsers.EnumeratedStringParser;
import com.martiansoftware.jsap.stringparsers.FileStringParser;

import add.entities.FeatureList;
import add.features.FeatureAnalyzer;
import add.features.detector.repairactions.RepairActionDetector;
import add.features.detector.repairpatterns.RepairPatternDetector;
import add.features.extractor.MetricExtractor;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import gumtree.spoon.diff.Diff;

/**
 * Created by tdurieux
 */
public class Launcher {
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    private Config config;

    public Launcher(String[] args) throws JSAPException {
        JSAP jsap = this.initJSAP();
        JSAPResult arguments = this.parseArguments(args, jsap);
        if (arguments == null) {
            System.exit(-1);
        }
        this.initConfig(arguments);

        Logger logger = (Logger) LoggerFactory.getLogger("fr.inria");
        logger.setLevel(Level.DEBUG);
    }

    private void showUsage(JSAP jsap) {
        System.err.println();
        System.err.println("Usage: java -jar patchclustering.jar <arguments>");
        System.err.println();
        System.err.println("Arguments:");
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

        String launcherModeValues = "";
        for (LauncherMode mode : LauncherMode.values()) {
            launcherModeValues += mode.name()+";";
        }
        launcherModeValues = launcherModeValues.substring(0, launcherModeValues.length()-1);

        FlaggedOption opt = new FlaggedOption("launcherMode");
        opt.setShortFlag('m');
        opt.setLongFlag("launcherMode");
        opt.setRequired(true);
        opt.setAllowMultipleDeclarations(false);
        opt.setUsageName(launcherModeValues);
        opt.setStringParser(EnumeratedStringParser.getParser(launcherModeValues));
        opt.setHelp("Provide the launcher mode, which is the type of the features that will be extracted.");
        jsap.registerParameter(opt);

        opt = new FlaggedOption("bugId");
        opt.setShortFlag('b');
        opt.setLongFlag("bugId");
        opt.setRequired(true);
        opt.setAllowMultipleDeclarations(false);
        opt.setStringParser(JSAP.STRING_PARSER);
        opt.setHelp("Provide the bug id (this is used only for information presentation).");
        jsap.registerParameter(opt);

        opt = new FlaggedOption("buggySourceDirectory");
        opt.setLongFlag("buggySourceDirectory");
        opt.setRequired(true);
        opt.setAllowMultipleDeclarations(false);
        opt.setStringParser(JSAP.STRING_PARSER);
        opt.setHelp("Provide the path to the buggy source code directory of the bug.");
        jsap.registerParameter(opt);

        opt = new FlaggedOption("diffPath");
        opt.setLongFlag("diff");
        opt.setRequired(true);
        opt.setAllowMultipleDeclarations(true);
        opt.setStringParser(JSAP.STRING_PARSER);
        opt.setHelp("Provide the path to the diff file.");
        jsap.registerParameter(opt);

        opt = new FlaggedOption("outputDirectory");
        opt.setShortFlag('o');
        opt.setLongFlag("output");
        opt.setRequired(false);
        opt.setAllowMultipleDeclarations(false);
        opt.setStringParser(FileStringParser.getParser().setMustBeDirectory(true).setMustExist(true));
        opt.setHelp("Provide an existing path to output the extracted features as a JSON file (optional).");
        jsap.registerParameter(opt);

        return jsap;
    }

    private void initConfig(JSAPResult arguments) {
        this.config = new Config();
        this.config.setLauncherMode(LauncherMode.valueOf(arguments.getString("launcherMode").toUpperCase()));
        this.config.setBugId(arguments.getString("bugId"));
        this.config.setBuggySourceDirectoryPath(arguments.getString("buggySourceDirectory"));
        this.config.setDiffPath(arguments.getString("diffPath"));
        if (arguments.getFile("outputDirectory") != null) {
            this.config.setOutputDirectoryPath(arguments.getFile("outputDirectory").getAbsolutePath());
        }
    }

    protected void execute() {
        FeatureList features = new FeatureList(this.config);
        List<FeatureAnalyzer> featureAnalyzers = new ArrayList<>();

        Diff editScript = null;
        if (this.config.getLauncherMode() == LauncherMode.REPAIR_PATTERNS ||
                this.config.getLauncherMode() == LauncherMode.ALL) {
            RepairPatternDetector detector = new RepairPatternDetector(this.config);
            editScript = detector.getEditScript();
            featureAnalyzers.add(detector);
        }
        if (this.config.getLauncherMode() == LauncherMode.REPAIR_ACTIONS ||
                this.config.getLauncherMode() == LauncherMode.ALL) {
            featureAnalyzers.add(new RepairActionDetector(this.config, editScript));
        }
        if (this.config.getLauncherMode() == LauncherMode.METRICS ||
                this.config.getLauncherMode() == LauncherMode.ALL) {
            featureAnalyzers.add(new MetricExtractor(this.config));
        }

        for (FeatureAnalyzer featureAnalyzer : featureAnalyzers) {
            features.add(featureAnalyzer.analyze());
        }

        LOGGER.info(features.toCSV());

        if (this.config.getOutputDirectoryPath() != null) {
            JSONObject json = new JSONObject(features.toString());
            JSONOutputFileCreator.writeJSONfile(json.toString(4), this.config);
        }
    }

    public static void main(String[] args) throws Exception {
        Launcher launcher = new Launcher(args);
        launcher.execute();
    }

}
