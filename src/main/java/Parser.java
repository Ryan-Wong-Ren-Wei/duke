public class Parser {

    public Parser(){}

    public Command parseInput(String userInput){
        String command;
        String continuation;
        if (userInput.contains(" ")){
            int indexOfSpace = userInput.indexOf(' ');
            command = userInput.substring(0, indexOfSpace);
            continuation = userInput.substring(indexOfSpace + 1);
            return new Command(command, continuation);
        } else {
            return new Command(userInput);
        }
    }
}
