package fr.inria.spirals.main;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.stringparsers.EnumeratedStringParser;
import com.martiansoftware.jsap.stringparsers.FileStringParser;
import fi.iki.elonen.NanoHTTPD;
import fr.inria.spirals.entities.FeatureList;
import fr.inria.spirals.features.FeatureAnalyzer;
import fr.inria.spirals.features.detector.repairactions.RepairActionDetector;
import fr.inria.spirals.features.detector.repairpatterns.RepairPatternDetector;
import fr.inria.spirals.features.extractor.MetricExtractor;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tdurieux
 */
public class Server extends NanoHTTPD {

    public Server() throws IOException {
        super(9888);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:9888/ \n");
    }
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (session.getMethod() == Method.POST) {


            try {
                Map<String, String> parms = session.getParms();
                session.parseBody(parms);
                JSONObject data = new JSONObject(parms.get("postData"));

                Config config = new Config();
                config.setLauncherMode(LauncherMode.ALL);
                config.setBugId(data.getString("bugId"));
                config.setBuggySourceDirectoryPath(data.getString("buggySourceDirectory"));
                config.setDiffPath(data.getString("diffPath"));

                FeatureList features = new FeatureList(config);
                List<FeatureAnalyzer> featureAnalyzers = new ArrayList<>();
                featureAnalyzers.add(new RepairPatternDetector(config));
                featureAnalyzers.add(new RepairActionDetector(config));
                featureAnalyzers.add(new MetricExtractor(config));

                for (FeatureAnalyzer featureAnalyzer : featureAnalyzers) {
                    features.add(featureAnalyzer.analyze());
                }
                return newFixedLengthResponse(features.toJson().toString(4));
            } catch (Exception e) {
                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text", e.getMessage());
            }
        } else {
            Response response = newFixedLengthResponse("Not supported");
            response.setStatus(Response.Status.FORBIDDEN);
            return response;
        }
    }
}
