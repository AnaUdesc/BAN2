import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.HashSet;
import java.time.LocalTime;

public class ReservasModel {

    public static void create(ReservasBean reserva, Connection con) throws SQLException {
    PreparedStatement st = null;
    try {
        st = con.prepareStatement("INSERT INTO reservas (id_animal, id_plano, matricula, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva) "
        + "VALUES (?, ?, ?, ?, ?, ?, ? , ?)");
        //st.setInt(1, reserva.getId_cliente());
        st.setInt(1, reserva.getId_animal());
        st.setInt(2, reserva.getId_plano());
        st.setInt(3, reserva.getMatricula());
        st.setDate(4, new java.sql.Date(reserva.getData().getTime()));
        st.setTime(5, java.sql.Time.valueOf(reserva.getHora_entrada()));
        st.setTime(6, java.sql.Time.valueOf(reserva.getHora_saida()));
        st.setString(7, reserva.getObservacoes_reserva());
        st.setString(8, reserva.getStatus_reserva());
        st.execute();

    } finally {
        if (st != null) {
            st.close();
        }
    }
    }


    public static HashSet<ReservasBean> listAll(Connection con) throws SQLException {
        Statement st = null;
        HashSet<ReservasBean> list = new HashSet<>();
        try {
            st = con.createStatement();
            String sql = "SELECT id_animal, id_plano, matricula, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva FROM reservas";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                //int id_cliente = result.getInt(1);
                int id_animal = result.getInt(1);
                int id_plano = result.getInt(2);
                int matricula = result.getInt(3);
                java.util.Date data = new java.util.Date(result.getDate(4).getTime());            
                Time hora_entrada = result.getTime(5);
                Time hora_saida = result.getTime(6);
                LocalTime localTimeEntrada = hora_entrada.toLocalTime();
                LocalTime localTimeSaida = hora_saida.toLocalTime();
                String observacoes_reserva = result.getString(7);
                String status_reserva = result.getString(8);
                
                ReservasBean reserva = new ReservasBean(id_animal, id_plano, matricula, data, localTimeEntrada, localTimeSaida, observacoes_reserva, status_reserva);

                /*ReservasBean reserva;
                reserva = new ReservasBean(id_cliente, id_animal, id_plano, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva);
                */
                
                list.add(reserva);
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    
    public static void listarAnimaisDisponiveis(Connection con) throws SQLException {
    Statement st = null;
    try {
        st = con.createStatement();
        String sql = "SELECT id_animal, nome_animal FROM animais";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            int id_animal = result.getInt("id_animal");
            String nome_animal = result.getString("nome_animal");
            System.out.println("ID: " + id_animal + ", Nome do Animal: " + nome_animal);
        }
    } finally {
        if (st != null) {
            st.close();
        }
    }
}
    
    public static void listarFuncionariosDisponiveis(Connection con) throws SQLException {
    Statement st = null;
    try {
        st = con.createStatement();
        String sql = "SELECT matricula, nome_funcionario FROM funcionarios";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            int matricula = result.getInt("matricula");
            String nome_funcionario = result.getString("nome_funcionario");
            System.out.println("Matricula: " + matricula + ", Nome do Funcionario: " + nome_funcionario);
        }
    } finally {
        if (st != null) {
            st.close();
        }
    }
}


    public static ReservasBean findById(int id_reserva, Connection con) throws SQLException {
        ReservasBean reserva = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_animal, id_plano, matricula, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva FROM reservas WHERE id_reserva = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id_reserva);
            rs = stmt.executeQuery();

            if (rs.next()) {
            //int id_cliente = rs.getInt(1);
            int id_animal = rs.getInt(1);
            int id_plano = rs.getInt(2);
            int matricula = rs.getInt(3);
            java.util.Date data = new java.util.Date(rs.getDate(4).getTime());
            Time hora_entrada = rs.getTime(5);
            Time hora_saida = rs.getTime(6);
            LocalTime localTimeEntrada = hora_entrada.toLocalTime();
            LocalTime localTimeSaida = hora_saida.toLocalTime();
            String observacoes_reserva = rs.getString(7);
            String status_reserva = rs.getString(8);
            
            reserva = new ReservasBean(id_animal, id_plano, matricula, data, localTimeEntrada, localTimeSaida, observacoes_reserva, status_reserva);

          //reserva = new ReservasBean(id_cliente, id_animal, id_plano, data, hora_entrada, localTimehora_saida, observacoes_reserva, status_reserva);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return reserva;
    }

    public static void update(ReservasBean reserva, Connection con) throws SQLException {
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("UPDATE reservas SET id_animal = ?, id_plano = ?, matricula = ?, hora_entrada = ?, hora_saida = ?, data=?, observacoes_reserva=?, status_reserva=? WHERE id_reserva=?");
            //st.setInt(1, reserva.getId_cliente());
            st.setInt(1, reserva.getId_animal());
            st.setInt(2, reserva.getId_plano());
            st.setInt(3, reserva.getMatricula());
            st.setDate(4, new java.sql.Date(reserva.getData().getTime()));
            st.setTime(5, java.sql.Time.valueOf(reserva.getHora_entrada()));
            st.setTime(6, java.sql.Time.valueOf(reserva.getHora_saida()));
            st.setString(7, reserva.getObservacoes_reserva());
            st.setString(8, reserva.getStatus_reserva());
            //st.setString(6, reserva.getTipo_acomodacao());
            st.setInt(9, reserva.getId_reserva());

            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public static void delete(int idReserva, Connection con) throws SQLException {
        PreparedStatement st = null;

        try {
            st = con.prepareStatement("DELETE FROM reservas WHERE id_reserva = ?");
            st.setInt(1, idReserva);

            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}