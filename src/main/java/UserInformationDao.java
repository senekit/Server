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


    public static void insert(UserInformation userInformation){
        String sql  = "insert into user_information(email,username,password,familyid) values('"+userInformation.getEmail()+"','"+userInformation.getUserName()+"','"+userInformation.getPassword()+"',"+userInformation.getFamilyId()+")";
        //System.out.println(sql);
        DataBaseController.exectue(sql);
        return;
    }

    public static void delete(UserInformation userInformation){
        String sql = "delete from user_information where email ='" + userInformation.getEmail() + "'";
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

    public static void updateFamilyId(String email, int familyId){
        String sql = "update user_information set familyid = '"+String.valueOf(familyId) + "' where email = '" + email + "'";
        DataBaseController.exectue(sql);
        return;
    }



}
