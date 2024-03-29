

public class UserService {

    private User user = new User();

    public boolean checkUser(String account, String password) {
        return DataBase.check(account, password);
    }


}
