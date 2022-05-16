package katya.client;

import katya.client.generators.ConsoleGeneratorHumanBeing;
import katya.client.generators.ScriptGeneratorHumanBeing;
import katya.client.util.CommandListener;
import katya.client.util.CommandManager;
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
    private final RequestCreator requestCreator = new RequestCreator();

    private ClientSocketWorker clientSocketWorker;
    private boolean statusOfCommandListening = true;

    public void startClientWorker() {
        GeneratorClientSocetWorker generatorClientSocetWorker = new GeneratorClientSocetWorker();
        clientSocketWorker = generatorClientSocetWorker.getClientSocketWorker();
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            CommandManager.performCommandd(scanner, clientSocketWorker);
        }
    }
}
