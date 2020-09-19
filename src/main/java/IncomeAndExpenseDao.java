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
        String sql  = "insert into income_expense(email,money,type,time_change) values('"+incomeAndExpense.getEmail()
                +"',"+incomeAndExpense.getMoney()+",'"+incomeAndExpense.getType()+"','"+incomeAndExpense.getTime().trim()+"')";
        System.out.println(sql);
        DataBaseController.exectue(sql);
        return;
    }


    /**
     * @Description:删除该用户所有的消费记录
     * @Author: Wry is a vegtable chicken
     * @Date: 2020/9/17 22:45
     * [incomeAndExpense]
      * @return: void
     **/
    public static void delete(IncomeAndExpense incomeAndExpense){
        String sql = "delete from income_expense where email = '"+
                incomeAndExpense.getEmail()+"'";
        DataBaseController.exectue(sql);
        return;
    }

    public static ResultSet selectWithEmailOrder(String email){
        String sql = "select * from income_expense where email = '" + email + "' order by  time_change asc";
        ResultSet resultSet = DataBaseController.exectue(sql);
        return resultSet;
    }

    public static ResultSet selectWithEmailDesc(String email){
        String sql = "select * from income_expense where email = '" + email + "' order by  time_change desc";
        ResultSet resultSet = DataBaseController.exectue(sql);
        return resultSet;
    }

    public static void deleteOne(IncomeAndExpense incomeAndExpense){
        String sql = "delete from income_expense where email = '" + incomeAndExpense.getEmail()+"'"+
                "and time_change = '"+incomeAndExpense.getTime()+"' and type = '"+ incomeAndExpense.getType() + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static void updateMoney(IncomeAndExpense incomeAndExpense){
        String sql = "update income_expense set money = " + String.valueOf(incomeAndExpense.getMoney()) +
                " where email = '" + incomeAndExpense.getEmail() + "' and type = '" + incomeAndExpense.getType() + "' and time_change = '"+
                incomeAndExpense.getTime() + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static void updateType(IncomeAndExpense incomeAndExpense){
        String sql = "update income_expense set Type = '" + incomeAndExpense.getType() + "' where email = '" + incomeAndExpense.getEmail() + "' and money = " +
                String.valueOf(incomeAndExpense.getMoney()) + " and time_change = '" + incomeAndExpense.getTime() + "'";
        DataBaseController.exectue(sql);
        return;
    }

    public static void updateTime(IncomeAndExpense incomeAndExpense){
        String sql ="update income_expense set time_change = '" + incomeAndExpense.getTime() + "' where email = '" + incomeAndExpense.getEmail() + "' and money = " +
                String.valueOf(incomeAndExpense.getMoney()) + " and type = '" + incomeAndExpense.getType() + "'";
        DataBaseController.exectue(sql);
        return;
    }




}
