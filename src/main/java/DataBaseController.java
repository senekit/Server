import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: Server
 * @description: 控制数据库的类
 * @author: yps
 * @create: 2020-09-16 20:48
 **/
public class DataBaseController {
    public static void main(String[] args) throws Exception{
        //1 注册驱动
        //2 获取连接
        //3 获取操作数据库的预处理对象
        //4 执行SQL 得到结果集
        //5 遍历结果集
        //6 释放资源
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        //1 注册驱动，加载驱动类
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());//没有这个jar包是无法运行的
        Class.forName("com.mysql.jdbc.Driver");//这里com.mysql.jdbc.Driver()仅是一个字符串，该类就可作为一个独立的类

        //2 建立连接
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database",
                "root", "8904652xuexi~!@");             //使用前请更改你的数据库信息
        //3 获取操作数据库的预处理对象
        pstm = conn.prepareStatement("select * from user_information");

        //4 执行SQL 得到结果集
        rs = pstm.executeQuery();

        //5 遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString(1) + " "+rs.getString(2) + " "+rs.getString(3) + " "+ rs.getInt(4));
        }
        //6 释放资源
        rs.close();
        pstm.close();
        conn.close();
    }
}
