
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
public class AnimaisModel {
 
    static void create(AnimaisBean m, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO animais (nome_animal, especie_animal, raca, idade, sexo_animal, observacao, cpf_cliente) "
                    + "VALUES (?,?,?,?,?,?, ?)");
            st.setString(1, m.getNome_animal());
            st.setString(2, m.getEspecie_animal());
            st.setString(3, m.getRaca());       
            st.setInt(4, m.getIdade());
            st.setString(5, m.getSexo_animal());
            st.setString(6, m.getObservacao());
            st.setInt(7, m.getCpf_cliente());
            
            st.execute();
            st.close();
    }
    
    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT nome_animal, especie_animal, raca, idade, sexo_animal, observacao, cpf_cliente FROM animais";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new AnimaisBean(result.getString(1), result.getString(2), result.getString(3), 
                result.getInt(4), result.getString(5), result.getString(6), result.getInt(7)));
            }
        return list;
    }    

    public static AnimaisBean findById(int id_animal, Connection con) throws SQLException {
    AnimaisBean mb = null; // Inicialize como null

    PreparedStatement stmt = null;
    ResultSet rs = null;
  
    
    try {
        String query = "SELECT * FROM animais WHERE id_animal = ?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, id_animal);
        rs = stmt.executeQuery();

        if (rs.next()) {
            // Preencher os dados do plano a partir do ResultSet
            String nome_animal = rs.getString("nome_animal");
            String especie_animal = rs.getString("especie_animal");
            String raca = rs.getString("raca");
            int idade = rs.getInt("idade");
            String sexo_animal = rs.getString("sexo_animal");
            String observacao = rs.getString("observacao");
            int cpf_cliente = rs.getInt("cpf_cliente");

            // Crie o objeto AnimaisBean com os dados recuperados
            mb = new AnimaisBean(nome_animal, especie_animal, raca, idade, sexo_animal, observacao, cpf_cliente);
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
    
    return mb; // Retorne o objeto AnimaisBean criado ou null se não encontrado
    }

    static void update(AnimaisBean existingAnimais, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "UPDATE animais SET nome_animal = ?, especie_animal = ?, raca = ?, idade = ?, sexo_animal = ?, observacao = ?, cpf_cliente = ? WHERE id_animal = ?";
        st = con.prepareStatement(query);
        st.setString(1, existingAnimais.getNome_animal());
        st.setString(2, existingAnimais.getEspecie_animal());
        st.setString(3, existingAnimais.getRaca());
        st.setInt(4, existingAnimais.getIdade());
        st.setString(5, existingAnimais.getSexo_animal());
        st.setString(6, existingAnimais.getObservacao());
        st.setInt(7, existingAnimais.getId_animal()); // Certifique-se de que o método getId_animal() esteja correto na classe AnimaisBean.
        st.setInt(8, existingAnimais.getCpf_cliente());
        
        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
      }
    }

    public static void delete(int id_animal, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "DELETE FROM animais WHERE id_animal = ?";
        st = con.prepareStatement(query);
        st.setInt(1, id_animal);

        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
    }
    }
    
    
    static HashSet listAllWithClientes(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT nome_animal, especie_animal, raca, idade, sexo_animal, observacao, cpf_cliente FROM animais NATURAL JOIN clientes";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            AnimaisBean mb = new AnimaisBean(result.getString(1), result.getString(2), result.getString(3), 
                result.getInt(4),result.getString(5),result.getString(6), result.getInt(7));
            //AmbulatoriosBean a = new AmbulatoriosBean(result.getInt(7), result.getInt(8), result.getInt(9));
            //mb.setAmbulatorio(a);
            list.add(mb);
        }
        return list;
    }
    

    /*static HashSet listAllWithClientes(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
