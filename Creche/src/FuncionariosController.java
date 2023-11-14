import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Iterator;

public class FuncionariosController {
    public void createFuncionarios() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para cadastrar um novo Funcionário:");

        System.out.print("Nome: ");
        String nome_funcionario = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome_funcionario = input.nextLine();
        System.out.print("Data de Nascimento (dd/MM/yyyy): ");
        Date data_nascimento = readDate(input);

        System.out.print("CPF: ");
        int cpf_funcionario = input.nextInt();
        input.nextLine(); // Limpar o buffer do teclado
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("E-mail: ");
        String email = input.nextLine();
        System.out.print("Data de Contratação (dd/MM/yyyy): ");
        Date data_contratacao = readDate(input);

        System.out.print("Status: ");
        String status = input.nextLine();
        System.out.print("Observacoes: ");
        String observacoes = input.nextLine();

        FuncionariosBean funcionario = new FuncionariosBean(nome_funcionario, sobrenome_funcionario, data_nascimento, cpf_funcionario, telefone, email, data_contratacao, status, observacoes);
        FuncionariosModel.create(funcionario);

        System.out.println("Funcionário criado com sucesso!");
    }

    public void listarFuncionarios() {
        HashSet<FuncionariosBean> all = FuncionariosModel.listAll();
        Iterator<FuncionariosBean> it = all.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void updateFuncionarios() {
        System.out.println("Lista de Funcionarios Disponíveis:");
        FuncionariosModel.listarFuncionariosDisponiveis();
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a Matricula do Funcionário que deseja atualizar: ");
        int matricula = input.nextInt();
        input.nextLine(); // Limpar o buffer do teclado

        FuncionariosBean existingFuncionario = FuncionariosModel.findById(matricula);

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
        System.out.print("Nova Data de Nascimento (dd/MM/yyyy): ");
        existingFuncionario.setDataNascimento(readDate(input));

        System.out.print("Novo CPF Funcionario: ");
        existingFuncionario.setCpfFuncionario(Integer.parseInt(input.nextLine()));
        System.out.print("Novo Telefone Funcionario: ");
        existingFuncionario.setTelefone(input.nextLine());
        System.out.print("Nova Email Funcionario: ");
        existingFuncionario.setEmail(input.nextLine());
        System.out.print("Nova Data de Contratação (dd/MM/yyyy): ");
        existingFuncionario.setDataContratacao(readDate(input));

        System.out.print("Novo Status Funcionario: ");
        existingFuncionario.setStatus(input.next());
        System.out.print("Novas Observacoes Funcionario: ");
        existingFuncionario.setObservacoes(input.next());

        FuncionariosModel.update(existingFuncionario);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    public void deleteFuncionarios() {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite a Matricula do Funcionário que deseja excluir: ");
        int matricula = input.nextInt();

        FuncionariosBean existingFuncionario = FuncionariosModel.findById(matricula);

        if (existingFuncionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        // Verifica se o funcionário está associado a alguma reserva
        if (FuncionariosModel.isFuncionariosInReserva(matricula)) {
            System.out.println("Não é possível excluir o funcionário, pois ele está associado a uma reserva.");
            return;
        }

        FuncionariosModel.delete(matricula);
        System.out.println("Funcionário excluído com sucesso!");
    }

    private Date readDate(Scanner input) {
        Date date = null;
        boolean dataValida = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        while (!dataValida) {
            String dataStr = input.nextLine();

            try {
                // Tente fazer o parsing da data
                java.util.Date utilDate = dateFormat.parse(dataStr);
                date = new Date(utilDate.getTime()); // Converta para java.sql.Date

                // Verifique se a data está no formato correto
                if (dataStr.equals(dateFormat.format(utilDate))) {
                    dataValida = true;
                } else {
                    System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
                }
            } catch (ParseException e) {
                System.out.print("Formato de data inválido. Tente novamente (dd/MM/yyyy): ");
            }
        }
        return date;
    }
}
