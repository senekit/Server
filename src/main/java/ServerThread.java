import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

                //向客户端发送消息
                outputStream = socket.getOutputStream();
                outputStream.write("OK".getBytes());
                System.out.println("OK");

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
}