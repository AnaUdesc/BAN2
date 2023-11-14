import org.neo4j.driver.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class PlanosController {

    public void createPlanos(Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            Scanner input = new Scanner(System.in);
            System.out.println("Insira os seguintes dados para cadastrar um novo Plano: ");
            System.out.print("Nome Plano: ");
            String nome_plano = input.nextLine();
            System.out.print("Descricao Plano: ");
            String descricao_plano = input.nextLine();
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

            PlanosBean newPlano = new PlanosBean(nome_plano, descricao_plano, duracao_plano, tipo_acomodacao, preco_plano, restricao_especie, disponibilidade);

            PlanosModel.create(newPlano, driver);
            System.out.println("Plano criado com sucesso!!");
        }
    }

    public void listarPlanos(Driver driver) {
        HashSet<PlanosBean> all = PlanosModel.listAll(driver);
        Iterator<PlanosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void updatePlano(Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            Scanner input = new Scanner(System.in);
            System.out.print("Digite o ID do Plano que deseja atualizar: ");
            int id_plano = input.nextInt();

            PlanosBean existingPlano = PlanosModel.findById(id_plano, driver);
            System.out.println("Parâmetros atuais: " + existingPlano);
            if (existingPlano == null) {
                System.out.println("Plano não encontrado.");
                return;
            }

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

            PlanosModel.update(id_plano, existingPlano, driver);
            System.out.println("Plano atualizado com sucesso!");
        }
    }

    public void deletePlano(Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            Scanner input = new Scanner(System.in);
            System.out.print("Digite o ID do Plano que deseja excluir: ");
            int id_plano = input.nextInt();

            PlanosBean existingPlano = PlanosModel.findById(id_plano, driver);
            if (existingPlano == null) {
                System.out.println("Plano não encontrado.");
                return;
            }

            // Verifica se o plano está associado a alguma reserva
            if (PlanosModel.isPlanosInReserva(id_plano, driver)) {
                System.out.println("Não é possível excluir o plano, pois ele está associado a uma reserva.");
                return;
            }

            PlanosModel.delete(id_plano, driver);
            System.out.println("Plano excluído com sucesso!");
        }
    }
}
