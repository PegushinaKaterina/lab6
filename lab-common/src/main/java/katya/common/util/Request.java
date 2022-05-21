package katya.common.util;

import katya.common.entites.HumanBeing;

import java.io.Serializable;
import java.time.LocalTime;

public final class Request implements Serializable {
    private final String commandName;
    private String clientInfo;
    private LocalTime currentTime;
    private final Long longArgument;
    private final Integer integerArgument;
    private final Double doubleArgument;
    private final HumanBeing humanBeingArgument;

    public Request(RequestBuilder requestBuilder) {
        this.commandName = requestBuilder.getCommandName();
        this.longArgument = requestBuilder.getLongArgument();
        this.integerArgument = requestBuilder.getIntegerArgument();
        this.doubleArgument = requestBuilder.getDoubleArgument();
        this.humanBeingArgument = requestBuilder.getHumanBeingArgument();
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

    public String getClientInfo() {
        return clientInfo;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        //что-то написать
        return null;
    }
}
