package market;

import market.connection.ConnectionFactory;
import market.menu.MenuPrincipal;

import java.sql.*;

public class MarketApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connection = connectionFactory.openConnection();

            connection.setAutoCommit(false);

            (new MenuPrincipal(connection)).menu();

            connectionFactory.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Conexão ao banco falhou.");
            connection.rollback();
        }
    }
}
