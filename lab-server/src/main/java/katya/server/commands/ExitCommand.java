package katya.server.commands;


import katya.common.Validator;

public class ExitCommand extends AbstractCommand {
    public static boolean isRunning = true;

    public ExitCommand() {
        super(new AbstractCommandBuilder()
                .withName("exit")
                .withQuantityOfArgs(0)
                .withDescription( "завершить программу (без сохранения в файл)")
                .withGeneratesHumanBeing(false));
    }

    @Override
    public void executeCommand(String[] commandArgs){
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            System.out.println("Принудительное завершение программы");
            isRunning = false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
