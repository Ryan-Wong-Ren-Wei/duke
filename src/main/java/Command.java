public class Command {
    protected String command;
    protected String continuation;

    public Command(String command, String continuation){
        this.command = command;
        this.continuation = continuation;
    }

    public Command(String command){
        this.command = command;
        this.continuation = "";
    }

    public void execute(TaskList tasks, UI ui, Storage storage){
        boolean changesMade = true;
        if (this.continuation.equals("")) {
            if (this.command.equals("list")) {
                ui.printListOfTasks(tasks);
            }
            changesMade = false;
        } else {
            switch (command) {
                case "done":
                    int taskNo = Integer.parseInt(continuation);
                    tasks.getTask(taskNo - 1).markAsDone();
                    break;

                case "delete":
                    int TaskNo = Integer.parseInt(continuation);
                    tasks.deleteTask(TaskNo - 1);
                    break;

                case "find":
                    String searchFor = continuation;
                    String allTasksFound = "";
                    for (Task taskFound : tasks.getTaskArrayList()) {
                        int index = 1;
                        if (taskFound.getDescription().contains(searchFor)) {
                            allTasksFound += index + ". " + taskFound.toString();
                        }
                        ++index;
                    }
                    if (!allTasksFound.isEmpty()) {
                        //tell UI to print allTasksFound String *********
                    } else {
                        //tell UI to print error message, no tasks found ********
                    }
                    changesMade = false;
                    break;

                case "todo":
                    tasks.addTask(new ToDo(continuation));
                    ui.taskAdded(new ToDo(continuation), tasks.getNumTasks());
                    break;

                case "deadline":
                    int slashPos = continuation.indexOf("/by");
                    String date = continuation.substring(slashPos + 4);
                    String description = continuation.substring(0, slashPos);
                    tasks.addTask(new Deadline(description, date));
                    ui.taskAdded(new Deadline(description, date),tasks.getNumTasks());
                    break;

                case "event":
                    slashPos = continuation.indexOf("/at");
                    date = continuation.substring(slashPos + 4);
                    description = continuation.substring(0, slashPos);
                    tasks.addTask(new Event(description, date));
                    ui.taskAdded(new Event(description, date),tasks.getNumTasks());
                    break;
            }
        }
        if (changesMade) storage.saveToFile(tasks, ui);
    }
}
