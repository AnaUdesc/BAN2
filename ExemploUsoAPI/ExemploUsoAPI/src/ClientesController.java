
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
public class ClientesController {
    
    public void createClientes(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para a cadastrar um novo Clientes: ");
        System.out.print("CPF: ");
        int cpf_cliente = input.nextInt();
        System.out.print("Nome: ");
        String nome_cliente = input.next();
        System.out.print("Sobrenome: ");
        String sobrenome_cliente = input.next();
        System.out.print("Email: ");
        String email = input.next();
        System.out.print("Rua: ");
        String rua = input.next();
        
        
        ClientesBean ab;
        ab = new ClientesBean(cpf_cliente, nome_cliente, sobrenome_cliente, email, rua);
        ClientesModel.create(ab, con);
        System.out.println("Cliente criado com sucesso!!");
    }

    void listarClientes(Connection con) throws SQLException {
        HashSet all = ClientesModel.listAll(con);
        Iterator<ClientesBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

}