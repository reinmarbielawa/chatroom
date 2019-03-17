package reinmarbielawa.chatroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String USAGE = "Chatroom client application.\nUsage: hostname port\n";

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.print(USAGE);
            System.exit(-1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (Socket s = new Socket(host, port);
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter userOut = new PrintWriter(System.out, true);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter serverOut = new PrintWriter(s.getOutputStream(), true)
        ) {
            Client client = new Client(userIn, userOut, serverIn, serverOut);
            client.run();
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Unexpected socket error.");
            LOGGER.severe(e.toString());
        }
    }
}
