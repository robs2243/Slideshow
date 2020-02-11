import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class Model extends Observable {

  private int countDown;
  private Image img;
  private Object[] pfade;
  //private ListIterator<Object> it;
  private ListIterator<String> it;
  protected int bilderIndex;
  protected List<String> result = new ArrayList<String>();

  public Model() {
    countDown = 10;
    img = null;
    
  }

  public int getCountDown() {
    return countDown;
  }

  public void tick() {
    
    if (countDown > 0) {
      countDown--;
      setChanged();
    }
    notifyObservers();
  
  }

 
  public Image getImage() {
      return img;
  }
  
  
  public void ladeBild(String pfadVomBild) {
  
      System.out.println(pfadVomBild);
      
      //URL myURL = new URL(pfadVomBild);
      try {
      img = new Image(new FileInputStream(pfadVomBild));
      }
      catch(Exception ex) {}
      setChanged();  //geerbt - legt fest, dass das beochtbare Objekt geändert hat
      notifyObservers(); //geerbt - Beobachter benachrichten
        
  }
  
  public void bilderArrayErstellen(String pfadZumBilderOrdner){
      
      try {        
        Stream<Path> bilderPfadeImOrdner = Files.walk(Paths.get(".\\src\\bilder"));
        result = bilderPfadeImOrdner.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
                
        it = result.listIterator();

      }
      catch(Exception ex) {
      
          ex.printStackTrace();    
      }

      setChanged();
      notifyObservers();
  
  }
    
    void naechstesBild() {
        System.out.println("nächste Bild geklickt!");
        try {
        if(it.hasNext()) {
            String pfadZuBild = it.next();
            //InputStream inputStream = Files.newInputStream(pfadZuBild);
            ladeBild(pfadZuBild);
        }
        }
        
        catch(Exception ex) {
        
            ex.printStackTrace();
            
        }
        
        setChanged();
        notifyObservers();
      
    }

    void vorherigesBild() {
        System.out.println("letztes Bild geklickt!");
        try {
        if(it.hasPrevious()) {
            String pfadZuBild = it.previous();
            //InputStream inputStream = Files.newInputStream(pfadZuBild);
            ladeBild(pfadZuBild);
        }
        }
        
        catch(Exception ex) {
        
            ex.printStackTrace();
            
        }
        
        setChanged();
        notifyObservers();
    }
    /*
        public void ladeBild(String pfadVomBild) {

        //pfadVomBild.replace("src\\", "");
        System.out.println(pfadVomBild);

        //img = new Image(getClass().getResource(pfadVomBild).toString());
        img = new Image("file:" + pfadVomBild);

        System.out.println("kjhfds");
        setChanged();  //geerbt - legt fest, dass das beochtbare Objekt geändert hat
        notifyObservers(); //geerbt - Beobachter benachrichten

    }
  
  
    public void naechstesBild(){
        bilderIndex++;
        if(bilderIndex == result.size()){
            bilderIndex = 0;
        }
        ladeBild(result.get(bilderIndex));
    }
    
       public void verherigesBild() {

        bilderIndex--;
        
        if(bilderIndex == -1){
            bilderIndex = result.size()-1;
        }
        ladeBild(result.get(bilderIndex));

    }
    

    public List<String> allesAusDemOrdnerLaden(String ordnerPfad){

        try (Stream<Path> walk = Files.walk(Paths.get(ordnerPfad))) {

            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            result.forEach(System.out::println);
            for(int i=0;i<result.size();i++){
                char[] chars= result.get(i).toCharArray();
                for(int y=0;y<chars.length;y++){
                    if(chars[y]=='\\'){
                        chars[y]='/';
                    }
                }
                result.remove(i);
                result.add(i,String.valueOf(chars                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ));
            }
            bilderIndex=0;
            img = new Image("file:"+result.get(bilderIndex));
            setChanged();
            notifyObservers();
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
   */
       
}
