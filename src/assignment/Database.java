package assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database{
    
    
  private static final String dbClassName = "com.mysql.jdbc.Driver";
  private static final String CONNECTION = "jdbc:mysql://127.0.0.1/assignment";
  private static Properties p = new Properties();
  private static Connection c;
  
  Database() {
      System.out.println(dbClassName);
//      Class.forName(dbClassName);

      
      p.put("user", "root");
      p.put("password", "Omwene11@");
      
      System.out.println("Connected!");
  }
    
    public static void main(String args[]){
        Database conn = new Database();
        conn.createUser("popo", "po@po.com", "1234");
    }
    
    public boolean createUser(String username, String email, String password){
      try{
          c = DriverManager.getConnection(CONNECTION, p);
          String query = "insert into users(username, email, password) values (?, ?, ?)";
          
          PreparedStatement preparedStmt = c.prepareStatement(query);
          preparedStmt.setString(1, username);
          preparedStmt.setString(2, email);
          preparedStmt.setString(3, password);
          
          preparedStmt.execute();
          System.out.println("created user " + username);
          c.close();
          return true;
      } catch(Exception e){
          e.printStackTrace();
          return false;
      }
  }
    
    public List findUser(String email, String password){
      List userList = new ArrayList();

      try{
          String query = "SELECT * FROM users where email=? and password=?";
          c = DriverManager.getConnection(CONNECTION, p);

          PreparedStatement preparedStmt = c.prepareStatement(query);
          preparedStmt.setString(1, email);
          preparedStmt.setString(2, password);

          ResultSet rs = preparedStmt.executeQuery();
          ResultSetMetaData rsmd = rs.getMetaData();

          int rowCount = 0;
          if (rs.last()){
              rowCount = rs.getRow();
              rs.beforeFirst();
          }

          if (rowCount == 0 ){
              userList.add("none");
              return userList;
          }

          while (rs.next()){
              userList.add(rs.getInt(1));
              userList.add(rs.getString(2));
              userList.add(rs.getString(3));
          }

          return userList;
      } catch(Exception e){
          e.printStackTrace();
          userList.add("none");
          return userList;
      }
  }
    
}