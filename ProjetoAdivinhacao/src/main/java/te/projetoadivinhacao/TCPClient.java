package te.projetoadivinhacao;
/**
 * 
 * Classe que cria um Cliente TCP que se comunica atrav�s de Sockets com um Servidor
 * 
 */

import java.io.* ;
import java.net.* ;


public class TCPClient
{
    public static void main (String args[]){
        
// Cria��o do socket do Cliente, acessando o endere�o/porta do Servidor  e
// cria��o dos fluxos de I/O. (in�cio)
        Socket s = null;
        try{
            int serverPort=8000;
            s = new Socket("127.0.0.1", serverPort);
            DataInputStream in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());
// Cria��o do socket do Cliente, acessando o endere�o/porta do Servidor  e
// cria��o dos fluxos de I/O. (final) 
            
// Conversa entre o Cliente e o Servidor. (in�cio)
            int i=0;
//            while(true){
              for(int j=0;j<10;++j){
                out.writeUTF("Pedido de Servico " + ++i);
                String data = in.readUTF();                
                System.out.println("Recebido a " + data);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
// Conversa entre o Cliente e o Servidor. (final)            

// Tratamento de Exce��es. (in�cio)
        }catch (UnknownHostException e){
            System.out.println("Sock:" + e.getMessage());
        }catch (EOFException e){System.out.println("EOF:" + e.getMessage());
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {if(s!=null) try {s.close();} catch (IOException e){System.out.println("IO:"+e.getMessage());}}
// Tratamento de Exce��es. (final)
    }
}