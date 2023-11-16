/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Employee_top_sellersController implements Initializable {
    
    Connection conn = null;
    PreparedStatement stmt = null;
    @FXML
    private TableView<Model_Top_Series> film_table;
    @FXML
    private TableColumn<Model_Top_Series, String> col_film;
    @FXML
    private TableColumn<Model_Top_Series, String> col_films_sold;
    @FXML
    private TableView<Model_Top_Series> series_table;
    @FXML
    private TableColumn<Model_Top_Series, String> col_series;
    @FXML
    private TableColumn<Model_Top_Series, String> col_series_sold;
    
    ObservableList<Model_Top_Series> oblist2 = FXCollections.observableArrayList();
    ObservableList<Model_Top_Series> oblist3 = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fetch(ActionEvent event) {
        oblist2.clear();
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT title,sum FROM top_series");
         
         while(rs.next()){
           
             oblist2.add(new Model_Top_Series(rs.getString("title"),rs.getString("sum")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_series.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_series_sold.setCellValueFactory(new PropertyValueFactory<>("sum"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        series_table.setItems(oblist2);
        
        oblist3.clear();
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT title,sum FROM top_films");
         
         while(rs.next()){
           
             oblist3.add(new Model_Top_Series(rs.getString("title"),rs.getString("sum")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_film.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_films_sold.setCellValueFactory(new PropertyValueFactory<>("sum"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        film_table.setItems(oblist3);
    }
    
}
