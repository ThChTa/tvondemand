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
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_catalogController implements Initializable {
    
    ObservableList<Model_Film_Table> oblist = FXCollections.observableArrayList();
    ObservableList<Model_Actor_Name> oblist1 = FXCollections.observableArrayList();
    ObservableList<Model_Actor_Choose> oblist2 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<Model_Series_Table> oblist4 = FXCollections.observableArrayList();
    ObservableList<Model_Season_Table> oblist5 = FXCollections.observableArrayList();
    ObservableList<Model_Actor_Choose> oblist6 = FXCollections.observableArrayList();
    ObservableList<String> oblist7 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist8 = FXCollections.observableArrayList();
    ObservableList<String> oblist9 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist10 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist11 = FXCollections.observableArrayList();
    
    
    @FXML
    private TableColumn<Model_Film_Table, String> col_title;
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private TableView<Model_Film_Table> film_table;
    @FXML
    private Pane film_pane;
    @FXML
    private Tab film_tab;
    @FXML
    private Tab series_tab;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn<Model_Film_Table, String> col_id;
    
    int index,film_id,language_id,org_language_id,actor_id,category_id,index1,series_id,index2,index3,lang_index,org_lang_index,lang_id,org_lang_id,categ_index,film_id1,categ_id;
    String rating1,special_features1,release_year1,length1,description1,category1,lang_name,org_lang_name,
            season_count1,choose_actor_id,choose_actor_fname,choose_actor_lname,title_ans,length_ans,release_ans,language_ans,org_language_ans,
            special_ft_ans,category_ans,rating_ans,descritpion_ans,seasons_ans;
    
    @FXML
    private Text film_title;
    @FXML
    private Text release_year;
    @FXML
    private Text special_ft;
    @FXML
    private Text rating;
    @FXML
    private Text length;
    @FXML
    private Text description;
    @FXML
    private Text language;
    @FXML
    private Text org_language;
    @FXML
    private TableColumn<Model_Actor_Name,String> actor_fname;
    @FXML
    private TableColumn<Model_Actor_Name,String> actor_lname;
    @FXML
    private TableView<Model_Actor_Name> actor_table;
    @FXML
    private Text category;
    @FXML
    private TextField title_film;
    @FXML
    private TextArea description_film;
    @FXML
    private ComboBox<Integer> release_year_film;
    @FXML
    private ComboBox<String> language_film;
    @FXML
    private ComboBox<String> rating_film;
    @FXML
    private ComboBox<String> org_language_film;
    @FXML
    private ComboBox<String> category_film;
    @FXML
    private TextField length_film;
    private ObservableList<String> rating_list = FXCollections.observableArrayList("G","PG","PG-13","R","NC-17");
    private ObservableList<Integer> release_year_list = FXCollections.observableArrayList();
    private ObservableList<String> language_list = FXCollections.observableArrayList();
    private ObservableList<String> category_list = FXCollections.observableArrayList();
    private ObservableList<String> special_features__list = FXCollections.observableArrayList("Trailers","Commentaries","Deleted Scenes","Behind the Scenes","NOTHING");
    private ObservableList<String> actors_list = FXCollections.observableArrayList();
    
    @FXML
    private Pane add_film_pane;
    @FXML
    private ComboBox<String> special_features_film;
    
    String act_fname,act_lname;
    @FXML
    private Pane add_actors_pane;
    @FXML
    private TableView<Model_Actor_Choose> add_actor_table;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_id;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_fname;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_lname;
    @FXML
    private TextField filterField1;
    @FXML
    private TableView<Model_Series_Table> series_table;
    @FXML
    private Pane series_pane;
    
    @FXML
    private TableColumn<Model_Season_Table,String> season_title;
    @FXML
    private TableColumn<Model_Season_Table,String> season_episodes;
    @FXML
    private TableView<Model_Actor_Name> actor_table1;
    @FXML
    private TableColumn<?, ?> actor_fname1;
    @FXML
    private TableColumn<?, ?> actor_lname1;
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
    private TableColumn<Model_Series_Table, String> col_series_id;
    @FXML
    private TableColumn<Model_Series_Table, String> col_series_title;
    @FXML
    private TextField filterField2;
    @FXML
    private TableView<Model_Season_Table> seasons_table;
    @FXML
    private Button Show_film_details_button;
    @FXML
    private Text series_season_count;
    @FXML
    private TableView<Model_Actor_Choose> added_actors_table;
    @FXML
    private TableColumn<Model_Actor_Choose, String> id_col;
    @FXML
    private TableColumn<Model_Actor_Choose, String> fname_col;
    @FXML
    private TableColumn<Model_Actor_Choose, String> lname_col;
    private Label nailabel;
    @FXML
    private Label not_empty_label;
    @FXML
    private Label not_empty_label1;
    @FXML
    private Pane add_series_pane;
    @FXML
    private TextArea description_series;
    @FXML
    private ComboBox<String> language_series;
    @FXML
    private ComboBox<String> rating_series;
    @FXML
    private ComboBox<String> org_language_series;
    @FXML
    private TextField title_series;
    @FXML
    private TextField seasons_series;
    @FXML
    private ComboBox<String> special_features_series;
    @FXML
    private ComboBox<Integer> release_year_series;
    @FXML
    private Label not_empty_label2;
    @FXML
    private Pane add_actors_pane1;
    @FXML
    private TextField filterField11;
    @FXML
    private TableView<Model_Actor_Choose> add_actor_table1;
    @FXML
    private TableColumn<Model_Actor_Choose,String> coll_actor_id1;
    @FXML
    private TableColumn<Model_Actor_Choose,String> coll_actor_fname1;
    @FXML
    private TableColumn<Model_Actor_Choose,String> coll_actor_lname1;
    @FXML
    private TableView<Model_Actor_Choose> added_actors_table1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> id_col1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> fname_col1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> lname_col1;
    @FXML
    private Label not_empty_label11;
    @FXML
    private ComboBox<String> category_series;
    @FXML
    private Button Show_series_details_button;
    @FXML
    private ComboBox<Integer> SeasonNo_series;
    @FXML
    private TextField season_episodesnumber;
    @FXML
    private Label not_empty_label111;
    @FXML
    private Label not_empty_label1111;
    @FXML
    private TextField season_title_add;
    @FXML
    private Button add_inv;
    @FXML
    private Label inv_label;
    @FXML
    private Button add_inv1;
    @FXML
    private Label inv_label1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show_film_details_button.setDisable(true);
        Show_series_details_button.setDisable(true);
    }    

    @FXML
    private void film_fetch(ActionEvent event) {

        language_list.clear();
        category_list.clear();
        release_year_list.clear();
        
        rating_film.setItems(rating_list);
        for(int i=2022;i>=1940;i--){
            release_year_list.add(i); //Arxikopoiisi tou comboBox twn imerominiwn 
        }
        release_year_film.setItems(release_year_list);
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM language");
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM category");
         
         while(rs.next()){
             language_list.add(rs.getString("name"));
         }
          
         while(rs1.next()){
             category_list.add(rs1.getString("name"));
         }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        language_film.setItems(language_list);
        org_language_film.setItems(language_list);
        category_film.setItems(category_list);
        special_features_film.setItems(special_features__list);
        
        oblist.clear();
       
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT film_id,title FROM film");
          
         while(rs.next()){
             oblist.add(new Model_Film_Table(rs.getString("film_id"),rs.getString("title")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_id.setCellValueFactory(new PropertyValueFactory<>("film_id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Film_Table
        
        
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
        coll_actor_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        coll_actor_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));//Onomata metavliton apo klasi Model_Film_Table
        coll_actor_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
        
        add_actor_table.setItems(oblist2);
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Actor_Choose> filteredData1 = new FilteredList<>(oblist2, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData1.setPredicate(actor -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if(actor.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                        return true; // Filter matches last name.
                                }
                                else if (actor.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (actor.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Actor_Choose> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(add_actor_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		add_actor_table.setItems(sortedData1);
        
    }

    @FXML
    private void show_film_details(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),film_pane);
        translate.setToY(-655);
        translate.play();
        series_tab.setDisable(true);
    
    }

    @FXML
    private void slide_down1(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),film_pane);
        translate.setToY(655);
        translate.play();
        series_tab.setDisable(false);
    }

    @FXML
    private void show_details(MouseEvent event) {
        
        index = film_table.getSelectionModel().getSelectedIndex();
            if(index<=-1){
                Show_film_details_button.setDisable(true);
                add_inv.setDisable(true);
                return;
            }
            else if(index>-1){
                film_title.setText(col_title.getCellData(index).toString());
                film_id = Integer.parseInt(col_id.getCellData(index));
                Show_film_details_button.setDisable(false);
                add_inv.setDisable(false);
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
            Logger.getLogger(Admin_catalogController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Admin_catalogController.class.getName()).log(Level.SEVERE, null, ex);
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
       /* col_id.setCellValueFactory(new PropertyValueFactory<>("film_id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        film_table.setItems(oblist);*/
        
    }

    @FXML
    private void add_film(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_film_pane);
        translate.setToY(-655);
        translate.play();
        series_tab.setDisable(true);
        
        release_year_film.setValue(null);
        release_year_film.setPromptText("Release Year");
        rating_film.setValue(null);
        rating_film.setPromptText("Rating");
        language_film.setValue(null);
        language_film.setPromptText("Language");
        org_language_film.setValue(null);
        org_language_film.setPromptText("Original Language");
        special_features_film.setValue(null);
        special_features_film.setPromptText("Special Features");
        category_film.setValue(null);
        category_film.setPromptText("Category");
        title_film.setText("");
        length_film.setText("");
        description_film.setText("");
    }

    @FXML
    private void slide_down2(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_film_pane);
        translate.setToY(655);
        translate.play();
        series_tab.setDisable(false);
    }

    @FXML
    private void call_add_actors(ActionEvent event) {
        if(title_film.getText().trim().isEmpty()||length_film.getText().trim().isEmpty()||description_film.getText().trim().isEmpty()||release_year_film.getValue()==null||
                rating_film.getValue()==null||language_film.getValue()==null||org_language_film.getValue()==null||special_features_film.getValue()==null){
            not_empty_label.setOpacity(1);
        }
        else{
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_actors_pane);
        translate.setToX(-715);
        translate.play();
        not_empty_label.setOpacity(0);
        
        title_ans = title_film.getText();
        length_ans = length_film.getText();
        release_ans = release_year_film.getValue().toString();
        language_ans = language_film.getValue();
        org_language_ans = org_language_film.getValue();
        special_ft_ans = special_features_film.getValue();
        rating_ans = rating_film.getValue(); 
        descritpion_ans = description_film.getText();
    
        try{
            String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         
            conn=DriverManager.getConnection(url,uname,password);
            ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM language ORDER BY language_id ASC");
            
            while(rs.next()){
                oblist7.add(rs.getString("name"));
            }
         
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT language_id FROM language ORDER BY language_id ASC");
            
            while(rs1.next()){
                oblist8.add(rs1.getInt("language_id"));
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        lang_index = oblist7.indexOf(language_ans);
        org_lang_index = oblist7.indexOf(org_language_ans);
        categ_index = oblist9.indexOf(category_ans);
        
        lang_id = oblist8.get(lang_index);
        org_lang_id = oblist8.get(org_lang_index);
        
        try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query1 = "INSERT INTO film VALUES(?,?,?,?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query1);
           
            stmt.setString(1, null);
            stmt.setString(2, title_ans);
            stmt.setString(3, descritpion_ans);
            stmt.setInt(4, Integer.parseInt(release_ans));
            stmt.setInt(5, lang_id);
            stmt.setInt(6, org_lang_id);
            stmt.setInt(7, Integer.parseInt(length_ans));
            stmt.setString(8, rating_ans);
            stmt.setString(9, special_ft_ans);
            
            stmt.executeUpdate();
            
      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    }

    @FXML
    private void fetch_series(ActionEvent event) {
        release_year_list.clear();
        language_list.clear();
        category_list.clear();
        
        rating_series.setItems(rating_list);
        for(int i=2022;i>=1940;i--){
            release_year_list.add(i); //Arxikopoiisi tou comboBox twn imerominiwn 
        }
        release_year_series.setItems(release_year_list);
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM language");
         ResultSet rs1 = conn.createStatement().executeQuery("SELECT name FROM category");
         
         while(rs.next()){
             language_list.add(rs.getString("name"));
         }
          
         while(rs1.next()){
             category_list.add(rs1.getString("name"));
         }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        language_series.setItems(language_list);
        org_language_series.setItems(language_list);
        category_series.setItems(category_list);
        special_features_series.setItems(special_features__list);
        
        oblist4.clear();
       
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT series_id,title FROM series");
          
         while(rs.next()){
             oblist4.add(new Model_Series_Table(rs.getString("series_id"),rs.getString("title")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_series_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_series_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        series_table.setItems(oblist4);
        
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Series_Table> filteredData2 = new FilteredList<>(oblist4, b -> true);    //SearchBar
		
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
		SortedList<Model_Series_Table> sortedData1 = new SortedList<>(filteredData2);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(series_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		series_table.setItems(sortedData1);
                
                
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
        coll_actor_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        coll_actor_fname1.setCellValueFactory(new PropertyValueFactory<>("fname"));//Onomata metavliton apo klasi Model_Film_Table
        coll_actor_lname1.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
        
        add_actor_table1.setItems(oblist2);
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Actor_Choose> filteredData3 = new FilteredList<>(oblist2, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField11.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData3.setPredicate(actor -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if(actor.getId().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                        return true; // Filter matches last name.
                                }
                                else if (actor.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (actor.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Actor_Choose> sortedData3 = new SortedList<>(filteredData3);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData3.comparatorProperty().bind(add_actor_table1.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		add_actor_table1.setItems(sortedData3);
        
                
    }

    @FXML
    private void show_series_details(MouseEvent event) {
        index2 = series_table.getSelectionModel().getSelectedIndex();
            if(index2<=-1){
                Show_series_details_button.setDisable(true);
                add_inv1.setDisable(true);
                return;
            }
            else if(index2>-1){
                series_title.setText(col_series_title.getCellData(index2).toString());
                series_id = Integer.parseInt(col_series_id.getCellData(index2));
                Show_series_details_button.setDisable(false);
                add_inv1.setDisable(false);
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
            Logger.getLogger(Admin_catalogController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Admin_catalogController.class.getName()).log(Level.SEVERE, null, ex);
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
        col_series_title.setCellValueFactory(new PropertyValueFactory<>("title"));//Onomata metavliton apo klasi Model_Series_Table
        
        
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
    private void show_film_details_pane(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),series_pane);
        translate.setToY(-655);
        translate.play();
        film_tab.setDisable(true);
    }

    @FXML
    private void slide_down_series_pane(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),series_pane);
        translate.setToY(655);
        translate.play();
        film_tab.setDisable(false);
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
    private void actors_to_add(MouseEvent event) {
            
            index3 = add_actor_table.getSelectionModel().getSelectedIndex();
            if(index3<=-1){
                return;
            }
            else if(index3>-1){
                choose_actor_id = coll_actor_id.getCellData(index3).toString();
                choose_actor_fname = coll_actor_fname.getCellData(index3).toString();
                choose_actor_lname = coll_actor_lname.getCellData(index3).toString();
            }
            oblist6.add(new Model_Actor_Choose(choose_actor_id,choose_actor_fname,choose_actor_lname));
            
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            fname_col.setCellValueFactory(new PropertyValueFactory<>("fname"));
            lname_col.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
            added_actors_table.setItems(oblist6);
    }

    @FXML
    private void submit_film(ActionEvent event) {
        if(category_film.getValue()==null){
            not_empty_label1.setOpacity(1);
        }
        else{
        try{    
            not_empty_label1.setOpacity(0);
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT name FROM category ORDER BY category_id ASC");
            
            while(rs2.next()){
                oblist9.add(rs2.getString("name"));
            }
         
            ResultSet rs3 = conn.createStatement().executeQuery("SELECT category_id FROM category ORDER BY category_id ASC");
            
            while(rs3.next()){
                oblist10.add(rs3.getInt("category_id"));
            }
         
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT film_id FROM film WHERE title = '"+title_ans+"'");
          
         while(rs.next()){
           film_id1 = rs.getInt("film_id");
         }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
        
         category_ans = category_film.getValue();
         categ_index = oblist9.indexOf(category_ans);
         categ_id = oblist10.get(categ_index);
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
           String query2 = "INSERT INTO film_series_category VALUES(?,?,?)";
            stmt=conn.prepareStatement(query2);
            
            stmt.setInt(1, film_id1);
            stmt.setInt(2, categ_id);
            stmt.setString(3, null);
            
            stmt.executeUpdate();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
       
        try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            for (int counter = 0; counter < oblist6.size(); counter++) { 
                
                String query2 = "INSERT INTO film_series_actor VALUES(?,?,?)";
                stmt=conn.prepareStatement(query2);
            
                stmt.setInt(1, Integer.parseInt(id_col.getCellData(counter)));
                stmt.setInt(2, film_id1);
                stmt.setString(3, null);
            
                stmt.executeUpdate();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }  
        
        
         oblist6.clear();
         oblist7.clear();
         oblist8.clear();
         oblist9.clear();
         oblist10.clear();
        
        
      
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_actors_pane);
        translate.setToX(715);
        translate.play();
        
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),add_film_pane);
        translate1.setToY(655);
        translate1.play();
        series_tab.setDisable(false);
        
        
        
    }
    }

    @FXML
    private void add_serie(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_series_pane);
        translate.setToY(-655);
        translate.play();
        film_tab.setDisable(true);
        
        release_year_series.setValue(null);
        release_year_series.setPromptText("Release Year");
        rating_series.setValue(null);
        rating_series.setPromptText("Rating");
        language_series.setValue(null);
        language_series.setPromptText("Language");
        org_language_series.setValue(null);
        org_language_series.setPromptText("Original Language");
        special_features_series.setValue(null);
        special_features_series.setPromptText("Special Features");
        category_series.setValue(null);
        category_series.setPromptText("Category");
        title_series.setText("");
        seasons_series.setText("");
        description_series.setText("");
    }

    @FXML
    private void slide_down_series(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_series_pane);
        translate.setToY(655);
        translate.play();
        film_tab.setDisable(false);
    }

    @FXML
    private void call_add_actors_seasons(ActionEvent event) {
         if(title_series.getText().trim().isEmpty()||seasons_series.getText().trim().isEmpty()||description_series.getText().trim().isEmpty()||release_year_series.getValue()==null||
                rating_series.getValue()==null||language_series.getValue()==null||org_language_series.getValue()==null||special_features_series.getValue()==null){
            not_empty_label2.setOpacity(1);
        }
        else{
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_actors_pane1);
        translate.setToX(-765);
        translate.play();
        not_empty_label2.setOpacity(0);
        
        title_ans = title_series.getText();
        seasons_ans = seasons_series.getText();
        release_ans = release_year_series.getValue().toString();
        language_ans = language_series.getValue();
        org_language_ans = org_language_series.getValue();
        special_ft_ans = special_features_series.getValue();
        rating_ans = rating_series.getValue(); 
        descritpion_ans = description_series.getText();
        
         oblist11.clear();
        for(int i=1;i<= Integer.parseInt(seasons_ans);i++){
          oblist11.add(i); 
        }
        SeasonNo_series.setItems(oblist11); //egxorisi arithomou sezon se comboBox
        try{
            String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         
            conn=DriverManager.getConnection(url,uname,password);
            ResultSet rs = conn.createStatement().executeQuery("SELECT name FROM language ORDER BY language_id ASC");
            
            while(rs.next()){
                oblist7.add(rs.getString("name"));
            }
         
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT language_id FROM language ORDER BY language_id ASC");
            
            while(rs1.next()){
                oblist8.add(rs1.getInt("language_id"));
            }
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        lang_index = oblist7.indexOf(language_ans);
        org_lang_index = oblist7.indexOf(org_language_ans);
        categ_index = oblist9.indexOf(category_ans);
        
        lang_id = oblist8.get(lang_index);
        org_lang_id = oblist8.get(org_lang_index);
        
        try{
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query1 = "INSERT INTO series VALUES(?,?,?,?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query1);
           
            stmt.setString(1, null);
            stmt.setString(2, title_ans);
            stmt.setString(3, descritpion_ans);
            stmt.setInt(4, Integer.parseInt(release_ans));
            stmt.setInt(5, lang_id);
            stmt.setInt(6, org_lang_id);
            stmt.setInt(7, Integer.parseInt(seasons_ans));
            stmt.setString(8, rating_ans);
            stmt.setString(9, special_ft_ans);
            
            stmt.executeUpdate();
            
      
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    }

    @FXML
    private void actors_to_add1(MouseEvent event) {
        index3 = add_actor_table1.getSelectionModel().getSelectedIndex();
            if(index3<=-1){
                return;
            }
            else if(index3>-1){
                choose_actor_id = coll_actor_id1.getCellData(index3).toString();
                choose_actor_fname = coll_actor_fname1.getCellData(index3).toString();
                choose_actor_lname = coll_actor_lname1.getCellData(index3).toString();
            }
            oblist6.add(new Model_Actor_Choose(choose_actor_id,choose_actor_fname,choose_actor_lname));
            
            id_col1.setCellValueFactory(new PropertyValueFactory<>("id"));
            fname_col1.setCellValueFactory(new PropertyValueFactory<>("fname"));
            lname_col1.setCellValueFactory(new PropertyValueFactory<>("lname"));
        
            added_actors_table1.setItems(oblist6);
    }

    @FXML
    private void submit_serie(ActionEvent event) {
        if(category_series.getValue()==null){
            not_empty_label11.setOpacity(1);
        }
        else{
        try{    
            not_empty_label1111.setOpacity(0);
            not_empty_label11.setOpacity(0);
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT name FROM category ORDER BY category_id ASC");
            
            while(rs2.next()){
                oblist9.add(rs2.getString("name"));
            }
         
            ResultSet rs3 = conn.createStatement().executeQuery("SELECT category_id FROM category ORDER BY category_id ASC");
            
            while(rs3.next()){
                oblist10.add(rs3.getInt("category_id"));
            }
         
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT series_id FROM series WHERE title = '"+title_ans+"'");
          
         while(rs.next()){
           film_id1 = rs.getInt("series_id"); //xrisimopoio tin idia metavliti apo to film efoson arxikopoieitai ek neou
         }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
        
         category_ans = category_series.getValue();
         categ_index = oblist9.indexOf(category_ans);
         categ_id = oblist10.get(categ_index);
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
           String query2 = "INSERT INTO film_series_category VALUES(?,?,?)";
            stmt=conn.prepareStatement(query2);
            
            stmt.setString(1, null);
            stmt.setInt(2, categ_id);
            stmt.setInt(3, film_id1);
            
            stmt.executeUpdate();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
       
        try{    
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            for (int counter = 0; counter < oblist6.size(); counter++) { 
                
                String query2 = "INSERT INTO film_series_actor VALUES(?,?,?)";
                stmt=conn.prepareStatement(query2);
            
                stmt.setInt(1, Integer.parseInt(id_col1.getCellData(counter)));
                stmt.setString(2, null);
                stmt.setInt(3, film_id1);
            
                stmt.executeUpdate();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }  
        
        
         oblist6.clear();
         oblist7.clear();
         oblist8.clear();
         oblist9.clear();
         oblist10.clear();
        
        
      
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),add_actors_pane1);
        translate.setToX(715);
        translate.play();
        
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),add_series_pane);
        translate1.setToY(655);
        translate1.play();
        film_tab.setDisable(false);   
    }
    }

    @FXML
    private void submit_season_title(ActionEvent event) {
         if(season_episodesnumber.getText().trim().isEmpty()||season_title.getText().trim().isEmpty()
                 ||SeasonNo_series.getValue()==null||category_series.getValue()==null){
            not_empty_label111.setOpacity(1);
            not_empty_label1111.setOpacity(0);
        }
        else{
        not_empty_label111.setOpacity(0);
        not_empty_label1111.setOpacity(1);
        

        try{
            String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT series_id FROM series WHERE title = '"+title_ans+"'");
          
            while(rs.next()){
            film_id1 = rs.getInt("series_id"); //xrisimopoio tin idia metavliti apo to film efoson arxikopoieitai ek neou
            }
            String query = "INSERT INTO season VALUES (?,?,?,?,?)";
            stmt=conn.prepareStatement(query);
            
                stmt.setInt(1, film_id1);
                stmt.setInt(2, SeasonNo_series.getValue());
                stmt.setInt(3, Integer.parseInt(season_episodesnumber.getText()));
                stmt.setString(4, season_title_add.getText());
                stmt.setString(5, null);
                
            
                stmt.executeUpdate();
           
        }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
  
    }

    @FXML
    private void add_inventory(ActionEvent event) {
         inv_label.setOpacity(0);
        int film_null=1;//metavliti gia tin iparksi i oxi tis tainias sto inventory
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT film_id FROM inventory WHERE film_id = '"+film_id+"'");
          
            while(rs.next()){
                if(rs.getString("film_id")== ""+film_id){
                    film_null = 1;
                }
                else{
                    film_null = -1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(film_null == 1){
            try {
             String query = "INSERT INTO inventory VALUES (?,?)";
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setInt(2, film_id);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else if(film_null == -1){
            
            inv_label.setOpacity(1);
        }
    }

    @FXML
    private void add_inventory1(ActionEvent event) throws SQLException {
        inv_label1.setOpacity(0);
        int series_null=1;//metavliti gia tin iparksi i oxi tis tainias sto inventory
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT series_id FROM series_inventory WHERE series_id = '"+series_id+"'");
          
            while(rs.next()){
                if(rs.getString("series_id")== ""+series_id){
                    series_null = 1;
                }
                else{
                    series_null = -1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT season_id FROM season WHERE series_id = '"+series_id+"'");
          
            while(rs1.next()){

        if(series_null == 1){
            try {
             String query = "INSERT INTO series_inventory VALUES (?,?,?)";
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setInt(2, series_id);
            stmt.setInt(3, rs1.getInt("season_id"));
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else if(series_null == -1){
            
            inv_label1.setOpacity(1);
        }
    }
    }
    
}
