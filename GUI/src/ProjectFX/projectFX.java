
package ProjectFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class projectFX extends Application {
    Parent root;
    double xOffset, yOffset;

    @Override
    public void start(Stage stage) throws Exception {
       try{
        root =FXMLLoader.load(getClass().getResource("projectFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Project Vaseis");
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        
        stage.show();
        stage.centerOnScreen();
        
       }catch(Exception e){
           e.printStackTrace();
       }
        
        
    }   
    public static void main(String[] args){
        launch(args);
    }
}
