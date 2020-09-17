/**
 * @program: Server
 * @description: IncomeAndExpense
 * @author: Wry is a vegetable guy
 * @create: 2020-09-17 20:11
 **/
public class IncomeAndExpense {
    private String email;
    private String time;
    private String type;
    private int money;

    public IncomeAndExpense(String email, int money, String type, String time) {
        this.email = email;
        this.time = time;
        this.type = type;
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
