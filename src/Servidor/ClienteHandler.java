package Servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHandler implements Runnable {
    private Socket cliente;

    public ClienteHandler(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(cliente.getInputStream());
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);

            out.println("LOGIN");
            String login = in.nextLine();

            out.println("SENHA");
            String senha = in.nextLine();

            if (BancoDados.autenticar(login, senha) == null) {
                out.println("ERRO, LOGIN OU SENHA INVALIDOS");
                cliente.close();
                return;
            }

            out.println("OK");

            while (in.hasNextLine()) {
                String comando = in.nextLine();

                if (comando.equals("LISTAR")) {
                    out.println("PRODUTOS DISPONIVEIS");
                    for (Produto p : BancoDados.getProdutos()) {
                        out.println(p.toString());
                    }
                    out.println("---Isso é tudo!---");

                } else if (comando.startsWith("COMPRAR")) {

                    String[] partes = comando.split(",");

                    if (partes.length != 3) {
                        out.println("ERRO:FORMATO_INVALIDO");
                        continue;
                    }
                    try {
                        int codigo = Integer.parseInt(partes[1]);
                        int qtd = Integer.parseInt(partes[2]);

                        Produto p = BancoDados.buscarProduto(codigo);

                        if (p == null) {
                            out.println("ERRO:PRODUTO_INEXISTENTE");
                            continue;
                        }
                        if (!p.temEstoque(qtd)) {
                            out.println("ERRO:ESTOQUE_INSUFICIENTE");
                            continue;
                        }

                        double total = p.getPreco() * qtd;
                        out.println("TOTAL= " + total);
                        out.println("digite OK para confirmar");

                        String confirmar = in.nextLine();

                        if (confirmar.equalsIgnoreCase("OK")) {
                            if (p.comprar(qtd)) {
                                out.println("COMPRA_OK");
                            } else {
                                out.println("ERRO:ESTOQUE_INSUFICIENTE");
                            }
                        } else {
                            out.println("comando inválido, compra cancelada");
                        }

                    }catch (NumberFormatException e) {
                        out.println("ERRO: NUMERO_INVALIDO");
                        continue;
                    }

                } else if (comando.equals("SAIR")) {
                    out.println("DESCONECTADO");
                    break;
                } else {
                    out.println("ERRO: COMANDO INVALIDO");
                }
            }

        } catch(IOException ex){
            System.out.println(ex.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {

            }
        }
    }
}
