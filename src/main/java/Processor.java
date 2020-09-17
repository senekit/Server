/**
 * @program: Server
 * @description: 处理传来的数据
 * @author: Wry is a vegetable guy
 * @create: 2020-09-16 20:22
 **/
public class Processor {
    public static void main(String[] args)
    {
        UserInformation userInformation = new UserInformation("1111","2222","3333",0);
        UserInformationDao.delete(userInformation);
    }

}
