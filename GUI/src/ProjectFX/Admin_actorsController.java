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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_actorsController implements Initializable {

    @FXML
    private TableView<Model_Actor_Choose> add_actor_table1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_id1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_fname1;
    @FXML
    private TableColumn<Model_Actor_Choose, String> coll_actor_lname1;
    ObservableList<Model_Actor_Choose> oblist2 = FXCollections.observableArrayList();
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private TextField filterField11;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField first_name1;
    @FXML
    private TextField last_name1;
    @FXML
    private Button update_button;
    
    int index,actor_id;
    @FXML
    private Label not_empty_label_fail;
    @FXML
    private Label not_empty_label_suc;
    @FXML
    private Label not_empty_label_fail1;
    @FXML
    private Label not_empty_label_suc1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        update_button.setDisable(true);
        
    }    

    @FXML
    private void fetch(ActionEvent event) {
        
        not_empty_label_fail.setOpacity(0);
        not_empty_label_suc.setOpacity(0);
        not_empty_label_fail1.setOpacity(0);
        not_empty_label_suc1.setOpacity(0);
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
    private void show_info(MouseEvent event) {
         index = add_actor_table1.getSelectionModel().getSelectedIndex();
            if(index<=-1){
                update_button.setDisable(true);
                return;
            }
            else if(index>-1){
                first_name1.setText(coll_actor_fname1.getCellData(index).toString());
                last_name1.setText(coll_actor_lname1.getCellData(index).toString());
                actor_id = Integer.parseInt(coll_actor_id1.getCellData(index));
                update_button.setDisable(false);
            }
    }

    @FXML
    private void add_actor(ActionEvent event) {
        if(first_name.getText().trim().isEmpty()||last_name.getText().trim().isEmpty()){
            not_empty_label_fail1.setOpacity(1);
            not_empty_label_suc1.setOpacity(0);
        }
        else{
            not_empty_label_fail1.setOpacity(0);
            not_empty_label_suc1.setOpacity(1);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query1 = "INSERT INTO actor VALUES(?,?,?)";
         
                stmt=conn.prepareStatement(query1);
           
                stmt.setString(1, null);
                stmt.setString(2, first_name.getText());
                stmt.setString(3, last_name.getText());

                stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        }
        
    }

    @FXML
    private void update_actor(ActionEvent event) {
        if(first_name1.getText().trim().isEmpty()||last_name1.getText().trim().isEmpty()){
            not_empty_label_fail.setOpacity(1);
            not_empty_label_suc.setOpacity(0);
        }
        else{
            not_empty_label_fail.setOpacity(0);
            not_empty_label_suc.setOpacity(1);
            
            try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query ="UPDATE actor SET first_name = '"+first_name1.getText()+"' ,last_name = '"+last_name1.getText()+"' WHERE actor_id = '"+actor_id+"'";
                stmt=conn.prepareStatement(query);
                stmt.executeUpdate(query);  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        }
        
    }

    
    
}
