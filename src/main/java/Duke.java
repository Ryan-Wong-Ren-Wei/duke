import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> tasks;
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

        while (!userInput.equals("bye")){
            System.out.print(lineSeparation + userInput + "\n" + lineSeparation);
            userInput = reader.nextLine();
        }

        System.out.println(lineSeparation + "Bye. Hope to see you again soon!" + "\n" + lineSeparation);
    }
}
