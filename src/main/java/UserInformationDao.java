import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Server
 * @description: UserInformationDao
 * @author: Wry is a vegetable guy
 * @create: 2020-09-17 09:59
 **/

/*
 *       参数                        返回值
 *   增  UserInformation             null
 *   删  UserInformation             null
 *   查  email                       ResultSet
 *   查  familyId                    ResultSet
 *   改  email familyId              null
 *
 *
 */
public class UserInformationDao {

    static int familyNumber = 100;

    public static void insert(UserInformation userInformation){
        String sql  = "insert into user_information(email,username,password,familyid) values('"+userInformation.getEmail()+"','"+userInformation.getUserName()+"','"+userInformation.getPassword()+"',"+userInformation.getFamilyId()+")";
        //System.out.println(sql);
        DataBaseController.exectue(sql);
        return;
    }

    public static void delete(String email){
        String sql = "delete from user_information where email ='" + email + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static ResultSet selectWithEmail(String email){
        String sql = "select * from user_information where email = '" + email + "'";
        ResultSet resultSet = DataBaseController.exectue(sql);
        return resultSet;
    }

    public static ResultSet selectWithFamilyId(int familId){
        String sql = "select * from user_information where familyid = " + String.valueOf(familId) + "";
        ResultSet resultSet = DataBaseController.exectue(sql);
        return resultSet;
    }

    public static void updatePassword(String email,String password){
        String sql = "update user_information set password = '" + password + "' where email = '" + email + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static void updateFamilyId(String email, int familyId){
        String sql = "update user_information set familyid = '"+String.valueOf(familyId) + "' where email = '" + email + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static void createFamilyId(String email,int number){
        updateFamilyId(email,number);

    }

    public static  void updateFamilyID(int familyId) {
        String sql = "update user_information set familyid = 0 where familyId = '" + String.valueOf(familyId) + "'";
        DataBaseController.exectue(sql);
        return;

    }

    public static  void quitFamily(String email) {
        String sql = "update user_information set familyid = 0 where email = '"+ email +"'";
        DataBaseController.exectue(sql);
        return;

    }


    public static void createFamily(String email){
        updateFamilyId(email,familyNumber);
        familyNumber += 1;
    }

    public static void breakUpFamily(String email){
        String sql = "select * from user_information where email = '" + email + "'";
        ResultSet resultSet = DataBaseController.exectue(sql);

        try {
            resultSet.next();
         //   System.out.println(resultSet.getInt(4));
            int familyId = resultSet.getInt(4);
            updateFamilyID(familyId);
        }catch (SQLException e){
            //e.printStackTrace();
        }

        return;
    }

    public static ResultSet getFamilyId(String email){
        String sql1 = "select familyid from user_information where email ='" + email +"'";
        ResultSet rs1 = DataBaseController.exectue(sql1);
        try {
            rs1.next();
            int familyId = rs1.getInt(1);
            String sql2 = "select * from user_information where familyid =" + String.valueOf(familyId);
            ResultSet resultSet = DataBaseController.exectue(sql2);
            return  resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
