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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Rental_historyController implements Initializable {

    @FXML
    private TableView<Model_Series_Rent> rent_hist_table;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_id;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_date;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_film_title;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_season_title;
    @FXML
    private TableView<Model_Rent_Class> rent_hist_table1;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_id1;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_date1;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_film_title1;
    PreparedStatement stmt = null;
    Connection conn = null;
    String cust_id;
    ObservableList<Model_Rent_Class> oblist4 = FXCollections.observableArrayList();
    ObservableList<Model_Series_Rent> oblist6 = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void fetch(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT customer_id FROM current_customer");
         
         while(rs.next()){
             cust_id = rs.getString("customer_id");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
         
         oblist4.clear();
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT rental_id,rental_date,inventory_id FROM rental WHERE customer_id='"+cust_id+"'");
          
         while(rs.next()){
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT film_id FROM inventory WHERE inventory_id='"+rs.getString("inventory_id")+"'");
             
             while(rs2.next()){
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT title FROM film WHERE film_id='"+rs2.getString("film_id")+"'");
                    
                    while(rs1.next()){
                        oblist4.add(new Model_Rent_Class(rs.getString("rental_id"),rs.getString("rental_date"),rs1.getString("title"))); //Onomata metavliton apo vasi
                    
                    
                    col_rent_id1.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
                    col_rent_date1.setCellValueFactory(new PropertyValueFactory<>("rental_date"));
                    col_rent_film_title1.setCellValueFactory(new PropertyValueFactory<>("film_title"));
        
                    }
                    rent_hist_table1.setItems(oblist4);
             }    
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT customer_id FROM current_customer");
         
         while(rs.next()){
             cust_id = rs.getString("customer_id");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
         
         oblist6.clear();
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT rental_id,rental_date,series_inventory_id FROM rental WHERE customer_id='"+cust_id+"'");
          
         while(rs.next()){
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT series_id,season_id FROM series_inventory WHERE series_inventory_id='"+rs.getString("series_inventory_id")+"'");
             
             while(rs2.next()){
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT season.season_title,series.title FROM series INNER JOIN season"
                    + " ON series.series_id = season.series_id WHERE season.season_id = '"+rs2.getInt("season_id")+"'");
                    
                    while(rs1.next()){
                        oblist6.add(new Model_Series_Rent(rs.getString("rental_id"),rs.getString("rental_date"),rs1.getString("series.title"),rs1.getString("season.season_title"))); //Onomata metavliton apo vasi
                    
                    
                    col_rent_id.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
                    col_rent_date.setCellValueFactory(new PropertyValueFactory<>("rental_date"));
                    col_rent_film_title.setCellValueFactory(new PropertyValueFactory<>("series_title"));
                    col_season_title.setCellValueFactory(new PropertyValueFactory<>("season_title"));
        
                    }
                    rent_hist_table.setItems(oblist6);
             }    
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
        
    }
    
}
