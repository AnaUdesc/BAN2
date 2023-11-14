
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
        System.out.println("Insira os seguintes dados para a cadastrar um novo Cliente: ");
        System.out.print("CPF: ");
        int cpf_cliente = Integer.parseInt(input.nextLine());
        System.out.print("Nome: ");
        String nome_cliente = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome_cliente = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Rua: ");
        String rua = input.nextLine();
        System.out.print("Cidade: ");
        String cidade = input.nextLine();
        System.out.print("Estado: ");
        String estado = input.nextLine();
        System.out.print("Pais: ");
        String pais = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.next();

        ClientesBean ab;
        ab = new ClientesBean(cpf_cliente, nome_cliente, sobrenome_cliente, email, rua, cidade, estado, pais, telefone);

        // Chamar método create da classe ClientesNeo4jModel
        ClientesNeo4jModel.create(ab);
        System.out.println("Cliente criado com sucesso!!");
    }

    void listarClientes() {
        // Chamar método listAll da classe ClientesNeo4jModel
        HashSet all = ClientesNeo4jModel.listAll();
        Iterator<ClientesBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void updateClientes() {
        Scanner input = new Scanner(System.in);
        System.out.println("Lista de Clientes Disponíveis:");
        // Chamar método listarClientesDisponiveis da classe ClientesNeo4jModel
        ClientesNeo4jModel.listarClientesDisponiveis();
        System.out.print("Digite a Matricula do Cliente que deseja atualizar: ");
        int cpf_cliente = input.nextInt();

        input.nextLine();

        // Chamar método findById da classe ClientesNeo4jModel
        ClientesBean existingClientes = ClientesNeo4jModel.findById(cpf_cliente);
        System.out.println("Parâmetros atuais: " + existingClientes);
        if (existingClientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        existingClientes.setCpf_cliente(cpf_cliente);

        System.out.print("Novo Nome Cliente: ");
        existingClientes.setNome_cliente(input.nextLine());
        System.out.print("Novo Sobrenome Cliente: ");
        existingClientes.setSobrenome_cliente(input.nextLine());
        System.out.print("Nova Email Cliente: ");
        existingClientes.setEmail(input.nextLine());
        System.out.print("Nova Rua do Cliente: ");
        existingClientes.setRua(input.nextLine());
        System.out.print("Nova Cidade Cliente: ");
        existingClientes.setCidade(input.nextLine());
        System.out.print("Novo Estado Cliente: ");
        existingClientes.setEstado(input.nextLine());
        System.out.print("Novo Pais Cliente: ");
        existingClientes.setPais(input.nextLine());
        System.out.print("Novo Telefone Cliente: ");
        existingClientes.setTelefone(input.nextLine());

        // Chamar método update da classe ClientesNeo4jModel
        ClientesNeo4jModel.update(existingClientes);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void deleteClientes() {
        Scanner input = new Scanner(System.in);
        System.out.println("Lista de Clientes Disponíveis:");
        // Chamar método listarClientesDisponiveis da classe ClientesNeo4jModel
        ClientesNeo4jModel.listarClientesDisponiveis();
        System.out.print("Digite o CPF do Cliente que deseja excluir: ");
        int cpf_cliente = input.nextInt();

        // Chamar método findById da classe ClientesNeo4jModel
        ClientesBean existingClientes = ClientesNeo4jModel.findById(cpf_cliente);
        if (existingClientes == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Chamar método delete da classe ClientesNeo4jModel
        ClientesNeo4jModel.delete(cpf_cliente);
        System.out.println("Cadastro Cliente excluído com sucesso!");
    }
}