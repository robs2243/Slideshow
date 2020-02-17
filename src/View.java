
import java.io.FileInputStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class View implements Observer {

    private Model model;
    private Stage stage;
    private Label label;
    private Button countButton;
    private Button showImage;
    private ImageView bildIV;
    private TextField pfadTF;
    private String pfad;
    private Scene s;
    private Button bilderLaden;
    private Button naechstesBild;
    private Button vorherigesBild;
    private final Canvas canvas;
    private GraphicsContext g;
    private CheckBox loeschenBox; 
    private GridPane gridPane;

    public View(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;
        label = new Label();
        countButton = new Button("Count");
        showImage = new Button("Zeige Bild");
        bilderLaden = new Button("Bilder laden");
        naechstesBild = new Button("Nächstes Bild");
        vorherigesBild = new Button("Vorheriges Bild");
        loeschenBox = new CheckBox("Korrektur löschen");
        bildIV = new ImageView();
        pfadTF = new TextField();
        canvas = new Canvas(250,250);
        g = canvas.getGraphicsContext2D();
        canvas.setHeight(600.0);
        canvas.setWidth(600.0);
        //stage.setScene(new Scene(new VBox(bildIV, label, countButton, showImage, pfadTF)));
        //s = new Scene(new 300, 300);
        //////////////////
        //Creating a Grid Pane 
        gridPane = new GridPane();

        //Setting size for the pane  
        gridPane.setMinSize(1000, 1000);

        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid 
        gridPane.add(bildIV, 0, 0);
        gridPane.add(pfadTF, 0, 1);
        gridPane.add(label, 0, 2);
        gridPane.add(countButton, 1, 2);
        gridPane.add(vorherigesBild, 2, 3);
        gridPane.add(showImage, 2, 2);
        gridPane.add(bilderLaden, 3, 2);
        gridPane.add(naechstesBild, 3, 3);
        gridPane.add(loeschenBox, 3, 4);
        gridPane.add(canvas, 0, 0);

        //Creating a scene object 
        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        ////////////
        updateLabel();
        model.addObserver(this);
    }

    private void updateLabel() {

        label.setText("Countdown: " + model.getCountDown());
        pfadTF.setText("bilder"); //Default-Wert damit TextField nicht leer ist


    }

    private void updatePicture() {
        bildIV.setImage(model.getImage());
//        canvas = new Canvas(250,250);
//        gridPane.add(canvas, 0, 0);
//        g = canvas.getGraphicsContext2D();
//        canvas.setHeight(600.0);
//        canvas.setWidth(600.0);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        updateLabel();
        updatePicture();
        naechstesBild();
        vorherigesBild();
    }

    public Stage getStage() {
        return stage;
    }

    public Label getLabel() {
        return label;
    }

    public Button getCountButton() {
        return countButton;
    }

    public TextField getTextFlied() {

        return pfadTF;

    }

    public Button getshowImageButton() {

        return showImage;
    }

    public Button getBilderLaden() {

        return bilderLaden;
    }

    public Button getNaechstesBild() {

        return naechstesBild;

    }

    private void naechstesBild() {

        bildIV.setImage(model.getImage());

    }

    public Button getVorherigesBild() {

        return vorherigesBild;

    }
    
    public CheckBox getLoeschenBox(){
    
        return loeschenBox;
        
    }
    
    public Canvas getCanvas() {
    
        return canvas;
    
    }
    
    public GraphicsContext getGraphicsContext() {
        
        return g;
    
    }

    private void vorherigesBild() {

        bildIV.setImage(model.getImage());

    }

}
