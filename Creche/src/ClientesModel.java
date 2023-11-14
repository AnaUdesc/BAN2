import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ana Clara
 */


public class ClientesModel {

    public static void create(ClientesBean a, Driver driver) {
        try (Session session = driver.session()) {
            String query = "CREATE (c:Cliente {cpf_cliente: $cpf_cliente, nome_cliente: $nome_cliente, sobrenome_cliente: $sobrenome_cliente, email: $email, rua: $rua, cidade: $cidade, estado: $estado, pais: $pais, telefone: $telefone})";
            session.run(query, parameters(
                    "cpf_cliente", a.getCpf_cliente(),
                    "nome_cliente", a.getNome_cliente(),
                    "sobrenome_cliente", a.getSobrenome_cliente(),
                    "email", a.getEmail(),
                    "rua", a.getRua(),
                    "cidade", a.getCidade(),
                    "estado", a.getEstado(),
                    "pais", a.getPais(),
                    "telefone", a.getTelefone()
            ));
        }
    }

    public static void update(ClientesBean existingClientes, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (c:Cliente {cpf_cliente: $cpf_cliente}) SET c.nome_cliente = $nome_cliente, c.sobrenome_cliente = $sobrenome_cliente, c.email = $email, c.rua = $rua, c.cidade = $cidade, c.estado = $estado, c.pais = $pais, c.telefone = $telefone";
            session.run(query, parameters(
                    "cpf_cliente", existingClientes.getCpf_cliente(),
                    "nome_cliente", existingClientes.getNome_cliente(),
                    "sobrenome_cliente", existingClientes.getSobrenome_cliente(),
                    "email", existingClientes.getEmail(),
                    "rua", existingClientes.getRua(),
                    "cidade", existingClientes.getCidade(),
                    "estado", existingClientes.getEstado(),
                    "pais", existingClientes.getPais(),
                    "telefone", existingClientes.getTelefone()
            ));
        }
    }

    public static void delete(int cpf_cliente, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (c:Cliente {cpf_cliente: $cpf_cliente}) DETACH DELETE c";
            session.run(query, parameters("cpf_cliente", cpf_cliente));
        }
    }

    public static ClientesBean findById(int cpf_cliente, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (c:Cliente {cpf_cliente: $cpf_cliente}) RETURN c";
            Result result = session.run(query, parameters("cpf_cliente", cpf_cliente));

            if (result.hasNext()) {
                Record record = result.next();
                Node clienteNode = record.get("c").asNode();

                return new ClientesBean(
                        clienteNode.get("cpf_cliente").asInt(),
                        clienteNode.get("nome_cliente").asString(),
                        clienteNode.get("sobrenome_cliente").asString(),
                        clienteNode.get("email").asString(),
                        clienteNode.get("rua").asString(),
                        clienteNode.get("cidade").asString(),
                        clienteNode.get("estado").asString(),
                        clienteNode.get("pais").asString(),
                        clienteNode.get("telefone").asString()
                );
            }
        }

        return null;
    }

    public static boolean isClientesInAnimais(int cpf_cliente, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (c:Cliente)-[:POSSUI]->(a:Animal) WHERE c.cpf_cliente = $cpf_cliente RETURN COUNT(a) > 0 AS hasAnimais";
            Result result = session.run(query, parameters("cpf_cliente", cpf_cliente));

            if (result.hasNext()) {
                Record record = result.next();
                return record.get("hasAnimais").asBoolean();
            }
        }

        return false;
    }
}
