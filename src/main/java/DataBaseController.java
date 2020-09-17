import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: Server
 * @description: 控制数据库的类
 * @author: yps
 * @create: 2020-09-16 20:48
 **/
public class DataBaseController {
    static private String hostIp;
    static private String port;
    static private String dbName;
    static private String dbUser;
    static private String password;

    public DataBaseController(String hostIp,String port,String daName,String dbUser,String password){
        this.hostIp = "127.0.0.1";
        this.port = "3306";
        this.dbName = "database";
        this.dbUser = "root";
        this.password = "1234";
    }

    public static ResultSet exectue(String sql) {
        Connection connection = getConnection();
        if(connection==null)return null;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            String sqlType = sql.substring(0,6);
            if(sqlType.equals("select")) {


                return pst.executeQuery();

            }else{
              //  System.out.println("11111");
                pst.execute();
                pst.close();
                connection.close();
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
    }

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
////连接数据库
//public class JDBCDemo {
//	public static void main(String[] args) {
//		// 打开连接
//		// 1.加载驱动
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("数据库包无法加载");
//			e.printStackTrace();
//		}
//		// 2.设置连接字符串,Mysql的端口默认就是3306
//		String url = "jdbc:mysql://localhost:3306/javafx_db";
//		// 3.通过刚加载的数据库驱动以及连接字符串来获取数据库连接对象
//		// DriverManager的getConnection中的参数:
//		// 1.连接字符串
//		// 2.用户名
//		// 3.密码
//		Connection con;
//		String sql = null;
//		try {
//			con = DriverManager.getConnection(url, "root", "admin");
//
//			// 发送要执行的SQL语句
//			sql = "select  from login_tb where username=? and password=?";
//			PreparedStatement pst;
//
//			pst = con.prepareStatement(sql);
//			pst.setString(1, "张三");
//			pst.setString(2, "456");
//			ResultSet rs = pst.executeQuery();
//
//			if (rs.next()) {
//				System.out.println("成功");
//			} else {
//				System.out.println("失败");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println(sql);
//			e.printStackTrace();
//		}
//
//		// 接收结果
//	}
//}

