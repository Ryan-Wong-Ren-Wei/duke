import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testNumTasks(){
        ArrayList<String> readFromFile = new ArrayList<String>();
        String fileContent;
        fileContent = "[T][✘] hello";
        readFromFile.add(fileContent);
        fileContent = "[T][✓] gpaweijgpweijg";
        readFromFile.add(fileContent);
        fileContent = "[E][✘] apfiejpawi (at: 33410413)";
        readFromFile.add(fileContent);

        TaskList tasksTest = new TaskList(readFromFile);
        assertEquals(3, tasksTest.getNumTasks());

    }
}