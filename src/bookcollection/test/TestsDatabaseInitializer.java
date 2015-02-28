package bookcollection.test;

import bookcollection.sqlite.SQLite;

public class TestsDatabaseInitializer extends SQLite {
	public TestsDatabaseInitializer(){
		super();
	}
	
	public void insertIntoTagsTable(){
		try {
			setupConnection();
            
            stmt = connection.createStatement();
            
            String sql = "";
            String tags = "math history science";
            for (String word : tags.split("\\s+")){
            	sql = String.format("insert into tb_tags values ('%s',NULL);", word);
            	stmt.addBatch(sql);
            }
            stmt.executeBatch();
            
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}
	
	public void insertIntoBooksTable(){
		try {
			setupConnection();
            
            stmt = connection.createStatement();
            
            String sql = "insert into tb_books values ('Booth2008','The craft of research', 'The University of Chicago Press', 2008)";
            stmt.addBatch(sql);
            stmt.executeBatch();
            
            closeConnection();
        } catch ( Exception e ) {handleDatabaseError(e);}
	}

}
