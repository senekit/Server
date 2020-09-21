/**
 * @program: Server
 * @description: Stock 股票类
 * @author: Wry is a vegetable guy
 * @create: 2020-09-21 13:57
 **/
public class Stock {
    private String code;
    private String name;
    private String todayOpeningPrice;
    private String todayTurnOver;
    private String amplitude;
    private String highest;
    private String lowest;

    public Stock(String code, String name, String todayOpeningPrice, String todayTurnOver, String amplitude, String highest, String lowest) {
        this.code = code;
        this.name = name;
        this.todayOpeningPrice = todayOpeningPrice;
        this.todayTurnOver = todayTurnOver;
        this.amplitude = amplitude;
        this.highest = highest;
        this.lowest = lowest;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTodayOpeningPrice() {
        return todayOpeningPrice;
    }

    public void setTodayOpeningPrice(String todayOpeningPrice) {
        this.todayOpeningPrice = todayOpeningPrice;
    }

    public String getTodayTurnOver() {
        return todayTurnOver;
    }

    public void setTodayTurnOver(String todayTurnOver) {
        this.todayTurnOver = todayTurnOver;
    }

    public String getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(String amplitude) {
        this.amplitude = amplitude;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }
}
