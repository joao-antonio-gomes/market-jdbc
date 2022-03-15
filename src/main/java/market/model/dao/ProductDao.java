package market.model.dao;

import market.connection.ConnectionFactory;
import market.model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao extends BaseDao {
    private Connection connection;

    public ProductDao(Connection connection) {
        super(connection);
        this.tableName = "product";
        this.idName = "id";
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
}
