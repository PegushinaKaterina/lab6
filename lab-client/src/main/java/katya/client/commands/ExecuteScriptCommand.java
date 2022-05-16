package katya.client.commands;


import katya.client.generators.ConsoleGeneratorHumanBeing;
import katya.client.generators.ScriptGeneratorHumanBeing;
import katya.client.util.workingWithCommand.CommandListener;
import katya.client.util.workingWithCommand.CommandManager;
import katya.client.util.workingWithCommand.CommandToSend;
import katya.client.util.workingWithServer.ClientSocketWorker;
import katya.common.entites.HumanBeing;
import katya.common.util.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс команды: execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public final class ExecuteScriptCommand {
    private static CommandListener commandListener;
    private static HashSet<String> hashSet = new HashSet<String>();

    private ExecuteScriptCommand() {
    }

    public static void executeCommand(String[] commandArgs, ClientSocketWorker clientSocketWorker) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, 1);
            String fileName = commandArgs[0];
            File file = checkScript(fileName);
            hashSet.add(fileName);
            Scanner scanner = new Scanner(file);
            HumanBeing.getGeneratorHumanBeing().changeState(new ScriptGeneratorHumanBeing(scanner));
            commandListener = CommandManager.getCommandListener();
            try (scanner) {
                do {
                    CommandToSend command = commandListener.readCommandFromScript(scanner);
                    CommandManager.performCommand(command, clientSocketWorker);
                } while (scanner.hasNextLine());
                hashSet.remove(commandArgs[0]);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            Scanner scanner = new Scanner(System.in);
            HumanBeing.getGeneratorHumanBeing().changeState(new ConsoleGeneratorHumanBeing(scanner));
            scanner.close();
        }
    }

    private static File checkScript(String fileName) throws IllegalArgumentException, IOException {
        if (hashSet.contains(fileName)) {
            throw new IllegalArgumentException("Возможно зацикливание");
        }
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл не найден");
        } else if (!file.canRead()) {
            throw new FileNotFoundException("Нет доступа на чтение");
        } else {
            return file;
        }
    }
}
