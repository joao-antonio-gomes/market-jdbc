package market.model.dao;

import market.model.entities.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends BaseDao {

    public CustomerDao(Connection connection) {
        super(connection);
        this.tableName = "customer";
        this.idName = "id";
    }

    public boolean create(Customer customer) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO customer (name, cellphone, cpf) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCellphone());
            statement.setString(3, customer.getCpf());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente. Causado por: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        System.out.println("Cliente cadastrado com sucesso.");
        return true;
    }

    public ResultSet getClientesComPedidos() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT c.id, c.name, c.cellphone, c.cpf FROM customer c " +
                    "JOIN \"order\" o ON c.id = o.customer_id GROUP BY c.id";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar clientes com pedidos. Causado por: " + e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }
}
