package market.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcConnectionApp {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite seu login:");
            String user = scanner.next();
            System.out.println("Digite sua senha:");
            String password = scanner.next();
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/market?useTimezone=true&serverTimezone=UTC",
                    user, password);
            System.out.println("Conectado ao banco com sucesso!");
        } catch (SQLException e) {
            System.out.println("Conexão ao banco falhou.");
        }
    }
}
