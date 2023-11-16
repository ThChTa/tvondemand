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
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_logsController implements Initializable {

    ObservableList<Model_Logs_Table> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Model_Logs_Table, String> fname;
    @FXML
    private TableColumn<Model_Logs_Table, String> lname;
    @FXML
    private TableColumn<Model_Logs_Table, String> chn_date;
    @FXML
    private TableColumn<Model_Logs_Table, String> katastasi;
    @FXML
    private TableColumn<Model_Logs_Table, String> eidos;
    @FXML
    private TableColumn<Model_Logs_Table, String> pinakas;
    @FXML
    private TableView<Model_Logs_Table> logs;
    PreparedStatement stmt = null;
    Connection conn = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fetch(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT first_name,last_name,change_date,katastasi,eidos,table_name_ FROM log_table");
          
         while(rs.next()){
             oblist.add(new Model_Logs_Table(rs.getString("first_name"),rs.getString("last_name"),rs.getString("change_date"),rs.getString("katastasi"),rs.getString("eidos"),rs.getString("table_name_")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));//Onomata metavliton apo klasi Model_Film_Table
        chn_date.setCellValueFactory(new PropertyValueFactory<>("change_date"));
        katastasi.setCellValueFactory(new PropertyValueFactory<>("katastasi"));
        eidos.setCellValueFactory(new PropertyValueFactory<>("eidos"));
        pinakas.setCellValueFactory(new PropertyValueFactory<>("table_name"));
        
        
        logs.setItems(oblist);
    }
    
}
