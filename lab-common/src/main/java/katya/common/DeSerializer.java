package katya.common;

import katya.common.util.Request;

import java.io.*;

public class DeSerializer {

    private DeSerializer() {
    }

    public static Request deSerializeRequest(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try (byteArrayInputStream; objectInputStream) {
            return (Request) objectInputStream.readObject();
        }
    }

    public static Response deSerializeResponse(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try (byteArrayInputStream; objectInputStream) {
            return (Response) objectInputStream.readObject();
        }
    }
}
