/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class Employee_pageController implements Initializable {
    Connection conn = null;
    PreparedStatement stmt = null;
    @FXML
    private Pane moving_pane;
    private Label employee_home;
    private Label employee_rental;
    @FXML
    private Label employee_profile;
    @FXML
    private Label employee_customers;
    @FXML
    private Label employee_logs;
    @FXML
    private Label employee_catalog;
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
    private Label employee_exit;

    ObservableList<String> oblist = FXCollections.observableArrayList();
    private Scene scene;
    @FXML
    private Label employee_actors;
    @FXML
    private Label employee_extra_options;
    @FXML
    private Label employee_top_sellers;
    @FXML
    private ComboBox<String> combo_city;
    @FXML
    private Label salary;
    @FXML
    private Label rating;
    @FXML
    private TextField email;
    @FXML
    private Label hire_date;
    int cur_emp,address_id,city_new,country_new;
    String country_name;
    @FXML
    private Label film_price;
    @FXML
    private Label series_price;
    @FXML
    private Label full_film_price;
    @FXML
    private Label full_series_price;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sameDayLastMonth = now.minusMonths(1); // ipologismos imerominias akrivos prin apo ena mina
        
        loadPage("employee_customers");
        loadPage("employee_logs");
        loadPage("employee_catalog");  //preload ton selidon gia pio smooth animations
        loadPage("employee_actors");
        loadPage("employee_extra_options");
        bp.setCenter(ap);
        
         try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            String query = "DELETE FROM top_series";
            stmt=conn.prepareStatement(query);
                
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                    CallableStatement stmt=conn.prepareCall("{call p(?,?,?,?)}");
                    stmt.setString(1,"s");
                    stmt.setInt(2,5);
                    stmt.setString(3,dtf.format(sameDayLastMonth));
                    stmt.setString(4,dtf.format(now));
                    stmt.execute();
                    
                   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT film_price,series_price,film_full_pack_price,series_full_pack_price FROM pricing");
          
            while(rs.next()){
                film_price.setText(rs.getString("film_price"));
                series_price.setText(rs.getString("series_price"));
                full_film_price.setText(rs.getString("film_full_pack_price"));
                full_series_price.setText(rs.getString("series_full_pack_price"));
            }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT employee_id,first_name,last_name,email,date_of_hire,country,address,district,city,postal_code,phone,salary,rating,gender FROM current_employee");
          
            while(rs.next()){
                fname.setText(rs.getString("first_name"));
                lname.setText(rs.getString("last_name"));
                email.setPromptText(rs.getString("email"));
                hire_date.setText(rs.getString("date_of_hire"));
                address.setText(rs.getString("address"));
                district.setText(rs.getString("district"));
                city.setText(rs.getString("city"));
                pcode.setText(rs.getString("postal_code"));
                phone.setText(rs.getString("phone"));
                salary.setText(rs.getString("salary"));
                rating.setText(rs.getString("rating"));
                gender.setText(rs.getString("gender"));
                cur_emp = rs.getInt("employee_id");
            }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            String query = "DELETE FROM top_films";
            stmt=conn.prepareStatement(query);
                
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                    CallableStatement stmt=conn.prepareCall("{call p(?,?,?,?)}");
                    stmt.setString(1,"m");
                    stmt.setInt(2,5);
                    stmt.setString(3,dtf.format(sameDayLastMonth));
                    stmt.setString(4,dtf.format(now));
                    stmt.execute();
                    
                   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
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
         translate.setToY(employee_profile.getLayoutY()-110);
         translate.setToX(employee_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         bp.setCenter(ap);
    }

    @FXML
    private void exit_come(MouseEvent event) throws IOException {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(employee_exit.getLayoutY()-100);
        translate.setToX(employee_exit.getLayoutX()-32);
        moving_pane.setPrefWidth(70);
        moving_pane.setPrefHeight(25);
        translate.play();
        loadPage("employee_exit");
    }

    @FXML
    private void customers_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(employee_customers.getLayoutY()-110);
         translate.setToX(employee_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("employee_customers");
    }

    @FXML
    private void logs_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(employee_logs.getLayoutY()-110);
         translate.setToX(employee_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("employee_logs");
    }

    @FXML
    private void catalog_come(MouseEvent event) {
          TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(employee_catalog.getLayoutY()-110);
         translate.setToX(employee_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         loadPage("employee_catalog");
    }
   

    @FXML
    private void actors_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(employee_actors.getLayoutY()-110);
        translate.setToX(employee_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("employee_actors");
    }
    
    @FXML
    private void extra_options_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(employee_extra_options.getLayoutY()-110);
        translate.setToX(employee_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("employee_extra_options");
    }
    
    @FXML
    private void top_sellers_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate.setToY(employee_top_sellers.getLayoutY()-110);
        translate.setToX(employee_exit.getLayoutX()-15);
        translate.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("employee_top_sellers");
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
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM customer WHERE employee_id = '"+cur_emp+"'");
          
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
         String query ="UPDATE employee SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"' WHERE employee_id = '"+cur_emp+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="UPDATE address SET address = '"+address.getText()+"' ,district = '"+district.getText()+"',city_id='"+city_new+"', postal_code='"+pcode.getText()+"',phone='"+phone.getText()+"' WHERE address_id =  '"+address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);          
          
          String query2 ="UPDATE current_administrator SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"',address = '"+address.getText()+"' ,district = '"+district.getText()+"',"
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

    
}
