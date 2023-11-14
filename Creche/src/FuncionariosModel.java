import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.driver.internal.value.NodeValue;

import java.util.HashSet;

public class FuncionariosModel {

    private static final String URI = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "your_password";

    public static void create(FuncionariosBean funcionario) {
        try (Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
             Session session = driver.session()) {

            session.writeTransaction(tx -> {
                tx.run("CREATE (f:Funcionario {nome: $nome, sobrenome: $sobrenome, dataNascimento: $dataNascimento, cpf: $cpf, telefone: $telefone, email: $email, dataContratacao: $dataContratacao, status: $status, observacoes: $observacoes})",
                        Values.parameters("nome", funcionario.getNomeFuncionario(),
                                "sobrenome", funcionario.getSobrenomeFuncionario(),
                                "dataNascimento", funcionario.getDataNascimento().toString(),
                                "cpf", funcionario.getCpfFuncionario(),
                                "telefone", funcionario.getTelefone(),
                                "email", funcionario.getEmail(),
                                "dataContratacao", funcionario.getDataContratacao().toString(),
                                "status", funcionario.getStatus(),
                                "observacoes", funcionario.getObservacoes()));
                return null;
            });

        } catch (Neo4jException e) {
            e.printStackTrace();
        }
    }

    public static HashSet<FuncionariosBean> listAll() {
        try (Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
             Session session = driver.session()) {

            return session.readTransaction(tx -> {
                Result result = tx.run("MATCH (f:Funcionario) RETURN f");
                HashSet<FuncionariosBean> funcionarios = new HashSet<>();
                while (result.hasNext()) {
                    Record record = result.next();
                    Node funcionarioNode = record.get("f").asNode();
                    FuncionariosBean funcionario = extractFuncionarioFromNode(funcionarioNode);
                    funcionarios.add(funcionario);
                }
                return funcionarios;
            });

        } catch (Neo4jException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public static FuncionariosBean findById(int cpf) {
        try (Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
             Session session = driver.session()) {

            return session.readTransaction(tx -> {
                Result result = tx.run("MATCH (f:Funcionario) WHERE f.cpf = $cpf RETURN f", Values.parameters("cpf", cpf));
                if (result.hasNext()) {
                    Record record = result.next();
                    Node funcionarioNode = record.get("f").asNode();
                    return extractFuncionarioFromNode(funcionarioNode);
                } else {
                    return null;
                }
            });

        } catch (Neo4jException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(FuncionariosBean existingFuncionario) {
        try (Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
             Session session = driver.session()) {

            session.writeTransaction(tx -> {
                tx.run("MATCH (f:Funcionario) WHERE f.cpf = $cpf " +
                                "SET f.nome = $nome, f.sobrenome = $sobrenome, f.dataNascimento = $dataNascimento, " +
                                "f.telefone = $telefone, f.email = $email, f.dataContratacao = $dataContratacao, " +
                                "f.status = $status, f.observacoes = $observacoes",
                        Values.parameters("cpf", existingFuncionario.getCpfFuncionario(),
                                "nome", existingFuncionario.getNomeFuncionario(),
                                "sobrenome", existingFuncionario.getSobrenomeFuncionario(),
                                "dataNascimento", existingFuncionario.getDataNascimento().toString(),
                                "telefone", existingFuncionario.getTelefone(),
                                "email", existingFuncionario.getEmail(),
                                "dataContratacao", existingFuncionario.getDataContratacao().toString(),
                                "status", existingFuncionario.getStatus(),
                                "observacoes", existingFuncionario.getObservacoes()));
                return null;
            });

        } catch (Neo4jException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int cpf) {
        try (Driver driver = GraphDatabase.driver(URI, AuthTokens.basic(USER, PASSWORD));
             Session session = driver.session()) {

            session.writeTransaction(tx -> {
                tx.run("MATCH (f:Funcionario) WHERE f.cpf = $cpf DETACH DELETE f", Values.parameters("cpf", cpf));
                return null;
            });

        } catch (Neo4jException e) {
            e.printStackTrace();
        }
    }

    private static FuncionariosBean extractFuncionarioFromNode(Node node) {
        return new FuncionariosBean(
                node.get("nome").asString(),
                node.get("sobrenome").asString(),
                Date.valueOf(node.get("dataNascimento").asString()),
                node.get("cpf").asInt(),
                node.get("telefone").asString(),
                node.get("email").asString(),
                Date.valueOf(node.get("dataContratacao").asString()),
                node.get("status").asString(),
                node.get("observacoes").asString()
        );
    }
}
