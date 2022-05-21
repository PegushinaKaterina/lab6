package katya.common.util;

import katya.common.entites.HumanBeing;

import java.util.LinkedList;

public class ResponseBuilder {
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

    public String getMessageToResponse() {
        return messageToResponse;
    }

    public HumanBeing getHumanBeingToResponse() {
        return humanBeingToResponse;
    }

    public LinkedList<HumanBeing> getCollectionToResponse() {
        return collectionToResponse;
    }
}
