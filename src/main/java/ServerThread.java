import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.ResultSet;

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
    public static String finalInformation;

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
                System.out.println(string);
                processing(string);
                //向客户端发送消息
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

    public static void processing(String message){
        String[] informationOfRequest = message.split("/");
        if(informationOfRequest[0].equals("L"))finalInformation = loginInformation(informationOfRequest);
        if(informationOfRequest[0].equals("R"))finalInformation = registerInformation(informationOfRequest);
        if(informationOfRequest[0].equals("M"))finalInformation = getIncomeAndExpense(informationOfRequest);
        if(informationOfRequest[0].equals("I"))finalInformation = getRecentInccomeAndExpense(informationOfRequest);
        if(informationOfRequest[0].equals("F"))finalInformation = getFamilyId(informationOfRequest);
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