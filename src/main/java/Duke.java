import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Object;

public class Duke {
    public static void main(String[] args) throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner reader = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lineSeparation = "____________________________________________________________\n";
        System.out.println(lineSeparation + "Hello! I'm Duke\nWhat can i do for you?\n" + lineSeparation);
        String userInput = reader.nextLine();

        File file = new File("data/Duke.txt");
        if (!file.exists()) {
            file.createNewFile();
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

        while (!userInput.equals("bye")) {
            boolean changesMade = false;

            if (userInput.equals("list")) {
                System.out.print(lineSeparation);
                for (int i = 0; i < tasks.size(); ++i) {
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
                    tasks.add(new Deadline(userInput.substring(9, slashPos), userInput.substring(slashPos + 4)));
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
                    tasks.add(new Event(userInput.substring(6, slashPos), userInput.substring(slashPos + 4)));
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
}
