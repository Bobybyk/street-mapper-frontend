package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient extends Thread {

    private BufferedReader br;
    private PrintWriter pw;


    public ThreadClient(Socket sock) throws IOException {
        br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        pw = new PrintWriter(sock.getOutputStream(), true);
    }

    @Override
    public void run() {
        String s;
        try {
            while (true) {
                System.out.print("Entrée une commande: ");
                System.out.print("> ");
                s = br.readLine();
                if (s != null)
                    pw.println(s);
                else
                    break;
            }
        } catch (Exception e) {
            System.err.println("Exception occured:\n" + e);
        }
    }
}

