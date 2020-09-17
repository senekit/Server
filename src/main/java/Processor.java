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
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense("www",1,"food","2020-09-27");
//        ResultSet rs = IncomeAndExpenseDao.selectWithEmailDesc("www");
//        if(rs!=null)System.out.println(rs);
//        try {
//            while (rs.next()) {
//               // System.out.println("成功");
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getString(3));
//                System.out.println(rs.getString(4));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        IncomeAndExpenseDao.deleteOne(incomeAndExpense);
       // UserInformationDao.updateFamilyId("1",333);

        //IncomeAndExpenseDao.insert(incomeAndExpense);
       // IncomeAndExpenseDao.delete(incomeAndExpense);
    }


}
