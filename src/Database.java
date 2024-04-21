import java.sql.*;

public class Database {
    Connection con;
    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
        int titleNum = 0;
        try {
            String sql = "SELECT artist.name, COUNT(album.title) FROM artist INNER JOIN album on artist.artistid=album.artistid WHERE artist.name='"+artistName+"' GROUP BY album.artistid, artist.name;";
            PreparedStatement prepstmt = con.prepareStatement(sql);
            ResultSet rs = prepstmt.executeQuery();
            while (rs.next()){
                titleNum=rs.getInt("count");
            }

            //Implement this method
            //Prevent SQL injections!
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
        //Implement this method
        //5 sec timeout
        try{
            String URL= Credentials.URL;
            String USERNAME=Credentials.USERNAME;
            String PASSWORD=Credentials.PASSWORD;
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return con.isValid(5);

        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        return false;
    }
}