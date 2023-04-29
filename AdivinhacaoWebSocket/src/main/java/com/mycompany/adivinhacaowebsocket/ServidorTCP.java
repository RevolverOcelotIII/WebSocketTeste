
package com.mycompany.adivinhacaowebsocket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorTCP {

    public static void main(String[] args) throws IOException {
        int portNumber = 1234;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Servidor iniciado na porta " + portNumber);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão recebida de " + clientSocket);

            Thread t = new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    int tentativas_restantes = 10;
                    Random rand = new Random();
                    int random_number = rand.nextInt(100);
                    out.println("Conectado\nBem vindo ao jogo de tentativas, você tem " + tentativas_restantes
                            + " tentativas para acertar o valor gerado aleatoriamente");
                    out.println("Insira sua tentativa:");
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Mensagem recebida do cliente " + clientSocket + ": " + inputLine);
                        try {
                            int tentativa = Integer.parseInt(inputLine);
                            if (tentativa > random_number) {
                                out.println("Muito alto\n");
                            } else if (tentativa < random_number) {
                                out.println("Muito baixo\n");
                            } else {
                                out.println("Acertou\n");
                                break;
                            }
                            tentativas_restantes--;
                            
                            if(tentativas_restantes==0){
                                out.println("Infelizmente você não consegui acertar o número, ele era: "+random_number);
                                break;
                            }
                            out.println("Você tem "+tentativas_restantes+" tentativas restantes");
                            out.println("Insira sua tentativa:");
                        } catch (NumberFormatException e) {
                            out.println("Valor inválido");
                        }
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao processar a conexão do cliente " + clientSocket);
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
}
