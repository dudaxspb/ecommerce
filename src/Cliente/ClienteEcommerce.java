package Cliente;

import java.net.Socket;
import java.util.Scanner;

public class ClienteEcommerce {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            Scanner in = new Scanner(socket.getInputStream());
            Scanner sc = new Scanner(System.in);
            // PrintWriter para enviar
            java.io.PrintWriter out = new java.io.PrintWriter(socket.getOutputStream(), true);

            System.out.println(in.nextLine()); // LOGIN
            out.println(sc.nextLine());

            System.out.println(in.nextLine()); // SENHA
            out.println(sc.nextLine());

            String resposta = in.nextLine();

            if (!resposta.startsWith("Bem vindo")) {
                System.out.println("Falha no login.");
                socket.close();
                return;
            }

            System.out.println("Login efetuado!\n");
            System.out.println("Bem vindo a loja!");

            while (true) {
                System.out.println("--- MENU ---");
                System.out.println("1 - Listar produtos");
                System.out.println("2 - Comprar produto");
                System.out.println("3 - Sair");
                System.out.print("Escolha: ");
                int op = Integer.parseInt(sc.nextLine());

                if (op == 1) {
                    out.println("LISTAR");

                    System.out.println("\nCATÁLOGO:");
                    String linha;
                    while (true) {
                        linha = in.nextLine();
                        if (linha.equals("---Isso é tudo!---")) break;
                        System.out.println(linha);
                    }
                    System.out.println();
                }

                else if (op == 2) {
                    System.out.print("Código do produto: ");
                    int cod = Integer.parseInt(sc.nextLine());

                    System.out.print("Quantidade: ");
                    int qtd = Integer.parseInt(sc.nextLine());

                    out.println("COMPRAR," + cod + "," + qtd);

                    // TOTAL ou ERRO
                    String resposta1 = in.nextLine();
                    System.out.println("Servidor: " + resposta1);

                    // Se erro, não precisa pedir confirmar
                    if (!resposta1.startsWith("TOTAL=")) continue;

                    // Servidor pediu OK
                    String pedidoOk = in.nextLine();
                    System.out.println("Servidor: " + pedidoOk);

                    System.out.print("Digite OK para confirmar: ");
                    String ok = sc.nextLine();
                    out.println(ok);

                    System.out.println("Servidor: " + in.nextLine());
                }

                else if (op == 3) {
                    out.println("SAIR");
                    System.out.println("Servidor: " + in.nextLine());
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Erro cliente: " + e.getMessage());
        }
    }
}
