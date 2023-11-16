package ProjectFX;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Customer_loginController implements Initializable {
     
    PreparedStatement stmt = null;
    Connection conn = null;
    private Stage stage;
    private Scene scene;
    private Parent root1;
    @FXML
    private AnchorPane customer_login;
    @FXML
    private Button switch_main;
    @FXML
    private TextField cust_username;
    
    String id,fname,lname,cr_date,active,country,address_id,address,district,city,p_code,phone,film_pk,series_pk,full_pk,city_id,country_id;
    
    ObservableList<String> oblist = FXCollections.observableArrayList();
    @FXML
    private Label not_empty_label;
    @FXML
    private PasswordField cust_password;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switch_main(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("projectFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void to_customer_page(ActionEvent event) throws IOException {
        int index;
        oblist.clear();
        not_empty_label.setOpacity(0);
        
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT email FROM customer");
          
            while(rs.next()){
            oblist.add(rs.getString("email"));
         }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        index = oblist.indexOf(cust_username.getText());
        String c1,c2;
        c1 = cust_username.getText();
        c2 = cust_password.getText();
        if(index!=-1 && c1.equals(c2)){
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                ResultSet rs = conn.createStatement().executeQuery("SELECT customer_id,first_name,last_name,address_id,active,create_date,film_pack,series_pack,full_pack FROM customer "
                    + "WHERE email = '"+cust_username.getText()+"'");

                while(rs.next()){
                    id = rs.getString("customer_id");
                    fname = rs.getString("first_name");
                    lname = rs.getString("last_name");
                    cr_date = rs.getString("create_date");
                    active = rs.getString("active");
                    address_id = rs.getString("address_id");
                    film_pk = rs.getString("film_pack");
                    series_pk = rs.getString("series_pack");
                    full_pk = rs.getString("full_pack");
                }             
             } 
            catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                 ResultSet rs1 = conn.createStatement().executeQuery("SELECT address,district,city_id,postal_code,phone FROM address "
                    + "WHERE address_id = '"+address_id+"'");
               
                while(rs1.next()){
                    address = rs1.getString("address");
                    district = rs1.getString("district");
                    city_id = rs1.getString("city_id");
                    p_code = rs1.getString("postal_code");
                    phone = rs1.getString("phone");
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
          
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT city,country_id FROM city "
                    + "WHERE city_id = '"+city_id+"'");
                
                while(rs2.next()){
                    city = rs2.getString("city");
                    country_id = rs2.getString("country_id");
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                 
                ResultSet rs3 = conn.createStatement().executeQuery("SELECT country FROM country "
                    + "WHERE country_id = '"+country_id+"'"); 

                while(rs3.next()){
                    country = rs3.getString("country");
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query = "UPDATE current_customer SET customer_id ='"+id+"',first_name='"+fname+"',last_name='"+lname+"'"
                    + ",email = '"+cust_username.getText()+"',create_date = '"+cr_date+"',active = '"+active+"',country ='"+country+"',address ='"+address+"'"
                    + ",district ='"+district+"',city = '"+city+"',postal_code = '"+p_code+"',phone = '"+phone+"',film_pack ='"+film_pk+"',series_pack ='"+series_pk+"'"
                    + ",full_pack ='"+full_pk+"',in_use = 1";
             
                stmt=conn.prepareStatement(query);
                stmt.executeUpdate();

             } catch (SQLException ex) {
                Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Parent root1 = FXMLLoader.load(getClass().getResource("customer_page.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root1);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.show();
        }
        else{
          not_empty_label.setOpacity(1);
        }
    }

    @FXML
    private void registration(MouseEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("customer_registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root1);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.centerOnScreen();
        stage.show();
        
    }
    
}
