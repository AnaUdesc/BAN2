import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GerarRelatorio {

    public static void gerarRelatorioReservas(Connection conn, String diretorioSaida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PrintWriter writer = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a matrícula do funcionário: ");
        int matricula = scanner.nextInt();

        try {
            // Configurar a conexão com o banco de dados PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/creche";
            String user = "postgres";
            String password = "BAN2";
            conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para obter todas as reservas feitas pelo funcionário
            String sql = "SELECT r.id_reserva, a.nome_animal, p.nome_plano, r.data, f.matricula, f.nome_funcionario "
                       + "FROM reservas r "
                       + "INNER JOIN animais a ON r.id_animal = a.id_animal "
                       + "INNER JOIN planos p ON r.id_plano = p.id_plano "
                       + "INNER JOIN funcionarios f ON r.matricula = f.matricula "
                       + "WHERE r.matricula = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, matricula);

            // Execute a consulta SQL
            rs = stmt.executeQuery();

            // Obtenha a data e a hora atuais
            LocalDateTime dataHoraAtual = LocalDateTime.now();

            // Formate a data e a hora para incluir no nome do arquivo
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String dataHoraFormatada = dataHoraAtual.format(formatter);

            // Nome do arquivo com a data e a hora formatadas
            String arquivoSaida = "relatorio_reservas_" + dataHoraFormatada + ".txt";

            // Especificar o diretório e o nome do arquivo de saída
            diretorioSaida = "C:\\Users\\anacl\\OneDrive\\Documentos\\BAN2";
            writer = new PrintWriter(new FileWriter(diretorioSaida + "\\" + arquivoSaida));

            // Cabeçalho do relatório
            writer.println("+-----------------------------------------------------------------------------------");
            writer.println("|                         Relatório Reservas por Funcionário                       |");
            writer.println("+----------------------------------------------------------------------------------+");
            writer.println("ID \t\t\tNome do Animal\t\tNome do Plano\t\tData da Reserva\t\tMatricula\t\tFuncionario");

            // Largura de cada coluna
            int larguraColuna = 25;            
            
            // Gravar o resultado no arquivo de saída
            while (rs.next()) {
                int idReserva = rs.getInt("id_reserva");
                String nomeAnimal = rs.getString("nome_animal");
                String nomePlano = rs.getString("nome_plano");
                Date data = rs.getDate("data");
                matricula = rs.getInt("matricula");
                String nomeFuncionario = rs.getString("nome_funcionario");   
                // Formate a data como uma string no formato desejado
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Escolha o formato de data desejado
                String formattedData = dateFormat.format(data);
                
                // Valores centralizados nas colunas
                String formattedIdReserva = String.format("%-" + larguraColuna + "s", String.valueOf(idReserva));
                String formattedNomeAnimal = String.format("%-" + larguraColuna + "s", nomeAnimal);
                String formattedNomePlano = String.format("%-" + larguraColuna + "s", nomePlano);
                // Agora, você pode adicionar formattedData ao seu relatório
                // Certifique-se de ajustar o tamanho da coluna conforme necessário
                String formattedDataColumn = String.format("%-" + larguraColuna + "s", formattedData);
                String formattedMatricula = String.format("%-" + larguraColuna + "s", String.valueOf(matricula));
                String formattedNomeFuncionario = String.format("%-" + larguraColuna + "s", nomeFuncionario);
                
                // Imprima os valores no relatório
                writer.println(formattedIdReserva + formattedNomeAnimal + formattedNomePlano + formattedDataColumn + formattedMatricula + formattedNomeFuncionario);
            }

            System.out.println("Relatório gerado com sucesso em " + diretorioSaida + "\\" + arquivoSaida);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /****************************/
    
    public static void gerarRelatorioReservasAnimal(Connection conn, String diretorioSaida) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PrintWriter writer = null;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do animal: ");
        int id_animal = scanner.nextInt();

        try {
            // Configurar a conexão com o banco de dados PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/creche";
            String user = "postgres";
            String password = "BAN2";
            conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para obter todas as reservas feitas para aquele animal
            String sql = "SELECT r.id_reserva, a.nome_animal, c.nome_cliente, r.matricula "
                       + "FROM reservas r "
                       + "INNER JOIN animais a ON r.id_animal = a.id_animal " 
                       + "INNER JOIN clientes c ON a.cpf_cliente = c.cpf_cliente "
                       + "WHERE r.id_animal = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_animal);

            // Execute a consulta SQL
            rs = stmt.executeQuery();

            // Obtenha a data e a hora atuais
            LocalDateTime dataHoraAtual = LocalDateTime.now();

            // Formate a data e a hora para incluir no nome do arquivo
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String dataHoraFormatada = dataHoraAtual.format(formatter);

            // Nome do arquivo com a data e a hora formatadas
            String arquivoSaida = "relatorio_reservas_animal_" + dataHoraFormatada + ".txt";

            // Especificar o diretório e o nome do arquivo de saída
            diretorioSaida = "C:\\Users\\anacl\\OneDrive\\Documentos\\BAN2";
            writer = new PrintWriter(new FileWriter(diretorioSaida + "\\" + arquivoSaida));

            // Cabeçalho do relatório
            writer.println("+---------------------------------------------+");
            writer.println("|  Relatório Reservas por Animal Selecionado  |");
            writer.println("+---------------------------------------------+");

            // Largura de cada coluna
            int larguraColuna = 25;

            writer.println("ID Reserva\t\tNome do Animal\t\tNome do Cliente\t\tMatricula");

            // Gravar o resultado no arquivo de saída
            while (rs.next()) {
                int idReserva = rs.getInt("id_reserva");
                String nomeAnimal = rs.getString("nome_animal");
                String nomeCliente= rs.getString("nome_cliente");
                int matricula = rs.getInt("matricula");

                // Valores centralizados nas colunas
                String formattedIdReserva = String.format("%-" + larguraColuna + "s", String.valueOf(idReserva));
                String formattedNomeAnimal = String.format("%-" + larguraColuna + "s", nomeAnimal);
                String formattedNomeCliente = String.format("%-" + larguraColuna + "s", nomeCliente);
                String formattedMatricula = String.format("%-" + larguraColuna + "s", String.valueOf(matricula));

                writer.println(formattedIdReserva + formattedNomeAnimal + formattedNomeCliente + formattedMatricula);
            }

            System.out.println("Relatório gerado com sucesso em " + diretorioSaida + "\\" + arquivoSaida);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**************************/
    
    public static void gerarRelatorioAnimaisPorPlanos(Connection con, String diretorioSaida) {
    //con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    PrintWriter writer = null;

    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o ID do plano: ");
    int idPlano = scanner.nextInt();

    try {
        // Configurar a conexão com o banco de dados PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/creche";
        String user = "postgres";
        String password = "BAN2";
        con = DriverManager.getConnection(url, user, password);

        // Consulta SQL para obter todos os animais e seus respectivos planos
        String sql = "SELECT a.nome_animal, r.id_plano, p.nome_plano " +
                     "FROM animais a " +
                     "INNER JOIN reservas r ON a.id_animal = r.id_animal " +
                     "INNER JOIN planos p ON r.id_plano = p.id_plano " + 
                     "WHERE r.id_plano = ?";

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, idPlano);

        // Execute a consulta SQL
        rs = stmt.executeQuery();

                // Obtenha a data e a hora atuais
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        // Formate a data e a hora para incluir no nome do arquivo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String dataHoraFormatada = dataHoraAtual.format(formatter);

        // Nome do arquivo com a data e a hora formatadas
        String arquivoSaida = "relatorio_animais_planos_" + dataHoraFormatada + ".txt";

        // Especificar o diretório e o nome do arquivo de saída
        diretorioSaida = "C:\\Users\\anacl\\OneDrive\\Documentos\\BAN2";
        writer = new PrintWriter(new FileWriter(diretorioSaida + "\\" + arquivoSaida));

        // Cabeçalho do relatório
        writer.println("+---------------------------------------------------------------------------------------------+");
        writer.println("|                                  Relatório Animais por Plano                                |");
        writer.println("+---------------------------------------------------------------------------------------------+");

            // Largura de cada coluna
        int larguraColuna = 25;

        // Cabeçalho das colunas
        writer.println(String.format("%-" + larguraColuna + "s | %-" + larguraColuna + "s | %-" + larguraColuna + "s", "Nome do Animal", "ID do Plano", "Nome do Plano"));
        writer.println(new String(new char[4 * larguraColuna]).replace('\0', '-'));

    // Gravar o resultado no arquivo de saída
    while (rs.next()) {
        String nomeAnimal = rs.getString("nome_animal");
        idPlano = rs.getInt("id_plano");
        String nomePlano = rs.getString("nome_plano");

            // Valores centralizados nas colunas
        String formattedNomeAnimal = String.format("%-" + larguraColuna + "s", nomeAnimal);
        String formattedIdPlano = String.format("%-" + larguraColuna + "s", String.valueOf(idPlano));
        String formattedNomePlano = String.format("%-" + larguraColuna + "s", nomePlano);

        writer.println(formattedNomeAnimal + " | " + formattedIdPlano + " | " + formattedNomePlano);
    }
        System.out.println("Relatório gerado com sucesso em " + diretorioSaida + "\\" + arquivoSaida);
    } catch (SQLException | IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (writer != null) {
                writer.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}