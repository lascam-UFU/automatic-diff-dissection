package add.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import add.entities.FeatureList;
import add.features.FeatureAnalyzer;
import add.features.detector.repairactions.RepairActionDetector;
import add.features.detector.repairpatterns.RepairPatternDetector;
import add.features.extractor.MetricExtractor;
import fi.iki.elonen.NanoHTTPD;

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
            ioe.printStackTrace();
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

                RepairPatternDetector detector = new RepairPatternDetector(config);
                featureAnalyzers.add(detector);
                featureAnalyzers.add(new RepairActionDetector(config, detector.getEditScript()));
                featureAnalyzers.add(new MetricExtractor(config));

                for (FeatureAnalyzer featureAnalyzer : featureAnalyzers) {
                    features.add(featureAnalyzer.analyze());
                }
                Response response = newFixedLengthResponse(Response.Status.OK, "application/json", features.toJson().toString(4));
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Headers", "*");
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, MIME_PLAINTEXT, e.getMessage());
            }
        } else if (session.getMethod() == Method.OPTIONS) {
            Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Max-Age", "3628800");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
            response.addHeader("Access-Control-Allow-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "content-type");
            return response;
        } else {
            Response response = newFixedLengthResponse("Not supported");
            response.setStatus(Response.Status.FORBIDDEN);
            return response;
        }
    }
}
