/**
 * 
 * Classe que cria um Cliente UDP que se comunica através de Sockets com um Servidor
 * 
 */

import java.io.* ;
import java.net.* ;


public class UDPClient
{
    public static void main (String args[]){
        
// Criação do socket do Cliente. (início)
        DatagramSocket s = null;
        try{
            int serverPort=8000;
            s = new DatagramSocket();
// Criação do socket do Cliente. (final) 
            
// Conversa entre o Cliente e o Servidor. (início)
            int i=0;
            for (int j=0; j<10; ++j) {
// Cliente envia um pedido de serviço ao Servidor.                
                String m = "";
                m = "Pedido de Servico " + ++i;
                byte[] data = m.getBytes();
                DatagramPacket request = new DatagramPacket(data,
                             data.length,
                             InetAddress.getByName("127.0.0.1"),
                             serverPort);
                s.send(request);

// Cliente aguarda pela resposta ao seu pedido de serviço.
                byte[] datax = new byte[40];
                DatagramPacket reply = new DatagramPacket(datax, datax.length);
                s.receive(reply);
                System.out.println("Recebida a " + new String(reply.getData()));                

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
// Conversa entre o Cliente e o Servidor. (final)            

// Tratamento de Exceções. (início)
        }catch (UnknownHostException e){
            System.out.println("Sock:" + e.getMessage());
        }catch (EOFException e){System.out.println("EOF:" + e.getMessage());
        }catch (IOException e){System.out.println("IO:" + e.getMessage());
        }finally {if(s!=null) s.close();}
// Tratamento de Exceções. (final)
    }
}