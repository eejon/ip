package athena;

import athena.gui.AthenaGui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String... args) {
        if (args.length > 0 && args[0].equals("--cli")) {
            // Option to run in CLI mode
            Athena chatbot = new Athena();
            chatbot.run();
        } else {
            Application.launch(AthenaGui.class, args);
        }
    }
}

