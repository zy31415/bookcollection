/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcollection.config;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author zy
 */
public class DirectoryInitializer {
    private File dir;
    private File configDir;
    
    public DirectoryInitializer(File directory){
        dir = directory;
        configDir = new File(dir.toString() + "/.tagadir/");
    }
    
    public void initDir(){
        createConfigDir();
        
    }
    
    public boolean ifDirExists(){
        return dir.exists();
    }
    
    public void createConfigDir(){
        configDir.mkdirs();
    }
    
    public boolean ifConfigDirExists(){
        return configDir.exists();
    }
    
    public void initDataBase(){
        
    }
}
