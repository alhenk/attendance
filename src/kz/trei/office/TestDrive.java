package kz.trei.office;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import kz.trei.office.util.DateStamp;
import kz.trei.office.util.TimeStamp;

public class TestDrive {
	private static final Logger LOGGER = Logger.getLogger(TestDrive.class);

	public static void main(String[] args) {

		LOGGER.info(new DateStamp());
		LOGGER.info(new TimeStamp());
		String tableName = "A";

		Connection conn = null;
		Statement stat = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:./resources/staff");
			stat = conn.createStatement();
			DatabaseMetaData dbm = conn.getMetaData();
			
			ResultSet tables = dbm.getTables(null, null, tableName , null);
			if (tables.next()) {
				System.out.println("Table "+tableName+" exist");
			} else {
				stat.execute("create table A (id int primary key, field2 int, field3 int)");
				stat.execute("insert into A values(1, 500, 1000)");
				stat.execute("insert into A values(2, 400,  1200)");
				stat.execute("insert into A values(3, 300,1295)");
				stat.execute("insert into A values(4, 100, 1295)");
				stat.execute("insert into A values(5, 100, 500)");
			}

			ResultSet rs;
			rs = stat.executeQuery("select * from A");
			while (rs.next()) {
				System.out.print(rs.getString("id") + "\t");
				System.out.print(rs.getString("field2") + "\t");
				System.out.println(rs.getString("field3"));
			}

			System.out.println(conn);
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		

	}
}
