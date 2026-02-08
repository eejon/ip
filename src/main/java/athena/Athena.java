package athena;

import java.io.IOException;

import athena.commands.Command;
import athena.exceptions.AthenaException;
import athena.parser.Parser;
import athena.storage.TaskStorage;
import athena.tasks.TaskManager;
import athena.ui.Gui;
import athena.ui.Ui;
/**
 * The main entry point of the Athena chatbot application.
 * Initializes the application components, including UI, and
 * TaskManager. Starts the main command processing loop.
 */
public class Athena {
    private TaskManager taskList;
    private Ui ui;
    private TaskStorage storage;

    /**
     * Constructs a new Athena Chatbot object
     * Initializes UI and TaskManager per Athena Chatbot Object
     */
    public Athena() {
        this.ui = new Ui(); // Load UI
        this.storage = new TaskStorage();
        this.taskList = new TaskManager(storage); // Load TaskManager
    }

    /**
     * Executes the main logic for Athena chatbot.
     */
    public void run() {
        ui.printGreeting();

        String result = initialize();
        if (result != null) {
            ui.showError(result);
        }

        while (true) {
            try {
                String input = ui.readInput();

                Command command = Parser.parse(input);
                int statusCode = command.dispatch(this.taskList, this.ui);
                assert statusCode == 0 || statusCode == 1 : "status code must be 0 (success) or 1 (exit)";
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

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Gui gui = new Gui();
            Command command = Parser.parse(input);
            int statusCode = command.dispatch(this.taskList, gui);
            if (statusCode == 1) {
                // Handle end program
            }
            return gui.getResponse();

        } catch (IndexOutOfBoundsException e) {

            return AthenaException.indexOutOfBoundsError();

        } catch (NumberFormatException e) {

            return AthenaException.nanError(e.getMessage());

        } catch (AthenaException e) {

            return e.getMessage();

        }
    }

    /**
     * Returns the greeting message specifically for the GUI
     */
    public String getGreeting() {
        Gui gui = new Gui();
        gui.printGreeting();
        return gui.getResponse();
    }

    /**
     * Loads the task list from local storage
     */
    public String initialize() {
        // Load tasks once at startup
        try {
            this.taskList.loadTasks();
            return null;
        } catch (IOException e) {
            return "Error loading tasks: " + e.getMessage();
        }
    }
}
