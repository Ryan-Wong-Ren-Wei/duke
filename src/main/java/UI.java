import java.util.ArrayList;

public class UI {
    private static String lineSeparation = "____________________________________________________________\n";

    public void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineSeparation + "Hello! I'm Duke\nWhat can i do for you?\n");

        System.out.println("Commands:");
        System.out.println("1. list: Print a list of tasks currently stored.");
        System.out.println("2. todo <description of task>: Adds a simple task with no time or date involved");
        System.out.println("3. event OR deadline <description of task> /at OR /by <time>: adds an event/deadline to the list of tasks.");
        System.out.println("4. done <task number>: completes a task");
        System.out.println("5. bye: exits the program\n");
        System.out.println("When entering dates and times, you may do so in the following format for faster entry : \n" +
                "<day>/<month>/<year> <time(24hr format)>\n" + lineSeparation);
        System.out.println("Enter a command:");
    }

    public void printInvalidCommand(){
        System.out.print(lineSeparation);
        System.out.println("Sorry! I don't know what that means.");
        System.out.print(lineSeparation);
    }

    public static void printListOfTasks(TaskList tasks){
        System.out.print(lineSeparation);
        System.out.print(tasks.listOfTasks_String());
        System.out.print(lineSeparation);
    }

    public static void bye(){
        System.out.print(lineSeparation + "Bye. Hope to see you again soon!\n" + lineSeparation);
    }

    public String getLineSeparation(){
        return lineSeparation;
    }

    public void taskAdded(Task taskAdded, int numTasks){
        System.out.println(lineSeparation + "Got it. I've added this task:");
        System.out.println(taskAdded.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.print(lineSeparation);
    }

    public void taskDone(Task task) {
        System.out.print(lineSeparation);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.print(lineSeparation);
    }

    public void taskDeleted(Task task) {
        System.out.print(lineSeparation);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.print(lineSeparation);
    }

    public void searchTasks(String allFoundTasks, boolean found) {
        if (found) {
            System.out.print(lineSeparation);
            System.out.println("Here are the matching tasks in your list:");
            System.out.print(allFoundTasks);
            System.out.print(lineSeparation);
        } else {
            System.out.print(lineSeparation);
            System.out.println("No such tasks were found! Please try again.");
            System.out.print(lineSeparation);
        }
    }

    public void noSuchTask(){
        System.out.print(lineSeparation);
        System.out.println("There is no such task! Please try again.");
        System.out.print(lineSeparation);
    }

    public void taskDescriptionEmpty(){
        System.out.print(lineSeparation);
        System.out.println("The description of your task cannot be empty!");
        System.out.print(lineSeparation);
    }

    public void notAnInteger(){
        System.out.print(lineSeparation);
        System.out.println("That is not an integer! Please enter the index of the task you intend to alter.");
        System.out.print(lineSeparation);
    }

    public void deadlineFormatWrong(){
        System.out.print(lineSeparation);
        System.out.println("Please enter the name of the task followed by the deadline, separated by /by");
        System.out.print(lineSeparation);
    }

    public void eventFormatWrong(){
        System.out.print(lineSeparation);
        System.out.println("Please enter the name of the event and its date/time, separated by /at");
        System.out.print(lineSeparation);
    }
}
