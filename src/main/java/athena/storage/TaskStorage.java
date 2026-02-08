package athena.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import athena.exceptions.AthenaException;
import athena.exceptions.AthenaInvalidDate;
import athena.exceptions.AthenaInvalidFormat;
import athena.tasks.Deadline;
import athena.tasks.Event;
import athena.tasks.Task;
import athena.tasks.Todo;
/**
 * Handles all file read and write operations for modifications to task list.
 * Contains logic to parse text file data into respective Task objects and loads it into task list.
 * Saves task list into custom text format.
 */
public class TaskStorage {
    private static final String STORAGE_FILEPATH = "./data/athena.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String filePath;

    /**
     * Constructs a TaskStorage object with the default storage file path.
     */
    public TaskStorage() {
        this.filePath = STORAGE_FILEPATH;
    }

    /**
     * Constructs a TaskStorage object with a custom storage file path.
     *
     * @param filepath The custom file path for storing tasks.
     */
    public TaskStorage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Saves the list of tasks to the storage file.
     * Creates the parent directory if it doesn't exist.
     * Each task is saved in its file format on a separate line.
     *
     * @param data The list of tasks to save.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void saveTasks(List<Task> data) throws IOException {
        File file = new File(this.filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        String content = data.stream()
            .map(task -> task.toFileFormat())
            .collect(Collectors.joining("\n"));
        writer.write(content.trim());
        writer.close();
    }

    /**
     * Loads tasks from the storage file.
     * Creates the parent directory and file if they don't exist.
     * Corrupted lines are skipped with error messages logged.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs during reading.
     */
    public List<Task> loadTasks() throws IOException {
        File file = new File(this.filePath);
        List<Task> taskList = new ArrayList<>();

        // Create directory if it doesn't exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // Create file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
            return taskList; // Return empty list for new file
        }

        // Read tasks from file
        // https://stackoverflow.com/questions/5516020/bufferedreader-read-multiple-lines-into-a-single-string
        FileReader fileIn = new FileReader(file);
        BufferedReader bufferIn = new BufferedReader(fileIn);
        String line;
        int lineNumber = 1;
        while ((line = bufferIn.readLine()) != null) {
            try {
                Task task = parseTask(line.trim());
                taskList.add(task);
                lineNumber++;
            } catch (AthenaException e) {
                // Log corrupted line but continue loading other tasks
                System.err.println("\t By the gods! I have never seen such disorder on line "
                    + lineNumber + ": " + e.getMessage());
            }
        }
        bufferIn.close();
        return taskList;
    }

    /**
     * Parses a line from the storage file into a Task object.
     * Supports Todo (T), Deadline (D), and Event (E) task types.
     *
     * @param line The line to parse in format: "TYPE | STATUS | DESCRIPTION | [DATE_INFO]".
     * @return The parsed Task object.
     * @throws AthenaException If the line format is invalid or task type is unknown.
     */
    private Task parseTask(String line) throws AthenaException {
        String[] format = line.split(" \\| ");

        if (format.length < 3) {
            throw AthenaInvalidFormat.invalidFormat();
        }

        String type = format[0];
        String status = format[1];
        String description = format[2];

        Task task;

        switch (type) {
        case "T":
            if (format.length != 3) {
                throw AthenaInvalidFormat.invalidTodoFormat();
            }
            task = new Todo(description);
            break;

        case "D":
            if (format.length != 4) {
                throw AthenaInvalidFormat.invalidDeadlineFormat();
            }
            try {
                LocalDate dueDate = LocalDate.parse(format[3].trim(), DATE_FORMAT);
                task = new Deadline(description, dueDate);
            } catch (DateTimeParseException e) {
                throw AthenaInvalidDate.invalidDate();
            }
            break;

        case "E":
            if (format.length != 4) {
                throw AthenaInvalidFormat.invalidEventFormat();
            }
            String[] duration = format[3].split(" - ");
            if (duration.length < 2) {
                throw AthenaInvalidFormat.invalidEventFormat();
            }
            try {
                LocalDate from = LocalDate.parse(duration[0].trim(), DATE_FORMAT);
                LocalDate to = LocalDate.parse(duration[1].trim(), DATE_FORMAT);
                task = new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw AthenaInvalidDate.invalidDate();
            }
            break;

        default:
            throw new AthenaException("\t The tapestry is frayed; this record is lost to chaos.");
        }

        // Set completion status
        if (status.equals("1")) {
            task.markDone();
        } else if (!status.equals("0")) {
            throw new AthenaException("Invalid status value: " + status);
        }

        return task;
    }

}
