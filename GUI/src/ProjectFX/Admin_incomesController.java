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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_incomesController implements Initializable {
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private TableView<Model_Incomes_Table> incomes_table;
    @FXML
    private TableColumn<Model_Incomes_Table, String> col_month_year;
    @FXML
    private TableColumn<Model_Incomes_Table, String> col_total;
    
    ObservableList<Model_Incomes_Table> oblist2 = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void fetch(ActionEvent event) {
        double sum=0;
        oblist2.clear();
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT distinct * FROM total WHERE sum IS NOT NULL");
         
         while(rs.next()){
            if(rs.getString("sum")==null){
                sum = 0;
            }
            else{
                sum = rs.getDouble("sum");
            }
             oblist2.add(new Model_Incomes_Table(rs.getString("month_year"),sum+""));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_month_year.setCellValueFactory(new PropertyValueFactory<>("mon_year"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("amount"));//Onomata metavliton apo klasi Model_Film_Table
        
        
        incomes_table.setItems(oblist2);
        
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Incomes_Table> filteredData = new FilteredList<>(oblist2, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(actor -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if(actor.getMon_year().toLowerCase().indexOf(lowerCaseFilter) != -1){
                                        return true; // Filter matches last name.
                                }
                                else if (actor.getAmount().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Incomes_Table> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(incomes_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		incomes_table.setItems(sortedData);
     

}
}