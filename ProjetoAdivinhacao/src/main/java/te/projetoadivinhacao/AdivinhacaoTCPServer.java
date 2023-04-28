/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package te.projetoadivinhacao;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class AdivinhacaoTCPServer {

    public static void main(String args[]) {
        try {
            int server_port = 8000;
            ServerSocket listenSocket = new ServerSocket(server_port);
            while(true){
                Socket client_socket = listenSocket.accept();
                ConnectionTCP c = new ConnectionTCP(client_socket);
                System.out.println("Adivinhe um valor:");
            }
        } catch (IOException e) {
            System.out.println("Listen:" + e.getMessage());
        }
    }
}

class ConnectionTCP extends Thread 
{
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

// Conexão da Thread-Servidora com o socket do Cliente e
// criação dos fluxos de I/O. (início)    
    public ConnectionTCP (Socket aClientSocket){
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out = new DataOutputStream( clientSocket.getOutputStream());
            this.start();
        } catch(IOException e)
        {System.out.println("Connection:" + e.getMessage());}
    }
// Conexão da Thread-Servidora com o socket do Cliente e
// criação dos fluxos de I/O. (final)     
    
// Conversa entre a Thread-Servidor com o Cliente. (in�cio)
    public void run(){
        Random rand = new Random();
        int i=0;
        ArrayList<Adivinhacao> usuarios = new ArrayList<Adivinhacao>();
        try{
            
            while(true){
            String tentativa = in.readUTF();
            if(tentativa.length()>0){
                usuarios.add(new Adivinhacao(rand.nextInt(100),i));
                i++;
            }
            System.out.println("Recebido o " + data);
            out.writeUTF("Resposta ao "+ data);
        }
// Conversa entre a Thread-Servidor com o Cliente. (final)          

// Tratamento de Exceções. (início)
        }catch (EOFException e){System.out.println("EOF: Conexao Encerrada");
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {try {clientSocket.close();} catch (IOException e){System.out.println("IO:"+e.getMessage());}}
// Tratamento de Exceções. (final)
    }
}