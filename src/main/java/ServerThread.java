import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: Server
 * @description: 多线程处理数据
 * @author: Wry is a vegetable guy
 * @create: 2020-09-16 20:23
 **/
class ServerThread extends Thread {
    private Socket socket ;
    InputStream inputStream;
    OutputStream outputStream;
    public  String finalInformation;

    public  ServerThread(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try {
            while (true){
                //接收客户端的消息并打印
                System.out.println(socket);
                inputStream=socket.getInputStream();
                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                String string = new String(bytes);
               // System.out.println(string);
                processing(string);
                //向客户端发送消息
                System.out.println("i////"+finalInformation);
                System.out.println(recommendStockSteady());
                outputStream = socket.getOutputStream();
                outputStream.write(finalInformation.getBytes());
                if(finalInformation!=null)finalInformation = null;
                //System.out.println(finalInformation);

            }
        } catch (Exception e) {
            System.out.println("客户端主动断开连接了");
            //e.printStackTrace();
        }
        //操作结束，关闭socket
        try{
            socket.close();
        }catch(IOException e){
            System.out.println("关闭连接出现异常");
            e.printStackTrace();
        }
    }

    public  void processing(String message){
        String[] informationOfRequest = message.split("/");
        if(informationOfRequest[0].equals("L"))finalInformation = loginInformation(informationOfRequest);
        if(informationOfRequest[0].equals("R"))finalInformation = registerInformation(informationOfRequest);
        if(informationOfRequest[0].equals("M"))finalInformation = getIncomeAndExpense(informationOfRequest);
        if(informationOfRequest[0].equals("I"))finalInformation = getRecentInccomeAndExpense(informationOfRequest);
        if(informationOfRequest[0].equals("F"))finalInformation = getFamilyId(informationOfRequest);
        if(informationOfRequest[0].equals("T"))finalInformation = updateIncomeAndExpenseType(informationOfRequest);
        if(informationOfRequest[0].equals("O"))finalInformation = updateIncomeAndExpenseMoney(informationOfRequest);
        if(informationOfRequest[0].equals("D"))finalInformation = updateIncomeAndExpenseTime(informationOfRequest);
        if(informationOfRequest[0].equals("E"))finalInformation = deleteIncomeAndExpense(informationOfRequest);
        if(informationOfRequest[0].equals("P"))finalInformation = updatePassword(informationOfRequest);
        if(informationOfRequest[0].equals("C"))finalInformation = sendIdentifyCode(informationOfRequest);
        if(informationOfRequest[0].equals("A"))finalInformation = deleteUser(informationOfRequest);
        if(informationOfRequest[0].equals("B"))finalInformation = getWeek(informationOfRequest);
        if(informationOfRequest[0].equals("G"))finalInformation = getFamilyInformation(informationOfRequest);
        if(informationOfRequest[0].equals("H"))finalInformation = createFamily(informationOfRequest);
        if(informationOfRequest[0].equals("Z"))finalInformation = deleteFamily(informationOfRequest);
        if(informationOfRequest[0].equals("J"))finalInformation = joinFamily(informationOfRequest);
        if(informationOfRequest[0].trim().equals("WS"))finalInformation = recommendStockSteady();
        if(informationOfRequest[0].trim().equals("HS"))finalInformation = recommendStockHot();
        if(informationOfRequest[0].trim().equals("RS"))finalInformation = recommendStockRisk();
    }

    public static String recommendStockSteady(){
       ResultSet resultSet =  StockDao.selectWithTodayZhenFu();
       String message = "";
       try{
           while(resultSet.next()){
               message = message  + resultSet.getString(1)+ "/";
               message = message  + resultSet.getString(2)+ "/";
               message = message  + resultSet.getString(3)+ "/";
               message = message  + resultSet.getString(4)+ "/";
               message = message  + resultSet.getString(5)+ "/";
               message = message  + resultSet.getString(6)+ "/";
               message = message  + resultSet.getString(9)+ "/";
           }
           message = message +"#"+recommendStockRisk();
           return message;
       }catch(Exception e){
           e.printStackTrace();
       }
       return "F";
    }

    public static String recommendStockHot(){
        ResultSet resultSet =  StockDao.selectWithTodayTurnOver();
        String message = "";
        try{
            while(resultSet.next()){
                message = message  + resultSet.getString(1)+ "/";
                message = message  + resultSet.getString(2)+ "/";
                message = message  + resultSet.getString(3)+ "/";
                message = message  + resultSet.getString(4)+ "/";
                message = message  + resultSet.getString(5)+ "/";
                message = message  + resultSet.getString(6)+ "/";
                message = message  + resultSet.getString(9)+ "/";
            }
            return message;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "F";
    }

    public static String recommendStockRisk(){
        ResultSet resultSet =  StockDao.selecttWithChengJiaoE();
        String message = "";
        try{
            while(resultSet.next()){
                message = message  + resultSet.getString(1)+ "/";
                message = message  + resultSet.getString(2)+ "/";
                message = message  + resultSet.getString(3)+ "/";
                message = message  + resultSet.getString(4)+ "/";
                message = message  + resultSet.getString(5)+ "/";
                message = message  + resultSet.getString(6)+ "/";
                message = message  + resultSet.getString(9)+ "/";
            }
            return message;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "F";
    }


    public static String joinFamily(String[] information){
        String email = information[1].trim();
        int id = Integer.valueOf(information[2].trim());
        UserInformationDao.createFamilyId(email,id);
        return "S";
    }


    public static String deleteFamily(String[] information){

        int id = Integer.valueOf(information[1].trim());
        UserInformationDao.updateFamilyID(id);
        return "S";
    }


    public static String createFamily(String[] information){
        int number = (int)(Math.random()*1000000);
        String email = information[1].trim();
        UserInformationDao.createFamilyId(email,number);
        String ans ="I/"+String.valueOf(number);
        return ans;
    }


    public static String getFamilyInformation(String[] information) {
        String email = information[1].trim();
        return IncomeAndExpenseDao.getFamilyMember(UserInformationDao.getFamilyId(email),email);
    }


    public static  String getWeek(String[] information){
        String email = information[1].trim();
        return IncomeAndExpenseDao.getRecentWeek(email);
    }



    public static String updatePassword(String[] information){
        String email = information[1].trim();
        String password = information[2].trim();
        UserInformationDao.updatePassword(email,password);
        return "S";
    }

    public static String deleteUser(String[] information){
        String email = information[1].trim();
        UserInformationDao.delete(email);
        return "S";
    }


    public static String sendIdentifyCode(String[] information){
        String email = information[1].trim();
        int identifyCode = (int)(Math.random()*10000);
        try {
            EmailSentToUser.sendEmailtoUser(email,"验证码为:" + String.valueOf(identifyCode));
            System.out.println("");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "F";
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return "F";
        }
        return "S/" + String.valueOf(identifyCode);
    }

    public static String updateIncomeAndExpenseType(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateType(incomeAndExpense);
        return "S";
    }

    public static String updateIncomeAndExpenseTime(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateTime(incomeAndExpense);
        return "S";
    }

    public static String updateIncomeAndExpenseMoney(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateMoney(incomeAndExpense);
        return "S";
    }

    public static String deleteIncomeAndExpense(String[] information){
        //System.out.println("1111");
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.deleteOne(incomeAndExpense);
        return "S";
    }





    /**
     * @Description:返回登录的结果
     * @Author: Wry is a vegtable chicken
     * @Date: 2020/9/17 18:26
     * [information]
     * @return: java.lang.String
     **/
    public static String loginInformation(String[] information){
        String email = information[1];
        String password = information[2];
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try {
            while (rs.next()) {
                if(email.equals(rs.getString(1))) {
                    if(password.equals(rs.getString(3)))return "A";
                    else return "F";
                }
                //System.out.println(rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "N";
    }

    /**
     * @Description:注册结果返回
     * @Author: Wry is a vegtable chicken
     * @Date: 2020/9/17 18:27
     * [information]
     * @return: java.lang.String
     **/
    public static String registerInformation(String[] information){
        String email = information[1];
        String password = information[2];
        String name = information[3];
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try {
            while (rs.next()) {
                if(email.equals(rs.getString(1))) {
                    return "F";
                }
                //System.out.println(rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        UserInformation newUser = new UserInformation(email,password,name,0);
        UserInformationDao.insert(newUser);
        return "B";
    }

    public static String getIncomeAndExpense(String[] information){
        String email = information[1];
        String type = information[2];
        int money = Integer.parseInt(information[3]);
        String time = information[4];
        //  String time = null;
        // time = new String(information[4],"utf-8");
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(email,money,type,time);
        IncomeAndExpenseDao.insert(incomeAndExpense);
        return "C";
    }

    public static String getRecentInccomeAndExpense(String[] information){
        String email = information[1].trim();
        ResultSet rs = IncomeAndExpenseDao.selectWithEmailDesc(email);
        String sentMessage = "I";
        int temp = 0;
        try{
            while(rs.next()){
                sentMessage = sentMessage + "/" + rs.getString(3)
                        + "/" + rs.getString(2) + "/" +rs.getString(4);
                //  System.out.println("11111111111111111111111"+sentMessage);
                temp++;
                if(temp >= 10)break;
                //   System.out.println(rs.getString(2)+rs.getString(3)+rs.getString(4));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        if(sentMessage.equals("I"))sentMessage = "Q";
        // System.out.println(sentMessage);
        return sentMessage;
    }

    public static String getFamilyId(String[] information){
        String email = information[1].trim();
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try{
            while(rs.next()){
                return rs.getString(4)+"/"+rs.getString(2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return "0";
    }



}