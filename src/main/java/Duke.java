import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Object;
import java.util.EnumSet;

public class Duke {
    public static void main(String[] args) throws IOException {
        String lineSeparation = "____________________________________________________________\n";
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner reader = new Scanner(System.in);

        printWelcomeMessageAndInstructions();

        File file = new File("data/Duke.txt");
        if (!file.exists()) {
            System.out.println("No file found\nCreating new file...");
            file.createNewFile();
            System.out.println("done!");
        }

        Scanner scanFile = new Scanner(file);
        String fileContent;
        while (scanFile.hasNextLine()) {
            fileContent = scanFile.nextLine();
            boolean isDone = fileContent.charAt(4) == '\u2713';
            if (fileContent.charAt(1) == 'T') {
                tasks.add(new ToDo(fileContent.substring(7), isDone));
            } else if (fileContent.charAt(1) == 'E') {
                int posOfLine = fileContent.indexOf("(at: ");
                tasks.add(new Event(fileContent.substring(7, posOfLine), fileContent.substring(posOfLine + 5, fileContent.length() - 1), isDone));
            } else if (fileContent.charAt(1) == 'D') {
                int posOfLine = fileContent.indexOf("(by: ");
                tasks.add(new Deadline(fileContent.substring(7, posOfLine), fileContent.substring(posOfLine + 5, fileContent.length() - 1), isDone));
            }
        }

        String userInput = reader.nextLine();
        while (!userInput.equals("bye")) {
            boolean changesMade = false;

            if (userInput.equals("list")) {
                System.out.print(lineSeparation);
                for (int i = 0; i < tasks.size(); ++i) {
                    if (tasks.get(i) == null) continue;
                    int j = i + 1;
                    System.out.println(j + "." + tasks.get(i).toString());
                }
                System.out.print(lineSeparation);

            } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("done")) {
                if (userInput.equals("done")) {
                    System.out.print(lineSeparation);
                    System.out.println("☹ OOPS!!! The description of a completed task cannot be empty.");
                    System.out.print(lineSeparation);
                } else {
                    int taskNo = Integer.parseInt(userInput.substring(5));
                    tasks.get(taskNo - 1).markAsDone();
                    System.out.print(lineSeparation);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[\u2713] " + tasks.get(taskNo - 1).getDescription());
                    System.out.print(lineSeparation);
                    changesMade = true;
                }

            } else if (userInput.length() >= 6 && userInput.substring(0, 6).equals("delete")) {
                if (userInput.equals("delete")) {
                    System.out.print(lineSeparation);
                    System.out.println("☹ OOPS!!! The description of a delete command cannot be empty.");
                    System.out.print(lineSeparation);
                } else {
                    int taskIndex = Integer.parseInt(userInput.substring(7));
                    Task dummy = tasks.get(7);
                    tasks.remove(tasks.get(7));
                    System.out.print(lineSeparation);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + dummy.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(lineSeparation);
                    dummy = null;
                }


            } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")) {
                if (userInput.equals("todo")) {
                    System.out.print(lineSeparation);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.print(lineSeparation);
                } else {
                    tasks.add(new ToDo(userInput.substring(5)));
                    System.out.print(lineSeparation);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("[T][\u2718] " + userInput.substring(5));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(lineSeparation);
                    changesMade = true;
                }

            } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
                if (userInput.equals("deadline")) {
                    System.out.print(lineSeparation);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.print(lineSeparation);
                } else {
                    int slashPos = userInput.indexOf("/by");
                    String date = userInput.substring(slashPos + 4);
                    if ((date.length() - date.replace("/", "").length()) == 2) date = convertDateFormat(date);
                    tasks.add(new Deadline(userInput.substring(9, slashPos), date));
                    System.out.print(lineSeparation);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(lineSeparation);
                    changesMade = true;
                }

            } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
                if (userInput.equals("event")) {
                    System.out.print(lineSeparation);
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.print(lineSeparation);
                } else {
                    int slashPos = userInput.indexOf("/at");
                    String date = userInput.substring(slashPos + 4);
                    if (date.contains("/")) date = convertDateFormat(date);
                    tasks.add(new Event(userInput.substring(6, slashPos), date));
                    System.out.print(lineSeparation);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.print(lineSeparation);
                    changesMade = true;
                }
            } else {
                System.out.print(lineSeparation);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.print(lineSeparation);
            }

            userInput = reader.nextLine();
            if (changesMade == true) saveToFile(tasks);
        }


        System.out.println(lineSeparation + "Bye. Hope to see you again soon!" + "\n" + lineSeparation);
    }

    public static void saveToFile(ArrayList<Task> tasks) throws IOException {
        String toWriteToFile = "";
        for (Task currTask : tasks) {
            toWriteToFile += currTask.toString() + "\n";
        }
        FileWriter writer = new FileWriter("data/Duke.txt");
        writer.write(toWriteToFile);
        writer.close();
    }

    public static String convertDateFormat(String date){
        String dateEdited = date;
        String day = date.substring(0, dateEdited.indexOf('/'));

        dateEdited = date.substring(dateEdited.indexOf('/')+1);
        String month = dateEdited.substring(0, dateEdited.indexOf('/'));

        dateEdited = dateEdited.substring(dateEdited.indexOf('/')+1);
        String year = dateEdited.substring(0, dateEdited.indexOf(' '));

        dateEdited = dateEdited.substring(dateEdited.indexOf(' ')+1);

        String hours = dateEdited.substring(0,2);
        String minutes = dateEdited.substring(2);

        int dayInt = Integer.parseInt(day);
        if (dayInt < 4) {
            day = (dayInt == 3) ? "3rd" : (dayInt == 2) ? "2nd" : "1st";
        }

        int monthInt = Integer.parseInt(month);
        for (Months monthEnum : Months.values()){
            if (monthEnum.getNum() == monthInt) {
                month = monthEnum.getMonth();
            }
        }

        int hoursInt = Integer.parseInt(hours);

        String time;
        if (hoursInt > 12) {
            hoursInt -= 12;
            time = Integer.toString(hoursInt) + "." + minutes + "pm";
        } else {
            time = Integer.toString(hoursInt) + "." + minutes + "am";
        }

        return day + " of " + month + " " + year + ", " + time;
    }

    public static void printWelcomeMessageAndInstructions(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lineSeparation = "____________________________________________________________\n";
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
}