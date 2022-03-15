package market.model.dao;

import market.connection.ConnectionFactory;
import market.model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    private Connection connection;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public void listAll() throws SQLException {
        String sql = "SELECT id, name, price FROM product";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " - " + resultSet.getDouble("price"));
        }
    }

    public void listById(int id) throws SQLException {
        String sql = "SELECT id, name, price FROM product WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name") + " - " + resultSet.getDouble("price"));
        }
    }

    public boolean create(Product product) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO product (name, price) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        System.out.println("Produto inserido com sucesso.");
        return true;
    }

    public boolean delete(int id) {
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
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
