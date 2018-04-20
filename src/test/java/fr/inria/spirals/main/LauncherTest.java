package fr.inria.spirals.main;

import com.martiansoftware.jsap.JSAPException;
import fr.inria.spirals.utils.TestUtils;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class LauncherTest {

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("Chart 1");

        String[] args = new String[] {"-m", LauncherMode.ALL.name(),
                "-b", config.getBugId(),
                "--buggySourceDirectory", config.getBuggySourceDirectoryPath(),
                "--fixedSourceDirectory", config.getFixedSourceDirectoryPath(),
                "--diff", config.getDiffPath(),
                "-o", System.getProperty("user.dir")
        };

        Launcher launcher;
        try {
            launcher = new Launcher(args);
            launcher.execute();
        } catch (JSAPException e) {
            e.printStackTrace();
        }
    }

}
