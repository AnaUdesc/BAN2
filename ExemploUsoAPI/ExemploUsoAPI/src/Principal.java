/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Ana
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;
        do{
            op = menu();
            try {
                switch (op) {
                    case 1: new ClientesController().createClientes(con);
                            break;
                    case 2: new MedicosController().createMedico(con);
                            break;
                    case 3: new ClientesController().listarClientes(con);
                            break;
                    case 4: new MedicosController().listarMedicos(con);
                            break;
                    case 5: new MedicosController().listarMedicosAmbulatorios(con);
                            break;
                }
            }catch(SQLException ex) {
                //ex.printStackTrace();
                System.out.println(ex.getMessage());
                continue;
            }
        } while(op>0 && op<6);  
        con.close();
    }    
    
    private static int menu() {
        System.out.println("");
        System.out.println("Informe o numero da opcao que desejas executar: ");
        System.out.println("1 - Inserir um novo Cliente");
        System.out.println("2 - Inserir um novo medico");
        System.out.println("3 - Exibir todos os Clientes");
        System.out.println("4 - Exibir todos os medicos");
        System.out.println("5 - Exibir todos os Animais e seus respectivos Tutores");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opcao: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
