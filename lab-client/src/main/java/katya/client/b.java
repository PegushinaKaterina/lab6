package katya.client;

public class b {
}
/*
package katya.client;

import katya.client.generators.ConsoleGeneratorHumanBeing;
import katya.client.generators.ScriptGeneratorHumanBeing;
import katya.client.util.CommandListener;
import katya.client.util.CommandToSend;
import katya.client.util.RequestCreator;
import katya.common.Response;
import katya.common.Validator;
import katya.common.entites.HumanBeing;
import katya.common.util.CheckBoolean;
import katya.common.util.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientWorker {

    private final CommandListener commandListener = new CommandListener();
    //private final RequestCreator requestCreator = new RequestCreator();
    private final int maxPort = 65535;
    //private ClientSocketWorker clientSocketWorker;
    private boolean statusOfCommandListening = true;

    public void startClientWorker() {
        askForAddress();
        askForPort();
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            while (statusOfCommandListening) {
                CommandToSend command = commandListener.readCommand(scanner);
                if ("exit".equals(command.getCommandName().toLowerCase(Locale.ROOT))) {
                    System.out.println("Завершение работы клиента");
                    changeStatus();
                } else if ("execute_script".equals(command.getCommandName())) {
                    executeScript(command.getCommandArgs());
                } else if (sendRequest(command)) {
                    receiveResponse();
                }
            }
        }
    }
    public void changeStatus() {
        statusOfCommandListening = !statusOfCommandListening;
    }

    private boolean inputAnswer() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        boolean answer = true;
        try (scanner) {
            String stringAnswer = scanner.nextLine().trim();
            answer = new Validator<Boolean>(stringAnswer)
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckBoolean::checkBoolean, "Ответ должен быть да/нет")
                    .getValue();
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
        return answer;
    }

    private void askForAddress() {
        String question = "Вы хотите использовать адрес сервера по умолчанию? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning) {
            try {
                boolean answer = inputAnswer();
                inputAddress(answer);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                e.getMessage();
            } catch (UnknownHostException e) {
                System.out.println(("Неизвестный адрес, повторите попытку"));
                System.out.println(question);
            } catch (SocketException e) {
                System.out.println(("Ошибка при создании Сокета, повторите попытку"));
                System.out.println(question);
            }
        }
    }

    private void inputAddress(boolean answer) throws UnknownHostException, SocketException {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            if (answer) {
                clientSocketWorker = new ClientSocketWorker();
            } else {
                System.out.println(("Введите интернет-адрес сервера"));
                String address = scanner.nextLine();
                clientSocketWorker = new ClientSocketWorker();
                clientSocketWorker.setAddress(address);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
    }

    private void askForPort() {
        String question = "Вы хотите использовать порт по умолчанию? Введите да/нет";
        System.out.println(question);
        boolean isRunning = true;
        while (isRunning) {
            try {
                boolean answer = inputAnswer();
                inputPort(answer);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        }
    }

    private void inputPort(boolean answer) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            if (!answer) {
                System.out.println("Введите порт удаленного хоста (1-65535)");
                String port = scanner.nextLine();
                int portInt = new Validator<Integer>(port)
                        .withCheckingNull(false)
                        .withCheckingFunction(Integer::parseInt, "Номер порта должен быть целым числом")
                        .withCheckingPredicate((arg) -> (int) arg > 0 && (int) arg <= maxPort, "Номер порта должен быть больше 0 и меньше 65536")
                        .getValue();
                clientSocketWorker.setPort(portInt);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(1);
        }
    }

    private boolean sendRequest(CommandToSend command) {
        try {
            Request request = requestCreator.createRequestOfCommand(command);
            request.setCurrentTime(LocalTime.now());
            request.setClientInfo(clientSocketWorker.getAddress() + " " + clientSocketWorker.getPort());
            clientSocketWorker.sendRequest(request);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при сериализации запроса, повторите попытку");
        }
        return false;
    }

    private void receiveResponse() {
        try {
            Response response = clientSocketWorker.receiveResponse();
            System.out.println(response.toString());
        } catch (SocketTimeoutException e) {
            System.out.println(("The waiting time for a response from the server has been exceeded, try again later"));
        } catch (IOException e) {
            System.out.println(("An error occurred while receiving a response from the server"));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(("The response came damaged"));
        }
    }

    private HashSet<String> hashSet = new HashSet<String>();

    private void executeScript(String[] args) {
        try {
            Validator.validateQuantityOfArgs(args, 1);
            String fileName = args[0];
            File file = checkScript(fileName);
            hashSet.add(fileName);
            Scanner scanner = new Scanner(file);
            HumanBeing.generatorHumanBeing.changeState(new ScriptGeneratorHumanBeing(scanner));
            try (scanner) {
                do {
                    CommandToSend command = commandListener.readCommandFromScript(scanner);
                    if ("execute_script".equals(command.getCommandName())) {
                        executeScript(command.getCommandArgs());
                    } else {
                        if (sendRequest(command)) {
                            receiveResponse();
                        }
                    }
                } while (scanner.hasNextLine());
                hashSet.remove(args[0]);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            Scanner scanner = new Scanner(System.in);
            HumanBeing.generatorHumanBeing.changeState(new ConsoleGeneratorHumanBeing(scanner));
            scanner.close();
        }
    }

    private File checkScript(String fileName) throws IllegalArgumentException, IOException {

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

 */