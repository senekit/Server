import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: Server
 * @description: 服务端
 * @author: Wry is a vegetable guy
 * @create: 2020-09-16 20:14
 **/

public class Server {
    //监听端口
    private static final int PORT = 8888;


    public static void main(String[] args) throws Exception {

    //    Application.launch(ServerMainUi.class);

        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //建立服务器的Socket，并设定一个监听的端口PORT
            serverSocket = new ServerSocket(PORT);
            //由于需要进行循环监听，因此获取消息的操作应放在一个while大循环中
            UiThread uiThread = new UiThread();
            uiThread.start();
            while(true){
                try {
                    //建立跟客户端的连接
                    socket = serverSocket.accept();
                    ServerThread thread = new ServerThread(socket,uiThread.mainUiServer);
                    thread.start();

                } catch (Exception e) {
                    System.out.println("建立与客户端的连接出现异常");
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("端口被占用");
            e.printStackTrace();
        }
        finally {
            serverSocket.close();
        }

    }

}

//test