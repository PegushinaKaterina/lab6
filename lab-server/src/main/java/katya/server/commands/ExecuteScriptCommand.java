package katya.server.commands;


import katya.server.CollectionHumanBeing;
import katya.common.Validator;
import katya.common.state.ConsoleGeneratorHumanBeing;
import katya.common.state.ScriptGeneratorHumanBeing;
import katya.common.workingWithCommand.CommandListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Класс команды: execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    HashSet<String> hashSet = new HashSet<String>();

    public ExecuteScriptCommand() {
        super(new AbstractCommandBuilder()
                .withName("execute_script")
                .withQuantityOfArgs(1)
                .withDescription("считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме")
                .withDescriptionOfArgs("название файла")
                .withGeneratesHumanBeing(false));
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validator.validateQuantityOfArgs(commandArgs, getQuantityOfArgs());
            if (hashSet.contains(commandArgs[0])) {
                throw new IllegalArgumentException("Возможно зацикливание");
            }
            String fileName = commandArgs[0];
            hashSet.add(fileName);
            File file = new File(fileName);
            if (!file.exists()) {
                throw new FileNotFoundException("Файл не найден");
            } else if(!file.canRead()){
                throw new FileNotFoundException("Нет доступа на чтение");
            } else {
                Scanner scanner = new Scanner(file);
                CollectionHumanBeing.generatorHumanBeing.changeState(new ScriptGeneratorHumanBeing(scanner));
                try (scanner){
                    do {
                        String string = scanner.nextLine();
                        CommandListener.manager.performCommand(string);
                    } while (scanner.hasNextLine());
                }
            }
            hashSet.remove(commandArgs[0]);
            Scanner scanner = new Scanner(System.in);
            CollectionHumanBeing.generatorHumanBeing.changeState(new ConsoleGeneratorHumanBeing(scanner));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Scanner scanner = new Scanner(System.in);
            CollectionHumanBeing.generatorHumanBeing.changeState(new ConsoleGeneratorHumanBeing(scanner));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Scanner scanner = new Scanner(System.in);
            CollectionHumanBeing.generatorHumanBeing.changeState(new ConsoleGeneratorHumanBeing(scanner));
        }
    }
}
