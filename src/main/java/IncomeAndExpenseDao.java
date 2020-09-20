import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static ResultSet selectWithEmailAndTime(String email,String time){
        String sql = "select * from income_expense where email = '" + email +"' and time_change = '" + time + "'";
        ResultSet resultSet = DataBaseController.exectue(sql);
        return  resultSet;
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

    public static String getRecentWeek(String email)
    {
        String ans = "CAO";

        for(int i=0;i<=6;i++)
        {
            int income = 0;
            int expense = 0;
            String date = getPastDate(i,new Date());
            ResultSet rs =  selectWithEmailAndTime(email,date);
            try{
                while(rs.next()){
                    int tempCost = Integer.valueOf(rs.getString(2));
                    if(tempCost>0)income += tempCost ;
                    else expense +=tempCost;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            expense = expense * (-1);
            ans = ans + "/" + String.valueOf(income) +"/" + String.valueOf(expense);
        }

        return ans;
    }


    public static String getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }


//select * from StudentGrade a where (select count(1) from studentGrade where subId=a.subId and grade>=a.grade)<=2

    public static String getFamilyMember(ResultSet resultSet,String email){
        try{
            String ans = "I/";
            int times = 0;
            while(resultSet.next()&&times<3){
                times ++;
                if(resultSet.getString(1).equals(email))continue;
                ans = ans + resultSet.getString(2)+"/";
                ResultSet rs = selectWithEmailDesc(resultSet.getString(1));
                int count = 0;
                while(rs.next()&&count<3){
                    count++;
                    ans = ans + rs.getString(3)+"/"+rs.getString(2)+"/"+rs.getString(4)+"/";
                }
                while(count<3){
                    count++;
                    ans = ans +" / / /";
                }

            }

            while(times<=3){
                // System.out.println(times+"1111");
                times++;
                ans = ans +" / / / / / / / / / /";
            }

            return  ans;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
