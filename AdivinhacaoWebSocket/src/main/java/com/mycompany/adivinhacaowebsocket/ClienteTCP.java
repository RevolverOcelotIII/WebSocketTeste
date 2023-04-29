
package com.mycompany.adivinhacaowebsocket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteTCP {

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 1234;

        try (
            Socket socket = new Socket(hostName, portNumber); PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);) {
            String inputLine;
            System.out.println(in.readLine()); //Conectado/Bem vindo
            System.out.println(in.readLine()); //Insira tentativa

            while (true) {
                inputLine = in.readLine();
                if(inputLine == null) break;
                else System.out.println(inputLine); // insira tentativa ou null em caso de sem tentativas
                System.out.print("> ");
                String userInput = scanner.nextLine(); //aguardo do proximo input
                out.println(userInput); //envio do valor do cliente
                System.out.println(in.readLine()); // Resultado da tentativa
                in.readLine();//ignorar esse valor
                inputLine = in.readLine();
                if (inputLine == null) break;
                else System.out.println(inputLine); // Tentativas Restantes ou null em caso de acerto

            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não foi possível obter I/O para a conexão com "
                    + hostName);
            System.exit(1);
        }
    }
}
