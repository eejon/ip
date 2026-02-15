# Athena User Guide
> *"Visualize the outcome you desire"* 🦉 

_**Athena**_ is a **task management chatbot** inspired by Greek mythology, designed to help you organize your tasks with strategic precision. 

You can interact with _**Athena**_ through a <u>Graphical User Interface (GUI)</u> for a more interactive experience or a <u>Command Line Interface (CLI)</u> for quicker task management.

---

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing all tasks: `list`](#viewing-all-tasks-list)
  - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
  - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an event task: `event`](#adding-an-event-task-event)
  - [Marking a task as done: `mark`](#marking-a-task-as-done-mark)
  - [Marking a task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
  - [Sorting tasks by priority: `sort`](#sorting-tasks-by-priority-sort)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
- [Command Summary](#command-summary)
- [FAQ](#faq)
- [Credits](#credits)

---

## Quick Start

1. Ensure you have Java `17` installed on your computer. 
**For Mac users**: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html)

2. Download the latest `athena.jar` from [here](https://github.com/eejon/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Athena.

4. Open a command terminal, `cd` into the folder containing the jar file, and run:
   ```
   java -jar athena.jar
   ```

5. A GUI should appear in a few seconds with Athena's greeting message.
[!ui](./UI.png)

6. Type a command in the command box and press Enter to execute it.
   - Example: typing `list` and pressing Enter will display all your tasks.

7. Refer to the [Features](#features) section for details of each command.

---

## Features

> **:information_source: Notes about the command format:**
>
> - Words in `UPPER_CASE` are parameters to be supplied by the user.
>   - Example: in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
>
> - Parameters must be in the specified order.
>
> - All dates must follow the format `yyyy-MM-dd`.
>   - Example: `2026-02-15`
>
> - Task indices start from 1 (not 0).

---

### Viewing all tasks: `list`

Shows a list of all tasks in your task manager.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
Your campaign stands as follows:
    1. [T][ ] read book
    2. [D][ ] return book (by: Feb 15 2026)
    3. [E][ ] team meeting (from: Feb 10 2026 to: Feb 10 2026)
```

---

### Adding a todo task: `todo`

Adds a todo task to your task list. Todo tasks have no date/time attached.

**Format:** `todo DESCRIPTION`

**Examples:**
- `todo read book`
- `todo buy groceries`

**Expected output:**
```
Understood. A new objective is forged:
    [T][ ] read book
The record shows 1 tasks awaiting your mastery!
```

---

### Adding a deadline task: `deadline`

Adds a deadline task with a due date.

**Format:** `deadline DESCRIPTION /by DATE`

**Parameters:**
- `DESCRIPTION`: The task description
- `DATE`: Due date in `yyyy-MM-dd` format

**Examples:**
- `deadline return book /by 2026-02-15`
- `deadline submit report /by 2026-03-01`

**Expected output:**
```
Understood. A new objective is forged:
    [D][ ] return book (by: Feb 15 2026)
The record shows 2 tasks awaiting your mastery!
```

---

### Adding an event task: `event`

Adds an event task with a start and end date.

**Format:** `event DESCRIPTION /from START_DATE /to END_DATE`

**Parameters:**
- `DESCRIPTION`: The event description
- `START_DATE`: Start date in `yyyy-MM-dd` format
- `END_DATE`: End date in `yyyy-MM-dd` format

> **:exclamation: Note:** The end date must be on or after the start date.

**Examples:**
- `event project meeting /from 2026-02-10 /to 2026-02-10`
- `event conference /from 2026-03-15 /to 2026-03-17`

**Expected output:**
```
Understood. A new objective is forged:
    [E][ ] team meeting (from: Feb 10 2026 to: Feb 10 2026)
The record shows 3 tasks awaiting your mastery!
```

---

### Marking a task as done: `mark`

Marks the specified task as completed.

**Format:** `mark INDEX`

**Parameters:**
- `INDEX`: The task number shown in the task list (must be a positive integer)

**Example:**
- `mark 1`

**Expected output:**
```
Strategy realized. This triumph is recorded:
   [T][X] read book
```

---

### Marking a task as not done: `unmark`

Marks the specified task as not completed.

**Format:** `unmark INDEX`

**Parameters:**
- `INDEX`: The task number shown in the task list (must be a positive integer)

**Example:**
- `unmark 1`

**Expected output:**
```
Restored. Focus your efforts here once more:
   [T][ ] read book
```

---

### Deleting a task: `delete`

Deletes the specified task from your task list.

**Format:** `delete INDEX`

**Parameters:**
- `INDEX`: The task number shown in the task list (must be a positive integer)

> **:exclamation: Warning:** This action cannot be undone!

**Example:**
- `delete 2`

**Expected output:**
```
Struck from the record. The objective is removed:
   [D][ ] return book (by: Feb 15 2026)
The record shows 2 tasks awaiting your mastery!
```

---

### Finding tasks by keyword: `find`

Finds all tasks whose descriptions contain the specified keyword.

**Format:** `find KEYWORD`

**Parameters:**
- `KEYWORD`: The search term (case-insensitive)

> **:bulb: Tip:** The search is case-insensitive, so `find book` will match both "Book" and "book".

**Examples:**
- `find book`
- `find meeting`

**Expected output:**
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Feb 15 2026)
```

---

### Sorting tasks by priority: `sort`

Displays all tasks sorted by their priority (based on dates).

**Format:** `sort`

**Sorting order:**
1. Events (sorted by start date, earliest first)
2. Deadlines (sorted by due date, earliest first)
3. Todos (no date, appear last)

**Example:**
```
sort
```

**Expected output:**
```
Your campaign stands as follows:
	1. [E][ ] project meeting (from: Feb 10 2026 to: Feb 10 2026)
	2. [D][ ] return book (by: Feb 15 2026)
	3. [T][ ] read book
```

---

### Exiting the program: `bye`

Exits the Athena application.

**Format:** `bye`

**Expected output:**
```
Strategy never rests. I shall remain here, watchful.

                                                ~Athena 🦉
```

---

## Command Summary
> The following was generated with the help of AI.

| Action | Format | Example |
|--------|--------|---------|
| **List all tasks** | `list` | `list` |
| **Add todo** | `todo DESCRIPTION` | `todo read book` |
| **Add deadline** | `deadline DESCRIPTION /by DATE` | `deadline return book /by 2026-02-15` |
| **Add event** | `event DESCRIPTION /from START /to END` | `event meeting /from 2026-02-21 /to 2026-02-21` |
| **Mark as done** | `mark INDEX` | `mark 1` |
| **Mark as not done** | `unmark INDEX` | `unmark 1` |
| **Delete task** | `delete INDEX` | `delete 2` |
| **Find tasks** | `find KEYWORD` | `find book` |
| **Sort by priority** | `sort` | `sort` |
| **Exit** | `bye` | `bye` |

---

## FAQ
> The following was assisted by AI.

**Q: How do I transfer my data to another computer?**
**A**: Copy the `data/athena.txt` file from your current computer to the same folder where you place `athena.jar` on the new computer. Create a `data` folder if it doesn't exist.

**Q: Where is my data stored?**
**A**: Your tasks are automatically saved to `data/athena.txt` in the same directory as `athena.jar`. The file is created automatically on first use.

**Q: Can I edit the data file directly?**
**A**: Yes, but note that the file follows a specific format. Incorrect edits may cause Athena to skip corrupted lines or fail to load data.

**Q: What happens if I enter an invalid date?**
**A**: Athena will display an error message: "The fates do not recognize this date. Speak in the tongue of yyyy-MM-dd."

---

## Credits

### Documentation
- User Guide format inspired by [AddressBook Level-3](https://se-education.org/addressbook-level3/UserGuide.html)
- [GitHub Flavored Markdown](https://github.github.com/gfm/) for documentation formatting

### Images
- **Athena profile image** (`DaAthena.png`): [iStock - Athena the Goddess](https://www.istockphoto.com/vector/premium-athena-the-goddess-black-gm1317103878-404633825)
- **User profile image** (`DaUser.png`): [UXWing - Man User Circle Icon](https://uxwing.com/man-user-circle-icon/)