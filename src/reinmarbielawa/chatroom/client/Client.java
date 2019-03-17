package reinmarbielawa.chatroom.client;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Client implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private BufferedReader userIn;
    private PrintWriter userOut;
    private BufferedReader serverIn;
    private PrintWriter serverOut;

    public Client(BufferedReader userIn, PrintWriter userOut, BufferedReader serverIn, PrintWriter serverOut) {
        this.userIn = userIn;
        this.userOut = userOut;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
    }

    public void run() {
        LOGGER.info("Chatroom client has started.");
        userOut.println("Chatroom client has started.");
    }
}
