import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

  private View view;
  private Model model;

  public Controller(Model model, View view) {
    this.model = model;
    this.view = view;

    // Event-Handler registrieren
    this.view.getCountButton().setOnAction(event -> countAction(event));
    this.view.getshowImageButton().setOnAction(event -> setPicture(event));
    this.view.getBilderLaden().setOnAction(event -> bilderLadenEvent(event));
    this.view.getNaechstesBild().setOnAction(event -> naechstesBildEvent(event));
    this.view.getVorherigesBild().setOnAction(event -> vorherigesBildEvent(event));

    model.addObserver(this);
    this.view.getStage().show();
  
  }

  public void countAction(ActionEvent event) {
    model.tick();
  }

  @Override
  public void update(Observable o, Object arg) {
    if (model.getCountDown() == 1) {
      Alert alert = new Alert(Alert.AlertType.WARNING, 
              "Achtung, gleich!", ButtonType.OK);
      alert.show();
    }
  }
    
    private void setPicture(ActionEvent event) {
        
        //model.ladeBild(view.getTextFlied().getText());
       
    }
    
    private void bilderLadenEvent(ActionEvent event) {
        
        model.bilderArrayErstellen(view.getTextFlied().getText());
        System.out.println("Bilderarray erstellen!");
    
    }

    private void naechstesBildEvent(ActionEvent event) {
        
        model.naechstesBild();
        
    }

    private void vorherigesBildEvent(ActionEvent event) {
        
        model.vorherigesBild();
        
    }
}
