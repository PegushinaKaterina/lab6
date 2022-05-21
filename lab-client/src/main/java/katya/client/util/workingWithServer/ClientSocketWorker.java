package katya.client.util.workingWithServer;

import katya.common.util.DeSerializer;
import katya.common.util.Request;
import katya.common.util.Response;
import katya.common.util.Serializer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSocketWorker {
    private static final int DEFAULT_PORT = 1337;
    private final DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private InetAddress host;
    private int port = DEFAULT_PORT;

    private String address = "localhost";

    public ClientSocketWorker() throws IOException {
        host = InetAddress.getByName(address);
        socketAddress = new InetSocketAddress(host, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(null);
        datagramChannel.configureBlocking(false);
    }

    public void stopClient() throws IOException {
        datagramChannel.close();
    }

    public void sendRequest(Request request) throws IOException {
        try {
            ByteBuffer bufferToSend = Serializer.serializeRequest(request);
            datagramChannel.send(bufferToSend, socketAddress);
        } catch (IOException e) {
            throw new IOException("Произошла ошибка при отправке запроса");
        }
    }

    public Response receiveResponse() throws ClassNotFoundException, IOException {
        int receivedSize;
        ByteBuffer bufferToReceive;
        receivedSize = datagramChannel.socket().getReceiveBufferSize();
        bufferToReceive = ByteBuffer.allocate(receivedSize);
        SocketAddress socketAddress = datagramChannel.receive(bufferToReceive);
        if (socketAddress == null) {
            return null;
        }
        return DeSerializer.deSerializeResponse(bufferToReceive);
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public void setPort(int port) {
        this.port = port;
        this.socketAddress = new InetSocketAddress(host, port);
    }

    public void setAddress(String address) throws UnknownHostException {
        this.address = address;
        host = InetAddress.getByName(address);
        this.socketAddress = new InetSocketAddress(host, port);
    }

}
