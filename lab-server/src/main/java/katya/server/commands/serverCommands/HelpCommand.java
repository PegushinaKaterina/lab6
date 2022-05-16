package katya.server.commands.serverCommands;

import katya.common.Response;
import katya.common.Validator;
import katya.server.commands.clientCommands.AbstractClientCommand;

import java.util.HashMap;

public class HelpCommand extends AbstractServerCommand{

    private final HashMap<String, AbstractServerCommand> availableCommands;
    public HelpCommand(HashMap<String, AbstractServerCommand> availableCommands) {
        super(new AbstractServerCommand.AbstractCommandBuilder()
                .withName("help")
                .withDescription("вывести справку по доступным командам"));
        this.availableCommands = availableCommands;
    }

    @Override
    public String executeCommand() {
        StringBuilder stringBuilder = new StringBuilder("Доступные команды:\n");
        for (AbstractServerCommand command : availableCommands.values()) {
            stringBuilder.append(command.toString()).append("\n");
        }
        return String.valueOf(stringBuilder);
    }
}
