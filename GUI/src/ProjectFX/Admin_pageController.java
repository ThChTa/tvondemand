/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class Admin_pageController implements Initializable {
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private Pane moving_pane;
    @FXML
    private BorderPane bp;
    @FXML
    private Pane ap;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField address;
    @FXML
    private TextField district;
    @FXML
    private TextField pcode;
    @FXML
    private TextField phone;
    @FXML
    private Label city;
    @FXML
    private Label not_empty_label1;
    @FXML
    private Label not_empty_label;
    @FXML
    private Label gender;
    @FXML
    private Label admin_profile;
    @FXML
    private Label admin_customers;
    @FXML
    private Label admin_logs;
    @FXML
    private Label admin_catalog;
    @FXML
    private Label admin_actors;
    @FXML
    private Label admin_extra_options;
    @FXML
    private Label admin_exit;

    private Scene scene;
    @FXML
    private Label admin_employees;
    @FXML
    private Label admin_admins;
    @FXML
    private Label admin_incomes;
    @FXML
    private ComboBox<String> combo_city;
    @FXML
    private Label salary;
    @FXML
    private TextField email;
    int cur_adm,address_id,city_new,country_new;
    String country_name;
    ObservableList<String> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
     public void initialize(URL url, ResourceBundle rb) {
        loadPage("admin_customers");
        loadPage("admin_logs");
        loadPage("admin_catalog");  //preload ton selidon gia pio smooth animations
        loadPage("admin_actors");
        loadPage("admin_extra_options");
        loadPage("admin_employees");
         
        bp.setCenter(ap);
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sameDayLastMonth = now.minusMonths(1);
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT admin_id,first_name,last_name,email,country,address,district,city,postal_code,phone,salary,gender FROM current_administrator");
          
            while(rs.next()){
                fname.setText(rs.getString("first_name"));
                lname.setText(rs.getString("last_name"));
                email.setPromptText(rs.getString("email"));
                address.setText(rs.getString("address"));
                district.setText(rs.getString("district"));
                city.setText(rs.getString("city"));
                pcode.setText(rs.getString("postal_code"));
                phone.setText(rs.getString("phone"));
                salary.setText(rs.getString("salary"));
                gender.setText(rs.getString("gender"));
                cur_adm = rs.getInt("admin_id");
            }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist.add(rs.getString("city"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_city.setItems(oblist);
   
    }    

    @FXML
    private void profile_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(admin_profile.getLayoutY()-110);
         translate.setToX(admin_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         bp.setCenter(ap);
    }

    @FXML
    private void exit_come(MouseEvent event) throws IOException {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_exit.getLayoutY()-100);
        translate.setToX(admin_exit.getLayoutX()-32);
        moving_pane.setPrefWidth(70);
        moving_pane.setPrefHeight(25);
        translate.play();
        loadPage("admin_exit");
    }

    @FXML
    private void customers_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(admin_customers.getLayoutY()-110);
         translate.setToX(admin_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("admin_customers");
    }

    @FXML
    private void logs_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(admin_logs.getLayoutY()-110);
         translate.setToX(admin_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("admin_logs");
    }

    @FXML
    private void catalog_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(admin_catalog.getLayoutY()-110);
         translate.setToX(admin_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("employee_catalog");
    }
   

    @FXML
    private void actors_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_actors.getLayoutY()-110);
        translate.setToX(admin_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("admin_actors");
    }
    
    @FXML
    private void extra_options_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_extra_options.getLayoutY()-110);
        translate.setToX(admin_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("admin_extra_options");
    }
    
    @FXML
    private void employees_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_employees.getLayoutY()-110);
        translate.setToX(admin_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("admin_employees");
    }

    @FXML
    private void confirm_changes(ActionEvent event) {
         if(fname.getText().trim().isEmpty()||lname.getText().trim().isEmpty()||address.getText().trim().isEmpty()||district.getText().trim().isEmpty()||
                 pcode.getText().trim().isEmpty()||phone.getText().trim().isEmpty()||city.getText().trim().isEmpty()||combo_city.getValue()==null){
            not_empty_label.setOpacity(0);
            not_empty_label1.setOpacity(1);
        }
        else{
           try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM administrator WHERE admin_id = '"+cur_adm+"'");
          
            while(rs.next()){
                address_id = rs.getInt("address_id");
            }
            } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
           try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city_id,country_id FROM city WHERE city = '"+combo_city.getValue()+"'");
          
            while(rs.next()){
                city_new = rs.getInt("city_id");
                country_new = rs.getInt("country_id");
            }
            } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
           try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT country FROM country WHERE country_id = '"+country_new+"'");
          
            while(rs.next()){
                country_name = rs.getString("country");
            }
            } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
                not_empty_label.setOpacity(1);
                not_empty_label1.setOpacity(0);
                  try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query ="UPDATE administrator SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"' WHERE admin_id = '"+cur_adm+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="UPDATE address SET address = '"+address.getText()+"' ,district = '"+district.getText()+"',city_id='"+city_new+"', postal_code='"+pcode.getText()+"',phone='"+phone.getText()+"' WHERE address_id =  '"+address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);          
          
          String query2 ="UPDATE current_employee SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"',address = '"+address.getText()+"' ,district = '"+district.getText()+"',"
                  + " postal_code='"+pcode.getText()+"',phone='"+phone.getText()+"',city='"+combo_city.getValue()+"',country = '"+country_name+"'";
          stmt=conn.prepareStatement(query2);
          stmt.executeUpdate(query2);
          
          city.setText(combo_city.getValue());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }  
        }
    }

     private void loadPage(String page){
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Customer_pageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
    }

    @FXML
    private void admins_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_admins.getLayoutY()-110);
        translate.setToX(admin_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("admin_admins");
    }

    @FXML
    private void incomes_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(admin_incomes.getLayoutY()-110);
        translate.setToX(admin_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("admin_incomes");
    }

    
    
}
