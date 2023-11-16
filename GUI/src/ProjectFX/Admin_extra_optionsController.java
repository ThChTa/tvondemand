/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_extra_optionsController implements Initializable {

    @FXML
    private TextField category;
    @FXML
    private TableView<Model_Category_Table> category_table;
    @FXML
    private TableColumn<Model_Category_Table, String> col_category;
    @FXML
    private TextField city;
    @FXML
    private TableView<Model_City_Table> city_table;
    @FXML
    private TableColumn<Model_City_Table, String> col_city;
    @FXML
    private TextField language;
    @FXML
    private TableView<Model_Language_Table> language_table;
    @FXML
    private TableColumn<Model_Language_Table, String> col_language;
    @FXML
    private TextField country;
    @FXML
    private TableView<Model_Country_Table> country_table;
    @FXML
    private TableColumn<Model_Country_Table, String> col_country;
    
    PreparedStatement stmt = null;
    Connection conn = null;
    
    ObservableList<String> naiii = FXCollections.observableArrayList();
    ObservableList<Model_Category_Table> oblist = FXCollections.observableArrayList();
    ObservableList<Model_Language_Table> oblist1 = FXCollections.observableArrayList();
    ObservableList<Model_City_Table> oblist2 = FXCollections.observableArrayList();
    ObservableList<Model_Country_Table> oblist3 = FXCollections.observableArrayList();
     ObservableList<String> oblist4 = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> country_combo;
    @FXML
    private Label not_empty_label_category;
    @FXML
    private Label not_empty_label_category1;
    @FXML
    private Label not_empty_label_city;
    @FXML
    private Label not_empty_label_city1;
    @FXML
    private Label not_empty_label_country;
    @FXML
    private Label not_empty_label_language;
    @FXML
    private Label not_empty_label_language1;
    @FXML
    private Label not_empty_label_country1;
    @FXML
    private Pane pricing_pane;
    @FXML
    private Pane pricing_pane1;
    @FXML
    private Label film_price;
    @FXML
    private Label f_film_price;
    @FXML
    private Label series_price;
    @FXML
    private Label f_series_price;
    @FXML
    private TextField ffilmp;
    @FXML
    private TextField fseriesp;
    @FXML
    private TextField filmp;
    @FXML
    private TextField seriesp;
    private Text naii;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            String query = "DELETE FROM total";
            stmt=conn.prepareStatement(query);
                
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        try{    
                
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                    
                    CallableStatement stmt=conn.prepareCall("{call total_m()}");

                    stmt.execute();
                    
                   
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }    

    @FXML
    private void fetch(ActionEvent event) {
        
        not_empty_label_country.setOpacity(0);
        not_empty_label_city.setOpacity(0);
        not_empty_label_language.setOpacity(0);
        not_empty_label_category.setOpacity(0);
        not_empty_label_country1.setOpacity(0);
        not_empty_label_city1.setOpacity(0);
        not_empty_label_language1.setOpacity(0);
        not_empty_label_category1.setOpacity(0);
        
        oblist.clear();
        oblist1.clear();
        oblist2.clear();
        oblist3.clear();
        oblist4.clear();
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM category");
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM language");
         ResultSet rs2 = conn.createStatement().executeQuery("SELECT city FROM city");
         ResultSet rs3 = conn.createStatement().executeQuery("SELECT country FROM country");
         
         while(rs.next()){
             oblist.add(new Model_Category_Table(rs.getString("name")));
         }
          
         while(rs1.next()){
             oblist1.add(new Model_Language_Table(rs1.getString("name")));
         }
         
         while(rs2.next()){
             oblist2.add(new Model_City_Table(rs2.getString("city")));
         }
         
         while(rs3.next()){
             oblist3.add(new Model_Country_Table(rs3.getString("country")));
             oblist4.add(rs3.getString("country"));
         }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        
        col_category.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_language.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_country.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        category_table.setItems(oblist);
        language_table.setItems(oblist1);
        city_table.setItems(oblist2);
        country_table.setItems(oblist3);
        
        country_combo.setItems(oblist4);
    }

    @FXML
    private void category_add(ActionEvent event) {
        if(category.getText().trim().isEmpty()){
            not_empty_label_category.setOpacity(1);
            not_empty_label_category1.setOpacity(0);
        }
        else{
            not_empty_label_category.setOpacity(0);
            not_empty_label_category1.setOpacity(1);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query1 = "INSERT INTO category VALUES(?,?)";
         
                stmt=conn.prepareStatement(query1);
           
                stmt.setString(1, null);
                stmt.setString(2, category.getText());

                stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }      
        }
    }

    @FXML
    private void city_add(ActionEvent event) {
        int count=0;
        if(city.getText().trim().isEmpty()||country_combo.getValue()==null){
            not_empty_label_city.setOpacity(1);
            not_empty_label_city1.setOpacity(0);
        }
        else{
            not_empty_label_city.setOpacity(0);
            not_empty_label_city1.setOpacity(1);
            try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT country_id  FROM country WHERE country = '"+country_combo.getValue().toString()+"'");
         
         while(rs.next()){
             count = rs.getInt("country_id");
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }  
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query1 = "INSERT INTO city VALUES(?,?,?)";
         
                stmt=conn.prepareStatement(query1);
           
                stmt.setString(1, null);
                stmt.setString(2, city.getText());
                stmt.setInt(3, count);

                stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }      
        }
    }

    @FXML
    private void language_add(ActionEvent event) {
        if(language.getText().trim().isEmpty()){
            not_empty_label_language.setOpacity(1);
            not_empty_label_language1.setOpacity(0);
        }
        else{
            not_empty_label_language.setOpacity(0);
            not_empty_label_language1.setOpacity(1);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query1 = "INSERT INTO language VALUES(?,?)";
         
                stmt=conn.prepareStatement(query1);
           
                stmt.setString(1, null);
                stmt.setString(2, language.getText());

                stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }      
        }
    }

    @FXML
    private void country_add(ActionEvent event) {
        if(country.getText().trim().isEmpty()){
            not_empty_label_country.setOpacity(1);
            not_empty_label_country1.setOpacity(0);
        }
        else{
            not_empty_label_country.setOpacity(0);
            not_empty_label_country1.setOpacity(1);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query1 = "INSERT INTO country VALUES(?,?)";
         
                stmt=conn.prepareStatement(query1);
           
                stmt.setString(1, null);
                stmt.setString(2, country.getText());

                stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }      
        }
    }

    @FXML
    private void pricing_come(ActionEvent event) {
        
         try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT film_price,series_price,film_full_pack_price,series_full_pack_price FROM pricing");
          
         while(rs.next()){
            film_price.setText(rs.getString("film_price"));
            series_price.setText(rs.getString("series_price"));
            f_film_price.setText(rs.getString("film_full_pack_price"));
            f_series_price.setText(rs.getString("series_full_pack_price"));
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }     
        
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.9),pricing_pane);
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.9),pricing_pane1);
        translate.setToX(-830);
        translate1.setToX(-800);
        translate.play();
        translate1.play();
    }

    @FXML
    private void film_update(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query = "UPDATE pricing SET film_price ='"+filmp.getText()+"'"; 
         stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          film_price.setText(filmp.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
    }

    @FXML
    private void series_update(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query = "UPDATE pricing SET series_price ='"+seriesp.getText()+"'"; 
         stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          series_price.setText(seriesp.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
    }

    @FXML
    private void f_film_update(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query = "UPDATE pricing SET film_full_pack_price ='"+ffilmp.getText()+"'";   
         stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          f_film_price.setText(ffilmp.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
    }

    @FXML
    private void f_series_update(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query = "UPDATE pricing SET series_full_pack_price ='"+fseriesp.getText()+"'"; 
         stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          f_series_price.setText(fseriesp.getText());
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
    }

    @FXML
    private void slide(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.9),pricing_pane);
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.9),pricing_pane1);
        translate.setToX(830);
        translate1.setToX(800);
        translate.play();
        translate1.play();
    }

    
}
