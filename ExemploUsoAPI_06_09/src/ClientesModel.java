
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebeca
 */
public class ClientesModel {

    public static void create(ClientesBean a, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO clientes (cpf_cliente, nome_cliente, sobrenome_cliente, email, rua, cidade, estado, pais, telefone) VALUES (?,?,?,?,?,?,?,?,?)");
            st.setInt(1, a.getCpf_cliente());
            st.setString(2, (String) a.getNome_cliente());
            st.setString(3, (String) a.getSobrenome_cliente());
            st.setString(4, (String) a.getEmail());
            st.setString(5, (String) a.getRua());
            st.setString(6, (String) a.getCidade());
            st.setString(7, (String) a.getEstado());
            st.setString(8, (String) a.getPais());
            st.setString(9, (String) a.getTelefone());
                  
            
            st.execute();
            st.close();  
    }

	public static ClientesBean findById(int id_cliente, Connection con) throws SQLException {
    ClientesBean mb = null; // Inicialize como null

    PreparedStatement stmt = null;
    ResultSet rs = null;
  
    
    try {
        String query = "SELECT * FROM clientes WHERE id_cliente = ?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, id_cliente);
        rs = stmt.executeQuery();

        if (rs.next()) {
            // Preencher os dados do cliente a partir do ResultSet
            String nome_cliente = rs.getString("nome_cliente");
            String sobrenome_cliente = rs.getString("sobrenome_cliente");
            String email = rs.getString("email");
            String rua = rs.getString("rua");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
			String pais = rs.getString("pais");
			String telefone = rs.getString("telefone");

            // Crie o objeto ClientesBean com os dados recuperados
            mb = new ClientesBean(nome_cliente, sobrenome_cliente, email, rua, cidade, estado, pais, telefone);
        }
    } finally {
        // Fechar recursos (ResultSet, PreparedStatement, etc.)
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
    }
    
    return mb; // Retorne o objeto ClientesBean criado ou null se não encontrado
    }

    static void update(ClientesBean existingClientes, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "UPDATE clientes SET nome_cliente = ?, sobrenome_cliente = ?, email = ?, rua = ?, cidade = ?, estado = ?, pais = ?, telefone = ? WHERE id_cliente = ?";
        st = con.prepareStatement(query);
        st.setString(1, existingClientes.getNome_cliente());
        st.setString(2, existingClientes.getSobrenome_cliente());
        st.setString(3, existingClientes.getEmail());
        st.setString(4, existingClientes.getRua());
        st.setString(5, existingClientes.getCidade());
        st.setString(6, existingClientes.getEstado());
		st.setString(7, existingClientes.getPais());
		st.setString(8, existingClientes.getTelefone());
        st.setInt(9, existingClientes.getid_cliente()); // Certifique-se de que o método getid_cliente() esteja correto na classe ClientesBean.

        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
      }
    }

    public static void delete(int id_cliente, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "DELETE FROM clientes WHERE id_cliente = ?";
        st = con.prepareStatement(query);
        st.setInt(1, id_cliente);

        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
    }
    }


    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT cpf_cliente, nome_cliente, sobrenome_cliente, email, rua, cidade, estado, pais, telefone FROM clientes";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ClientesBean(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6),
                                          result.getString(7), result.getString(8), result.getString(9)));
            }
        return list;
    }
}
