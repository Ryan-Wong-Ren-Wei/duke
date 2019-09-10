import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<String> inputList) {
        taskArrayList = new ArrayList<Task>();
        for (String currLine : inputList){
            boolean isDone = (currLine.charAt(4) == '\u2713');
            if (currLine.charAt(1) == 'T') {
                taskArrayList.add(new ToDo(currLine.substring(7), isDone));
            } else if (currLine.charAt(1) == 'E') {
                int posOfLine = currLine.indexOf("(at: ");
                taskArrayList.add(new Event(currLine.substring(7, posOfLine), currLine.substring(posOfLine + 5, currLine.length() - 1), isDone));
            } else if (currLine.charAt(1) == 'D') {
                int posOfLine = currLine.indexOf("(by: ");
                taskArrayList.add(new Deadline(currLine.substring(7, posOfLine), currLine.substring(posOfLine + 5, currLine.length() - 1), isDone));
            }
        }
    }

    public void addTask(Task task){
        this.taskArrayList.add(task);
    }

    public void deleteTask(int taskNo) {
        this.taskArrayList.remove(taskNo);
    }

    public ArrayList<Task> getTaskArrayList(){
        return this.taskArrayList;
    }

    public int getNumTasks(){
        return taskArrayList.size();
    }

    public Task getTask(int index){
        return taskArrayList.get(index);
    }

    public String listOfTasks_String() {
        String allTasks = "";
        for (int i = 0; i < taskArrayList.size(); ++i) {
            if (taskArrayList.get(i) == null) continue;
            int j = i + 1;
            allTasks += j + ". " + this.getTask(i).toString() + "\n";
        }
        return allTasks;
    }
}
