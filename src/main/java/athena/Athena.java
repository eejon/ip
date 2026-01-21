package athena;

import java.io.IOException;

import athena.commands.Command;
import athena.exceptions.AthenaException;
import athena.parser.Parser;
import athena.tasks.TaskManager;
import athena.ui.UI;

public class Athena {
    private TaskManager taskList;
    private UI ui;

    public Athena() {
        this.ui = new UI(); // Load UI
        this.taskList = new TaskManager(); // Load TaskManager
    }

    public void run() {
        ui.printGreeting();
        while (true) {
            try {
                String input = ui.readInput();

                Command command = Parser.parse(input);
                int statusCode = command.dispatch(this.taskList, this.ui);

                if (statusCode == 1) {
                    break;
                } else if (statusCode == -1) {
                    throw new AthenaException("Strategy stalled. I cannot execute this maneuver.");
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

    public static void main(String[] args) {
        Athena chatbot = new Athena();
        chatbot.run();
    }
}
