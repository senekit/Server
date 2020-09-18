import java.sql.ResultSet;

/**
 * @program: Server
 * @description: a class deal with table manager
 * @author: Wry is a vegetable guy
 * @create: 2020-09-18 16:12
 **/
public class ManagerDao {

    public static boolean isCorrect(String manager,String password){
        String sql = "select * from manager where manager = '"+manager+"'";
        ResultSet rs = DataBaseController.exectue(sql);
        if(rs!=null)System.out.println(rs);
        try {
            while (rs.next()) {
               // System.out.println("成功");
               if(password.equals(rs.getString(2)))return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void sendEmail(String manager){
        String sql = "select * from manager where manager = '"+manager+"'";
        ResultSet rs = DataBaseController.exectue(sql);
        if(rs!=null)System.out.println(rs);
        try {
            while (rs.next()) {
                // System.out.println("成功");
                EmailSentToUser.sendEmailtoUser(manager,rs.getString(2));
                return;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        //无此用户
        return;
    }




}
