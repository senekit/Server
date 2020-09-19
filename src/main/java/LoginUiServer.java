import javax.swing.*;
import java.awt.event.*;

public class LoginUiServer extends JDialog {
    public JPanel contentPane;
    public JButton buttonOK;
    public JButton buttonCancel;
    public JTextField managerEmail;
    public JPasswordField passwordField1;
    public String manager;
    public String password;

    public LoginUiServer() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        //this.buttonOK.setEnabled(false);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager = managerEmail.getText();
                password = new  String(passwordField1.getPassword());
                if(ManagerDao.isCorrect(manager,password) == 1){
                    onOK();
                }else if(ManagerDao.isCorrect(manager,password) == 2){
                    JOptionPane.showMessageDialog(null, "密码错误", "登录失败",JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "该账户不存在", "登录失败",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager = managerEmail.getText();
                ManagerDao.sendEmail(manager);
                JOptionPane.showMessageDialog(null, "成功发送邮件", "返回主页面登录",JOptionPane.INFORMATION_MESSAGE);
                //onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void onOK() {

        // add your code here
        dispose();
    }

    public void onCancel() {
        // add your code here if necessary

         dispose();
    }

   // public static void main(String[] args) {
//        LoginUiServer dialog = new LoginUiServer();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
  //  }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
