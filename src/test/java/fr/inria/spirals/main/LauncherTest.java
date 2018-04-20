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
        TestUtils.setupConfig("Chart 1");

        String[] args = new String[] {"-m", LauncherMode.ALL.name(),
                "-b", Config.getInstance().getBugId(),
                "--buggySourceDirectory", Config.getInstance().getBuggySourceDirectoryPath(),
                "--fixedSourceDirectory", Config.getInstance().getFixedSourceDirectoryPath(),
                "--diff", Config.getInstance().getDiffPath(),
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
