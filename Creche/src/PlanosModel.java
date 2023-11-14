import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.NoSuchRecordException;

import java.util.HashSet;
import java.util.List;

public class PlanosModel {

    public static void create(PlanosBean m, Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            session.writeTransaction(tx -> {
                tx.run("CREATE (p:Plano {nome: $nome, descricao: $descricao, duracao: $duracao, tipoAcomodacao: $tipoAcomodacao, preco: $preco, restricaoEspecie: $restricaoEspecie, disponibilidade: $disponibilidade})",
                        Values.parameters(
                                "nome", m.getNome_plano(),
                                "descricao", m.getDescricao_plano(),
                                "duracao", m.getDuracao_plano(),
                                "tipoAcomodacao", m.getTipo_acomodacao(),
                                "preco", m.getPreco_plano(),
                                "restricaoEspecie", m.getRestricao_especie(),
                                "disponibilidade", m.getDisponibilidade()
                        ));
                return null;
            });
        }
    }

    public static HashSet<PlanosBean> listAll(Driver driver) {
        HashSet<PlanosBean> list = new HashSet<>();
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (p:Plano) RETURN p");
                List<Record> records = result.list();

                for (Record record : records) {
                    Node planoNode = record.get("p").asNode();
                    PlanosBean plano = new PlanosBean(
                            planoNode.get("nome").asString(),
                            planoNode.get("descricao").asString(),
                            planoNode.get("duracao").asInt(),
                            planoNode.get("tipoAcomodacao").asString(),
                            planoNode.get("preco").asDouble(),
                            planoNode.get("restricaoEspecie").asString(),
                            planoNode.get("disponibilidade").asString()
                    );
                    list.add(plano);
                }
                return null;
            });
        }
        return list;
    }

    public static PlanosBean findById(int id, Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            return session.readTransaction(tx -> {
                Result result = tx.run("MATCH (p:Plano) WHERE id(p) = $id RETURN p",
                        Values.parameters("id", id));
                try {
                    Node planoNode = result.single().get("p").asNode();
                    return new PlanosBean(
                            planoNode.get("nome").asString(),
                            planoNode.get("descricao").asString(),
                            planoNode.get("duracao").asInt(),
                            planoNode.get("tipoAcomodacao").asString(),
                            planoNode.get("preco").asDouble(),
                            planoNode.get("restricaoEspecie").asString(),
                            planoNode.get("disponibilidade").asString()
                    );
                } catch (NoSuchRecordException e) {
                    return null;
                }
            });
        }
    }

    public static void update(int id, PlanosBean updatedPlano, Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (p:Plano) WHERE id(p) = $id " +
                                "SET p.nome = $nome, p.descricao = $descricao, p.duracao = $duracao, " +
                                "p.tipoAcomodacao = $tipoAcomodacao, p.preco = $preco, " +
                                "p.restricaoEspecie = $restricaoEspecie, p.disponibilidade = $disponibilidade",
                        Values.parameters(
                                "id", id,
                                "nome", updatedPlano.getNome_plano(),
                                "descricao", updatedPlano.getDescricao_plano(),
                                "duracao", updatedPlano.getDuracao_plano(),
                                "tipoAcomodacao", updatedPlano.getTipo_acomodacao(),
                                "preco", updatedPlano.getPreco_plano(),
                                "restricaoEspecie", updatedPlano.getRestricao_especie(),
                                "disponibilidade", updatedPlano.getDisponibilidade()
                        ));
                return null;
            });
        }
    }

    public static void delete(int id, Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (p:Plano) WHERE id(p) = $id DETACH DELETE p",
                        Values.parameters("id", id));
                return null;
            });
        }
    }

    public static boolean isPlanosInReserva(int id, Driver driver) {
        try (Session session = driver.session(SessionConfig.forDatabase("your_database"))) {
            return session.readTransaction(tx -> {
                Result result = tx.run("MATCH (r:Reserva)-[:TEM_PLANO]->(p:Plano) WHERE id(p) = $id RETURN count(r) > 0",
                        Values.parameters("id", id));
                return result.single().get(0).asBoolean();
            });
        }
    }
}
