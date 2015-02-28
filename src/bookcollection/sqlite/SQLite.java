package bookcollection.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookcollection.config.DirectoryInitializer;

public class SQLite {
	protected File db_file;
	protected Connection connection;
	protected Statement stmt;
	
	public SQLite(){
		db_file = new File("directory/info.db");
		connection = null;
		stmt = null;
	}
	
	
	protected void setupConnection() throws SQLException{
		String url = "jdbc:sqlite:" + db_file;
		connection = DriverManager.getConnection(url);
		stmt = connection.createStatement();
	}
	
	protected void closeConnection() throws SQLException{
		stmt.close();
		connection.close();
	}
	
	protected void handleDatabaseError(Exception e){
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		System.exit(0);
	}
	
	public void createTables(){
		createTagsTable();
		createAuthorshipsTable();
		createAuthorsTable();
		createBooksTable();
		createCategoriesTable();	
		createFilesTable();
	}
	
	public void createTagsTable(){
		Connection c= null;
        Statement stmt = null;
        
        DirectoryInitializer dirInit;
        
        dirInit = new DirectoryInitializer(new File("directory"));
        
        dirInit.initDir();
       
        String url = "jdbc:sqlite:" + db_file;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(url);
            
            stmt = c.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_tags" +
                    "(tag TEXT," +
                    "description TEXT," +
                    "PRIMARY KEY (tag)" +
                    ")";
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();            
        } catch ( Exception e ) {handleDatabaseError(e);}
    System.out.println("Opened database successfully");
	}
	
	public void createAuthorshipsTable(){
		try {
            setupConnection();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_authorships" +
                    "(author_id TEXT," +
                    "book_id TEXT," +
                    "PRIMARY KEY (author_id, book_id)" +
                    ")";
            
            stmt.executeUpdate(sql);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public void createAuthorsTable(){
		try {
            setupConnection();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_authors (" +
                    "author_id TEXT," +
                    "first_name TEXT," +
                    "middle_name TEXT," +
                    "last_name TEXT," +
                    "PRIMARY KEY (author_id)" +
                    ")";
            
            stmt.executeUpdate(sql);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public void createBooksTable(){
		try {
            setupConnection();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_books (" +
                    "book_id TEXT," +
                    "title TEXT," +
                    "publisher TEXT," +
                    "year int," +
                    "PRIMARY KEY (book_id)" +
                    ")";
            
            stmt.executeUpdate(sql);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public void createCategoriesTable(){
		try {
            setupConnection();
            
            String sql = "CREATE TABLE IF NOT EXISTS tb_categories (" +
                    "book_id TEXT," +
                    "tag TEXT," +
                    "PRIMARY KEY (book_id, tag)" +
                    ")";
            
            stmt.executeUpdate(sql);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public void createFilesTable(){
		try {
            setupConnection();
            
            String sql = "PRAGMA foreign_keys = ON;" +
            		"CREATE TABLE IF NOT EXISTS tb_files (" +
            		"book_id TEXT," +
                    "path TEXT," +
                    "PRIMARY KEY (path)," +
                    "foreign key (book_id) references tb_books(book_id)" +
                    ")";
            
            stmt.executeUpdate(sql);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public String [] getAllTags(){
		
        ResultSet result = null;
        String [] outs = null;

        try {
            setupConnection();
            
            String sql = "select tag from tb_tags;";
            
            result = stmt.executeQuery(sql);
            
            List<String> tp = new ArrayList<String>();

            while (result.next()){
            	tp.add(result.getString("tag"));
			}
            
            outs = tp.toArray(new String[tp.size()]);
    		
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
        
        return outs;
	}

}
