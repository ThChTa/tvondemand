/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_exitController implements Initializable {

    PreparedStatement stmt = null;
    Connection conn = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit_true(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.centerOnScreen();
        stage.show();
        try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query = "UPDATE current_administrator SET in_use = 0";
             
                stmt=conn.prepareStatement(query);
                stmt.executeUpdate();

             } catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
}
