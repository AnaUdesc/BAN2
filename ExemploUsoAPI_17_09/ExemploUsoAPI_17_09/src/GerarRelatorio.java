import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GerarRelatorio {
    public static void gerarRelatorio(Connection con) {
        try {
            
            // Inicialize a conexão com o banco de dados (substitua com sua lógica de conexão)
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/creche", "postgres", "BAN2");
            
            // Obtenha uma data atual para incluir no nome do arquivo
            Date dataAtual = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String nomeArquivo = "RelatorioReservas_" + dateFormat.format(dataAtual) + ".txt";

            // Monte o caminho completo do arquivo de relatório
            String diretorio = "C:\\Users\\anacl\\OneDrive\\Documentos\\BAN2"; // Substitua pelo seu diretório
            String caminhoCompleto = diretorio + "\\" + nomeArquivo;

            try (FileWriter arquivo = new FileWriter(caminhoCompleto);
            PreparedStatement stmt = con.prepareStatement(
                "SELECT r.id_reserva, c.cpf_cliente AS id_cliente, c.cpf_cliente, c.nome_cliente, c.sobrenome_cliente, r.id_animal, r.id_plano, r.data, r.hora_entrada, r.hora_saida, r.observacoes_reserva, r.status_reserva " +
                "FROM reservas r " +
                "INNER JOIN animais a ON r.id_animal = a.id_animal " +
                "INNER JOIN clientes c ON a.cpf_cliente = c.cpf_cliente");
                ResultSet rs = stmt.executeQuery()) {

                // Escreva o cabeçalho do relatório
                arquivo.write("ID Reserva | CPF Cliente | Nome Cliente | Sobrenome Cliente | ID Animal | ID Plano | Data | Hora de Entrada | Hora de Saída | Observações | Status\n");

                // Escreva os dados do relatório com base nos resultados da consulta
                while (rs.next()) {
                    int id_reserva = rs.getInt("id_reserva");
                    int cpf_cliente = rs.getInt("cpf_cliente");
                    String nome_cliente = rs.getString("nome_cliente");
                    String sobrenome_cliente = rs.getString("sobrenome_cliente");
                    int id_animal = rs.getInt("id_animal");
                    int id_plano = rs.getInt("id_plano");
                    Date data = rs.getDate("data");
                    String hora_entrada = rs.getTime("hora_entrada").toString();
                    String hora_saida = rs.getTime("hora_saida").toString();
                    String observacoes = rs.getString("observacoes_reserva");
                    String status = rs.getString("status_reserva");

                    String linhaRelatorio = String.format("%d | %d | %s | %s | %d | %d | %s | %s | %s | %s | %s | %s%n",
                    id_reserva, cpf_cliente, nome_cliente, sobrenome_cliente, id_animal, id_plano,
                    data, hora_entrada, hora_saida, observacoes, status);


                    arquivo.write(linhaRelatorio);
                }

                System.out.println("Arquivo de relatório gerado com sucesso em: " + caminhoCompleto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
