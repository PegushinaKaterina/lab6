package katya.client.util.workingWithServer;

import katya.common.util.DeSerializer;
import katya.common.util.Response;
import katya.common.util.Serializer;
import katya.common.util.Request;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSocketWorker {
    private final int DEFAULT_PORT = 324;
    private final DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private InetAddress host;
    private int port;

    private String address = "localhost";

    public ClientSocketWorker() throws IOException {
        port = DEFAULT_PORT;
        host = InetAddress.getByName(address);
        socketAddress = new InetSocketAddress(host, port);
        datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(socketAddress);
        datagramChannel.configureBlocking(false);
    }
    public void stopClient() throws IOException {
        datagramChannel.close();
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer bufferToSend = Serializer.serializeRequest(request);
        datagramChannel.send(bufferToSend,socketAddress);
    }

    public Response receiveResponse() throws ClassNotFoundException, IOException {
        int receivedSize = datagramChannel.socket().getReceiveBufferSize();
        ByteBuffer bufferToReceive = ByteBuffer.allocate(receivedSize);
        SocketAddress socketAddress = datagramChannel.receive(bufferToReceive);
        if (socketAddress == null)
            return null;
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
