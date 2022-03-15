package market.model.dao;

import java.sql.Connection;
import java.time.LocalDate;

public class OrderDao extends  BaseDao{
    public OrderDao(Connection connection) {
        super(connection);
        this.tableName = "order";
        this.idName = "id";
    }
}
