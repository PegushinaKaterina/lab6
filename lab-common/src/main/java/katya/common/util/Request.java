package katya.common.util;

import katya.common.entites.HumanBeing;

import java.time.LocalTime;

public class Request {

    private final String commandName;
    private String clientInfo;
    private LocalTime currentTime;
    private final Long longArgument;
    private final Integer integerArgument;
    private final Double doubleArgument;
    private final HumanBeing humanBeingArgument;

    private Request(RequestBuilder requestBuilder) {
        this.commandName = requestBuilder.commandName;
        this.longArgument = requestBuilder.longArgument;
        this.integerArgument = requestBuilder.integerArgument;
        this.doubleArgument = requestBuilder.doubleArgument;
        this.humanBeingArgument = requestBuilder.humanBeingArgument;
    }

    public static class RequestBuilder {
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
        public Request build(){
            return new Request(this);
        }

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
