/*
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.time.LocalTime;
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */

/*
public class ReservasModel {

    static void create(ReservasBean m, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO reservas (data_reserva, hora_entrada, hora_saida) "
                    + "VALUES (?,?,?)");
            st.setDate(1, m.getData_reserva());
            st.setLocalTime(2, m.getHora_entrada());
            st.setLocalTime(3, m.getHora_saida());     ;
            
            st.execute();
            st.close();
    }
    
    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT data_reserva, hora_entrada, hora_saida FROM reservas";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ReservasBean(result.getDate(1), result.getLocalTime(2), result.getLocalTime(3)));
            }
        return list;
    }    

    static HashSet listAllWithClientes(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT data_reserva, hora_entrada, hora_saida FROM reservas NATURAL JOIN clientes";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            ReservasBean mb = new ReservasBean(result.getDate(1), result.getLocalTime(2), result.getLocalTime(3)), 
            //AmbulatoriosBean a = new AmbulatoriosBean(result.getInt(7), result.getInt(8), result.getInt(9));
            //mb.setAmbulatorio(a);
            list.add(mb);
        }
        return list;
    }

    /*static HashSet listAllWithClientes(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
//}
