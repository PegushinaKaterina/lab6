package katya.common.util;

import katya.common.entites.HumanBeing;

public class RequestBuilder {
    private String commandName;
    private Long longArgument;
    private Integer integerArgument;
    private Double doubleArgument;
    private HumanBeing humanBeingArgument;

    public RequestBuilder withName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public RequestBuilder withLongArgument(Long longArgument) {
        this.longArgument = longArgument;
        return this;
    }

    public RequestBuilder withIntegerArgument(Integer integerArgument) {
        this.integerArgument = integerArgument;
        return this;
    }

    public RequestBuilder withDoubleArgument(Double doubleArgument) {
        this.doubleArgument = doubleArgument;
        return this;
    }

    public RequestBuilder withHumanBeingArgument(HumanBeing humanBeingArgument) {
        this.humanBeingArgument = humanBeingArgument;
        return this;
    }

    public String getCommandName() {
        return commandName;
    }

    public Long getLongArgument() {
        return longArgument;
    }

    public Integer getIntegerArgument() {
        return integerArgument;
    }

    public Double getDoubleArgument() {
        return doubleArgument;
    }

    public HumanBeing getHumanBeingArgument() {
        return humanBeingArgument;
    }
}
