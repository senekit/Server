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
public class UserInformationDao {

    public static void insert(UserInformation userInformation){
        String sql  = "insert into user_information(email,username,password,familyid) values('"+userInformation.getEmail()+"','"+userInformation.getUserName()+"','"+userInformation.getPassword()+"',"+userInformation.getFamilyId()+")";
        System.out.println(sql);
        DataBaseController.exectue(sql);
    }

    public static void delete(UserInformation userInformation){
        String sql = "delete from user_information where email ='" + userInformation.getEmail() + "'";
        DataBaseController.exectue(sql);
    }


}
