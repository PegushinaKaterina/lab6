package katya.server;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        ServerWorker serverWorker = new ServerWorker("file.csv");
        serverWorker.startServerWorker();
//        if (args.length == 1) {
//            ServerWorker serverWorker = new ServerWorker(args[0]);
//            serverWorker.startServerWorker();
//        } else {
//            System.out.println(("Wrong amount of args during entering launch command, " +
//                    "you must enter only file name for collection"));
//            System.exit(1);
//        }
    }
}
