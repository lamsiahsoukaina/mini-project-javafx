package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.PreparableStatement;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {
    
            static final String url = "jdbc:mysql://localhost:3306/retaurant";
            static final String username = "root";
            static final String password = "";
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            //JOptionPane.showMessageDialog(null, "Connected");
        } catch ( SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed");
            e.printStackTrace();
        }
        return conn;
    }
   
    public static void main(String[] args) {
        connect();
    }
    public static int count(String col, String table){
        Connection con=connect();
        try {
           PreparedStatement ps=con.prepareStatement("select count("+col+") from " +table);
           ResultSet rs= ps.executeQuery();

           if(rs.next()){
                return Integer.parseInt(rs.getString(1));
           }
        } catch (Exception e) {
            
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                
            }
        }



        return 0;
    }

    public static boolean insert(String table,int id,String name,String type,int cost){
        Connection con=connect();

        try {PreparedStatement ps = con.prepareStatement("INSERT INTO " + table + " VALUES (?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, type);
        ps.setInt(4, cost);
        return ps.execute();
        } catch (Exception e) {
           if(table.equals("Drinks")){
            JOptionPane.showMessageDialog(null,"Drink number or  Drink name already exist !");
           }else{
            JOptionPane.showMessageDialog(null,"Meal number or Meal name already exist !");
           }
        }finally{
            try {
                 con.close(); 
            } catch (Exception e) {

            }
        }

        return true;
    }
    public static ObservableList<Dranks> getDranks(){
        Connection conn=connect();
        ObservableList<Dranks> list =FXCollections.observableArrayList();
        try {
           PreparedStatement ps= conn.prepareStatement("select * from Drinks");
           ResultSet rs= ps.executeQuery();
           while(rs.next()){
              list.add(new Dranks(rs.getInt("numD"), rs.getString("nameD"), rs.getString("typeD"), rs.getInt("costD")));

           }
        } catch (Exception e) {
          
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
              
            }
        }
    return list;
}




 public static ObservableList<Meals> getMeals(){
        Connection conn=connect();
        ObservableList<Meals> list =FXCollections.observableArrayList();
        try {
           PreparedStatement ps= conn.prepareStatement("select * from Meals");
           ResultSet rs= ps.executeQuery();
           while(rs.next()){
              list.add(new Meals(rs.getInt("numM"), rs.getString("nameM"), rs.getString("typeM"), rs.getInt("costM")));

           }
        } catch (Exception e) {
          
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
              
            }
        }
    return list;
}

    public static boolean update(String table,String where,String name,String type,int cost){
        Connection con=connect();
        String sql=null;
        if (table.equals("Drinks")) {
            sql = "UPDATE " + table + " SET nameD = ?, typeD = ?, costD = ?" + where;
        } else {
            sql = "UPDATE " + table + " SET nameM = ?, typem = ?, costM = ?" + where;
        }
        

        try { 
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setDouble(3, cost);
            return !ps.execute();
        } catch (Exception e) {
             if(table.equals("Drinks")){
             JOptionPane.showMessageDialog(null,"Drink number orLLL  Drink name already exist !");
           }else{
             JOptionPane.showMessageDialog(null,"Meal number orLLL Meal name already exist !");
           }
        }finally{
            try {
                con.close();
            } catch (Exception e) {
                
            }
        }
        return false;
    }

    public static boolean delete(String table, String where) {
        try (Connection con = connect()) {
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM " + table + " WHERE " + where)) {
                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    

}
