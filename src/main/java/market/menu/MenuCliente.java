package market.menu;

import java.sql.Connection;

public class MenuCliente extends Menu {
    public MenuCliente(Connection connection) {
        super(connection);
    }

    public void menu() {
        limpaMenu();
        System.out.println("Qual operação deseja realizar?");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Listar cliente por ID");
        System.out.println("4 - Excluir cliente");
        System.out.println("0 - Voltar para o menu principal");
    }
}
