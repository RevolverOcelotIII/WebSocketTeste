import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorTCP {
    public ServidorTCP() {
    }

    public static void main(String[] args) throws IOException {
        int portNumber = 1234;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Servidor iniciado na porta " + portNumber);

        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conexão recebida de " + clientSocket);
            Thread t = new Thread(() -> {
                try {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    int tentativas_restantes = 10;
                    Random rand = new Random();
                    int random_number = rand.nextInt(200);
                    out.println("Conectado\nBem vindo ao jogo de tentativas, você tem " + tentativas_restantes + " tentativas para acertar o valor gerado aleatoriamente");
                    out.println("Insira sua tentativa:");

                    String inputLine;
                    while((inputLine = in.readLine()) != null) {
                        System.out.println("Mensagem recebida do cliente " + clientSocket + ": " + inputLine);

                        try {
                            int tentativa = Integer.parseInt(inputLine);
                            if (tentativa > random_number) {
                                out.println("Muito alto\n");
                            } else {
                                if (tentativa >= random_number) {
                                    out.println("Acertou\n");
                                    break;
                                }

                                out.println("Muito baixo\n");
                            }

                            --tentativas_restantes;
                            if (tentativas_restantes == 0) {
                                out.println("Infelizmente você não consegui acertar o número, ele era: " + random_number);
                                break;
                            }

                            out.println("Você tem " + tentativas_restantes + " tentativas restantes");
                            out.println("Insira sua tentativa:");
                        } catch (NumberFormatException var8) {
                            out.println("Valor inválido");
                        }
                    }

                    clientSocket.close();
                } catch (IOException var9) {
                    System.out.println("Erro ao processar a conexão do cliente " + clientSocket);
                    var9.printStackTrace();
                }
            });
            t.start();
        }
    }
}
