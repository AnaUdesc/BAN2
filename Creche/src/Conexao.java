import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Config;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class Conexao {
    private Driver driver;

    public Conexao() {
        String uri = "bolt://localhost:7687";  // URI do servidor Neo4j
        String user = "neo4j";  // Nome de usuário
        String senha = "senha";  // Senha

        Config config = Config.builder().build();
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, senha), config);
    }

    public Session getSession() {
        return driver.session();
    }

    public void close() {
        driver.close();
    }
}
