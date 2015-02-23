package bookcollection.gui;

import java.awt.EventQueue;
import bookcollection.config.DirectoryInitializer;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;

public class MainWindows {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows window = new MainWindows();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	test_sqlite3();
	}

	/**
	 * Create the application.
	 */
	public MainWindows() {
		initialize();
	}
	
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
