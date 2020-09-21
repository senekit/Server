import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Server
 * @description: return stock information
 * @author: Wry is a vegetable guy
 * @create: 2020-09-21 14:04
 **/
public class StockDao {

    public static ResultSet selectWithTodayTurnOver(){
        String sql = "select  * from stock order by chengjiaoliang desc limit 10";
        ResultSet rs = DataBaseController.exectue(sql);
        return rs;
    }

    public static ResultSet selectWithTodayZhenFu(){
        String sql = "select * from stock order by zhenfu desc limit 10";
        ResultSet rs = DataBaseController.exectue(sql);
        return rs;
    }

    public static ResultSet selecttWithChengJiaoE(){
        String sql = "select * from stock order by chengjiaoe desc limit 10";
        ResultSet rs = DataBaseController.exectue(sql);
        return rs;
    }

}
