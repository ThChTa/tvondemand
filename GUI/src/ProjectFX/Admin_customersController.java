/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ProjectFX;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import ProjectFX.Model_Cust_Table;
import com.mysql.cj.xdevapi.Statement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.WHITE;
import javafx.util.Duration;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author vpddk
 */
public class Admin_customersController implements Initializable {
    ObservableList<String> oblist8 = FXCollections.observableArrayList("Film Pack","Series pack","Full pack");
    ObservableList<String> oblist7 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist1 = FXCollections.observableArrayList();
    ObservableList<String> oblist2 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<String> oblist4 = FXCollections.observableArrayList();
    ObservableList<Model_Rent_Class> oblist5 = FXCollections.observableArrayList();
    ObservableList<Model_Series_Rent> oblist6 = FXCollections.observableArrayList();
    int ind1;
    String ind_city_id,rent_cust_id;
    String ind_address_id;
    @FXML
    private TableView<Model_Cust_Table> customers_table;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_id;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_fname;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_lname;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_mail;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_address;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_district;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_pcode;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_phone;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_city;
    @FXML
    private TableColumn<Model_Cust_Table,String> col_country;

    PreparedStatement stmt = null;
    Connection conn = null;
    /**
     * Initializes the controller class.
     */
    ObservableList<Model_Cust_Table> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;
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
    private Label not_empty_label;
    @FXML
    private Pane cust_reg;
    @FXML
    private Button add_customer;
    @FXML
    private Pane cust_reg1;
    @FXML
    private Pane cust_reg2;
    
    int index=-1;
    @FXML
    private TextField email;
    @FXML
    private Pane cust_reg3;
    @FXML
    private TextField fname1;
    @FXML
    private TextField lname1;
    @FXML
    private TextField address1;
    @FXML
    private TextField district1;
    @FXML
    private TextField pcode1;
    @FXML
    private TextField phone1;
    private TextField city1;
    private TextField country1;
    @FXML
    private Label not_empty_label11;
    @FXML
    private TextField email1;
    @FXML
    private Pane cust_reg21;
    @FXML
    private Pane cust_reg11;
    
    long cust_phone; // metavliti gia tin anazitisi tou sostou address_id kata ti leitourgeia update
    int ind;
    int update_address_id;
    String city_answer;
    String address_phone,curr_cust_city1;
    int curr_cust_fpk,curr_cust_spk,curr_cut_ffpk,fpk,spk,ffpk,city_new;
    private Label not_empty_label12;
    @FXML
    private ComboBox<String> city_options;
    private Label not_empty_label21;
    @FXML
    private Pane cust_reg32;
    @FXML
    private Label not_empty_label111;
    @FXML
    private Label not_empty_label2;
    @FXML
    private Button update_button;
    int cust_id;
    @FXML
    private Button delete_button;
    @FXML
    private Pane rental_info;
    @FXML
    private TableView<Model_Rent_Class> rent_hist_table;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_id;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_date;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_rent_film_title;
    @FXML
    private Button rent_button;
    @FXML
    private TableColumn<Model_Rent_Class, String> col_cr_date;
    @FXML
    private TableColumn<Model_Cust_Table, String> col_film_pack;
    @FXML
    private TableColumn<Model_Cust_Table, String> col_series_pack;
    @FXML
    private TableColumn<Model_Cust_Table, String> col_full_pack;
    @FXML
    private TableColumn<Model_Cust_Table, String> col_active;
    @FXML
    private TableView<Model_Series_Rent> rent_hist_table2;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_id2;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_date2;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_rent_film_title2;
    @FXML
    private TableColumn<Model_Series_Rent, String> col_season_title;
    @FXML
    private Label curr_cust_city;
    @FXML
    private Label curr_cust_pack;
    @FXML
    private ComboBox<String> combo_city;
    @FXML
    private ComboBox<String> pack_combo;
    @FXML
    private ComboBox<String> pack_combo1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ffpk = fpk = spk = 0;
        pack_combo.setItems(oblist8);
        pack_combo1.setItems(oblist8);
        oblist7.clear();
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist7.add(rs.getString("city"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Customer_registrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_city.setItems(oblist7);
    }    

    @FXML
    private void fetch(ActionEvent event) {
        oblist.clear();
        oblist1.clear();
        oblist4.clear();
        
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT customer.customer_id, customer.first_name, customer.last_name,customer.email,customer.create_date,"
                 + "customer.film_pack,customer.series_pack,customer.full_pack,customer.active,"
                 + "address.address,address.district,address.postal_code,address.phone,city.city,country.country "
                 + "FROM customer INNER JOIN address ON customer.address_id = address.address_id"
                 + " INNER JOIN city ON address.city_id = city.city_id "
                 + " INNER JOIN country ON city.country_id = country.country_id");
          
         while(rs.next()){
             oblist.add(new Model_Cust_Table(rs.getString("customer_id"),rs.getString("first_name"),rs.getString("last_name"),
                     rs.getString("email"),rs.getString("address"),rs.getString("district"),rs.getString("postal_code"),rs.getString("phone"),
                     rs.getString("city"),rs.getString("country"),rs.getString("create_date"),rs.getString("film_pack"),rs.getString("series_pack"),rs.getString("full_pack"),rs.getString("active")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));    //Onomata metavliton apo klasi Model_Cust_Table
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_district.setCellValueFactory(new PropertyValueFactory<>("district"));
        col_pcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        col_cr_date.setCellValueFactory(new PropertyValueFactory<>("cr_date"));
        col_film_pack.setCellValueFactory(new PropertyValueFactory<>("fpack"));
        col_series_pack.setCellValueFactory(new PropertyValueFactory<>("spack"));
        col_full_pack.setCellValueFactory(new PropertyValueFactory<>("flpack"));
        col_active.setCellValueFactory(new PropertyValueFactory<>("active"));
        
        
        customers_table.setItems(oblist);
        
       
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Cust_Table> filteredData = new FilteredList<>(oblist, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(customer -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if (customer.getCustomer_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (customer.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (customer.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getDistrict().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getPcode().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (customer.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Cust_Table> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(customers_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		customers_table.setItems(sortedData);
                
                 try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist4.add(rs.getString("city"));       //Onomata metavliton apo vasi
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
       
            city_options.setItems(oblist4);
    }

    @FXML
    private void cust_submit(MouseEvent event) {
    }

    @FXML
    private void add_customer(ActionEvent event) {
        email.setText("");
        fname.setText("");
        lname.setText("");
        address.setText("");
        district.setText("");
        pcode.setText("");
        phone.setText("");
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg);
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),cust_reg1);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.7),cust_reg2);
        translate.setToY(-620);
        translate1.setToY(-620);
        translate2.setToY(-700);
        translate.play();
        translate1.play();
        translate2.play();
        not_empty_label2.setOpacity(0);
        not_empty_label111.setOpacity(0);
    }

    @FXML
    private void slide_down(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg);
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),cust_reg1);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.7),cust_reg2);
        TranslateTransition translate3 = new TranslateTransition(Duration.seconds(0.7),cust_reg32);
        translate.setToY(620);
        translate1.setToY(620);
        translate2.setToY(700);
        translate3.setToX(710);
        translate.play();
        translate1.play();
        translate2.play();
        translate3.play();
    }

    @FXML
    private void submit(ActionEvent event) {
        oblist2.clear();
        oblist3.clear();
       if(address.getText().trim().isEmpty()||district.getText().trim().isEmpty()||pcode.getText().trim().isEmpty()||
                phone.getText().trim().isEmpty()||city_options.getValue()==null){
            not_empty_label21.setOpacity(0);
            not_empty_label12.setOpacity(1);
        }
        else{
        city_answer = city_options.getValue();
        ind1 = oblist4.indexOf(city_answer);
        ind_city_id = Integer.toString(oblist1.get(ind1));
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
            TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg32);
            translate.setToX(-710);
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
    private void show_stoixeia(MouseEvent event) {
        index = customers_table.getSelectionModel().getSelectedIndex();
       if(index<=-1){
           update_button.setDisable(true);
           delete_button.setDisable(true);
           rent_button.setDisable(true);
           return;
       }
       else if(index>-1){
       email1.setPromptText(col_mail.getCellData(index).toString());
       fname1.setText(col_fname.getCellData(index).toString());
       lname1.setText(col_lname.getCellData(index).toString());
       address1.setText(col_address.getCellData(index).toString());
       district1.setText(col_district.getCellData(index).toString());
       pcode1.setText(col_pcode.getCellData(index).toString());
       phone1.setText(col_phone.getCellData(index).toString());
       cust_phone = Long.parseLong(col_phone.getCellData(index).toString()); // arxikopoiisi pou tha xrisimopoiithei sti methodo update
       cust_id = Integer.parseInt(col_id.getCellData(index).toString());
       update_button.setDisable(false);
       delete_button.setDisable(false);
       rent_button.setDisable(false);
       curr_cust_city1 = col_city.getCellData(index).toString();
       curr_cust_fpk = Integer.parseInt(col_film_pack.getCellData(index).toString());
       curr_cust_spk = Integer.parseInt(col_series_pack.getCellData(index).toString());
       curr_cut_ffpk = Integer.parseInt(col_full_pack.getCellData(index).toString());
       update_button.setDisable(false);
       delete_button.setDisable(false);
       rent_button.setDisable(false);
        if(curr_cut_ffpk==1){
            curr_cust_pack.setText("Full Pack");
            ffpk = 1;
        }
        if(curr_cust_spk==1){
            curr_cust_pack.setText("Series Pack");
            spk = 1;
        }
        if(curr_cust_fpk==1){
            curr_cust_pack.setText("Film Pack");
            fpk = 1;
        }
        curr_cust_city.setText(curr_cust_city1);  
       }
    }

    @FXML
    private void update_cust(ActionEvent event) {
         TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg3);
         TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),cust_reg11);
         TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.7),cust_reg21);
         translate.setToY(-620);
         translate1.setToY(-620);
         translate2.setToY(-700);
         translate.play();
         translate1.play();
         translate2.play();
         not_empty_label1.setOpacity(0);
         not_empty_label11.setOpacity(0);
    }

    @FXML
    private void update(ActionEvent event) {
        int fpk=0;
        int spk=0;
        int ffpk=0;
         if(pack_combo.getValue()=="Film Pack"){
            fpk = 1;
        }
        else if(pack_combo.getValue()=="Series pack"){
            spk = 1;
        }
        else if(pack_combo.getValue()=="Full pack"){
            ffpk = 1;
        }
         String up_fname,up_lname,up_address,up_district,up_pcode,up_phone;
         up_fname = fname1.getText().toString();
         up_lname = lname1.getText().toString();
         up_address = address1.getText().toString();
         up_district = district1.getText().toString();
         up_pcode = pcode1.getText().toString();
         up_phone = phone1.getText().toString();
         if(fname1.getText().trim().isEmpty()||lname1.getText().trim().isEmpty()
                  ||address1.getText().trim().isEmpty()||district1.getText().trim().isEmpty()
                  ||pcode1.getText().trim().isEmpty()||phone1.getText().trim().isEmpty()||pack_combo.getValue()==null||combo_city.getValue()==null){
            not_empty_label1.setOpacity(0);
            not_empty_label11.setOpacity(1);
        }
         else{
            try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address WHERE phone ='" + cust_phone +"'");
          
         while(rs.next()){
             update_address_id = rs.getInt("address_id");
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
            try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city_id FROM city WHERE city = '"+combo_city.getValue()+"'");
          
            while(rs.next()){
                city_new = rs.getInt("city_id");
            }
            } catch (SQLException ex) {
            Logger.getLogger(Employee_catalogController.class.getName()).log(Level.SEVERE, null, ex);
            }
             try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         String query ="UPDATE customer SET first_name = '"+up_fname+"' ,last_name = '"+up_lname+"' WHERE customer_id = '"+cust_id+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="UPDATE address SET address = '"+up_address+"' ,district = '"+up_district+"', postal_code='"+up_pcode+"',phone='"+up_phone+"',city_id = '"+city_new+"' WHERE address_id =  '"+update_address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
             not_empty_label1.setOpacity(1);
             not_empty_label11.setOpacity(0); 
             
             try{    
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
                String query ="UPDATE customer SET film_pack = '"+fpk+"' ,series_pack = '"+spk+"',full_pack = '"+ffpk+"' WHERE customer_id = '"+cust_id+"'";
                stmt=conn.prepareStatement(query);
                stmt.executeUpdate(query);          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         }
         
    }

    @FXML
    private void slide_down1(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),cust_reg3);
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.7),cust_reg11);
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(0.7),cust_reg21);
        translate.setToY(620);
        translate1.setToY(620);
        translate2.setToY(700);
        translate.play();
        translate1.play();
        translate2.play();
    }

    @FXML
    private void cust_submit1(ActionEvent event) {
        int fpk=0;
        int spk=0;
        int ffpk=0;
         if(pack_combo1.getValue()=="Film Pack"){
            fpk = 1;
        }
        else if(pack_combo1.getValue()=="Series pack"){
            spk = 1;
        }
        else if(pack_combo1.getValue()=="Full pack"){
            ffpk = 1;
        }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
            LocalDateTime now = LocalDateTime.now();  
        if(email.getText().trim().isEmpty()||fname.getText().trim().isEmpty()||lname.getText().trim().isEmpty()){
            not_empty_label2.setOpacity(0);
            not_empty_label111.setOpacity(1);
        }
        else{
        address_phone = address.getText();
        ind = oblist2.indexOf(address_phone);
        ind_address_id = Integer.toString(oblist3.get(ind));
    
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
            stmt.setString(8, ""+fpk);
            stmt.setString(9, ""+spk);
            stmt.setString(10, ""+ffpk);
            
            not_empty_label2.setOpacity(1);
            not_empty_label111.setOpacity(0);
            stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address WHERE phone ='" + cust_phone +"'");
          
         while(rs.next()){
             update_address_id = rs.getInt("address_id");
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
             try{     
                 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
               
                 String query3 ="DELETE FROM rental WHERE customer_id =  '"+cust_id+"'";
         stmt=conn.prepareStatement(query3);
          stmt.executeUpdate(query3);   
                 
          String query2 ="DELETE FROM payment WHERE customer_id =  '"+cust_id+"'";
         stmt=conn.prepareStatement(query2);
          stmt.executeUpdate(query2); 
          
        
         String query ="DELETE FROM customer WHERE address_id = '"+update_address_id+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="DELETE FROM address WHERE address_id =  '"+update_address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);
  
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
    }

    @FXML
    private void see_rental_info(ActionEvent event) {
         
        if(index>-1){
       cust_id = Integer.parseInt(col_id.getCellData(index).toString());
       }
         oblist5.clear();
         
        try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT rental_id,rental_date,inventory_id FROM rental WHERE customer_id='"+cust_id+"'");
          
         while(rs.next()){
                ResultSet rs2 = conn.createStatement().executeQuery("SELECT film_id FROM inventory WHERE inventory_id='"+rs.getString("inventory_id")+"'");
             
             while(rs2.next()){
                ResultSet rs1 = conn.createStatement().executeQuery("SELECT title FROM film WHERE film_id='"+rs2.getString("film_id")+"'");
                    
                    while(rs1.next()){
                        oblist5.add(new Model_Rent_Class(rs.getString("rental_id"),rs.getString("rental_date"),rs1.getString("title"))); //Onomata metavliton apo vasi
                    
                    
                    col_rent_id.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
                    col_rent_date.setCellValueFactory(new PropertyValueFactory<>("rental_date"));
                    col_rent_film_title.setCellValueFactory(new PropertyValueFactory<>("film_title"));
        
                    }
                    rent_hist_table.setItems(oblist5);
             }    
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
                    
                    
                    col_rent_id2.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
                    col_rent_date2.setCellValueFactory(new PropertyValueFactory<>("rental_date"));
                    col_rent_film_title2.setCellValueFactory(new PropertyValueFactory<>("series_title"));
                    col_season_title.setCellValueFactory(new PropertyValueFactory<>("season_title"));
        
                    }
                    rent_hist_table2.setItems(oblist6);
             }    
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rental_info);
        translate.setToY(-640);
        translate.play();
    }

    @FXML
    private void rental_back(ActionEvent event) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.7),rental_info);
        translate.setToY(640);
        translate.play();
    }


}