package database;

public class CurrentUser {
    private static int userID = 0;
    private static String userLogin = null;
    private static String userPassword = null;
    public static int getUserID(){ return userID; }
    public static String getUserLogin(){ return userLogin; }
    public static String getUserPassword(){return userPassword; }
    public static void setUser(int ID, String login, String password){
        userID = ID;
        userLogin = login;
        userPassword = password;
    }
    public static void setUserPassword(String password){ userPassword = password; }
    public static void updateUser(){ Driver.updateUser(userID,userPassword); }
}
