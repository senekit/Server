import javafx.application.Application;

import javax.mail.MessagingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.GeneralSecurityException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: Server
 * @description: 处理传来的数据
 * @author: Wry is a vegetable guy
 * @create: 2020-09-16 20:22
 **/
public class Processor {
    public static void main(String[] args)
    {
        ResultSet rs = StockDao.selecttWithChengJiaoE();
        try{
            int n = 0 ;
            while(rs.next()){
                System.out.println(n++);
                System.out.print(rs.getString(1)+" ");
                System.out.print(rs.getString(2));
                System.out.println(" "+rs.getString(3)+"  "+rs.getString(4));
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//        ResultSet rs = IncomeAndExpenseDao.selectWithEmailAndTime("www","2020-09-01");
//        if(rs!=null)System.out.println(rs);
//        try {
//            while(rs.next()){
//                System.out.println(rs.getString(2));
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }


        // IncomeAndExpense incomeAndExpense = new IncomeAndExpense("ccc",-1222,"eat","2020-09-123");
       // IncomeAndExpenseDao.updateTime(incomeAndExpense);
//        final MainUiServer mainUiServer = new MainUiServer();
//        mainUiServer.pack();
//        mainUiServer.setVisible(false);
//
//        final LoginUiServer dialog = new LoginUiServer();
//
//        dialog.addWindowListener(new WindowListener() {
//            public void windowOpened(WindowEvent e) {
//                System.out.println("窗口打开了");
//                //mainUiServer.setVisible(true);
//            }
//
//            public void windowClosing(WindowEvent e) {
////                mainUiServer.setVisible(true);
////                System.out.println("窗口2了");
////                if(ManagerDao.isCorrect(dialog.manager,dialog.password)==1)
////                {
////
////                }
//            }
//
//            public void windowClosed(WindowEvent e) {
//                mainUiServer.setVisible(true);
//                System.out.println("窗口1了");
//                if(ManagerDao.isCorrect(dialog.manager,dialog.password)==1)
//                {
//
//                }
//
//            }
//
//            public void windowIconified(WindowEvent e) {
//
//            }
//
//            public void windowDeiconified(WindowEvent e) {
//
//            }
//
//            public void windowActivated(WindowEvent e) {
//
//            }
//
//            public void windowDeactivated(WindowEvent e) {
//
//            }
//        });
//
//        dialog.pack();
//        dialog.setVisible(true);
//
//
//        System.exit(0);


    }







//        dialog.buttonOK.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//               // dialog.onOK();
//                dialog.manager = dialog.managerEmail.getText();
//                dialog.password = new  String(dialog.passwordField1.getPassword());
//                if(ManagerDao.isCorrect(dialog.manager,dialog.password)){
//
//                    dialog.dispose();
//
//                }else{
//
//                }
//            }
//        });
//
//        dialog.buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//
//            }
//        });








// UserInformation userInformation = new UserInformation("1","2222","3333",0);
//  UserInformationDao.delete(userInformation);
//IncomeAndExpense incomeAndExpense = new IncomeAndExpense("rw",2,"food","2021-09-01");
//        ResultSet rs = IncomeAndExpenseDao.selectWithEmailDesc("请在此输入您的邮箱");
//        String sentMessage = "I";
//        if(rs!=null)System.out.println(rs);
//        try {
//            while(rs.next()){
//                sentMessage = sentMessage + "/" + rs.getString(3)
//                        + "/" + rs.getString(2) + "/" +rs.getString(4);
//               // temp++;
//               // if(temp >= 10)break;
//                System.out.println(sentMessage);
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//   IncomeAndExpenseDao.deleteOne(incomeAndExpense);
// UserInformationDao.updateFamilyId("1",333);

//IncomeAndExpenseDao.insert(incomeAndExpense);
// IncomeAndExpenseDao.delete(incomeAndExpense);
//System.out.println(ManagerDao.sendEmail("1792700051@qq.com"));
//  ManagerDao.sendEmail("1792700051@qq.com");
