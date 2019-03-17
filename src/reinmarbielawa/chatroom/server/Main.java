package reinmarbielawa.chatroom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final String USAGE = "Chatroom server application.\nUsage: port\n";

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.print(USAGE);
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);

        System.out.printf("Starting chatroom server at port %d\n", port);

        try (ServerSocket ss = new ServerSocket(port);
             Socket s = ss.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            String line;
            while ((line = in.readLine()) != null)
                out.println(line);
        } catch (IOException e) {
            System.err.println("Socket error occurred.");
        }

        System.out.println("Closing...");
    }
}
