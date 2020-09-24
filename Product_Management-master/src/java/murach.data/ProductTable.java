package murach.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import murach.business.Product;
//;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class ProductTable {

	/*
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }
    */

    public static List<Product> selectProducts() {
		                ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM products ";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            ArrayList<Product> productarray = new ArrayList<Product>();
            Product product = null;
            while (rs.next()) {
                product = new Product();
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(Double.parseDouble(rs.getString("price")));
                productarray.add(product);
            }
            /*
            query = "SET FOREIGN_KEY_CHECKS=1 ";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            */
            return productarray;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(statement);
            pool.freeConnection(connection);
        }       // remove this line and implement the logic
    }

    public static Product selectProduct(String productCode) {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products "
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                product.setCode(rs.getString("code"));
                product.setDescription(rs.getString("description"));
                product.setPrice(Double.parseDouble(rs.getString("price")));
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean exists(String productCode) {
		//throw new NotImplementedException(); // remove this line and implement the logic
                        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM products "
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
    
    private static void saveProducts(List<Product> products) {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO products (code, description, price) "
                + "VALUES (?, ?, ?)";  
        Iterator<Product> it = products.iterator();
        try {
            while(it.hasNext()) {
                Product product = it.next();
                ps = connection.prepareStatement(query);
                ps.setString(1, product.getCode());
                ps.setString(2, product.getDescription());
                ps.setString(3, Double.toString(product.getPrice())); 
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void insertProduct(Product product) {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO products (code, description, price) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setString(3, Double.toString(product.getPrice()));
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void updateProduct(Product product) {
		//throw new NotImplementedException(); // remove this line and implement the logic
                        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE products SET "
                + "description = ?, "
                + "price = ? "
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getDescription());           
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getCode());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
          
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void deleteProduct(Product product) {
		//throw new NotImplementedException(); // remove this line and implement the logic
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM products "
                + "WHERE code = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, product.getCode());
            ps.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println(e);
        
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
}
