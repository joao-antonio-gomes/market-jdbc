package market.menu;

import market.connection.ConnectionFactory;
import market.model.dao.CustomerDao;
import market.model.dao.OrderDao;
import market.model.dao.ProductDao;
import market.model.entities.Customer;
import market.model.entities.Order;
import market.model.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuVenda extends Menu {
    private OrderDao orderDao;

    public MenuVenda() throws SQLException {
        this.orderDao = new OrderDao(new ConnectionFactory().openConnection());
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
                new MenuPrincipal().menu();
                break;
            default:
                System.out.println("Opção inválida!");
                menu();
        }
    }

    private void excluiVenda() throws SQLException {
        System.out.println("Digite o ID da venda: ");
        int id = scanner.nextInt();
        orderDao.deleteById(id);
        System.out.println("Venda excluida com sucesso!");
        System.out.println("Voltando para o menu principal, aperte ENTER para continuar...");
        scanner.nextLine();
        new MenuPrincipal().menu();
    }

    private void listaVendas() throws SQLException {
        System.out.println("Listando vendas...");
        ResultSet resultSet = orderDao.listAll();
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id") + " Cliente: " + resultSet.getString("customer_id") +
                    " Produto: " + resultSet.getString("product_id") + "Data: " + resultSet.getString("order_date"));
        }
        System.out.println("Voltando para o menu principal, aperte ENTER para continuar...");
        scanner.nextLine();
        scanner.nextLine();
        new MenuPrincipal().menu();
    }

    private void listaVendaPorId() throws SQLException {
        System.out.println("Digite o ID da venda: ");
        int id = scanner.nextInt();
        ResultSet resultSetOrder = orderDao.listById(id);

        while (resultSetOrder.next()) {

            ResultSet resultSetProduct = new ProductDao(new ConnectionFactory().openConnection()).listById(id);
            ResultSet resultSetCustomer = new CustomerDao(new ConnectionFactory().openConnection()).listById(id);

            while (resultSetProduct.next()) {
                System.out.println("ID: " + resultSetProduct.getInt("id") + " Nome: " + resultSetProduct.getString("name") + " Preço: " + resultSetProduct.getDouble("price"));
            }
            while (resultSetCustomer.next()) {
                System.out.println("ID: " + resultSetCustomer.getInt("id") + " Nome: " + resultSetCustomer.getString("name") + " CPF: " + resultSetCustomer.getString("cpf"));
            }
        }
        
        System.out.println("Voltando para o menu principal, aperte ENTER para continuar...");
        scanner.nextLine();
        new MenuPrincipal().menu();
    }

    private void cadastraVenda() throws SQLException {
        System.out.println("Digite o ID do cliente: ");
        int idCustomer = scanner.nextInt();
        System.out.println("Digite o ID do produto: ");
        int idProduct = scanner.nextInt();

        ResultSet resultSetProduct = new ProductDao(new ConnectionFactory().openConnection()).listById(idProduct);
        ResultSet resultSetCustomer = new CustomerDao(new ConnectionFactory().openConnection()).listById(idCustomer);
        while (resultSetProduct.next()) {
            System.out.println("ID: " + resultSetProduct.getInt("id") + " Nome: " + resultSetProduct.getString("name") + " Preço: " + resultSetProduct.getDouble("price"));
        }
        while (resultSetCustomer.next()) {
            System.out.println("ID: " + resultSetCustomer.getInt("id") + " Nome: " + resultSetCustomer.getString("name") + " CPF: " + resultSetCustomer.getString("cpf"));
        }
        System.out.println("Confirma o cadastro da venda? (S/N)");
        String confirma = scanner.next();
        if (confirma.equalsIgnoreCase("S")) {
            new Order(idCustomer, idProduct);
            orderDao.create(new Order(idCustomer, idProduct));
            System.out.println("Venda cadastrada com sucesso!");
        }
        System.out.println("Voltando para o menu principal, aperte ENTER para continuar...");
        scanner.nextLine();
        new MenuPrincipal().menu();
    }
}
