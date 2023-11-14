
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.driver.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */

public class AnimaisNeo4jModel {

    public static void create(AnimaisBean animal, Session session) {
        String query = "CREATE (a:Animal {nome: $nome, especie: $especie, raca: $raca, idade: $idade, sexo: $sexo, observacao: $observacao, cpfCliente: $cpfCliente}) RETURN id(a) as nodeId";
        StatementResult result = session.run(query, Values.parameters(
                "nome", animal.getNome_animal(),
                "especie", animal.getEspecie_animal(),
                "raca", animal.getRaca(),
                "idade", animal.getIdade(),
                "sexo", animal.getSexo_animal(),
                "observacao", animal.getObservacao(),
                "cpfCliente", animal.getCpf_cliente()
        ));

        if (result.hasNext()) {
            Record record = result.next();
            Long nodeId = record.get("nodeId").asLong();
            animal.setNeo4jNodeId(nodeId);
        }
    }

    public static HashSet<AnimaisBean> listAllFromNeo4j(Session session) {
        HashSet<AnimaisBean> list = new HashSet<>();
        String query = "MATCH (a:Animal) RETURN a.nome, a.especie, a.raca, a.idade, a.sexo, a.observacao, a.cpfCliente, id(a) as nodeId";
        StatementResult result = session.run(query);

        while (result.hasNext()) {
            Record record = result.next();
            String nome = record.get("a.nome").asString();
            String especie = record.get("a.especie").asString();
            String raca = record.get("a.raca").asString();
            int idade = record.get("a.idade").asInt();
            String sexo = record.get("a.sexo").asString();
            String observacao = record.get("a.observacao").asString();
            int cpfCliente = record.get("a.cpfCliente").asInt();
            Long nodeId = record.get("nodeId").asLong();

            AnimaisBean animal = new AnimaisBean(nome, especie, raca, idade, sexo, observacao, cpfCliente, nodeId);
            list.add(animal);
        }

        return list;
    }

    public static AnimaisBean findByIdFromNeo4j(int id_animal, Session session) {
        AnimaisBean animal = null;
        String query = "MATCH (a:Animal) WHERE id(a) = $id RETURN a.nome, a.especie, a.raca, a.idade, a.sexo, a.observacao, a.cpfCliente, id(a) as nodeId";
        StatementResult result = session.run(query, Values.parameters("id", id_animal));

        if (result.hasNext()) {
            Record record = result.next();
            String nome = record.get("a.nome").asString();
            String especie = record.get("a.especie").asString();
            String raca = record.get("a.raca").asString();
            int idade = record.get("a.idade").asInt();
            String sexo = record.get("a.sexo").asString();
            String observacao = record.get("a.observacao").asString();
            int cpfCliente = record.get("a.cpfCliente").asInt();
            Long nodeId = record.get("nodeId").asLong();

            animal = new AnimaisBean(nome, especie, raca, idade, sexo, observacao, cpfCliente, nodeId);
        }

        return animal;
    }

    public static void updateInNeo4j(AnimaisBean existingAnimais, Session session) {
        String query = "MATCH (a:Animal) WHERE id(a) = $id SET a.nome = $nome, a.especie = $especie, a.raca = $raca, a.idade = $idade, a.sexo = $sexo, a.observacao = $observacao, a.cpfCliente = $cpfCliente";
        session.run(query, Values.parameters(
                "id", existingAnimais.getNeo4jNodeId(),
                "nome", existingAnimais.getNome_animal(),
                "especie", existingAnimais.getEspecie_animal(),
                "raca", existingAnimais.getRaca(),
                "idade", existingAnimais.getIdade(),
                "sexo", existingAnimais.getSexo_animal(),
                "observacao", existingAnimais.getObservacao(),
                "cpfCliente", existingAnimais.getCpf_cliente()
        ));
    }

    public static void deleteFromNeo4j(int id_animal, Session session) {
        String query = "MATCH (a:Animal) WHERE id(a) = $id DETACH DELETE a";
        session.run(query, Values.parameters("id", id_animal));
    }
    
    
    public static boolean isAnimaisInReserva(int id_animal, GraphDatabaseService graphDb) {
    try (Transaction tx = graphDb.beginTx()) {
        Result result = graphDb.execute("MATCH (:Reserva)-[:CONTEM]->(a:Animal {id_animal: {id_animal}}) RETURN COUNT(a) > 0 AS exists", 
                Collections.singletonMap("id_animal", id_animal));
        
        return (boolean) result.next().get("exists");
    }
}

}