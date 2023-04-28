package te.projetoadivinhacao;

/**
 * 
 * Classe que cria um Servidor TCP que cria Threads para se comunicar atrav�s 
 * de Sockets com diversos Clientes.
 * 
 */

import java.io.* ;
import java.net.* ;

public class TCPServer
{
    public static void main(String args[]){
        try{
            
// Cria��o do socket do Servidor, utilizando a porta serverPort.
            int serverPort=8000;
            ServerSocket listenSocket = new ServerSocket(serverPort);

// Loop principal de espera por novos Clientes.            
            while(true){

// Servidor aguarda a conex�o de algum Cliente.
                Socket clientSocket = listenSocket.accept();

// Cria��o da Thread e conex�o desta ao socket do Cliente.
                ConnectionTCP c = new ConnectionTCP(clientSocket);
                System.out.println("Conectou!");
            }
        }catch(IOException e) {System.out.println("Listen:" + e.getMessage());}
    }
}

class ConnectionTCP extends Thread 
{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

// Conex�o da Thread-Servidora com o socket do Cliente e
// cria��o dos fluxos de I/O. (in�cio)    
    public ConnectionTCP (Socket aClientSocket){
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out = new DataOutputStream( clientSocket.getOutputStream());
            this.start();
        } catch(IOException e)
        {System.out.println("Connection:" + e.getMessage());}
    }
// Conex�o da Thread-Servidora com o socket do Cliente e
// cria��o dos fluxos de I/O. (final)     
    
// Conversa entre a Thread-Servidor com o Cliente. (in�cio)
    public void run(){
        int i=0;
        try{
            while(true){
            String data = in.readUTF();
            System.out.println("Recebido o " + data);
            out.writeUTF("Resposta ao "+ data);
        }
// Conversa entre a Thread-Servidor com o Cliente. (final)          

// Tratamento de Exce��es. (in�cio)
        }catch (EOFException e){System.out.println("EOF: Conexao Encerrada");
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {try {clientSocket.close();} catch (IOException e){System.out.println("IO:"+e.getMessage());}}
// Tratamento de Exce��es. (final)
    }
}
