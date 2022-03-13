package market.connection;

import java.sql.*;

public class ConnectionFactory {
    public Connection openConnection() throws SQLException {
        System.out.println("Abrindo conexão com banco de dados...");
        String user = "postgres";
        String password = "postgres";
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?useTimezone=true&serverTimezone=UTC",
                user, password);
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
        System.out.println("Encerrando conexão com banco de dados...");
    }
}
