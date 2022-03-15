package market.menu;

import java.sql.Connection;

public class MenuVenda extends Menu {
    public MenuVenda(Connection connection) {
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
    }
}
