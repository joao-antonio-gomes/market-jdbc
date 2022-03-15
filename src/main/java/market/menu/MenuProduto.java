package market.menu;

import market.model.dao.ProductDao;
import market.model.entities.Product;

import java.sql.Connection;

public class MenuProduto extends Menu {
    public MenuProduto(Connection connection) {
        super(connection);
    }

    public void menu() {
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

    private void escolheOpcao(int opcao) {
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

    private void excluirProduto() {
    }

    private void listarProdutoPorId() {
    }

    private void listarProdutos() {
    }

    private void cadastrarProduto() {
        System.out.println("Digite o nome do produto:");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.println("Digite o preço do produto:");
        double preco = scanner.nextDouble();

        Product product = new Product(nome, preco);
        ProductDao productDao = new ProductDao(this.connection);
        productDao.create(product);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("Produto: " + product.getName() + " - Preço: " + product.getPrice());
        System.out.println("Pressione ENTER para voltar ao menu principal...");
        scanner.next();
        (new MenuPrincipal(this.connection)).menu();
    }
}
