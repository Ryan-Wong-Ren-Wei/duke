import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
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

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.print(lineSeparation);
                for (int i = 0; i < tasks.size(); ++i) {
                    int j = i + 1;
                    System.out.println(j + "." + tasks.get(i).toString());
                }
                System.out.print(lineSeparation);
                userInput = reader.nextLine();

            } else if (userInput.length() >= 4 && userInput.substring(0,4).equals("done")) {
                int taskNo = Integer.parseInt(userInput.substring(5));
                tasks.get(taskNo - 1).markAsDone();
                System.out.print(lineSeparation);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[\u2713] " + tasks.get(taskNo - 1).getDescription());
                System.out.print(lineSeparation);
                userInput = reader.nextLine();

            } else if (userInput.length() >= 4 && userInput.substring(0,4).equals("todo")){
                tasks.add(new ToDo(userInput.substring(5)));
                System.out.print(lineSeparation);
                System.out.println("Got it. I've added this task:");
                System.out.println("[T][\u2718] " + userInput.substring(5));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.print(lineSeparation);
                userInput = reader.nextLine();

            } else if (userInput.length() >= 8 && userInput.substring(0,8).equals("deadline")){
                int slashPos = userInput.indexOf("/by");
                tasks.add(new Deadline(userInput.substring(9,slashPos), userInput.substring(slashPos+4)));
                System.out.print(lineSeparation);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size()-1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.print(lineSeparation);
                userInput = reader.nextLine();

            } else if (userInput.length() >= 5 && userInput.substring(0,5).equals("event")){
                int slashPos = userInput.indexOf("/at");
                tasks.add(new Event(userInput.substring(9,slashPos), userInput.substring(slashPos+4)));
                System.out.print(lineSeparation);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size()-1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.print(lineSeparation);
                userInput = reader.nextLine();
            }
        }

        System.out.println(lineSeparation + "Bye. Hope to see you again soon!" + "\n" + lineSeparation);
    }
}
