public interface UserServiceInterface
{
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
    public void showFriendList()

}
