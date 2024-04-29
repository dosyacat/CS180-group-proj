public interface UserServiceInterface
{
    /**
     * Interface for UserService Class
     * <p>Purdue University -- CS18000 -- Spring 2024</p>
     *
     * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
     * @version April 29, 2024
     */
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
