import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PlanosController {
    public void createPlanos(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo Plano: ");
        System.out.print("Nome Plano: ");
        String nome_plano = input.next();
        System.out.print("Descricao Plano: ");
        String descricao_plano = input.next();           
        System.out.print("Duracao Plano: ");
        int duracao_plano = input.nextInt();
        System.out.print("Tipo Acomodacao: ");
        String tipo_acomodacao = input.next();         
        System.out.print("Preco Plano: ");
        double preco_plano = input.nextDouble();
        System.out.print("Restricao Especie: ");
        String restricao_especie = input.next();
        System.out.print("Disponibilidade: ");
        String disponibilidade = input.next();
        
        // Corrija esta linha para criar uma instância de PlanosBean
        PlanosBean mb;
        mb = new PlanosBean(nome_plano, descricao_plano, duracao_plano, tipo_acomodacao, preco_plano, restricao_especie, disponibilidade);
        
        PlanosModel.create(mb, con);
        System.out.println("Plano criado com sucesso!!");     
    }

    void listarPlanos(Connection con) throws SQLException {
        HashSet<PlanosBean> all = PlanosModel.listAll(con);
        Iterator<PlanosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    /*void listarPlanosClientes(Connection con) throws SQLException {
        HashSet all = PlanosModel.listAllWithClientes(con);
        Iterator<PlanosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }*/
	
 public void updatePlano(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Plano que deseja atualizar: ");
        int id_plano= input.nextInt();
        
        PlanosBean existingPlano;
        existingPlano = PlanosModel.findById(id_plano, con);
        System.out.println("Parâmetros atuais: "+existingPlano);
        if (existingPlano == null) {
            System.out.println("Plano não encontrado.");
            return;
        }
        existingPlano.setId_plano(id_plano); // Defina o id_plano no objeto existingPlano


        System.out.print("Novo Nome Plano: ");
        existingPlano.setNome_plano(input.next());
        System.out.print("Nova Descricao Plano: ");
        existingPlano.setDescricao_plano(input.next());
        System.out.print("Nova Duracao Plano: ");
        existingPlano.setDuracao_plano(input.nextInt());
        System.out.print("Novo Tipo Acomodacao: ");
        existingPlano.setTipo_acomodacao(input.next());
        System.out.print("Novo Preco Plano: ");
        existingPlano.setPreco_plano(input.nextDouble());
        System.out.print("Nova Restricao Especie: ");
        existingPlano.setRestricao_especie(input.next());
        System.out.print("Nova Disponibilidade: ");
        existingPlano.setDisponibilidade(input.next());

        PlanosModel.update(existingPlano, con);
        System.out.println("Plano atualizado com sucesso!");
    }

    public void deletePlano(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o ID do Plano que deseja excluir: ");
        int id_plano = input.nextInt();

        PlanosBean existingPlano = PlanosModel.findById(id_plano, con);
        if (existingPlano == null) {
            System.out.println("Plano não encontrado.");
            return;
        }

        PlanosModel.delete(id_plano, con);
        System.out.println("Plano excluído com sucesso!");
    }

    /*private PlanosBean PlanosBean(String nome_plano, String duracao_plano, String tipo_acomodacao, double preco_plano, String disponibilidade) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}

