/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class Customer_registrationController implements Initializable {
    ObservableList<String> oblist = FXCollections.observableArrayList();
    ObservableList<Integer> oblist1 = FXCollections.observableArrayList();
    ObservableList<String> oblist2 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<String> oblist4 = FXCollections.observableArrayList("Film Pack","Series pack","Full pack");
    int ind;
    private Stage stage;
    private Scene scene;
    String ind_city_id;
    String ind_address_id;
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
    private TextField city;
    private TextField country;
    @FXML
    private Label not_empty_label1;
    @FXML
    private Label not_empty_label;

    PreparedStatement stmt = null;
    Connection conn = null;
    private TextField username;
    @FXML
    private ComboBox<String> city_options;
    
    String city_answer;
    String address_phone;
    @FXML
    private TextField email;
    @FXML
    private Pane cust_reg2;
    @FXML
    private Label not_empty_label11;
    @FXML
    private Label not_empty_label2;
    @FXML
    private ComboBox<String> pack_combo;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pack_combo.setItems(oblist4);
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
       
        city_options.setItems(oblist);
     
    }    


    @FXML
    private void cust_submit(ActionEvent event) {
        
        
        if(address.getText().trim().isEmpty()||district.getText().trim().isEmpty()||pcode.getText().trim().isEmpty()||
                phone.getText().trim().isEmpty()||city_options.getValue()==null){
            not_empty_label.setOpacity(0);
            not_empty_label1.setOpacity(1);
        }
        else{
        city_answer = city_options.getValue();
        ind = oblist.indexOf(city_answer);
        ind_city_id = Integer.toString(oblist1.get(ind));
        try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query = "INSERT INTO address VALUES (?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setString(2, address.getText());
            stmt.setString(3, district.getText());
            stmt.setString(4, ind_city_id );
            stmt.setString(5, pcode.getText());
            stmt.setString(6, phone.getText());
            
            stmt.executeUpdate();
            TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg2);
            translate.setToX(430);
            translate.play();
             try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address FROM address ORDER BY phone ASC"); //monadiko tilefono den tha iparxei sigxisi
            
            while(rs.next()){
                oblist2.add(rs.getString("address"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address ORDER BY phone ASC"); //monadiko tilefono den tha iparxei sigxisi
            
            while(rs.next()){
                oblist3.add(rs.getInt("address_id"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        }
    }

    @FXML
    private void cust_submit1(ActionEvent event) { 
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
         LocalDateTime now = LocalDateTime.now();  
         int fpack=0;
         int spack=0;
         int flpack=0;
        if(email.getText().trim().isEmpty()||fname.getText().trim().isEmpty()||lname.getText().trim().isEmpty()||pack_combo.getValue()==null){
            not_empty_label2.setOpacity(0);
            not_empty_label11.setOpacity(1);
        }
        else{
        address_phone = address.getText();
        ind = oblist2.indexOf(address_phone);
        ind_address_id = Integer.toString(oblist3.get(ind));
        
        if(pack_combo.getValue()=="Film Pack"){
            fpack = 1;
        }
        else if(pack_combo.getValue()=="Series pack"){
            spack = 1;
        }
        else if(pack_combo.getValue()=="Full pack"){
            flpack = 1;
        }
        try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setString(2, fname.getText());
            stmt.setString(3, lname.getText());
            stmt.setString(4, email.getText());
            stmt.setString(5, ind_address_id);
            stmt.setString(6, "1");
            stmt.setString(7, dtf.format(now));
            stmt.setString(8, ""+fpack);
            stmt.setString(9, ""+spack);
            stmt.setString(10,""+flpack);
            
            JOptionPane.showMessageDialog(null,"REGISTRATION SUCSESFUL!!");
            stmt.executeUpdate();
            Parent root1 = FXMLLoader.load(getClass().getResource("customer_login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root1);
            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.show();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        }
    }
}
