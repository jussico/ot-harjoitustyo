package colorapp.ui;

import java.util.Properties;

import colorapp.domain.QuizService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorUi extends Application {
    private QuizService colorService;
    
    private Scene colorListScene;
    private Scene colorQuestScene;
    
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        colorService = new QuizService(properties);
    }
        
    @Override
    public void start(Stage primaryStage) {               
        // colorListScene
        
        VBox colorListPane = new VBox(10);
        HBox inputPane = new HBox(10);
        colorListPane.setPadding(new Insets(10));
                
        String username = "seikkailija";
        menuLabel.setText(username + " seikkailee");
        
        primaryStage.setScene(colorListScene);
        
        Button startButton = new Button("start the quest");    
        startButton.setOnAction(e->{            
            primaryStage.setScene(colorQuestScene);   
        });  
        
        colorListPane.getChildren().addAll(inputPane, startButton);       
        
        colorListScene = new Scene(colorListPane, 300, 250);    
   
        // colorQuest scene
        
        VBox colorQuestPane = new VBox(10);
        
        HBox newQuessPane = new HBox(10);
        newQuessPane.setPadding(new Insets(10));
        TextField newQuessInput = new TextField(); 
        Label newQuessLabel = new Label("quess");
        newQuessLabel.setPrefWidth(100);
        newQuessPane.getChildren().addAll(newQuessLabel, newQuessInput);
             
        Label quessCreationMessage = new Label();
        
        Button createNewQuessButton = new Button("quess");
        createNewQuessButton.setPadding(new Insets(10));

        createNewQuessButton.setOnAction(e->{
            String quess = newQuessInput.getText();
   
            String oikea = "FFFFFF"; // TODO: jostain
            
            if ( quess.length()!=6 ) {
                quessCreationMessage.setText("RGB color must be 6 digits - i.e. FF00FF");
                quessCreationMessage.setTextFill(Color.RED);              
            } else if ( colorService.makeQuess(quess, oikea) ){
                quessCreationMessage.setText("");                
                primaryStage.setScene(colorQuestScene);      
            }
 
        });  
        
        colorQuestPane.getChildren().addAll(quessCreationMessage, newQuessPane, createNewQuessButton); 
       
        colorQuestScene = new Scene(colorQuestPane, 300, 250);
        
        
        // seutp primary stage
        
        primaryStage.setTitle("ColorQuests");
        primaryStage.setScene(colorListScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");            
        });
    }

    @Override
    public void stop() {
      // tee lopetustoimenpiteet täällä
      System.out.println("sovellus sulkeutuu");
    }    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
