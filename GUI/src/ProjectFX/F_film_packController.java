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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class F_film_packController implements Initializable {
    
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private TableView<Model_Film_Table> film_table;
    @FXML
    private TableColumn<Model_Film_Table, String> col_id;
    @FXML
    private TableColumn<Model_Film_Table, String> col_title;
    @FXML
    private Button Show_film_details_button;
    @FXML
    private TextField filterField;
    @FXML
    private Pane film_pane;
    @FXML
    private TableView<Model_Actor_Name> actor_table;
    @FXML
    private TableColumn<Model_Actor_Name, String> actor_fname;
    @FXML
    private TableColumn<Model_Actor_Name, String> actor_lname;
    @FXML
    private Text rating;
    @FXML
    private Text special_ft;
    @FXML
    private Text length;
    @FXML
    private Text release_year;
    @FXML
    private Text description;
    @FXML
    private Text film_title;
    @FXML
    private Text language;
    @FXML
    private Text org_language;
    @FXML
    private Text category;

    private TextField length_film;
    ObservableList<Model_Film_Table> oblist = FXCollections.observableArrayList();
    ObservableList<Model_Actor_Name> oblist1 = FXCollections.observableArrayList();
    ObservableList<Model_Actor_Choose> oblist2 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<Model_Rent_Class> oblist4 = FXCollections.observableArrayList();
    
    
    int index,film_id,language_id,org_language_id,actor_id,category_id,rent_film_id,rent_inventory_id,rental_id;
    String rating1,special_features1,release_year1,length1,description1,category1,lang_name,org_lang_name,cust_id;
    @FXML
    private Button rent_button;
    @FXML
    private Label labell;
    @FXML
    private AnchorPane rent_pane;
    @FXML
    private Label rent_label;
    private Pane rent_history_pane;
    private TableView<Model_Rent_Class> rent_hist_table;
    private TableColumn<Model_Rent_Class, String> col_rent_id;
    private TableColumn<Model_Rent_Class, String> col_rent_date;
    private TableColumn<Model_Rent_Class, String> col_rent_film_title;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_film_details_button.setDisable(true);
        rent_button.setDisable(true);
    }    

    @FXML
    public void show_details(MouseEvent event) {
         index = film_table.getSelectionModel().getSelectedIndex();
            if(index<=-1){
                Show_film_details_button.setDisable(true);
                rent_button.setDisable(true);
                return;
            }
            else if(index>-1){
                film_title.setText(col_title.getCellData(index).toString());
                film_id = Integer.parseInt(col_id.getCellData(index));
                Show_film_details_button.setDisable(false);
                rent_button.setDisable(false);
                rent_film_id = Integer.parseInt(col_id.getCellData(index));
            }
            
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT release_year,length,rating,special_features,description,language_id,original_language_id FROM film WHERE film_id = '"+film_id+"'");
          
            while(rs.next()){
             release_year1 = rs.getString("release_year");
             length1 = rs.getString("length");
             rating1 = rs.getString("rating");
             special_features1 = rs.getString("special_features");
             description1 = rs.getString("description");
             language_id = rs.getInt("language_id");
             org_language_id = rs.getInt("original_language_id");
             
         }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT category_id FROM film_series_category WHERE film_id='"+film_id+"'");
          
         while(rs.next()){
                
                category_id = rs.getInt("category_id");
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM category WHERE category_id='"+category_id+"'");
                    
                    while(rs1.next()){
                        category1 = rs1.getString("name");
                    }
                    
                category.setText(category1);
                
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM language WHERE language_id = '"+language_id+"'");
            
            while(rs.next()){
                lang_name = rs.getString("name");
            }
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM language WHERE language_id = '"+org_language_id+"'");
            
            while(rs1.next()){
                org_lang_name = rs1.getString("name");
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
          
         if(index>-1){
             release_year.setText(release_year1);
             length.setText(length1+" minutes");
             rating.setText(rating1);
             special_ft.setText(special_features1);
             description.setText(description1);
             language.setText(lang_name);
             
             org_language.setText(org_lang_name);
             
         }
         
         oblist1.clear();
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT actor_id FROM film_series_actor WHERE film_id='"+film_id+"'");
          
         while(rs.next()){
                
                actor_id = rs.getInt("actor_id");
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT first_name,last_name FROM actor WHERE actor_id='"+actor_id+"'");
                    
                    while(rs1.next()){
                        oblist1.add(new Model_Actor_Name(rs1.getString("first_name"),rs1.getString("last_name"))); //Onomata metavliton apo vasi
                    }
                    
                    actor_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
                    actor_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));//Onomata metavliton apo klasi Model_Actor_Name
        
        
                    actor_table.setItems(oblist1);
                
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
        
    }

    @FXML
    private void film_fetch(ActionEvent event) {
       
        oblist.clear();
        oblist3.clear();
       
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT film_id FROM inventory");
          
            while(rs.next()){
                 try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT film_id,title FROM film WHERE film_id = '"+rs.getInt("film_id")+"'");

            while(rs1.next()){
                oblist.add(new Model_Film_Table(rs1.getString("film_id"),rs1.getString("title")));       //Onomata metavliton apo vasi
            }

            }
            catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
            }    
            col_id.setCellValueFactory(new PropertyValueFactory<>("film_id"));
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Film_Table
           }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        
       
        
        
       film_table.setItems(oblist);
        
                
                
                oblist2.clear();
       
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT actor_id,first_name,last_name FROM actor");
          
         while(rs.next()){
             oblist2.add(new Model_Actor_Choose(rs.getString("actor_id"),rs.getString("first_name"),rs.getString("last_name")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }

    @FXML
    private void show_film_details(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),film_pane);
        translate.setToY(-655);
        translate.play();
    }

    @FXML
    private void film_table_search(MouseEvent event) {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Film_Table> filteredData = new FilteredList<>(oblist, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(film -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if(film.getFilm_id().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                        return true; // Filter matches last name.
                                }
                                else if (film.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Film_Table> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(film_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		film_table.setItems(sortedData);
    }

    @FXML
    private void slide_down1(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),film_pane);
        translate.setToY(655);
        translate.play();
    }

    @FXML
    private void rent_film(ActionEvent event) throws IOException {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_pane);
        translate.setToX(-845);
        translate.play();
        rent_label.setOpacity(0);
    }

    @FXML
    private void rental(ActionEvent event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
        LocalDateTime now = LocalDateTime.now(); 
        double film_price=0.0;
        
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
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT inventory_id FROM inventory WHERE film_id ='"+rent_film_id+"'");
         
         while(rs1.next()){
             rent_inventory_id = rs1.getInt("inventory_id");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
       try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query1 = "INSERT INTO rental VALUES(?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query1);
           
            stmt.setString(1, null);
            stmt.setString(2, dtf.format(now));
            stmt.setInt(3, rent_inventory_id);
            stmt.setString(4, cust_id);
            stmt.setString(5, null);
            
            stmt.executeUpdate();
            
      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT rental_id FROM rental ORDER BY rental_date DESC LIMIT 1"); //lamvanw tin teleutaia seira tou pinaka rental
         
         while(rs.next()){
             rental_id = rs.getInt("rental_id");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
       try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs2 = conn.createStatement().executeQuery("SELECT film_full_pack_price FROM pricing");//lipsi tis timis ana epeisodeio seiras
         
         while(rs2.next()){
            film_price = rs2.getDouble("film_full_pack_price");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query1 = "INSERT INTO payment VALUES(?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query1);
           
            stmt.setString(1, null);
            stmt.setString(2, cust_id );
            stmt.setInt(3, rental_id);
            stmt.setDouble(4, film_price);
            stmt.setString(5, dtf.format(now));
            
            
            stmt.executeUpdate();
            
      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       rent_label.setOpacity(1);
    }

    @FXML
    private void go_back(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_pane);
        translate.setToX(845);
        translate.play();
    }

    private void rent_history(ActionEvent event) {
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
                    
                    
                    col_rent_id.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
                    col_rent_date.setCellValueFactory(new PropertyValueFactory<>("rental_date"));
                    col_rent_film_title.setCellValueFactory(new PropertyValueFactory<>("film_title"));
        
                    }
                    rent_hist_table.setItems(oblist4);
             }    
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_history_pane);
        translate.setToX(-845);
        translate.play();
    }

    private void rent_history_back(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_history_pane);
        translate.setToX(845);
        translate.play();
    }
}
