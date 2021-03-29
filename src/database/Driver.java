package database;

import java.sql.*;
import java.util.*;

public class Driver {
    static Connection connection = null;
    static Statement statement = null;
    public static void connect(){
        String name = "root";
        String password = "Zaq12wsx";
        String url = "jdbc:mysql://localhost:3306/quizdatabase";
        //jdbc:mysql://localhost:3306/quizDatabase
        /*try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }*/
        //1) public ResultSet executeQuery(String sql): is used to execute SELECT query. It returns the object of ResultSet.
        //2) public int executeUpdate(String sql): is used to execute specified query, it may be create, drop, insert, update, delete etc.
        //3) public boolean execute(String sql): is used to execute queries that may return multiple results.
        //4) public int[] executeBatch(): is used to execute batch of commands.
        try {
            connection = DriverManager.getConnection(url, name, password);
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static boolean login(String userLogin,String userPassword){
        //String query = "SELECT * FROM users WHERE userLogin="\""+userLogin+"\" AND userPassword=\""+userPassword+"\"";
        boolean result = false;
        try {
            String sql = "SELECT * FROM users WHERE userLogin=? AND userPassword=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userLogin);
            preparedStatement.setString(2,userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) result = true;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return result;
    }

    public static boolean register(String userLogin, String userPassword){
        int result = 0;
        try {
            String sql = "INSERT users VALUE (0,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userLogin);
            preparedStatement.setString(2,userPassword);
            result = preparedStatement.executeUpdate();
            /*ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String s = "";
                for (int i = 1; i <= 3; i++) {
                    s += resultSet.getString(i) + ":";
                }
                System.out.println(s);
            }*/
        } catch (SQLException exception){
            //exception.printStackTrace();
            System.out.println("Error");
            return false;
        }
        return result > 0;
    }

    public static boolean isLoginTaken(String userLogin) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM users WHERE userLogin=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) result = true;
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return result;
    }

    public static int getID(String userLogin, String userPassword){
        int id = 0;
        try {
            String sql = "SELECT userID FROM users WHERE userLogin=? AND userPassword=?;;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userLogin);
            preparedStatement.setString(2,userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString(1));
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return id;
    }

    public static void updateUser(int userID, String userPassword){
        try {
            String sql = "UPDATE users SET userPassword=? WHERE userID=? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userPassword);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException exception){
            exception.printStackTrace();
            System.out.println("Error");
        }
    }

    public static ArrayList<ArrayList<String>> getQuestions(String gameDifficulty){
        ArrayList<ArrayList<String>> questions = new ArrayList<ArrayList<String>>();
        try{
            String sql = "SELECT * FROM questions_eng WHERE difficulty=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,gameDifficulty);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ArrayList<String> questionSet = new ArrayList<String>();
                for(int i = 1; i <= 8; i++){
                    questionSet.add(resultSet.getString(i));
                }
                questions.add(questionSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(ArrayList<String> s: questions){
            String r="";
            for(int i=0;i< s.size();i++){
                r+=s.get(i) + " ";
            }
            System.out.println(r);
        }
        return questions;
    }
}
