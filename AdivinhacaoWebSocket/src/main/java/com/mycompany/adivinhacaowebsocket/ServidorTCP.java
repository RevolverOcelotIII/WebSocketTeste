
package com.mycompany.adivinhacaowebsocket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorTCP {

    public static void main(String[] args) throws IOException {
        //número da porta do servidor
        int portNumber = 1234;
        //declaração do servidor
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Servidor iniciado na porta " + portNumber);
        
        while (true) {
            //Esperando conexão com cliente, até que aconteça, o código será interrompido aqui
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão recebida de " + clientSocket);
            //Para todo cliente que se conectar, será instanciada uma nova thread, e tudo o que acontecer na thread será exclusivo para cada cliente
            //Também é executado de maneira assícrona
            Thread t = new Thread(() -> {
                try {
                    //PrintWriter utilizado para enviar uma mensagem do servidor para o cliente, recebe no construtor o socket do cliente para saber que é ele que vai receber as mensagens
                    //o getOutputStream é utilizado para acessar o canal de mensagens enviadas do servidor ao cliente
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    //BufferedReader utilizado para receber mensagem do cliente no socket inserido no construtor
                    //o getInputStream é utilizado para acessar o canal de mensagens enviadas para o cliente
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // Espaço utilizado para definir a lógica do programa de adivinhação, deve ser feita antes do while((inputLine = in.readLine()) != null)
                    //porque depois disso ele vai executar sempre que receber uma nova mensagem do clente, através do in.readLine()
                    int tentativas_restantes = 10;
                    Random rand = new Random();
                    int random_number = rand.nextInt(100);
                    out.println("Conectado\nBem vindo ao jogo de tentativas, você tem " + tentativas_restantes
                            + " tentativas para acertar o valor gerado aleatoriamente");
                    out.println("Insira sua tentativa:");
                    String inputLine;
                    //Programa de verdade começa aqui, o while é iniciado quando o cliente envia o seu input
                    while ((inputLine = in.readLine()) != null) {
                        //Mensagem emitida exclusivamente no servidor, diferentemente do out.println() que é responsável por enviar a mensagem do servidor para o cliente
                        System.out.println("Mensagem recebida do cliente " + clientSocket + ": " + inputLine);
                        try {
                            //tratamento do input feito pelo cliente quando foi solicitado
                            int tentativa = Integer.parseInt(inputLine);
                            if (tentativa > random_number) {
                                out.println("Muito alto\n");
                            } else if (tentativa < random_number) {
                                out.println("Muito baixo\n");
                            } else {
                                //declara o fim do programa por conseguir acertar o número
                                out.println("Acertou\n");
                                break;
                            }
                            tentativas_restantes--;
                            //declara o fim do programa, por não conseguir acertar o número dentro do número de tentativas
                            //quando o while((inputLine = in.readLine()) != null) é quebrado através do break; a conexão com o cliente é encerrada
                            //portanto, na próxima vez que o cliente solicitar uma mensagem do servidor através do in.readLine(), por não haver conexão, o valor retornado será null, isso é utilizado como parâmetro para também encerrar o programa cliente
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
                    //ao sair do while através do break, a conexão é rompida com o cliente, como explicado anteriormente
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Erro ao processar a conexão do cliente " + clientSocket);
                    e.printStackTrace();
                }
            });
            //as threads criadas para cada cliente são assícronas, e tudo o que está dentro de suas chaves, será executado neste comando
            t.start();
        }
    }
}
