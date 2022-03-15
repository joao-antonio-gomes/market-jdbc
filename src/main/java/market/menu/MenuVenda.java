package market.menu;

import market.model.dao.CustomerDao;
import market.model.dao.OrderDao;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuVenda extends Menu {
    private OrderDao orderDao;

    public MenuVenda(Connection connection) {
        super(connection);
        this.orderDao = new OrderDao(connection);
    }

    public void menu() throws SQLException {
        limpaMenu();
        System.out.println("Qual operação deseja realizar?");
        System.out.println("1 - Cadastrar venda");
        System.out.println("2 - Listar vendas");
        System.out.println("3 - Listar venda por ID");
        System.out.println("4 - Excluir venda");
        System.out.println("0 - Voltar para o menu principal");

        int opcao = scanner.nextInt();

        escolheOpcao(opcao);
    }

    private void escolheOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                cadastraVenda();
                break;
            case 2:
                listaVendas();
                break;
            case 3:
                listaVendaPorId();
                break;
            case 4:
                excluiVenda();
                break;
            case 0:
                (new MenuPrincipal(this.connection)).menu();
                break;
            default:
                System.out.println("Opção inválida!");
                menu();
        }
    }

    private void menuPrincipal() {
    }

    private void excluiVenda() {
    }

    private void listaVendas() {
    }

    private void listaVendaPorId() {
    }

    private void cadastraVenda() {

    }


}
