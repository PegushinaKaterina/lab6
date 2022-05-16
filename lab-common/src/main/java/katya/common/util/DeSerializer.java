package katya.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public final class DeSerializer {

    private DeSerializer() {
    }

    public static Request deSerializeRequest(byte[] acceptedBuf) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try (byteArrayInputStream; objectInputStream) {
            return (Request) objectInputStream.readObject();
        }
    }

    public static Response deSerializeResponse(ByteBuffer byteBuffer) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try (byteArrayInputStream; objectInputStream) {
            return (Response) objectInputStream.readObject();
        }
    }
}
