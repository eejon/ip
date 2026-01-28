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

import athena.exceptions.AthenaException;
import athena.tasks.Deadline;
import athena.tasks.Event;
import athena.tasks.Task;
import athena.tasks.Todo;

public class TaskStorage {
    private static final String STORAGE_FILEPATH = "./data/athena.txt";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String filePath;

    public TaskStorage() {
        this.filePath = STORAGE_FILEPATH;
    }

    public TaskStorage(String filepath) {
        this.filePath = filepath;
    }

    // Store
    public void saveTasks(List<Task> data) throws IOException {
        File file = new File(this.filePath);
        
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        FileWriter writer = new FileWriter(file);
        StringBuilder sb = new StringBuilder();
        for (Task task : data) {
            sb.append(task.toFileFormat());
            sb.append("\n");
        }
        writer.write(sb.toString().trim());
        writer.close();
    }

    // Load
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
                System.err.println("Warning: Corrupted data on line " + lineNumber + ": " + e.getMessage());
            }
        }
        bufferIn.close();
        return taskList;
    }

    private Task parseTask(String line) throws AthenaException {
        String[] format = line.split(" \\| ");
        
        if (format.length < 3) {
            throw new AthenaException("Invalid file format: insufficient fields");
        }

        String type = format[0];
        String status = format[1];
        String description = format[2];

        Task task;
        
        switch (type) {
        case "T":
            if (format.length != 3) {
                throw new AthenaException("Invalid format for Todo task");
            }
            task = new Todo(description);
            break;

        case "D":
            if (format.length != 4) {
                throw new AthenaException("Invalid format for Deadline task");
            }
            try {
                LocalDate dueDate = LocalDate.parse(format[3].trim(), DATE_FORMAT);
                task = new Deadline(description, dueDate);
            } catch (DateTimeParseException e) {
                throw new AthenaException("Invalid date format in file for Deadline task");
            }
            break;

        case "E":
            if (format.length != 4) {
                throw new AthenaException("Invalid format for Event task");
            }
            String[] duration = format[3].split("-");
            if (duration.length < 2) {
                throw new AthenaException("Invalid format for Event task");
            }
            try {
                LocalDate from = LocalDate.parse(duration[0].trim(), DATE_FORMAT);
                LocalDate to = LocalDate.parse(duration[1].trim(), DATE_FORMAT);
                task = new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new AthenaException("Invalid date format in file for Event task");
            }
            break;

        default:
            throw new AthenaException("Unknown task type: " + type);
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
