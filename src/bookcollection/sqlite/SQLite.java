package bookcollection.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import bookcollection.config.DirectoryInitializer;

public class SQLite {
	public static void test_sqlite3(){
		Connection c= null;
        Statement stmt = null;
        
        DirectoryInitializer dirInit;
        
        dirInit = new DirectoryInitializer(new File("directory"));
        
        dirInit.initDir();
       
        
        
        String db_file = "info.db";
        String url = "jdbc:sqlite:"+db_file;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            
            stmt = c.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_tags" +
                    "(tag TEXT," +
                    "description TEXT)";
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();            
        } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Opened database successfully");
	}

}
