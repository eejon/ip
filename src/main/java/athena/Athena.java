package athena;

import java.io.IOException;

import athena.commands.Command;
import athena.exceptions.AthenaException;
import athena.parser.Parser;
import athena.tasks.TaskManager;
import athena.ui.UI;
/**
 * The main entry point of the Athena chatbot application.
 * Initializes the application components, including UI, and
 * TaskManager. Starts the main command processing loop.
 */
public class Athena {
    private TaskManager taskList;
    private UI ui;

    /**
     * Constructs a new Athena Chatbot object
     * Initializes UI and TaskManager per Athena Chatbot Object
     */
    public Athena() {
        this.ui = new UI(); // Load UI
        this.taskList = new TaskManager(); // Load TaskManager
    }

    /**
     * Execution of the main logic for Athena chatbot. 
     */
    public void run() {
        ui.printGreeting();
        while (true) {
            try {
                String input = ui.readInput();

                Command command = Parser.parse(input);
                int statusCode = command.dispatch(this.taskList, this.ui);

                if (statusCode == 1) {
                    break;
                }

            } catch (IOException e) {

                this.ui.showError(e.getMessage());

            } catch (IndexOutOfBoundsException e) {

                this.ui.showIndexOutOfBoundsError();

            } catch (NumberFormatException e) {

                ui.showNanError(e.getMessage());

            } catch (AthenaException e) {

                ui.showError(e.getMessage());

            } finally {

                ui.printLine();

            }
        }
    }

    /**
     * Program entry point
     */
    public static void main(String[] args) {
        Athena chatbot = new Athena();
        chatbot.run();
    }
}
