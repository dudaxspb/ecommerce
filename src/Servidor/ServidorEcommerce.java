package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket cliente;

        try{
            servidor = new ServerSocket(12345);
            System.out.println("Servidor iniciado");
            while(true){
                cliente = servidor.accept();
                System.out.println("Cliente " +cliente.getInetAddress().getHostAddress() +" conectado");
                new Thread(new ClienteHandler(cliente)).start();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
