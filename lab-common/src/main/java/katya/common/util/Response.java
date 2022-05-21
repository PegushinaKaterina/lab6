package katya.common.util;

import katya.common.entites.HumanBeing;

import java.io.Serializable;
import java.util.LinkedList;

public final class Response implements Serializable {

    private final String messageToResponse;
    private final HumanBeing humanBeingToResponse;
    private final LinkedList<HumanBeing> collectionToResponse;

    public Response(ResponseBuilder responseBuilder) {
        this.messageToResponse = responseBuilder.getMessageToResponse();
        this.humanBeingToResponse = responseBuilder.getHumanBeingToResponse();
        this.collectionToResponse = responseBuilder.getCollectionToResponse();
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
        return "Response contains: " + (messageToResponse == null ? "" : "message")
                + (humanBeingToResponse == null ? "" : ", humanBeing")
                + (collectionToResponse == null ? "" : ", collection");
    }

    @Override
    public String toString() {
        StringBuilder collection = new StringBuilder();
        if (!(collectionToResponse == null)) {
            if (collectionToResponse.isEmpty()) {
                collection.append("Коллекция пуста");
            } else {
                for (HumanBeing humanBeing : collectionToResponse) {
                    collection.append(humanBeing.toString()).append("\n");
                }
            }

            return String.valueOf(collection);
        }
        return (messageToResponse == null ? "" : messageToResponse)
                + (humanBeingToResponse == null ? "" : "\n" + humanBeingToResponse)
                + "\n" + collection;
    }
}
