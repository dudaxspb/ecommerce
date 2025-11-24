package Servidor;

import java.util.ArrayList;
import java.util.HashMap;

public class BancoDados {
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    static {
        Usuario maria = new Usuario("maria123", "MAKES");
        usuarios.put(maria.getLogin(), maria);
        Usuario daniel = new Usuario("daniel123", "DANIEL");
        usuarios.put(daniel.getLogin(), daniel);
        Usuario joao = new Usuario("joao123", "JOAO");
        usuarios.put(joao.getLogin(), joao);

        Produto p1 = new Produto("Gloss", 7862, 49.90, 10);
        Produto p2 = new Produto("Blush", 3028, 52.20, 5);
        Produto p3 = new Produto("Base", 6281, 37.70, 7);
        Produto p4 = new Produto("Máscara de cílios", 6281, 37.70, 3);
        Produto p5 = new Produto("Iluminador", 8620, 48.70, 2);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
        produtos.add(p4);
        produtos.add(p5);
    }

    public static Usuario autenticar(String login,  String senha) {
        Usuario usuario = usuarios.get(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
    public static Produto buscarProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<Produto> getProdutos(){
        return produtos;
    }
}
