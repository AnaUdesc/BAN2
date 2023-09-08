import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class ReservasModel {

    public static void create(ReservasBean reserva, Connection con) throws SQLException {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("INSERT INTO reservas (id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, reserva.getId_cliente());
            st.setInt(2, reserva.getId_animal());
            st.setDate(3, new java.sql.Date(reserva.getData().getTime()));
            st.setInt(4, reserva.getId_plano_reserva());
            st.setString(5, reserva.getObservacoes_reserva());
            st.setString(6, reserva.getStatus_reserva());
            st.setString(7, reserva.getTipo_acomodacao());

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
            String sql = "SELECT id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao FROM reservas";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                int id_cliente = result.getInt(1);
                int id_animal = result.getInt(2);
                java.util.Date data = new java.util.Date(result.getDate(3).getTime());
                int id_plano_reserva = result.getInt(4);
                String observacoes_reserva = result.getString(5);
                String status_reserva = result.getString(6);
                String tipo_acomodacao = result.getString(7);

                ReservasBean reserva = new ReservasBean(id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao);
                list.add(reserva);
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return list;
    }

    public static ReservasBean findById(int idReserva, Connection con) throws SQLException {
        ReservasBean reserva = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao FROM reservas WHERE id_reserva = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idReserva);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id_cliente = rs.getInt(1);
                int id_animal = rs.getInt(2);
                java.util.Date data = new java.util.Date(rs.getDate(3).getTime());
                int id_plano_reserva = rs.getInt(4);
                String observacoes_reserva = rs.getString(5);
                String status_reserva = rs.getString(6);
                String tipo_acomodacao = rs.getString(7);

                reserva = new ReservasBean(id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao);
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
            st = con.prepareStatement("UPDATE reservas SET id_cliente=?, id_animal=?, data=?, id_plano_reserva=?, observacoes_reserva=?, status_reserva=?, tipo_acomodacao=? WHERE id_reserva=?");
            st.setInt(1, reserva.getId_cliente());
            st.setInt(2, reserva.getId_animal());
            st.setDate(3, new java.sql.Date(reserva.getData().getTime()));
            st.setInt(4, reserva.getId_plano_reserva());
            st.setString(5, reserva.getObservacoes_reserva());
            st.setString(6, reserva.getStatus_reserva());
            st.setString(7, reserva.getTipo_acomodacao());
            st.setInt(8, reserva.getId_reserva());

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
