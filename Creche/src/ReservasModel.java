import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.StatementResult;
import org.neo4j.driver.Values;

import java.sql.SQLException;
import java.sql.Time;
import java.util.HashSet;
import java.time.LocalTime;

public class ReservasModel {

    public static void create(ReservasBean reserva, Driver driver) {
        try (Session session = driver.session()) {
            String query = "CREATE (r:Reserva {id_animal: $id_animal, id_plano: $id_plano, matricula: $matricula, data: $data, hora_entrada: $hora_entrada, hora_saida: $hora_saida, observacoes_reserva: $observacoes_reserva, status_reserva: $status_reserva})";
            session.run(query,
                    Values.parameters("id_animal", reserva.getId_animal(),
                            "id_plano", reserva.getId_plano(),
                            "matricula", reserva.getMatricula(),
                            "data", reserva.getData(),
                            "hora_entrada", reserva.getHora_entrada().toString(),
                            "hora_saida", reserva.getHora_saida().toString(),
                            "observacoes_reserva", reserva.getObservacoes_reserva(),
                            "status_reserva", reserva.getStatus_reserva()));
        }
    }

    public static HashSet<ReservasBean> listAll(Driver driver) {
        HashSet<ReservasBean> list = new HashSet<>();
        try (Session session = driver.session()) {
            String query = "MATCH (r:Reserva) RETURN r.id_animal, r.id_plano, r.matricula, r.data, r.hora_entrada, r.hora_saida, r.observacoes_reserva, r.status_reserva";
            StatementResult result = session.run(query);

            while (result.hasNext()) {
                var record = result.next();
                int id_animal = record.get("r.id_animal").asInt();
                int id_plano = record.get("r.id_plano").asInt();
                int matricula = record.get("r.matricula").asInt();
                String data = record.get("r.data").asString();
                LocalTime hora_entrada = LocalTime.parse(record.get("r.hora_entrada").asString());
                LocalTime hora_saida = LocalTime.parse(record.get("r.hora_saida").asString());
                String observacoes_reserva = record.get("r.observacoes_reserva").asString();
                String status_reserva = record.get("r.status_reserva").asString();

                ReservasBean reserva = new ReservasBean(id_animal, id_plano, matricula, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva);
                list.add(reserva);
            }
        }
        return list;
    }

    public static void listarAnimaisDisponiveis(Driver driver) {
    try (Session session = driver.session()) {
        String query = "MATCH (a:Animal) RETURN a.id_animal, a.nome_animal";
        StatementResult result = session.run(query);

        while (result.hasNext()) {
            var record = result.next();
            int id_animal = record.get("a.id_animal").asInt();
            String nome_animal = record.get("a.nome_animal").asString();
            System.out.println("ID: " + id_animal + ", Nome do Animal: " + nome_animal);
        }
    }
}

public static void listarFuncionariosDisponiveis(Driver driver) {
    try (Session session = driver.session()) {
        String query = "MATCH (f:Funcionario) RETURN f.matricula, f.nome_funcionario";
        StatementResult result = session.run(query);

        while (result.hasNext()) {
            var record = result.next();
            int matricula = record.get("f.matricula").asInt();
            String nome_funcionario = record.get("f.nome_funcionario").asString();
            System.out.println("Matricula: " + matricula + ", Nome do Funcionario: " + nome_funcionario);
        }
    }
}

    public static ReservasBean findById(int id_reserva, Driver driver) {
        ReservasBean reserva = null;
        try (Session session = driver.session()) {
            String query = "MATCH (r:Reserva) WHERE ID(r) = $id_reserva RETURN r.id_animal, r.id_plano, r.matricula, r.data, r.hora_entrada, r.hora_saida, r.observacoes_reserva, r.status_reserva";
            StatementResult result = session.run(query, Values.parameters("id_reserva", id_reserva));

            if (result.hasNext()) {
                var record = result.next();
                int id_animal = record.get("r.id_animal").asInt();
                int id_plano = record.get("r.id_plano").asInt();
                int matricula = record.get("r.matricula").asInt();
                String data = record.get("r.data").asString();
                LocalTime hora_entrada = LocalTime.parse(record.get("r.hora_entrada").asString());
                LocalTime hora_saida = LocalTime.parse(record.get("r.hora_saida").asString());
                String observacoes_reserva = record.get("r.observacoes_reserva").asString();
                String status_reserva = record.get("r.status_reserva").asString();

                reserva = new ReservasBean(id_animal, id_plano, matricula, data, hora_entrada, hora_saida, observacoes_reserva, status_reserva);
            }
        }
        return reserva;
    }

    public static void update(ReservasBean reserva, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (r:Reserva) WHERE ID(r) = $id_reserva SET r.id_animal = $id_animal, r.id_plano = $id_plano, r.matricula = $matricula, r.data = $data, r.hora_entrada = $hora_entrada, r.hora_saida = $hora_saida, r.observacoes_reserva = $observacoes_reserva, r.status_reserva = $status_reserva";
            session.run(query,
                    Values.parameters("id_reserva", reserva.getId_reserva(),
                            "id_animal", reserva.getId_animal(),
                            "id_plano", reserva.getId_plano(),
                            "matricula", reserva.getMatricula(),
                            "data", reserva.getData(),
                            "hora_entrada", reserva.getHora_entrada().toString(),
                            "hora_saida", reserva.getHora_saida().toString(),
                            "observacoes_reserva", reserva.getObservacoes_reserva(),
                            "status_reserva", reserva.getStatus_reserva()));
        }
    }

    public static void delete(int idReserva, Driver driver) {
        try (Session session = driver.session()) {
            String query = "MATCH (r:Reserva) WHERE ID(r) = $id_reserva DELETE r";
            session.run(query, Values.parameters("id_reserva", idReserva));
        }
    }
}
