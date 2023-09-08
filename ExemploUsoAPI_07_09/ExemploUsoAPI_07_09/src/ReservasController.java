
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Date;
import java.time.LocalTime;

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
public class ReservasController {
    public void createReservas(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar uma nova Reserva: ");
        System.out.print("Data Reserva: ");
        Date data_reserva = input.next();
        System.out.print("Hora Entrada: ");
        LocalTime hora_entrada = input.next(); 
        
        // Criar um formato para a hora (HH:mm)
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // Fazer a conversão da string para LocalTime usando o formato definido
        LocalTime hora_entrada = LocalTime.parse(hora_entrada_str, formatter);

*/

/*
        System.out.print("Hora Saida: ");
        LocalTime hora_saida = input.next();
        
        ReservasBean mb = new ReservasBean(data_reserva, hora_entrada, hora_saida);
        AnimaisModel.create(mb, con);
        System.out.println("Reserva criada com sucesso!!");     
    }

    void listarReservas(Connection con) throws SQLException {
        HashSet all = ReservasModel.listAll(con);
        Iterator<ReservasBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void listarReservasClientes(Connection con) throws SQLException {
        HashSet all = ReservasModel.listAllWithClientes(con);
        Iterator<ReservasBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}

*/