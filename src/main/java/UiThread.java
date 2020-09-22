/**
 * @program: Server
 * @description: a thread for ui
 * @author: Wry is a vegetable guy
 * @create: 2020-09-22 08:38
 **/
public class UiThread extends Thread{

    public MainUiServer mainUiServer = new MainUiServer();

    public void run(){
        try{
            mainUiServer.pack();
            mainUiServer.setVisible(true);
            System.exit(1);

        }catch (Exception e){
            e.printStackTrace();
            this.interrupt();

        }
    }


}
