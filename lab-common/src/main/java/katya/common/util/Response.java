package katya.common.util;

import katya.common.entites.HumanBeing;

import java.util.LinkedList;

public class Response {

    private final String messageToResponse;
    private final HumanBeing humanBeingToResponse;
    private final LinkedList<HumanBeing> collectionToResponse;

    private Response(ResponseBuilder responseBuilder) {
        this.messageToResponse = responseBuilder.messageToResponse;
        this.humanBeingToResponse = responseBuilder.humanBeingToResponse;
        this.collectionToResponse = responseBuilder.collectionToResponse;
    }

    public static class ResponseBuilder {
        private String messageToResponse;
        private HumanBeing humanBeingToResponse;
        private LinkedList<HumanBeing> collectionToResponse;

        public ResponseBuilder withMessageToResponse(String messageToResponse) {
            this.messageToResponse = messageToResponse;
            return this;
        }

        public ResponseBuilder withHumanBeingToResponse(HumanBeing humanBeingToResponse) {
            this.humanBeingToResponse = humanBeingToResponse;
            return this;
        }

        public ResponseBuilder withCollectionToResponse(LinkedList<HumanBeing> collectionToResponse) {
            this.collectionToResponse = collectionToResponse;
            return this;
        }
        public Response build(){
            return new Response(this);
        }

    }

    public String getMessageToResponse() {
        return messageToResponse;
    }

    public HumanBeing getHumanBeingToResponse() {
        return humanBeingToResponse;
    }

    public LinkedList<HumanBeing> getCollectionToResponse() {
        return collectionToResponse;
    }

    public String getInfoAboutResponse() {
        // что-то
        return null;
    }

    @Override
    public String toString() {
        // что-то
        return null;
    }
}
