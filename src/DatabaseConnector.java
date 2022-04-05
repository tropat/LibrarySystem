import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
public class DatabaseConnector {
	private static Connection con = null;
	public static void connectToDatabase() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","java","java");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static ResultSet execute(String statement) {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(statement);	
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return rs;
	}
	public static Connection getCon() {
		return con;
	}
}
