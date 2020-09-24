package murach.data;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import murach.business.User;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserTable {
	
	//Static initializer, it runs when the class is intialized (it is executed once)
    /*static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    */
    
    public static void addRecord(User user) throws IOException {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (firstName, lastName, email, password) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static User getUser(String emailAddress) throws IOException {
        //throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        

        String query = "SELECT * FROM users "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, emailAddress);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<User> getUsers() throws IOException {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users ";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            ArrayList<User> userarray = new ArrayList<User>();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                userarray.add(user);
            }
            /*
            query = "SET FOREIGN_KEY_CHECKS=1 ";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            */
            return userarray;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }       
    }

    public static HashMap<String, User> getUsersMap() throws IOException {
		//throw new NotImplementedException(); // remove this line and implement the logic             
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM users ";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            HashMap<String, User> Usermap = new HashMap<String, User>();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                Usermap.put(user.getEmail(), user);
            }
            /*
            query = "SET FOREIGN_KEY_CHECKS=1 ";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            */
            return Usermap;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }       
    }
}
