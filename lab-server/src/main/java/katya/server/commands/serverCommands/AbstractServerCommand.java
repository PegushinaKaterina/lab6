package katya.server.commands.serverCommands;

public abstract class AbstractServerCommand {
    private final String name; // Имя
    private final String description; // Описание

    public AbstractServerCommand(AbstractCommandBuilder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class AbstractCommandBuilder {
        private String name; // Имя
        private String description; // Описание

        public AbstractCommandBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AbstractCommandBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
    }

    public abstract String executeCommand();

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
            return name + " - " + description;
    }
}

