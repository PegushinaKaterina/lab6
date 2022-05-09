package katya.client;

public class CommandListener {
    public static CommandManager manager;

    public CommandListener(CollectionHumanBeing collectionHumanBeing) {
        manager = new CommandManager(
                new HelpCommand(CommandManager.AVAILABLE_COMMANDS),
                new InfoCommand(collectionHumanBeing),
                new ShowCommand(collectionHumanBeing),
                new AddCommand(collectionHumanBeing),
                new UpdateCommand(collectionHumanBeing),
                new RemoveByIdCommand(collectionHumanBeing),
                new ClearCommand(collectionHumanBeing),
                new SaveCommand(collectionHumanBeing),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new RemoveHeadCommand(collectionHumanBeing),
                new RemoveLoverCommand(collectionHumanBeing),
                new HistoryCommand(CommandManager.commandHistory.getHistory()),
                new RemoveAllByMinutesOfWaitingCommand(collectionHumanBeing),
                new SumOfMinutesOfWaitingCommand(collectionHumanBeing),
                new CountByImpactSpeedCommand(collectionHumanBeing));
    }


    public void readCommandsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            try {
                System.out.print("Введите команду: ");
                String string = scanner.nextLine();
                manager.performCommand(string);
                scanner.close();
            } catch (NoSuchElementException e) {
                System.out.println("Введен недопустимый символ");
                scanner.close();
                System.exit(0);
            }
        }
    }

}
