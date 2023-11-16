/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class ProjectFXMLController implements Initializable {
    
    
    private Parent root1;
    @FXML
    private AnchorPane login;
    @FXML
    private StackPane parent_container;
    @FXML
    private Button customer_button;
    @FXML
    private AnchorPane main_page;
    @FXML
    private AnchorPane root;
    @FXML
    private Button employee_button;
    @FXML
    private Button admin_button;
    @FXML
    private ImageView filmdisk;
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }      
    
    @FXML
    private void slide_back(ActionEvent event) {
         RotateTransition rt = new RotateTransition(Duration.millis(630), filmdisk);
         rt.setByAngle(-45);
         rt.setCycleCount(1);
         ;
         rt.setAutoReverse(true);
 
         rt.play();
        
         TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),login);
         translate.setToX(-10);
         translate.play();
    }

    @FXML
    private void switch_customer_login(ActionEvent event) throws IOException {
     try{ 
        root1 = FXMLLoader.load(getClass().getResource("customer_login.fxml"));
        Scene scene = customer_button.getScene();
        
        root1.translateYProperty().set(scene.getHeight());
        parent_container.getChildren().add(root1);
        
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root1.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
           parent_container.getChildren().remove(login);
        });
        timeline.play();
     }catch(Exception e){
           e.printStackTrace();
       }
     
    }

    @FXML
    private void switch_employee_login(ActionEvent event) {
        try{ 
        root1 = FXMLLoader.load(getClass().getResource("employee_login.fxml"));
        Scene scene = employee_button.getScene();
        
        root1.translateYProperty().set(scene.getHeight());
        parent_container.getChildren().add(root1);
        
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root1.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
           parent_container.getChildren().remove(login);
        });
        timeline.play();
     }catch(Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    private void switch_admin_login(ActionEvent event) {
        try{ 
        root1 = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        Scene scene = admin_button.getScene();
        
        root1.translateYProperty().set(scene.getHeight());
        parent_container.getChildren().add(root1);
        
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root1.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.4), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(event1->{
           parent_container.getChildren().remove(login);
        });
        timeline.play();
     }catch(Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    private void filmDisk(MouseEvent event) {
        RotateTransition rt = new RotateTransition(Duration.millis(630), filmdisk);
        rt.setByAngle(45);
        rt.setCycleCount(1);
        ;
        rt.setAutoReverse(true);
 
        rt.play();
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),login);
        translate.setToX(150);
        translate.play();
    }
    
}
