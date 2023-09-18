
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */
public class AnimaisController {
    
    private boolean clienteExists(int cpf_cliente, Connection con) throws SQLException {
        PreparedStatement st = null;
        ResultSet result = null;
        try {
            st = con.prepareStatement("SELECT cpf_cliente FROM clientes WHERE cpf_cliente = ?");
            st.setInt(1, cpf_cliente);
            result = st.executeQuery();
            return result.next(); // Retorna true se o cliente existir
        } finally {
            if (result != null) {
                result.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    public void createAnimais(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo Animal: ");
        System.out.print("Nome Animal: ");
        String nome_animal = input.next();
        System.out.print("Especie Animal: ");
        String especie_animal = input.next();
        System.out.print("Raca: ");
        String raca = input.next();
        System.out.print("Idade: ");
        int idade = input.nextInt();
        System.out.print("Sexo Animal: ");
        String sexo_animal = input.next();
        System.out.print("Observacao: ");
        String observacao = input.next();

        System.out.println("Clientes disponíveis:");
        ClientesModel.listarClientesDisponiveis(con);
        System.out.print("Insira o CPF do cliente ao vincular um animal: ");
        int cpf_cliente = input.nextInt();

        if (!clienteExists(cpf_cliente, con)) {
            System.out.println("CPF do cliente não encontrado. Tente novamente.");
            return; // Encerre a função se o CPF do cliente não for encontrado
        }

        AnimaisBean mb = new AnimaisBean(nome_animal, especie_animal, raca, idade, sexo_animal, observacao, cpf_cliente);
        AnimaisModel.create(mb, con);
        System.out.println("Animal criado com sucesso!!");
    }


    void listarAnimais(Connection con) throws SQLException {
        HashSet all = AnimaisModel.listAll(con);
        Iterator<AnimaisBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    
    public void updateAnimais(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Animal que deseja atualizar: ");
        int id_animal= input.nextInt();
        
        AnimaisBean existingAnimais;
        existingAnimais = AnimaisModel.findById(id_animal, con);
        System.out.println("Parâmetros atuais: "+existingAnimais);
        if (existingAnimais == null) {
            System.out.println("Animal não encontrado.");
            return;
        }
        existingAnimais.setId_animal(id_animal); // Defina o id_animal no objeto existingAnimais
      
        System.out.print("Novo Nome Animal: ");
        existingAnimais.setNome_animal(input.next());
        System.out.print("Nova Especie Animal: ");
        existingAnimais.setEspecie_animal(input.next());
        System.out.print("Nova Raca Animal: ");
        existingAnimais.setRaca(input.next());
        System.out.print("Nova Idade do Animal: ");
        existingAnimais.setIdade(input.nextInt());
        System.out.print("Novo Sexo Animal: ");
        existingAnimais.setSexo_animal(input.next());
        System.out.print("Nova Observacao: ");
        existingAnimais.setObservacao(input.next());
        System.out.print("Nova Cliente: ");
        existingAnimais.setCpf_cliente(input.nextInt());

        AnimaisModel.update(existingAnimais, con);
        System.out.println("Animal atualizado com sucesso!");
    }

    public void deleteAnimais(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Animal que deseja excluir: ");
        int id_animal = input.nextInt();

        AnimaisBean existingAnimais = AnimaisModel.findById(id_animal, con);
        if (existingAnimais == null) {
            System.out.println("Animal não encontrado.");
            return;
        }

        AnimaisModel.delete(id_animal, con);
        System.out.println("Cadastro Animal excluído com sucesso!");
    }

    void listarAnimaisClientes(Connection con) throws SQLException {
        HashSet all = AnimaisModel.listAllWithClientes(con);
        Iterator<AnimaisBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}
