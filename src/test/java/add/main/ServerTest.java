package add.main;

import add.utils.Constants;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class ServerTest {

    @Test
    public void testServerLauncher() throws IOException {
        Server server = new Server();
        URL url = new URL("http://localhost:" + server.getListeningPort());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        String path = Constants.class.getResource("/patches/math_5").getPath();
        String jsonInputString = "{'bugId': 'test', 'buggySourceDirectory': '" + path + "/buggy-version', 'diffPath': '" + path + "/path.diff'}";

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(Charset.defaultCharset());
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        JSONObject results = new JSONObject(content.toString());
        Assert.assertEquals(2, results.getJSONObject("repairActions").getInt("varReplVar"));
    }
}