package market.menu;

import market.model.dao.ProductDao;
import market.model.entities.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuProduto extends Menu {
    private ProductDao productDao;

    public MenuProduto(Connection connection) {
        super(connection);
        this.productDao = new ProductDao(connection);
    }

    public void menu() throws SQLException {
        limpaMenu();
        System.out.println("Qual operação deseja realizar?");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Listar produto por ID");
        System.out.println("4 - Excluir produto");
        System.out.println("0 - Voltar para o menu principal");
        
        int opcao = scanner.nextInt();
        
        escolheOpcao(opcao);
    }

    private void escolheOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                listarProdutoPorId();
                break;
            case 4:
                excluirProduto();
                break;
            case 0:
                (new MenuPrincipal(this.connection)).menu();
                break;
        }
    }

    private void excluirProduto() throws SQLException {
        System.out.println("Digite o ID do produto que deseja excluir:");
        int id = scanner.nextInt();
        productDao.deleteById(id);
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }

    private void listarProdutoPorId() throws SQLException {
        System.out.println("Digite o ID do produto:");
        int id = scanner.nextInt();
        ResultSet resultSet = productDao.listById(id);
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " - " + resultSet.getDouble("price"));
        }
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }

    private void listarProdutos() throws SQLException {
        ResultSet resultSet = productDao.listAll();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " - " + resultSet.getDouble("price"));
        }
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }

    private void cadastrarProduto() throws SQLException {
        System.out.println("Digite o nome do produto:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.println("Digite o preço do produto:");
        double preco = scanner.nextDouble();

        Product product = new Product(nome, preco);
        productDao.create(product);
        System.out.println("Produto: " + product.getName() + " - Preço: " + product.getPrice());
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }
}
