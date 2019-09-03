import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Object;
import java.util.EnumSet;

public class Duke {
    static String lineSeparation = "____________________________________________________________\n";
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Scanner reader = new Scanner(System.in);
        UI ui = new UI();

        File file = new File("data/Duke.txt");
        Storage storage = new Storage(file);
        ArrayList<String> fileContent = storage.readFromFile(ui);
        TaskList tasks = new TaskList(fileContent);
        ui.welcome();

        String userInput = reader.nextLine();
        while (!userInput.equals("bye")) {
           Command currCommand = parser.parseInput(userInput);
           currCommand.execute(tasks, ui, storage);
           userInput = reader.nextLine();
        }

        ui.bye();
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
}