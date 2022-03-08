//this class connects the UI to the database
//it holds all the insert, update, delete methods

//libraries required
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Database {
    //class variable
    private static Connection con;

    public static Connection getConnection() {
        //establishing connection
        try {
            String url = "jdbc:mysql://localhost:3306/todoListDB";
            String username = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, username,password);
            return conn;

        } catch (Exception e) { System.out.println(e.getMessage()); }
        return null;
    }

    //inserts the task to database
    public static void insertInTable(String taskdesc) throws SQLException {
        try{
            String query =("INSERT INTO tasklist(taskdesc) VALUES('"+taskdesc+"')");
            con = getConnection();
            PreparedStatement insert = con.prepareStatement(query);
            insert.execute();
            insert.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            con.close();
        }
    }

    //retrieves the task list from database
    public static ArrayList<Task> readFromTable() {
        try {
            con = getConnection();
            PreparedStatement select = con.prepareStatement("SELECT * FROM tasklist");

            ArrayList<Task> array = new ArrayList<>();
            ResultSet result = select.executeQuery();

            while (result.next()) {
                int id= result.getInt(1);
                String taskdesc= result.getString(2);
                boolean completed= result.getBoolean(3);
                array.add(new Task(id,taskdesc,completed));
            }
            select.close();
            con.close();
            return array;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //updates the task list in database
    public static void updateInTable(String task, boolean comp, int id) {
        try{
            con = getConnection();
            String query = "UPDATE tasklist SET taskdesc = ?, completed= ? WHERE id = ?";
            PreparedStatement update = con.prepareStatement(query);
            update.setString(1,task);
            update.setBoolean(2,comp);
            update.setInt(3,id);

            // executing prepared stmt
            update.executeUpdate();
            update.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //deletes the task or task status from database
    public static void deleteFromTable(int id) {
        try{
            con = getConnection();
            String query= "DELETE FROM tasklist WHERE id=?";
            PreparedStatement delete = con.prepareStatement(query);

            delete.setInt(1,id);
            delete.executeUpdate();
            delete.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}












