/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Series_pack_catalogController implements Initializable {
    
    PreparedStatement stmt = null;
    Connection conn = null;
    
    @FXML
    private TableView<Model_Rent_Series> series_table;
    @FXML
    private TableColumn<Model_Rent_Series, String> col_series_id;
    @FXML
    private TableColumn<Model_Rent_Series, String> col_series_title;
    @FXML
    private TextField filterField2;
    @FXML
    private Button Show_series_details_button;
    @FXML
    private Pane series_pane;
    @FXML
    private Text series_season_count;
    @FXML
    private TableView<Model_Season_Table> seasons_table;
    @FXML
    private TableColumn<Model_Season_Table, String> season_title;
    @FXML
    private TableColumn<Model_Season_Table, String> season_episodes;
    @FXML
    private Text series_rating;
    @FXML
    private Text series_special_ft;
    @FXML
    private Text series_release_year;
    @FXML
    private Text series_description;
    @FXML
    private Text series_title;
    @FXML
    private Text series_language;
    @FXML
    private Text series_org_language;
    @FXML
    private Text series_category;
    @FXML
    private TableView<Model_Actor_Name> actor_table1;
    @FXML
    private TableColumn<?, ?> actor_fname1;
    @FXML
    private TableColumn<?, ?> actor_lname1;
    int index,film_id,language_id,org_language_id,actor_id,category_id,index1,series_id,index2,index3,lang_index,org_lang_index,lang_id,org_lang_id,categ_index,film_id1,categ_id,
            rental_id,rent_inventory_id,rent_series_id;
    String rating1,special_features1,release_year1,length1,description1,category1,lang_name,org_lang_name,
            season_count1,choose_actor_id,choose_actor_fname,choose_actor_lname,title_ans,length_ans,release_ans,language_ans,org_language_ans,
            special_ft_ans,category_ans,rating_ans,descritpion_ans,seasons_ans,cust_id,ses_id,ses_title;
    ObservableList<Model_Actor_Name> oblist1 = FXCollections.observableArrayList();
    ObservableList<Model_Season_Table> oblist5 = FXCollections.observableArrayList();
    ObservableList<Model_Rent_Series> oblist4 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<Model_Series_Rent> oblist6 = FXCollections.observableArrayList();
    @FXML
    private Button rent_button;
    private TableColumn<Model_Rent_Series,  String>col_season_title;
    @FXML
    private AnchorPane rent_pane;
    @FXML
    private Label labell;
    @FXML
    private Label rent_label;
    private Pane rent_history_pane;
    private TableView<Model_Series_Rent> rent_hist_table;
    private TableColumn<Model_Series_Rent, String> col_rent_id;
    private TableColumn<Model_Series_Rent, String> col_rent_date;
    private TableColumn<Model_Series_Rent, String> col_rent_film_title;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_series_title1;
    @FXML
    private Label nai;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Show_series_details_button.setDisable(true);
    }    

    @FXML
    private void show_series_details(MouseEvent event) {
        index2 = series_table.getSelectionModel().getSelectedIndex();
            if(index2<=-1){
                Show_series_details_button.setDisable(true);
                return;
            }
            else if(index2>-1){
                series_title.setText(col_series_title.getCellData(index2).toString());
                series_id = Integer.parseInt(col_series_id.getCellData(index2));
                Show_series_details_button.setDisable(false);
                rent_series_id = Integer.parseInt(col_series_id.getCellData(index2));
                ses_title = col_series_title1.getCellData(index2).toString();
                rent_button.setDisable(false);
            }
            
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT release_year,rating,special_features,description,language_id,original_language_id,season_count FROM series WHERE series_id = '"+series_id+"'");
          
            while(rs.next()){
             release_year1 = rs.getString("release_year");
             rating1 = rs.getString("rating");
             special_features1 = rs.getString("special_features");
             description1 = rs.getString("description");
             language_id = rs.getInt("language_id");
             org_language_id = rs.getInt("original_language_id");
             season_count1 = rs.getString("season_count");
             
         }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT category_id FROM film_series_category WHERE series_id='"+series_id+"'");
          
         while(rs.next()){
                
                category_id = rs.getInt("category_id");
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM category WHERE category_id='"+category_id+"'");
                    
                    while(rs1.next()){
                        category1 = rs1.getString("name");
                    }
                    
                series_category.setText(category1);
                
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
           
          
         if(index2>-1){
             series_release_year.setText(release_year1);
             series_rating.setText(rating1);
             series_special_ft.setText(special_features1);
             series_description.setText(description1);
             series_language.setText(lang_name);
             series_season_count.setText(season_count1);
             
             series_org_language.setText(org_lang_name);
             
         }
         
         oblist1.clear();
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT actor_id FROM film_series_actor WHERE series_id='"+series_id+"'");
          
         while(rs.next()){
                
                actor_id = rs.getInt("actor_id");
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT first_name,last_name FROM actor WHERE actor_id='"+actor_id+"'");
                    
                    while(rs1.next()){
                        oblist1.add(new Model_Actor_Name(rs1.getString("first_name"),rs1.getString("last_name"))); //Onomata metavliton apo vasi
                    }
                    
                    actor_fname1.setCellValueFactory(new PropertyValueFactory<>("fname"));
                    actor_lname1.setCellValueFactory(new PropertyValueFactory<>("lname"));//Onomata metavliton apo klasi Model_Actor_Name
        
                    actor_table1.setItems(oblist1);
                
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        
        /*col_series_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_series_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Rent_Series
        
        
        series_table.setItems(oblist4);*/
        
        oblist5.clear();
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT season_title,season_episodes FROM season WHERE series_id = '"+series_id+"'");
          
         while(rs.next()){
             oblist5.add(new Model_Season_Table(rs.getString("season_title"),rs.getString("season_episodes")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        season_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        season_episodes.setCellValueFactory(new PropertyValueFactory<>("episodes"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        seasons_table.setItems(oblist5);
    }

    @FXML
    private void fetch_series(ActionEvent event) {
        oblist4.clear();
        oblist3.clear();
       
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT season_id,series_id FROM series_inventory");
          
            while(rs.next()){
                 try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT series.series_id,season.season_title,series.title FROM series INNER JOIN season"
                    + " ON series.series_id = season.series_id WHERE season.season_id = '"+rs.getInt("season_id")+"'");

            while(rs1.next()){
                oblist4.add(new Model_Rent_Series(rs.getString("series_id"),rs1.getString("series.title"),rs1.getString("season.season_title")));       //Onomata metavliton apo vasi
            }

            }
            catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
            }    
            col_series_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_series_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_series_title1.setCellValueFactory(new PropertyValueFactory<>("season_name"));//Onomata metavliton apo klasi Model_Rent_Series*/
           }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        series_table.setItems(oblist4);
        
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Rent_Series> filteredData2 = new FilteredList<>(oblist4, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField2.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData2.setPredicate(series -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if(series.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                        return true; // Filter matches last name.
                                }
                                else if (series.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Rent_Series> sortedData1 = new SortedList<>(filteredData2);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(series_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		series_table.setItems(sortedData1);
    }

    @FXML
    private void show_film_details_pane(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),series_pane);
        translate.setToY(-655);
        translate.play();
    }

    @FXML
    private void slide_down_series_pane(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),series_pane);
        translate.setToY(655);
        translate.play();
    }

    @FXML
    private void rent_season(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_pane);
        translate.setToX(-845);
        translate.play();
        rent_label.setOpacity(0);
    }

    @FXML
    private void rental(ActionEvent event) {
        int season_episodes=0;
        double ep_price=0.0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
        LocalDateTime now = LocalDateTime.now(); 
        
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
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT series_inventory.series_inventory_id FROM series_inventory INNER JOIN season"
                 + " ON series_inventory.season_id = season.season_id WHERE season.series_id ='"+rent_series_id+"' AND season.season_title = '"+ses_title+"'");
         
         while(rs1.next()){
             rent_inventory_id = rs1.getInt("series_inventory.series_inventory_id");
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
            stmt.setString(3, null);
            stmt.setString(4, cust_id);
            stmt.setInt(5, rent_inventory_id);
            
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
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT season_episodes FROM season WHERE series_id = '"+rent_series_id+"' AND season_title = '"+ses_title+"'"); //lamvanw tin teleutaia seira tou pinaka rental
         
         while(rs1.next()){
            season_episodes = rs1.getInt("season_episodes");
         }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
       try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs2 = conn.createStatement().executeQuery("SELECT series_price FROM pricing");//lipsi tis timis ana epeisodeio seiras
         
         while(rs2.next()){
            ep_price = rs2.getDouble("series_price");
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
            stmt.setDouble(4, ep_price*season_episodes);
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

    private void rent_history_back(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_history_pane);
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
        
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rent_history_pane);
        translate.setToX(-845);
        translate.play();
    }
    
}
