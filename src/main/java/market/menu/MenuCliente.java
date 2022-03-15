package market.menu;

import market.model.dao.CustomerDao;
import market.model.dao.ProductDao;
import market.model.entities.Customer;
import market.model.entities.Product;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuCliente extends Menu {
    private CustomerDao customerDao;

    public MenuCliente(Connection connection) {
        super(connection);
        this.customerDao = new CustomerDao(connection);
    }

    public void menu() throws SQLException {
        limpaMenu();
        System.out.println("Qual operação deseja realizar?");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Listar cliente por ID");
        System.out.println("4 - Excluir cliente");
        System.out.println("0 - Voltar para o menu principal");

        int opcao = scanner.nextInt();

        escolheOpcao(opcao);
    }

    private void escolheOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                listarClientePorId();
                break;
            case 4:
                excluirCliente();
                break;
            case 0:
                (new MenuPrincipal(this.connection)).menu();
                break;
            default:
                System.out.println("Opção inválida");
                menu();
        }
    }

    private void excluirCliente() {

    }

    private void listarClientePorId() {
    }

    private void listarClientes() {
    }

    private void cadastrarCliente() throws SQLException {
        System.out.println("Digite o nome do cliente");
        String nome = scanner.next();
        System.out.println("Digite o CPF do cliente");
        String cpf = scanner.next();
        System.out.println("Digite o telefone do cliente");
        String telefone = scanner.next();
        Customer customer = new Customer(nome, cpf, telefone);
        customerDao.create(customer);
        System.out.println("Nome: " + customer.getName() + " CPF: " + customer.getCpf() + " Telefone: " + customer.getCellphone());
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }
}
