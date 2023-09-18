import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;

public class FuncionariosController {
    public void createFuncionarios(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo Funcionário:");

        System.out.print("Nome: ");
        String nome_funcionario = input.nextLine(); 
        System.out.print("Sobrenome: ");
        String sobrenome_funcionario = input.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        Date data_nascimento = null;
        boolean dataValida = false;

        while (!dataValida) {
            String dataStr = input.next();

            try {
                // Tente fazer o parsing da data
                java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
                data_nascimento = new Date(utilDate.getTime()); // Converta para java.sql.Date

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(utilDate);
                if (dataStr.equals(formattedDate)) {
                    dataValida = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }

        System.out.print("CPF: ");
        int cpf_funcionario = input.nextInt();
        input.nextLine(); // Limpar o buffer do teclado 
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("E-mail: ");
        String email = input.nextLine();
        System.out.print("Data de Contratação (dd/MM/yyyy): ");
        Date data_contratacao = null;
        boolean dataValida2 = false;

        while (!dataValida2) {
            String dataStr = input.nextLine();

            try {
                // Tente fazer o parsing da data
                java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);
                data_contratacao = new Date(utilDate.getTime()); // Converta para java.sql.Date

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(utilDate);
                if (dataStr.equals(formattedDate)) {
                    dataValida2 = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }

        System.out.print("Status: ");
        String status = input.nextLine();
        System.out.print("Observacoes: ");
        String observacoes = input.nextLine();
        
        FuncionariosBean funcionario = new FuncionariosBean(nome_funcionario, sobrenome_funcionario, data_nascimento, cpf_funcionario, telefone, email, data_contratacao, status, observacoes);
        FuncionariosModel.create(funcionario, con);

        System.out.println("Funcionário criado com sucesso!");
    }

    public void listarFuncionarios(Connection con) throws SQLException {
        HashSet<FuncionariosBean> all = FuncionariosModel.listAll(con);
        Iterator<FuncionariosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void updateFuncionarios(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a Matricula do Funcionário que deseja atualizar: ");
        int matricula = input.nextInt();
        input.nextLine(); // Limpar o buffer do teclado

        FuncionariosBean existingFuncionario = FuncionariosModel.findById(matricula, con);

        if (existingFuncionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Parâmetros atuais: " + existingFuncionario.toString());

        System.out.print("Novo Nome: ");
        String novoNome = input.nextLine();
        existingFuncionario.setNomeFuncionario(novoNome);
        System.out.print("Novo Sobrenome: ");
        String novoSobrenome = input.nextLine();
        existingFuncionario.setSobrenomeFuncionario(novoSobrenome);
        System.out.print("Nova Data de Nascimento: ");
        java.util.Date data_nascimento = null;
        boolean dataValida = false;

        while (!dataValida) {
            String dataStr = input.nextLine();

            try {
                // Tente fazer o parsing da data
                data_nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(data_nascimento);
                if (dataStr.equals(formattedDate)) {
                    dataValida = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }

        existingFuncionario.setDataNascimento(data_nascimento);
        System.out.print("Novo CPF Funcionario: ");
        existingFuncionario.setCpfFuncionario(Integer.parseInt(input.nextLine()));
        System.out.print("Novo Telefone Funcionario: ");
        existingFuncionario.setTelefone(input.nextLine());
        System.out.print("Nova Email Funcionario: ");
        existingFuncionario.setEmail(input.nextLine());        
        System.out.print("Nova Data de Contratacao: ");
        java.util.Date data_contratacao = null;
        boolean dataValida2 = false;

        while (!dataValida2) {
            String dataStr = input.next();

            try {
                // Tente fazer o parsing da data
                data_contratacao = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

                // Verifique se a data está no formato correto
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(data_contratacao);
                if (dataStr.equals(formattedDate)) {
                    dataValida2 = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }
        
        System.out.print("Novo Status Funcionario: ");
        existingFuncionario.setStatus(input.next());
        System.out.print("Novas Observacoes Funcionario: ");
        existingFuncionario.setObservacoes(input.next());
        
        // Repita o processo para os outros campos que deseja atualizar

        FuncionariosModel.update(existingFuncionario, con);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    public void deleteFuncionarios(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a Matricula do Funcionário que deseja excluir: ");
        int matricula = input.nextInt();

        FuncionariosBean existingFuncionario = FuncionariosModel.findById(matricula, con);

        if (existingFuncionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        FuncionariosModel.delete(matricula, con);
        System.out.println("Funcionário excluído com sucesso!");
    }
}
