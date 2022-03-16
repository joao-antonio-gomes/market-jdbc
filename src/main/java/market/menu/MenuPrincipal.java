package market.menu;

import market.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuPrincipal extends Menu {

    public void menu() throws SQLException {
        Menu.limpaMenu();
        System.out.println("Qual menu deseja acessar?");
        System.out.println("1 - Menu de Clientes");
        System.out.println("2 - Menu de Produtos");
        System.out.println("3 - Menu de Vendas");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                new MenuCliente().menu();
                break;
            case 2:
                new MenuProduto().menu();
                break;
            case 3:
                new MenuVenda().menu();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
                this.menu();
                break;
        }
    }
}
