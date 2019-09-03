import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private File file;
    private Scanner scanFile;

    public Storage(File file){
        this.file = file;
    }

    public ArrayList<String> readFromFile(UI ui) {
        boolean fileAssigned = false;
        System.out.print(ui.getLineSeparation());
        while (!fileAssigned) {
            try {
                this.scanFile = new Scanner(file);
                fileAssigned = true;
                System.out.println("Task list loaded!");
            } catch (FileNotFoundException FNFe) {
                boolean fileCreated = false;
                System.out.println("No Duke file found!\nCreating new file...");
                while (!fileCreated) {
                    try {
                        fileCreated = file.createNewFile();
                        fileCreated = true;
                    } catch (IOException IOe) {
                        System.out.println("Retrying...");
                    }
                }
                System.out.println("New file created!\nAssigning...");
            }
            System.out.print(ui.getLineSeparation());
        }

        ArrayList<String> readFromFile = new ArrayList<String>();

        String fileContent;
        while (this.scanFile.hasNextLine()) {
            fileContent = this.scanFile.nextLine();
            readFromFile.add(fileContent);
        }

        return readFromFile;
    }

    public void saveToFile(TaskList tasks, UI ui){
        String toWriteToFile = "";
        for (Task currTask : tasks.getTaskArrayList()) {
            toWriteToFile += currTask.toString() + "\n";
        }

        boolean fileSaved = false;
        while (!fileSaved) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(toWriteToFile);
                writer.close();
                fileSaved = true;
            } catch (IOException IOe) {
            }
        }
    }
}
