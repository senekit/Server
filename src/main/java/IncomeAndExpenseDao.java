import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Server
 * @description: IncomeAndExpenseDao
 * @author: Wry is a vegetable guy
 * @create: 2020-09-17 20:22
 **/
public class IncomeAndExpenseDao {
    public static void insert(IncomeAndExpense incomeAndExpense){
        String sql  = "insert into income_expense(email,money,type,time) values('"+incomeAndExpense.getEmail()
                +"',"+incomeAndExpense.getMoney()+",'"+incomeAndExpense.getType()+"','"+incomeAndExpense.getTime()+"')";
        //System.out.println(sql);
        DataBaseController.exectue(sql);
        return;
    }


    //
    public static void delete(IncomeAndExpense incomeAndExpense){
        String sql = "delete from income_expense where email = '"+
                incomeAndExpense.getEmail()+"'";
        DataBaseController.exectue(sql);
        return;
    }

}
