import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class FuncionariosModel {

    public static void create(FuncionariosBean funcionario, Connection con) throws SQLException {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("INSERT INTO funcionarios (nome_funcionario, sobrenome_funcionario, data_nascimento, cpf_funcionario, telefone, email, data_contratacao, status, observacoes) VALUES (?,?,?,?,?,?,?,?,?)");
            //st.setInt(1, funcionario.getMatriculaFuncionario());
            st.setString(1, funcionario.getNomeFuncionario());
            st.setString(2, funcionario.getSobrenomeFuncionario());
            st.setDate(3, funcionario.getDataNascimento());
            st.setInt(4, funcionario.getCpfFuncionario());
            st.setString(5, funcionario.getTelefone());
            st.setString(6, funcionario.getEmail());
            st.setDate(7, funcionario.getDataContratacao());
            st.setString(8, funcionario.getStatus());
            st.setString(9, funcionario.getObservacoes());

            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public static HashSet<FuncionariosBean> listAll(Connection con) throws SQLException {
        Statement st = null;
        HashSet<FuncionariosBean> list = new HashSet<>();
        try {
            st = con.createStatement();
            String sql = "SELECT nome_funcionario, sobrenome_funcionario, data_nascimento, cpf_funcionario,telefone, email, data_contratacao, status, observacoes FROM funcionarios";
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                FuncionariosBean funcionario = new FuncionariosBean(
                        //result.getInt("matricula"),
                        result.getString("nome_funcionario"),
                        result.getString("sobrenome_funcionario"),
                        result.getDate("data_nascimento"),
                        result.getInt("cpf_funcionario"),
                        result.getString("telefone"),
                        result.getString("email"),
                        result.getDate("data_contratacao"),
                        result.getString("status"),
                        result.getString("observacoes")
                );
                list.add(funcionario);
            }
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return list;
    }

    public static FuncionariosBean findById(int matricula, Connection con) throws SQLException {
        FuncionariosBean funcionario = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM funcionarios WHERE matricula = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, matricula);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = new FuncionariosBean(
                         //rs.getInt("matricula"),
                        rs.getString("nome_funcionario"),
                        rs.getString("sobrenome_funcionario"),
                        rs.getDate("data_nascimento"),
                        rs.getInt("cpf_funcionario"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getDate("data_contratacao"),
                        rs.getString("status"),
                        rs.getString("observacoes")
                );
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return funcionario;
    }

    public static void update(FuncionariosBean existingFuncionario, Connection con) throws SQLException {
        PreparedStatement st = null;

        try {
            String query = "UPDATE funcionarios SET nome_funcionario = ?, sobrenome_funcionario = ?, data_nascimento = ?, cpf_funcionario = ?, telefone = ?, email = ?, data_contratacao = ?, status = ?, observacoes = ? WHERE matricula = ?";
            st = con.prepareStatement(query);
            st.setString(1, existingFuncionario.getNomeFuncionario());
            st.setString(2, existingFuncionario.getSobrenomeFuncionario());
            st.setDate(3, existingFuncionario.getDataNascimento());
            st.setInt(4, existingFuncionario.getCpfFuncionario());
            st.setString(5, existingFuncionario.getTelefone());
            st.setString(6, existingFuncionario.getEmail());
            st.setDate(7, existingFuncionario.getDataContratacao());
            st.setString(8, existingFuncionario.getStatus());
            st.setString(9, existingFuncionario.getObservacoes());
            st.setInt(10, existingFuncionario.getMatriculaFuncionario());

            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public static void delete(int matricula, Connection con) throws SQLException {
        PreparedStatement st = null;

        try {
            String query = "DELETE FROM funcionarios WHERE matricula = ?";
            st = con.prepareStatement(query);
            st.setInt(1, matricula);

            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
}
