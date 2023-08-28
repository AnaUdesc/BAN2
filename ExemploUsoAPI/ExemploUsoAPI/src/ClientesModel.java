
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
            st = con.prepareStatement("INSERT INTO clientes (cpf_cliente, nome_cliente, sobrenome_cliente, email, rua) VALUES (?,?,?,?,?)");
            st.setInt(1, a.getCpf_cliente());
            st.setString(2, (String) a.getNome_cliente());
            st.setString(3, (String) a.getSobrenome_cliente());
            st.setString(4, (String) a.getEmail());
            st.setString(5, (String) a.getRua());
                  
            
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT cpf_cliente, nome, sobrenome_cliente, email, rua FROM clientes";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ClientesBean(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
        return list;
    }
}
