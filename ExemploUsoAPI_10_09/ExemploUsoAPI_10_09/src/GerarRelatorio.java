/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
 
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GerarRelatorio {

    public static void main(String[] args) {
        Connection con = null;
        try {
            // Inicialize a conexão com o banco de dados (substitua com sua lógica de conexão)
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/creche", "postgres", "BAN2");

            // Obtenha uma data atual para incluir no nome do arquivo
            Date dataAtual = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String nomeArquivo = "RelatorioReservas_" + dateFormat.format(dataAtual) + ".txt";

            // Monte o caminho completo do arquivo de relatório
            String diretorio = "C:\temp"; // Substitua pelo seu diretório
            String caminhoCompleto = diretorio + "\\" + nomeArquivo;

            FileWriter arquivo = new FileWriter(caminhoCompleto);

            // Escreva o cabeçalho do relatório
            arquivo.write("ID Reserva | ID Animal | ID Plano | Data | Hora de Entrada | Hora de Saída | Observações | Status\n");

            // Consulta SQL para obter os dados das reservas (substitua com sua consulta)
            String consulta = "SELECT id_reserva, id_animal, id_plano, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva FROM reservas";
            PreparedStatement stmt = con.prepareStatement(consulta);
            ResultSet rs = stmt.executeQuery();

            // Escreva os dados do relatório com base nos resultados da consulta
            while (rs.next()) {
                int id_reserva = rs.getInt("id_reserva");
                int id_animal = rs.getInt("id_animal");
                int id_plano = rs.getInt("id_plano");
                Date data = rs.getDate("data");
                String hora_entrada = rs.getTime("hora_entrada").toString();
                String hora_saida = rs.getTime("hora_saida").toString();
                String observacoes = rs.getString("observacoes_reserva");
                String status = rs.getString("status_reserva");

                String linhaRelatorio = String.format("%d | %d | %d | %s | %s | %s | %s | %s%n",
                        id_reserva, id_animal, id_plano, data, hora_entrada, hora_saida, observacoes, status);

                arquivo.write(linhaRelatorio);
            }

            arquivo.close();
            System.out.println("Arquivo de relatório gerado com sucesso em: " + caminhoCompleto);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Feche a conexão com o banco de dados
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
