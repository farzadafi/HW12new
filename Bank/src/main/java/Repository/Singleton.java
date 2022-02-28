package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Singleton
{
    private Connection connection;
    private static Singleton obj;

    private Singleton() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "afshar");
    }


    public static Singleton getInstance() throws SQLException, ClassNotFoundException {
        if (obj==null)
            obj = new Singleton();
        return obj;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static Singleton getObj() {
        return obj;
    }

    public static void setObj(Singleton obj) {
        Singleton.obj = obj;
    }
}