package athena;

import java.io.IOException;

import athena.commands.Command;
import athena.exceptions.AthenaException;
import athena.parser.Parser;
import athena.storage.TaskStorage;
import athena.tasks.TaskManager;
import athena.ui.Gui;
import athena.ui.Response;
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
        try {
            initialize();
        } catch (AthenaException e) {
            ui.showError(e.getMessage());
        }

        while (true) {
            try {
                String input = ui.readInput();

                Command command = Parser.parse(input);
                int statusCode = command.dispatch(this.taskList, this.ui);

                if (statusCode == Command.STATUS_CODE_EXIT) {
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
    public Response getResponse(String input) {
        try {
            Gui gui = new Gui();
            Command command = Parser.parse(input);
            int statusCode = command.dispatch(this.taskList, gui);
            if (statusCode == Command.STATUS_CODE_EXIT) {
                // Handle end program
            }
            return gui.getResponse();

        } catch (IndexOutOfBoundsException e) {

            return Response.error(AthenaException.indexOutOfBoundsError());

        } catch (NumberFormatException e) {

            return Response.error(AthenaException.nanError(e.getMessage()));

        } catch (AthenaException e) {

            return Response.error(e.getMessage());

        }
    }

    /**
     * Returns the greeting message specifically for the GUI
     */
    public Response getGreeting() {
        Gui gui = new Gui();
        gui.printGreeting();
        return gui.getResponse();
    }

    /**
     * Loads the task list from local storage
     */
    public void initialize() throws AthenaException {
        // Load tasks once at startup
        try {
            this.taskList.loadTasks();
        } catch (IOException e) {
            throw new AthenaException("A cloud obscures the battlefield! I cannot retrieve your scrolls at this time.");
        }
    }
}
