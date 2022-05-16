package katya.server.util.workingWithClient;

import katya.common.util.DeSerializer;
import katya.common.util.Response;
import katya.common.util.Serializer;
import katya.common.util.Request;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerSocketWorker {
    private final int DEFAULT_PORT = 324;
    private final int selectorDelay = 100;
    private final DatagramSocket datagramSocket;
    private int port = DEFAULT_PORT;
    private InetAddress serverAddress;

    public ServerSocketWorker(int port) throws SocketException {
        datagramSocket = new DatagramSocket();
        this.port = port;
    }

    public ServerSocketWorker() throws SocketException {
        datagramSocket = new DatagramSocket();
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void stopServer(){
        datagramSocket.close();
    }

    public Request receiveRequest() throws IOException, ClassNotFoundException {
        int receivedSize = datagramSocket.getReceiveBufferSize();
        byte[] byteArray = new byte[receivedSize];
        DatagramPacket dpToReceive = new DatagramPacket(byteArray, byteArray.length);
        datagramSocket.receive(dpToReceive);
        byteArray = dpToReceive.getData();
        return DeSerializer.deSerializeRequest(byteArray);
    }

    public void sendResponse(Response response) throws IOException {
        byte[] bufferToSend = Serializer.serializeResponse(response);
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, port);
        datagramSocket.send(datagramPacket);
    }
}
