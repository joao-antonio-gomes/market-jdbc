package market;

import market.connection.ConnectionFactory;

import java.sql.*;

public class MarketApp {
    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.openConnection();

            connectionFactory.closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Conexão ao banco falhou.");
        }
    }

    private static void listProducts(Connection connection) throws SQLException {
        String sql = "SELECT id, name, price FROM product";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " - " + resultSet.getDouble("price"));
        }
    }

    private static boolean createProduct(Connection connection, String name, double price) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO product (name, price) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        System.out.println("Produto inserido com sucesso.");
        return true;
    }

    private static boolean deleteProduct(Connection connection, int id) {
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM product WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int updateCount = statement.getUpdateCount();
            if (updateCount == 1) {
                System.out.println("Produto excluído com sucesso.");
            }
            if (updateCount == 0) {
                System.out.println("Produto não encontrado. Nenhum produto excluído.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
