import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ReservasController {
    public void createReserva(Connection con) throws SQLException, ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar uma nova reserva: ");
        System.out.print("ID do Cliente: ");
        int id_cliente = input.nextInt();
        System.out.print("ID do Animal: ");
        int id_animal = input.nextInt();
        System.out.print("Data da Reserva (dd/MM/yyyy): ");
        String dataStr = input.next();
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
        System.out.print("ID do Plano de Reserva: ");
        int id_plano_reserva = input.nextInt();
        System.out.print("Observações da Reserva: ");
        String observacoes_reserva = input.next();
        System.out.print("Status da Reserva: ");
        String status_reserva = input.next();
        System.out.print("Tipo de Acomodação: ");
        String tipo_acomodacao = input.next();

        // Verifique se os IDs de Cliente e Animal existem nas tabelas correspondentes
        if (!clienteExists(id_cliente, con) || !animalExists(id_animal, con)) {
            System.out.println("Cliente ou animal não encontrado. A reserva não pode ser criada.");
            return;
        }

        // Crie um objeto ReservasBean com os dados lidos
        ReservasBean reserva = new ReservasBean(id_cliente, id_animal, data, id_plano_reserva, observacoes_reserva, status_reserva, tipo_acomodacao);

        ReservasModel.create(reserva, con);
        System.out.println("Reserva criada com sucesso!!");
    }

	private boolean clienteExists(int id_cliente, Connection con) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT 1 FROM clientes WHERE id_cliente = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id_cliente);
            rs = stmt.executeQuery();

            return rs.next(); // Retorna true se o cliente existir, caso contrário, retorna false.
        } finally {
            // Feche os recursos (ResultSet, PreparedStatement, etc.)
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private boolean animalExists(int id_animal, Connection con) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT 1 FROM animais WHERE id_animal = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id_animal);
            rs = stmt.executeQuery();

            return rs.next(); // Retorna true se o animal existir, caso contrário, retorna false.
        } finally {
            // Feche os recursos (ResultSet, PreparedStatement, etc.)
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void listarReservas(Connection con) throws SQLException {
        HashSet<ReservasBean> all = ReservasModel.listAll(con);
        Iterator<ReservasBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void updateReserva(Connection con) throws SQLException, ParseException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID da Reserva que deseja atualizar: ");
        int id_reserva = input.nextInt();

        ReservasBean existingReserva = ReservasModel.findById(id_reserva, con);
        if (existingReserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        System.out.println("Parâmetros atuais: " + existingReserva);

        // Atualize os campos da reserva
        System.out.print("Nova Data da Reserva (dd/MM/yyyy): ");
        String newDataStr = input.next();
        Date newData = new SimpleDateFormat("dd/MM/yyyy").parse(newDataStr);
        existingReserva.setData(newData);
        System.out.print("Novas Observações da Reserva: ");
        existingReserva.setObservacoes_reserva(input.next());
        System.out.print("Novo Status da Reserva: ");
        existingReserva.setStatus_reserva(input.next());
        System.out.print("Novo Tipo de Acomodação: ");
        existingReserva.setTipo_acomodacao(input.next());

        ReservasModel.update(existingReserva, con);
        System.out.println("Reserva atualizada com sucesso!");
    }

    public void deleteReserva(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID da Reserva que deseja excluir: ");
        int id_reserva = input.nextInt();

        ReservasBean existingReserva = ReservasModel.findById(id_reserva, con);
        if (existingReserva == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        ReservasModel.delete(id_reserva, con);
        System.out.println("Reserva excluída com sucesso!");
    }
}
