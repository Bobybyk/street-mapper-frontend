package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRun {

        private static int port = 34347;
        private static String adresse = "localhost";

        public static void main(String[] args)throws IOException
        {
            Socket sock = new   Socket(adresse, port);
            BufferedReader sock_br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter sock_pw = new PrintWriter(sock.getOutputStream(), true);
            System.out.println("Connection établie");

            Thread threadClient = new ThreadClient(sock);
            threadClient.start();

            String value;
            while((value = sock_br.readLine()) != null) System.out.println("[Server]: " + value);
            sock.close();
        }
    }