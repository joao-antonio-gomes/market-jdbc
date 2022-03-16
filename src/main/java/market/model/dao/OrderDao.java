package market.model.dao;

import market.model.entities.Order;
import market.model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDao extends  BaseDao{
    public OrderDao(Connection connection) {
        super(connection);
        this.tableName = "order";
        this.idName = "id";
    }

    public boolean create(Order order) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO order (customer_id, product_id, order_date) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (int) order.getUserId());
            statement.setInt(2, (int) order.getProductId());
            statement.setObject(3, order.getDate());

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir venda. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        System.out.println("Venda criada com sucesso.");
        return true;
    }
}
