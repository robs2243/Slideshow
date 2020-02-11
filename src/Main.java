import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    
  @Override
  public void start(Stage primaryStage) throws Exception {

    Model model = new Model();
    View view = new View(model, primaryStage);
    new Controller(model, view);
  }
    
  public static void main(String[] args) {
    launch(args);
    
  }

}
