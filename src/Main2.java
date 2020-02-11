import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main2{

  public static void blubb(){
  
    try{
        
        Stream<Path> paths = Files.walk(Paths.get("./src/bilder"));
        //paths.filter(Files::isRegularFile).forEach(System.out::println);
        Object[] pfade = paths.toArray();        
        //Path bla = (Path) pfade[0];
        //System.out.println("Pfad: " + bla.toString());
        System.out.println("Pfad: " + pfade[0].toString());
        
        
    } 
      
    catch(Exception e) {
      
       e.printStackTrace();
          
    }
  
  }
    
    
  public static void main(String[] args) {
    
    Main2.blubb();
       
  }
  
}
