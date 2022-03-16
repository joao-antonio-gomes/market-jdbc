package market.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract public class BaseDao {
    protected Connection connection;
    protected String tableName;
    protected String idName;

    public BaseDao(Connection connection) {
        this.connection = connection;
    }

    public ResultSet listAll() throws SQLException {
        String sql = String.format("SELECT * FROM \"%s\"", this.tableName);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        return statement.getResultSet();
    }

    public ResultSet listById(int id) throws SQLException {
        String sql = String.format("SELECT * FROM \"%s\" WHERE \"%s\" = ?", this.tableName, this.idName);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
        return statement.getResultSet();
    }

    public void deleteById(int id) {
        PreparedStatement statement = null;
        try {
            String sql = String.format("DELETE FROM \"%s\" WHERE \"%s\" = ?", this.tableName, this.idName);
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int updateCount = statement.getUpdateCount();
            if (updateCount == 1) {
                System.out.println("Item excluído com sucesso.");
            }
            if (updateCount == 0) {
                System.out.println("Item não encontrado. Nenhum item excluído.");
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir item. Causado por: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
