
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
public class PlanosModel {

    static void create(PlanosBean m, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO planos (nome_plano, descricao_plano, duracao_plano, tipo_acomodacao, preco_plano, restricao_especie, disponibilidade) "
                    + "VALUES (?,?,?,?,?,?,?)");
            st.setString(1, m.getNome_plano());
            st.setString(2, m.getDescricao_plano());
            st.setInt(3, m.getDuracao_plano());    
            st.setString(4, m.getTipo_acomodacao());
            st.setDouble(5, m.getPreco_plano());       //revisar tipo variavel
            st.setString(6, m.getRestricao_especie());
	    st.setString(7, m.getDisponibilidade()); //revisar tipo variavel
            
            st.execute();
            st.close();
    }
    
    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT nome_plano, descricao_plano, duracao_plano, tipo_acomodacao, preco_plano, restricao_especie, disponibilidade FROM planos";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new PlanosBean(result.getString(1), result.getString(2), result.getInt(3), 
                result.getString(4), result.getDouble(5), result.getString(6), result.getString(7)));
            }
        return list;
    }    

    public static PlanosBean findById(int id_plano, Connection con) throws SQLException {
    PlanosBean mb = null; // Inicialize como null

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String query = "SELECT * FROM planos WHERE id_plano = ?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, id_plano);
        rs = stmt.executeQuery();

        if (rs.next()) {
            // Preencher os dados do plano a partir do ResultSet
            String nomePlano = rs.getString("nome_plano");
            String descricaoPlano = rs.getString("descricao_plano");
            int duracaoPlano = rs.getInt("duracao_plano");
            String tipoAcomodacao = rs.getString("tipo_acomodacao");
            double precoPlano = rs.getDouble("preco_plano");
            String restricaoEspecie = rs.getString("restricao_especie");
            String disponibilidade = rs.getString("disponibilidade");

            // Crie o objeto PlanosBean com os dados recuperados
            mb = new PlanosBean(nomePlano, descricaoPlano, duracaoPlano, tipoAcomodacao, precoPlano, restricaoEspecie, disponibilidade);
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
    
    return mb; // Retorne o objeto PlanosBean criado ou null se não encontrado
    }

    static void update(PlanosBean existingPlano, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "UPDATE planos SET nome_plano = ?, descricao_plano = ?, duracao_plano = ?, tipo_acomodacao = ?, preco_plano = ?, restricao_especie = ?, disponibilidade = ? WHERE id_plano = ?";
        st = con.prepareStatement(query);
        st.setString(1, existingPlano.getNome_plano());
        st.setString(2, existingPlano.getDescricao_plano());
        st.setInt(3, existingPlano.getDuracao_plano());
        st.setString(4, existingPlano.getTipo_acomodacao());
        st.setDouble(5, existingPlano.getPreco_plano());
        st.setString(6, existingPlano.getRestricao_especie());
        st.setString(7, existingPlano.getDisponibilidade());
        st.setInt(8, existingPlano.getId_plano()); // Certifique-se de que o método getId_plano() esteja correto na classe PlanosBean.

        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
      }
    }

    public static void delete(int id_plano, Connection con) throws SQLException {
    PreparedStatement st = null;

    try {
        String query = "DELETE FROM planos WHERE id_plano = ?";
        st = con.prepareStatement(query);
        st.setInt(1, id_plano);

        st.executeUpdate();
    } finally {
        if (st != null) {
            st.close();
        }
    }
    
    /* static HashSet listAllWithClientes(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT nome_plano, descricao_plano, duracao_plano, tipo_acomodacao, preco_plano, restricao_especie, disponibilidade FROM planos NATURAL JOIN clientes";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            PlanosBean mb;
            mb = new PlanosBean(result.getString(1), result.getString(2), result.getInt(3),
                    result.getString(4), result.getDouble(5),result.getString(6),result.getString(7));
            //AmbulatoriosBean a = new AmbulatoriosBean(result.getInt(7), result.getInt(8), result.getInt(9));
            //mb.setAmbulatorio(a);
            list.add(mb);
        }
        return list;
    }*/

    /*static HashSet listAllWithClientes(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    /*static void update(PlanosBean existingPlano, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    /*static PlanosBean findById(int id_plano, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    /*static void delete(int id_plano, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/

    }
}
