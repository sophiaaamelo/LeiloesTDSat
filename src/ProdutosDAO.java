import java.sql.*;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // Metodo responsavel por listar todos os produtos cadastrados
    public ArrayList<ProdutosDTO> listarProdutos() {
        listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));
                listagem.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return listagem;
    }
    // Metodo responsavel por atualizar o status do produto para Vendido
public boolean venderProduto(Integer id) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Erro ao vender: " + e.getMessage());
        return false;
    }
}
// Metodo responsavel por listar todos os produtos com status Vendido
public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    listagem = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        while (resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            listagem.add(p);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao listar vendidos: " + e.getMessage());
    }
    return listagem;
}
}
