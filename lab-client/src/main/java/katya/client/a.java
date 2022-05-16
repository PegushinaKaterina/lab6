package katya.client;

import katya.common.DeSerializer;
import katya.common.Response;
import katya.common.Serializer;
import katya.common.util.Request;

import java.io.IOException;
import java.net.*;

public class a {
    public class ClientSocketWorker {
    }
}
    /*
        private static final int DEFAULT_PORT = 228;
        private static final int TIME_TO_RESPONSE = 4000;
        private final DatagramSocket datagramSocket;
        private int port;
        private String address = "localhost";
        private InetAddress serverAddress;

        public ClientSocketWorker() throws UnknownHostException, SocketException {
            port = DEFAULT_PORT;
            datagramSocket = new DatagramSocket();
            serverAddress = InetAddress.getByName(address);
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) throws UnknownHostException {
            this.address = address;
            serverAddress = InetAddress.getByName(address);
        }

        public void sendRequest(Request request) throws IOException {
            byte[] bufferToSend = Serializer.serializeRequest(request);
            DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, port);
            datagramSocket.send(datagramPacket);
        }

        public Response receiveResponse() throws ClassNotFoundException, IOException {
            datagramSocket.setSoTimeout(TIME_TO_RESPONSE);
            int receivedSize = datagramSocket.getReceiveBufferSize();
            byte[] byteBuf = new byte[receivedSize];
            DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
            datagramSocket.receive(dpFromServer);
            byte[] bytesFromServer = dpFromServer.getData();
            return DeSerializer.deSerializeResponse(bytesFromServer);
        }
    }

}
