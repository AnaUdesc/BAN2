import java.sql.Connection;
import java.text.ParseException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws Exception {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;
        do {
            op = menu();
            try {
                switch (op) {
                    case 1:
                        new ClientesController().createClientes(con);
                        break;
                    case 2:
                        new ClientesController().listarClientes(con);
                        break;
                    case 3:
                        new ClientesController().updateClientes(con);
                        break;
                    case 4:
                        new ClientesController().deleteClientes(con);
                        break;
                    case 5:
                        new AnimaisController().createAnimais(con);
                        break;
                    case 6:
                        new AnimaisController().listarAnimais(con);
                        break;
                    case 7:
                        new AnimaisController().updateAnimais(con);
                        break;
                    case 8:
                        new AnimaisController().deleteAnimais(con);
                        break;
                    case 9:
                        new PlanosController().createPlanos(con);
                        break;
                    case 10:
                        new PlanosController().listarPlanos(con);
                        break;
                    case 11:
                        new PlanosController().updatePlano(con);
                        break;
                    case 12:
                        new PlanosController().deletePlano(con);
                        break;
                    case 13:
                        new FuncionariosController().createFuncionarios(con);
                        break;
                    case 14:
                        new FuncionariosController().listarFuncionarios(con);
                        break;
                    case 15:
                        new FuncionariosController().updateFuncionarios(con);
                        break;
                    case 16:
                        new FuncionariosController().deleteFuncionarios(con);
                        break;    
                    case 17:
                        new ReservasController().createReservas(con);
                        break;
                    case 18:
                        new ReservasController().listarReservas(con);
                        break;
                    case 19:
                        new ReservasController().updateReservas(con);
                        break;
                    case 20:
                        new ReservasController().deleteReservas(con);
                        break;
                    case 21:
                    {
                        String diretorio = null;
                        GerarRelatorio.gerarRelatorioReservas(con, diretorio);
                    }
                        break;
                    case 22:
                    {
                        String diretorio = null;
                        GerarRelatorio.gerarRelatorioReservasAnimal(con, diretorio);
                    }
                        break;
                    case 23:
                    {
                       String diretorio = null;
                       GerarRelatorio.gerarRelatorioAnimaisPorPlanos(con, diretorio);
                    }                    
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        } while (op > 0 && op < 24);
        con.close();
    }

    private static int menu() {
        System.out.println("");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 - Inserir um novo Cliente");
        System.out.println("2 - Exibir todos os Clientes");
        System.out.println("3 - Alterar informações de Clientes");
        System.out.println("4 - Excluir informações de Clientes");
        System.out.println("5 - Inserir um novo Animal");
        System.out.println("6 - Exibir todos os Animais");
        System.out.println("7 - Alterar informações de Animais");
        System.out.println("8 - Excluir informação de Animais");
        System.out.println("9 - Inserir um novo Plano");
        System.out.println("10 - Exibir todos os Planos");
        System.out.println("11 - Alterar informação de Plano");
        System.out.println("12 - Excluir informação de Plano");
        System.out.println("13 - Inserir um novo Funcionario");
        System.out.println("14 - Exibir todos os Funcionarios");
        System.out.println("15 - Alterar informação de um Funcionario");
        System.out.println("16 - Excluir informação de um Funcionario");
        System.out.println("17 - Inserir uma nova Reserva");
        System.out.println("18 - Exibir todas as Reservas");
        System.out.println("19 - Alterar informação de Reserva");
        System.out.println("20 - Excluir informação de Reserva");
        System.out.println("21 - Gerar Relatórios Funcionarios");
        System.out.println("22 - Gerar Relatório Historico Reservas");
        System.out.println("23 - Gerar Relatório Animais por Planos");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    /*private static void gerarRelatorio(Connection con) {
        try {
            GerarRelatorio.gerarRelatorio(con);
            System.out.println("Relatório gerado com sucesso!");
        } catch (Exception ex) {
            System.out.println("Erro ao gerar o relatório: " + ex.getMessage());
        }
    }*/
}