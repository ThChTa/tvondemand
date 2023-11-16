/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class Customer_pageController implements Initializable {

    @FXML
    private Label customer_profile;
    @FXML
    private Label customer_rental;
    @FXML
    private Pane moving_pane;
    @FXML
    private BorderPane bp;
    @FXML
    private Pane ap;
    @FXML
    private Label customer_exit;
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
    private Label not_empty_label;
    @FXML
    private Label not_empty_label1;
    private TextField gender;
    PreparedStatement stmt = null;
    Connection conn = null;
    int film_pk,series_pk,full_pk;
    @FXML
    private Label reg_date;
    @FXML
    private TextField email;
    private Label customer_rental1;
    @FXML
    private Label series_rental;
    ObservableList<String> oblist4 = FXCollections.observableArrayList("Film Pack","Series pack","Full pack");
    @FXML
    private ComboBox<String> pack_combo;
    @FXML
    private Label film_price;
    @FXML
    private Label series_price;
    @FXML
    private Label full_film_price;
    @FXML
    private Label full_series_price;
    @FXML
    private Label update_label;
    int cur_cust_id;
    Stage stage;
    Scene scene;
    ObservableList<String> oblist = FXCollections.observableArrayList();
    ObservableList<Integer> oblist1 = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> combo_city;
    int address_id,city_new,country_new;
    String country_name;
    @FXML
    private Label current_pack;
    @FXML
    private Label rental_hist;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pack_combo.setItems(oblist4);
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT film_pack,series_pack,full_pack FROM current_customer");
          
            while(rs.next()){
                film_pk = rs.getInt("film_pack");
                series_pk = rs.getInt("series_pack");
                full_pk = rs.getInt("full_pack");
            }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT customer_id,first_name,last_name,email,create_date,country,address,district,city,postal_code,phone FROM current_customer");
          
            while(rs.next()){
                fname.setText(rs.getString("first_name"));
                lname.setText(rs.getString("last_name"));
                email.setPromptText(rs.getString("email"));
                reg_date.setText(rs.getString("create_date"));
                address.setText(rs.getString("address"));
                district.setText(rs.getString("district"));
                city.setText(rs.getString("city"));
                pcode.setText(rs.getString("postal_code"));
                phone.setText(rs.getString("phone"));
                cur_cust_id = rs.getInt("customer_id");
            }
         } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        if(full_pk==1){
            customer_rental.setText("Film Rental");
            current_pack.setText("Full Pack");
        }
        if(series_pk==1){
            series_rental.setOpacity(0);
            current_pack.setText("Series Pack");
        }
        if(film_pk==1){
            series_rental.setOpacity(0);
            current_pack.setText("Film Pack");
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
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist.add(rs.getString("city"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city_id FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist1.add(rs.getInt("city_id"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        combo_city.setItems(oblist);
    }  

    @FXML
    private void profile_come(MouseEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(customer_profile.getLayoutY()-110);
         translate.setToX(customer_exit.getLayoutX()-15);
         translate.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         bp.setCenter(ap);
    }

    @FXML
    private void rental_come(MouseEvent event) {
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate2.setToY(customer_rental.getLayoutY()-110);
         translate2.setToX(customer_exit.getLayoutX()-15);
         translate2.play();
         moving_pane.setPrefWidth(188);
         moving_pane.setPrefHeight(40);
         if(film_pk==1){
         loadPage("film_pack_catalog");
         }
         if(series_pk==1){
         loadPage("series_pack_catalog");
         }
         if(full_pk==1){
         loadPage("f_film_pack");
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
    private void exit_come(MouseEvent event) {
         TranslateTransition translate = new TranslateTransition(Duration.seconds(0.3),moving_pane);
         translate.setToY(customer_exit.getLayoutY()-100);
         translate.setToX(customer_exit.getLayoutX()-32);
         moving_pane.setPrefWidth(70);
         moving_pane.setPrefHeight(25);
         translate.play();
         loadPage("customer_exit");
    }

    @FXML
    private void confirm_changes(ActionEvent event) {
        if(fname.getText().trim().isEmpty()||lname.getText().trim().isEmpty()||address.getText().trim().isEmpty()||district.getText().trim().isEmpty()||pcode.getText().trim().isEmpty()||
                phone.getText().trim().isEmpty()||city.getText().trim().isEmpty()||combo_city.getValue()==null){
            not_empty_label.setOpacity(0);
            not_empty_label1.setOpacity(1);
        }
        else{
           try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM customer WHERE customer_id = '"+cur_cust_id+"'");
          
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
         String query ="UPDATE customer SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"' WHERE customer_id = '"+cur_cust_id+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="UPDATE address SET address = '"+address.getText()+"' ,district = '"+district.getText()+"',city_id='"+city_new+"', postal_code='"+pcode.getText()+"',phone='"+phone.getText()+"' WHERE address_id =  '"+address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);          
          
          String query2 ="UPDATE current_customer SET first_name = '"+fname.getText()+"' ,last_name = '"+lname.getText()+"',address = '"+address.getText()+"' ,district = '"+district.getText()+"',"
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

    @FXML
    private void s_rental_come(MouseEvent event) {
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate2.setToY(series_rental.getLayoutY()-110);
        translate2.setToX(customer_exit.getLayoutX()-15);
        translate2.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("f_series_pack");
    }

    @FXML
    private void update_pack(ActionEvent event) throws IOException {
        int fpack=0;
        int spack=0;
        int flpack=0;
        if(pack_combo.getValue()=="Film Pack"){
            fpack = 1;
        }
        else if(pack_combo.getValue()=="Series pack"){
            spack = 1;
        }
        else if(pack_combo.getValue()=="Full pack"){
            flpack = 1;
        }
        if(pack_combo.getValue()==null){
            update_label.setOpacity(1);
        }
        else{
            update_label.setOpacity(0);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query ="UPDATE customer SET film_pack = '"+fpack+"' ,series_pack = '"+spack+"',full_pack = '"+flpack+"' WHERE customer_id = '"+cur_cust_id+"'";
                stmt=conn.prepareStatement(query);
                stmt.executeUpdate(query);          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
            Parent root1 = FXMLLoader.load(getClass().getResource("customer_login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root1);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.show();
            
        }
        
    }

    @FXML
    private void r_history_come(MouseEvent event) {
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.3),moving_pane);
        translate2.setToY(rental_hist.getLayoutY()-110);
        translate2.setToX(customer_exit.getLayoutX()-15);
        translate2.play();
        moving_pane.setPrefWidth(188);
        moving_pane.setPrefHeight(40);
        loadPage("rental_history");
    }

    
}
