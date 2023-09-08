
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
        System.out.print("Cidade: ");
        String cidade = input.next();
        System.out.print("Estado: ");
        String estado = input.next();
        System.out.print("Pais: ");
        String pais = input.next();
        System.out.print("Telefone: ");
        String telefone = input.next();
        
        
        ClientesBean ab;
        ab = new ClientesBean(cpf_cliente, nome_cliente, sobrenome_cliente, email, rua, cidade, estado, pais, telefone);
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
	
	public void updateClientes(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Cliente que deseja atualizar: ");
        int id_cliente = input.nextInt();
        
        ClientesBean existingClientes;
        existingClientes = ClientesModel.findById(id_cliente, con);
        System.out.println("Parâmetros atuais: "+existingClientes);
        if (existingClientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        existingClientes.setId_cliente(id_cliente); // Defina o id_cliente no objeto existingClientes
      
        System.out.print("Novo Nome Cliente: ");
        existingClientes.setNome_cliente(input.next());
        System.out.print("Nova Sobrenome Cliente: ");
        existingClientes.setSobrenome_cliente(input.next());
        System.out.print("Nova Email Cliente: ");
        existingClientes.setEmail(input.next());
        System.out.print("Nova Rua Cliente: ");
        existingClientes.setRua(input.next());
        System.out.print("Novo Estado Cliente: ");
        existingClientes.setEstado(input.next());
        System.out.print("Novo Pais Cliente: ");
        existingClientes.setPais(input.next());
		System.out.print("Novo Telefone Cliente: ");
        existingClientes.setTelefone(input.next());

        ClientesModel.update(existingClientes, con);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void deleteClientes(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Cliente que deseja excluir: ");
        int id_cliente = input.nextInt();

        ClientesBean existingClientes = ClientesModel.findById(id_cliente, con);
        if (existingClientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        ClientesModel.delete(id_cliente, con);
        System.out.println("Cadastro Cliente excluído com sucesso!");
    }

}