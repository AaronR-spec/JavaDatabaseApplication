/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package databaseca;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author aaron
 */
public class DatabaseCa {
    static Scanner keyboard = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("(Database Java Application)");
        while(!exit)
        {
            System.out.println("\n(0) Back");
            System.out.println("(1) Search Products");
            System.out.println("(2) Delete Products");
            System.out.println("(3) Add Products");
            System.out.println("(4) Update Employee Name");
            System.out.println("(5) Purchase Game (NOT WORKING)");
            System.out.print("Select Option: ");
            int option = keyboard.nextInt();
            switch(option)
            {
                case 0:
                    System.out.println("Goodbye...");
                    keyboard.close();
                    exit = true;
                    break;
                case 1:
                    searchProduct();
                    break;            
                case 2:
                    deleteProduct();
                    break;  
                case 3:
                    addProduct();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    buyGame();
                    break;
            }
            if(option > 5 || option <0)
            {
                System.out.println(option +" Is Not An Option..");
            }
        
        }
    }
    public static void searchProduct()
    {
     keyboard.nextLine();
     System.out.print("\nSearch for a product: ");
      String title = keyboard.nextLine();
        Connection con = null;
        Statement st = null;
       ResultSet rs = null;
    
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs_ca2","root","");
        st=con.createStatement();
        String q="Select * from products where title like '%"+title+"%' ;";
        rs=st.executeQuery(q);
        while(rs.next())
        {
                    String name = rs.getString("title");
                    String rating = rs.getString("rating");
                    String genre = rs.getString("genre");
                    String developer = rs.getString("developer_studio");
                    String price = rs.getString("price");
                    String stock = rs.getString("stock");
                            
                    System.out.println("\nName: "+name+"\nRating: "+rating+"/5.0\nGenre:"+genre+"\nDeveloper: "+developer+"\nPrice: €"+price+"\nStock: "+stock);
        }
        
        }
        catch(SQLException e)
        {
            System.err.println("Error in database");;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Error in class not found");;
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { /* ignored */}
    }
    }
            
}
    public static void addProduct()
    {
       String title = "";
        double rating = 0.0;
        String genre = "";
       String studio = "";
       double price = 0.0;
       int stock = 0;
       keyboard.nextLine();
            System.out.print("\nTitle: ");
            title = keyboard.nextLine();
            System.out.print("Rating: ");
            rating = keyboard.nextDouble();
            keyboard.nextLine();
            System.out.print("Genre: ");
            genre = keyboard.nextLine();
           System.out.print("Development Studio: ");
           studio = keyboard.nextLine();
            System.out.print("Price: ");
            price = keyboard.nextDouble();
            System.out.print("Stock: ");
           stock = keyboard.nextInt();
        Connection con = null;
        Statement st = null;
       ResultSet rs = null;
    
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs_ca2","root","");
        st=con.createStatement();
        String insert="INSERT INTO Products(Title, Rating, Genre, Developer_Studio, Price, Stock) "
       + "VALUES ('"+title+"','"+rating+"','"+genre+"','"+studio+"','"+price+"',"+stock+");";
        String q ="Select * from products where title ='"+title+"' ;";
        st.executeUpdate(insert);
        rs=st.executeQuery(q);
        while(rs.next())
        {
                    title = rs.getString("title");
                    genre = rs.getString("genre");
                    price = rs.getDouble("price");
                    studio = rs.getString("developer_studio");
                    stock = rs.getInt("stock");
                            
                    System.out.println("\nName: "+title+"\nRating: "+rating+"/5.0\nGenre:"+genre+"\nDeveloper: "+studio+"\nPrice: €"+price+"\nStock: "+stock);
                    System.out.println("\nProduct added");
        }
        }
        catch(SQLException e)
        {
            System.err.println("Error in database");;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Error in class not found");;
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { /* ignored */}
    }
    }
    }
    public static void deleteProduct()
    {
        keyboard.nextLine();
        System.out.print("\nTitle of product: ");
        String title = keyboard.nextLine();
        Connection con = null;
        Statement st = null;
       ResultSet rs = null;
    
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs_ca2","root","");
        st=con.createStatement();
        String q="DELETE from products where title = '"+title+"' ;";
        int count = st.executeUpdate(q);
                System.err.println("Rows Effected " + count+"\n");
                if(count > 0)
                {
                    System.out.println("\n"+title+" deleted from database");
                }
        }
        catch(SQLException e)
        {
            System.err.println("Error in database");;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Error in class not found");;
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { /* ignored */}
    }
    }
            
}
    public static void updateEmployee()
    {
       keyboard.nextLine();
        System.out.print("Employee to update: ");
        String name = keyboard.nextLine();
        System.out.print("New Name: ");
        String newName = keyboard.nextLine();
        Connection con = null;
        Statement st = null;
       ResultSet rs = null;
    
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs_ca2","root","");
        st=con.createStatement();
        String q="Update employee set name = '"+newName+"' where name = '"+name+"';";
        int count = st.executeUpdate(q);
                System.err.println("Rows Effected " + count+"\n");
                if(count > 0)
                {
                    System.out.println("\nEmployee "+name+" updated to "+newName);
                }
        }
        catch(SQLException e)
        {
            System.err.println("Error in database");;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Error in class not found");;
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* ignored */}
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { /* ignored */}
    }
    }
            

    }
    
    public static void buyGame()
    {
        double price =0.0;
        double newBalance = 0.0;
        double balance = 0.0;
        int count=0;
      //  keyboard.nextLine();
      //  System.out.print("\nTitle of product: ");
      //  String title = keyboard.nextLine();
        Connection con = null;
        Statement st = null;
       ResultSet rs = null;
    
            try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs_ca2","root","");
        st=con.createStatement();
        String q="Select balance from customer where Customer_id != 1 ;";
        rs=st.executeQuery(q);
        balance = rs.getDouble("balance");
        System.out.println("balance = " +balance);

        if(balance > 0){
        q="Select price from products where Title = 'Minecraft' ;";
        rs=st.executeQuery(q);
        price = rs.getDouble("price");
            if(balance < price)
            {
                System.out.println("Not Enough Funds");
                return;
            }
            newBalance = balance - price;
            q="UPDATE Customer SET Balance = '"+newBalance+"' WHERE Customer_id = 1;";
            System.out.println("Update customer balance");
             count=st.executeUpdate(q);
             if(count>0)
             {
                if(count>0){
                System.err.println("Rows Effected " + count+"\n");
                }
                else
                {
                    System.out.println("Update Did Not Go Through");
                }
             }
        }
        else
        {
            System.out.println("No Available Funds");
            return;
        }
//         count = st.executeUpdate(q);
//                System.err.println("Rows Effected " + count+"\n");
//                if(count > 0)
//                {
//                    System.out.println("\n"+title+" deleted from database");
//                }
        }
        catch(SQLException e)
        {
            System.err.println("Error in database");;
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("Error in class not found");;
        }finally {
    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException e) { }
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { }
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException e) { }
    }
            
}
    }
}
