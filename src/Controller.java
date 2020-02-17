import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;

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
    this.view.getCanvas().setOnMousePressed(event -> mausAufCanvasGeklicked(event));
    this.view.getCanvas().setOnMouseDragged(event -> mausAufCanvasGezogen(event));

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
        //System.out.println(punkte.toString());
        this.view.getGraphicsContext().beginPath();
        
        for (PathElement elem : pfad.getElements()) {

            if (elem instanceof MoveTo) {

                this.view.getGraphicsContext().moveTo(((MoveTo) elem).getX(), ((MoveTo) elem).getY());

            }

            if (elem instanceof LineTo) {

                this.view.getGraphicsContext().lineTo(((LineTo) elem).getX(), ((LineTo) elem).getY());

            }

        }

        //this.view.getGraphicsContext().closePath();
        this.view.getGraphicsContext().setStroke(Color.GREEN);
        this.view.getGraphicsContext().stroke();

    }

    private void vorherigesBildEvent(ActionEvent event) {
        
        model.vorherigesBild();
        
        
    }

    Path pfad = new Path(); //ein Path-Objekt wird erstelle und mittels der 
                            // event-Methoden gefuellt
   
    private void mausAufCanvasGeklicked(MouseEvent event) {
        
        this.view.getGraphicsContext().beginPath();
        this.view.getGraphicsContext().moveTo(event.getX(), event.getY());
        MoveTo moveTo = new MoveTo();
        moveTo.setX(event.getX());
        moveTo.setY(event.getY());
        pfad.getElements().add(moveTo); //neues MoveTo-Objekt in Path eingefuegt
        this.view.getGraphicsContext().stroke();

    }

    private void mausAufCanvasGezogen(MouseEvent event) {
        //this.view.getGraphicsContext().beginPath();
        double size = 10;
        double x = event.getX() - size / 2; //Quaradmitte
        double y = event.getY() - size / 2;

        if (this.view.getLoeschenBox().isSelected()) {
            this.view.getGraphicsContext().clearRect(x, y, size * 2, size * 2); //Löschungsquadrat 10x
        } 
        else {
            this.view.getGraphicsContext().setFill(Color.RED);
            this.view.getGraphicsContext().setStroke(Color.RED);
            this.view.getGraphicsContext().setLineWidth(size); //Pinselgröße
            this.view.getGraphicsContext().lineTo(event.getX(), event.getY());
            
            LineTo lineTo = new LineTo();
            lineTo.setX(event.getX());
            lineTo.setY(event.getY());
            pfad.getElements().add(lineTo); //neues LineTo-Objekt in Path eingefuegt

            this.view.getGraphicsContext().stroke();
        }
    }
}
