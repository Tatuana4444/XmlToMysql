package dao;

import connection.GetConnection;
import org.apache.log4j.Logger;
import service.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseDao {

    private Statement statement;
    private Connection conn;
    private static final Logger logger = Logger.getLogger(Service.class);

    public DataBaseDao() throws SQLException {
        conn = GetConnection.create();
        statement = conn.createStatement();
    }
    public void insertUser(int id, String name,String login, String password, boolean isAdmin){
        try {
            String str = "INSERT INTO user(id,name,login,password,role)" +
                    " VALUES (" + id + ",'" + name + "','" +
                    login + "','" + password + "'," + isAdmin + "); ";
            statement.execute(str);
        }catch (SQLException e){
            logger.warn("User not imported to table", e);
            System.out.println("User not imported to table");
        }
    }

    public void insertProduct(int id, String name, String description, int price) {
        try {
            String str = "INSERT INTO product(id,name,description,price)" +
                    " VALUES (" + id + ",'" + name + "','" +
                    description + "'," + price + "); ";
            statement.execute(str);
        }catch (SQLException e){
            logger.warn("Product not imported to table", e);
            System.out.println("Product not imported to table");
        }
    }

    public void insertOrder(int id, int productId, int quantity, int userId) {
        try {
            String str = "INSERT INTO orders(id,product_id,quantity,user_id)" +
                    " VALUES (" + id + "," + productId + "," +
                    quantity + "," + userId +  "); ";
            statement.execute(str);
        }catch (SQLException e){
            logger.warn("Order not imported to table", e);
            System.out.println("Order not imported to table");
        }
    }
    public void destroyConnection(){
        try {
            statement.close();
            conn.close();
        }catch (SQLException e){
            logger.warn("Can't close connection", e);
            System.out.println("Can't close connection");
        }

    }
}
