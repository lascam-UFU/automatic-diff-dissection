package add.main;

import add.utils.TestUtils;
import com.martiansoftware.jsap.JSAPException;
import org.junit.Test;

/**
 * Created by fermadeiral
 */
public class LauncherTest {

    @Test
    public void chart1() {
        Config config = TestUtils.setupConfig("chart_1");

        String[] args = new String[] {"-m", LauncherMode.ALL.name(),
                "-b", config.getBugId(),
                "--buggySourceDirectory", config.getBuggySourceDirectoryPath(),
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

    @Test
    public void closure114() {
        Config config = TestUtils.setupConfig("closure_114");

        String[] args = new String[] {"-m", LauncherMode.ALL.name(),
                "-b", config.getBugId(),
                "--buggySourceDirectory", config.getBuggySourceDirectoryPath(),
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

    @Test
    public void mockito21() {
        Config config = TestUtils.setupConfig("mockito_21");

        String[] args = new String[] {"-m", LauncherMode.ALL.name(),
                "-b", config.getBugId(),
                "--buggySourceDirectory", config.getBuggySourceDirectoryPath(),
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
