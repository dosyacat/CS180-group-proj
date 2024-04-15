public interface UserServiceInterface
{
    /**
     * Interface for UserService Class
     * <p>Purdue University -- CS18000 -- Spring 2024</p>
     *
     * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
     * @version April 15, 2024
     */
    public User userSignIn(String account, String password);
    public void userSignUp();
    public void userView();
    public void userSearch();
    public void logout();
    public void editUserName();
    public void editEmail();
    public void editPassword();
    public void editBio();
    public void removeFriend();
    public void blockFriend();
    public void unBlockFriend();
    public void showFriendList();

}
