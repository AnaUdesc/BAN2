import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;

public class ReservasController {
        // Método para verificar se um animal existe no banco de dados
    private boolean animalExists(int id_animal, Connection con) throws SQLException {
        PreparedStatement st = null;
        ResultSet result = null;
        boolean idValido = false;

        while (!idValido) {
            try {
                st = con.prepareStatement("SELECT id_animal FROM animais WHERE id_animal = ?");
                st.setInt(1, id_animal);
                result = st.executeQuery();

                if (result.next()) {
                    idValido = true;
                } else {
                    System.out.println("ID de animal inválido. Tente novamente: ");
                    Scanner input = new Scanner(System.in);
                    id_animal = input.nextInt();
                }
            } finally {
                if (result != null) {
                    result.close();
                }
                if (st != null) {
                    st.close();
                }
            }
        }

        return true; // Retorna true quando o ID do animal for válido
    }


    // Método para verificar se um plano existe no banco de dados
    private boolean planoExists(int id_plano, Connection con) throws SQLException {
        PreparedStatement st = null;
        ResultSet result = null;
        boolean idValido = false;

        while (!idValido) {
            try {
                st = con.prepareStatement("SELECT id_plano FROM planos WHERE id_plano = ?");
                st.setInt(1, id_plano);
                result = st.executeQuery();

                if (result.next()) {
                    idValido = true;
                } else {
                    System.out.println("ID de plano inválido. Tente novamente: ");
                    Scanner input = new Scanner(System.in);
                    id_plano = input.nextInt();
                }
            } finally {
                if (result != null) {
                    result.close();
                }
                if (st != null) {
                    st.close();
                }
            }
        }

        return true; // Retorna true quando o ID do plano for válido
    }


    public void createReservas(Connection con) throws SQLException, ParseException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar uma nova reserva: ");
        
        System.out.println("Lista de Animais Disponíveis:");
        ReservasModel.listarAnimaisDisponiveis(con);
        System.out.print("ID do Animal: ");
        int id_animal = input.nextInt();
        // Verifique se o ID do animal existe no banco de dados
        if (!animalExists(id_animal, con)) {
            System.out.println("Animal não encontrado. A reserva não pode ser criada.");
            return;
        }
       
        System.out.println("Lista de Planos Disponíveis:");
        PlanosModel.listarPlanosDisponiveis(con);
        System.out.print("ID do Plano: ");
        int id_plano = input.nextInt();    

        // Verifique se o ID do plano existe no banco de dados
        if (!planoExists(id_plano, con)) {
            System.out.println("Plano não encontrado. A reserva não pode ser criada.");
            return;
        }  
        
        System.out.print("Data da Reserva (dd/MM/yyyy): ");
        Date data = null;
        boolean dataValida = false;

        while (!dataValida) {
            String dataStr = input.next();

            try {
                // Tente fazer o parsing da data
                data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(data);
                if (dataStr.equals(formattedDate)) {
                    dataValida = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }

                // Entrada da Hora de Entrada da Reserva
        LocalTime hora_entrada = null;
        boolean horaEntradaValida = false;
        while (!horaEntradaValida) {
            System.out.print("Nova Hora de Entrada da Reserva (HH:mm): ");
            String horaEntradaStr = input.next();

            // Defina um formato para a hora (HH:mm)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            try {
                // Faça a conversão da string para LocalTime
                hora_entrada = LocalTime.parse(horaEntradaStr, formatter);
                horaEntradaValida = true;
            } catch (DateTimeParseException e) {
                System.err.println("Formato de hora inválido. Use o formato HH:mm.");
            }
        }

        // Entrada da Hora de Saída da Reserva
        LocalTime hora_saida = null;
        boolean horaSaidaValida = false;
        while (!horaSaidaValida) {
            System.out.print("Nova Hora de Saída da Reserva (HH:mm): ");
            String horaSaidaStr = input.next();

            // Defina um formato para a hora (HH:mm)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            try {
                // Faça a conversão da string para LocalTime
                hora_saida = LocalTime.parse(horaSaidaStr, formatter);
                horaSaidaValida = true;
            } catch (DateTimeParseException e) {
                System.err.println("Formato de hora inválido. Use o formato HH:mm.");
            }
        }


        System.out.print("Observações da Reserva: ");
        String observacoes_reserva = input.next();
        System.out.print("Status da Reserva: ");
        String status_reserva = input.next(); 

        // Crie um objeto ReservasBean com os dados lidos
        ReservasBean reserva = new ReservasBean(id_animal, id_plano, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva);


        ReservasModel.create(reserva, con);
        System.out.println("Reserva criada com sucesso!!");
         }

    public static HashSet<ReservasBean> listAll(Connection con) throws SQLException {
    HashSet<ReservasBean> reservas = new HashSet<>();
    PreparedStatement st = null;
    ResultSet result = null;

    try {
        st = con.prepareStatement("SELECT * FROM reservas");
        result = st.executeQuery();

        while (result.next()) {
            int id_reserva = result.getInt("id_reserva");
            int id_animal = result.getInt("id_animal");
            int id_plano = result.getInt("id_plano");
            Date data = result.getDate("data");
            LocalTime hora_entrada = result.getTime("hora_entrada").toLocalTime();
            LocalTime hora_saida = result.getTime("hora_saida").toLocalTime();
            String observacoes_reserva = result.getString("observacoes_reserva");
            String status_reserva = result.getString("status_reserva");

            ReservasBean reserva = new ReservasBean(id_animal, id_plano, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva);
            reservas.add(reserva);
        }

        return reservas;
    } finally {
        if (result != null) {
            result.close();
        }
        if (st != null) {
            st.close();
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

    public void listarTodasReservas(Connection con) throws SQLException {
    HashSet<ReservasBean> all = ReservasModel.listAll(con);
    for (ReservasBean reserva : all) {
        System.out.println(reserva.toString());
    }
    }
    
    public void updateReservas(Connection con) throws SQLException, ParseException {
    Scanner input = new Scanner(System.in);
    
    System.out.println("Lista de todas as reservas:");
    listarTodasReservas(con);
    
    System.out.print("Digite o ID da Reserva que deseja atualizar: ");
    int id_reserva = input.nextInt();

    ReservasBean existingReserva = ReservasModel.findById(id_reserva, con);
    if (existingReserva == null) {
        System.out.println("Reserva não encontrada.");
        return;
    }

    System.out.println("Parâmetros atuais: " + existingReserva);

    System.out.println("Lista de Animais Disponíveis:");
    ReservasModel.listarAnimaisDisponiveis(con);
    System.out.print("Novo Animal para a Reserva: ");
    int id_animal = input.nextInt();
        // Verifique se o ID do animal existe no banco de dados
        if (!animalExists(id_animal, con)) {
            System.out.println("Animal não encontrado. A reserva não pode ser criada.");
            return;
        }

    System.out.println("Lista de Planos Disponíveis:");
    PlanosModel.listarPlanosDisponiveis(con);
    System.out.print("Novo Plano para a Reserva: ");
    int id_plano = input.nextInt();    

    // Verifique se o ID do plano existe no banco de dados
    if (!planoExists(id_plano, con)) {
        System.out.println("Plano não encontrado. A reserva não pode ser criada.");
        return;
    }  
    // Atualize os campos da reserva
    System.out.print("Nova Data da Reserva (dd/MM/yyyy): ");
    String newDataStr = input.next();
    /*Date newData = new SimpleDateFormat("dd/MM/yyyy").parse(newDataStr);
    existingReserva.setData(newData);*/
    Date data = null;
        boolean dataValida = false;

        while (!dataValida) {
            String dataStr = input.next();

            try {
                // Tente fazer o parsing da data
                data = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(data);
                if (dataStr.equals(formattedDate)) {
                    dataValida = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }
    

    // Entrada da Hora de Entrada da Reserva
    LocalTime hora_entrada = null;
    boolean horaEntradaValida = false;
    while (!horaEntradaValida) {
        System.out.print("Nova Hora de Entrada da Reserva (HH:mm): ");
        String horaEntradaStr = input.next();

        // Defina um formato para a hora (HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            // Faça a conversão da string para LocalTime
            hora_entrada = LocalTime.parse(horaEntradaStr, formatter);
            horaEntradaValida = true;
        } catch (Exception e) {
            System.err.println("Formato de hora inválido. Use o formato HH:mm.");
        }
    }

    // Entrada da Hora de Saída da Reserva
    LocalTime hora_saida = null;
    boolean horaSaidaValida = false;
    while (!horaSaidaValida) {
        System.out.print("Nova Hora de Saída da Reserva (HH:mm): ");
        String horaSaidaStr = input.next();

        // Defina um formato para a hora (HH:mm)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            // Faça a conversão da string para LocalTime
            hora_saida = LocalTime.parse(horaSaidaStr, formatter);
            horaSaidaValida = true;
        } catch (Exception e) {
            System.err.println("Formato de hora inválido. Use o formato HH:mm.");
        }
    }

    System.out.print("Novas Observações da Reserva: ");
    existingReserva.setObservacoes_reserva(input.next());
    System.out.print("Novo Status da Reserva: ");
    existingReserva.setStatus_reserva(input.next());

    // Atualize a reserva existente no banco de dados
    ReservasModel.update(existingReserva, con);
    System.out.println("Reserva atualizada com sucesso!!");
}


    public void deleteReservas(Connection con) throws SQLException {
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

