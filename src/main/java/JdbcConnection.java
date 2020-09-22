import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @program: Server
 * @description: JDBC connection
 * @author: Wry is a vegetable guy
 * @create: 2020-09-22 09:57
 **/
public class JdbcConnection {

    public static  Connection connection = getConnection();
    static private String hostIp;
    static private String port;
    static private String dbName;
    static private String dbUser;
    static private String password;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            hostIp = "127.0.0.1";
            port = "3306";
            dbName = "database";
            dbUser = "root";
            password = "1234";

        } catch (ClassNotFoundException e) {
            System.out.println("数据库包无法加载");
            e.printStackTrace();
        }

        String dbUrl = "jdbc:mysql://localhost:3306/database";
        try{
            Connection connection = DriverManager.getConnection(dbUrl,dbUser,password);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //return  getConnection();
        }
        return null;
    }
}
