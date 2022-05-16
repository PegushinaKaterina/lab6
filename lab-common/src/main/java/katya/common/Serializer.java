package katya.common;

import katya.common.util.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Serializer {
    public static ByteBuffer serializeRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        try (byteArrayOutputStream; objectOutputStream){
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        }
    }

    public static byte[] serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        try (byteArrayOutputStream; objectOutputStream) {
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
    }
}
