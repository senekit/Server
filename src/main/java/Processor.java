import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: Server
 * @description: 处理传来的数据
 * @author: Wry is a vegetable guy
 * @create: 2020-09-16 20:22
 **/
public class Processor {
    public static void main(String[] args)
    {
       // UserInformation userInformation = new UserInformation("1","2222","3333",0);
      //  UserInformationDao.delete(userInformation);
        //IncomeAndExpense incomeAndExpense = new IncomeAndExpense("rw",2,"food","2021-09-01");
        ResultSet rs = IncomeAndExpenseDao.selectWithEmailDesc("请在此输入您的邮箱");
        String sentMessage = "I";
        if(rs!=null)System.out.println(rs);
        try {
            while(rs.next()){
                sentMessage = sentMessage + "/" + rs.getString(3)
                        + "/" + rs.getString(2) + "/" +rs.getString(4);
               // temp++;
               // if(temp >= 10)break;
                System.out.println(sentMessage);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
     //   IncomeAndExpenseDao.deleteOne(incomeAndExpense);
       // UserInformationDao.updateFamilyId("1",333);

        //IncomeAndExpenseDao.insert(incomeAndExpense);
       // IncomeAndExpenseDao.delete(incomeAndExpense);
        //System.out.println(ManagerDao.sendEmail("1792700051@qq.com"));
      //  ManagerDao.sendEmail("1792700051@qq.com");
    }


}
