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
    MainUiServer mainUiServer = null;

    public  ServerThread(Socket socket,MainUiServer mainUiServer){
        this.socket=socket;
        this.mainUiServer = mainUiServer;
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
                outputStream = socket.getOutputStream();
                outputStream.write(finalInformation.getBytes());
                if(finalInformation!=null)finalInformation = null;
                //System.out.println(finalInformation);

            }
        } catch (Exception e) {
            System.out.println("服务端主动断开连接了");
            this.interrupt();
            //e.printStackTrace();
        }
        //操作结束，关闭socket
        try{
            socket.close();
            System.out.println("服务端主动断开连接了");
            this.interrupt();
        }catch(IOException e){
            System.out.println("关闭连接出现异常");
            e.printStackTrace();
            this.interrupt();
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

    public  String recommendStockSteady(){
       ResultSet resultSet =  StockDao.selectWithTodayZhenFu();
       String message = "";
       mainUiServer.add( "用户获取了股票信息");
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
           message = message +"#"+this.recommendStockRisk();
           return message;
       }catch(Exception e){
           e.printStackTrace();
       }
       return "F";
    }

    public static  String recommendStockHot(){
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

    public  String recommendStockRisk(){
        ResultSet resultSet =  StockDao.selecttWithChengJiaoE();
        mainUiServer.add( "用户获取了风险股票排行");
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


    public  String joinFamily(String[] information){
        String email = information[1].trim();
        int id = Integer.valueOf(information[2].trim());
        UserInformationDao.createFamilyId(email,id);
        mainUiServer.add( "用户加入了家庭"+String.valueOf(id));
        return "S";
    }


    public  String deleteFamily(String[] information){

        int id = Integer.valueOf(information[1].trim());
        UserInformationDao.updateFamilyID(id);
        mainUiServer.add( "解散家庭"+String.valueOf(id));
        return "S";
    }


    public  String createFamily(String[] information){
        int number = (int)(Math.random()*1000000);
        String email = information[1].trim();
        UserInformationDao.createFamilyId(email,number);
        String ans ="I/"+String.valueOf(number);
        mainUiServer.add( email + "创建家庭");
        return ans;
    }


    public  String getFamilyInformation(String[] information) {
        String email = information[1].trim();
        mainUiServer.add( email + "获取家庭成员");
        return IncomeAndExpenseDao.getFamilyMember(UserInformationDao.getFamilyId(email),email);
    }


    public  String getWeek(String[] information){
        String email = information[1].trim();
        mainUiServer.add("获取用户" + email + "一周的消费记录");
        return IncomeAndExpenseDao.getRecentWeek(email);
    }



    public  String updatePassword(String[] information){
        String email = information[1].trim();
        String password = information[2].trim();
        UserInformationDao.updatePassword(email,password);
        mainUiServer.add("修改用户密码：" + email );
        return "S";
    }

    public  String deleteUser(String[] information){
        String email = information[1].trim();
        UserInformationDao.delete(email);
        mainUiServer.add("删除用户：" + email );
        return "S";
    }


    public  String sendIdentifyCode(String[] information){
        String email = information[1].trim();
        int identifyCode = (int)(Math.random()*10000);
        try {
            EmailSentToUser.sendEmailtoUser(email,"验证码为:" + String.valueOf(identifyCode));
            System.out.println("");
        } catch (MessagingException e) {
            e.printStackTrace();
            mainUiServer.add("向" + email + "发送验证码失败");
            return "F";
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            mainUiServer.add("向" + email + "发送验证码失败");
            return "F";
        }
        mainUiServer.add("向" + email + "发送验证码:"+String.valueOf(identifyCode));
        return "S/" + String.valueOf(identifyCode);
    }

    public  String updateIncomeAndExpenseType(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateType(incomeAndExpense);
        mainUiServer.add(information[1].trim()+"修改了收支记录");
        return "S";
    }

    public  String updateIncomeAndExpenseTime(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateTime(incomeAndExpense);
        mainUiServer.add(information[1].trim()+"修改了收支记录");
        return "S";
    }

    public  String updateIncomeAndExpenseMoney(String[] information)
    {
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.updateMoney(incomeAndExpense);
        mainUiServer.add(information[1].trim()+"修改了收支记录");
        return "S";
    }

    public  String deleteIncomeAndExpense(String[] information){
        //System.out.println("1111");
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(information[1].trim(),Integer.valueOf(information[3].trim()),information[2].trim(),information[4].trim());
        IncomeAndExpenseDao.deleteOne(incomeAndExpense);
        mainUiServer.add(information[1].trim()+"删除了收支记录");
        return "S";
    }





    /**
     * @Description:返回登录的结果
     * @Author: Wry is a vegtable chicken
     * @Date: 2020/9/17 18:26
     * [information]
     * @return: java.lang.String
     **/
    public  String loginInformation(String[] information){
        String email = information[1];
        String password = information[2];
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try {
            while (rs.next()) {
                if(email.equals(rs.getString(1))) {
                    if(password.equals(rs.getString(3))){
                        mainUiServer.add(email+"登录成功");
                        return "A";
                    }
                    else
                    {
                        mainUiServer.add(email+"登录失败");
                        return "F";
                    }
                }
                //System.out.println(rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        mainUiServer.add(email+"不存在");
        return "N";
    }

    /**
     * @Description:注册结果返回
     * @Author: Wry is a vegtable chicken
     * @Date: 2020/9/17 18:27
     * [information]
     * @return: java.lang.String
     **/
    public  String registerInformation(String[] information){
        String email = information[1];
        String password = information[2];
        String name = information[3];
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try {
            while (rs.next()) {
                if(email.equals(rs.getString(1))) {
                    mainUiServer.add(email+"已存在，注册失败");
                    return "F";
                }
                //System.out.println(rs.getString(3));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        UserInformation newUser = new UserInformation(email,password,name,0);
        UserInformationDao.insert(newUser);
        mainUiServer.add(email+"注册成功");
        return "B";
    }

    public  String getIncomeAndExpense(String[] information){
        String email = information[1];
        String type = information[2];
        int money = Integer.parseInt(information[3]);
        String time = information[4];
        //  String time = null;
        // time = new String(information[4],"utf-8");
        IncomeAndExpense incomeAndExpense = new IncomeAndExpense(email,money,type,time);
        IncomeAndExpenseDao.insert(incomeAndExpense);
        mainUiServer.add(email+"新增了一条收支记录");
        return "C";
    }

    public  String getRecentInccomeAndExpense(String[] information){
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
        mainUiServer.add(email+"获取了收支记录");
        return sentMessage;
    }

    public  String getFamilyId(String[] information){
        String email = information[1].trim();
        ResultSet rs = UserInformationDao.selectWithEmail(email);
        try{
            while(rs.next()){
                return rs.getString(4)+"/"+rs.getString(2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        mainUiServer.add(email+"获取了家庭ID");
        return "0";
    }



}