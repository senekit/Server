/**
 * @program: Server
 * @description: Table UserInformation
 * @author: Wry is a vegetable guy
 * @create: 2020-09-17 10:00
 **/
public class UserInformation {
    private String email;
    private String password;
    private String userName;
    private int familyId;

    public UserInformation(String email, String password, String userName, int familyId) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.familyId = familyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }


}
