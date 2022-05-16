package katya.client.commands;


import katya.client.util.CommandManager;
import katya.common.Validator;

public class ExitCommand  {

    public static void executeCommand(String[] commandArgs){
        try {
            Validator.validateQuantityOfArgs(commandArgs, 0);
            System.out.println("Принудительное завершение программы");
            CommandManager.changeStatus();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
