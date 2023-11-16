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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author PX
 */
public class Admin_adminsController implements Initializable {
    PreparedStatement stmt = null;
    Connection conn = null;
    @FXML
    private TextField filterField;
    @FXML
    private Button add_employee;
    @FXML
    private Button update_button;
    @FXML
    private Button delete_button;
    @FXML
    private TableView<Model_Admins_Table> admins_table;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_id;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_fname;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_lname;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_mail;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_gender;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_salary;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_address;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_district;
    @FXML
    private TableColumn<Model_Admins_Table, String>col_pcode;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_phone;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_city;
    @FXML
    private TableColumn<Model_Admins_Table, String> col_country;
    @FXML
    private Pane cust_reg2;
    @FXML
    private Pane cust_reg1;
    @FXML
    private Pane cust_reg21;
    @FXML
    private Pane cust_reg11;
    @FXML
    private Pane cust_reg;
    @FXML
    private TextField address;
    @FXML
    private TextField district;
    @FXML
    private TextField pcode;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox<String> city_options;
    @FXML
    private Pane cust_reg32;
    @FXML
    private TextField lname;
    @FXML
    private TextField fname;
    @FXML
    private Label not_empty_label111;
    @FXML
    private Label not_empty_label2;
    @FXML
    private TextField email;
    @FXML
    private TextField salary;
    @FXML
    private ComboBox<String> gender;
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
    @FXML
    private Label not_empty_label11;
    @FXML
    private Label not_empty_label1;
    @FXML
    private TextField email1;
    @FXML
    private TextField salary1;
    ObservableList<Model_Admins_Table> oblist = FXCollections.observableArrayList();
    ObservableList<Integer> oblist1 = FXCollections.observableArrayList();
    ObservableList<String> oblist2 = FXCollections.observableArrayList();
    ObservableList<Integer> oblist3 = FXCollections.observableArrayList();
    ObservableList<String> oblist4 = FXCollections.observableArrayList();
    ObservableList<String> oblist7 = FXCollections.observableArrayList();
    private ObservableList<String> gender_list = FXCollections.observableArrayList("Male","Female");
    
    String city_answer;
     int ind1;
    String ind_city_id;
    String ind_address_id,curr_cust_city1;
    int index=-1;
    int ind;
    String address_phone;
    int cust_id,city_new;
    long cust_phone;
    int update_address_id;
    @FXML
    private Button make_employee_button;
    @FXML
    private Label naii;
    @FXML
    private Label curr_cust_city;
    @FXML
    private ComboBox<String> combo_city;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.setItems(gender_list);
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
         ResultSet rs = conn.createStatement().executeQuery("SELECT administrator.admin_id, administrator.first_name, administrator.last_name,administrator.email,administrator.gender"
                 + ",administrator.salary,"
                 + "address.address,address.district,address.postal_code,address.phone,city.city,country.country "
                 + "FROM administrator INNER JOIN address ON administrator.address_id = address.address_id"
                 + " INNER JOIN city ON address.city_id = city.city_id "
                 + " INNER JOIN country ON city.country_id = country.country_id");
          
         while(rs.next()){
             oblist.add(new Model_Admins_Table(rs.getString("admin_id"),rs.getString("first_name"),rs.getString("last_name"),
                     rs.getString("email"),rs.getString("gender"),rs.getString("salary"),
                     rs.getString("address"),rs.getString("district"),rs.getString("postal_code"),rs.getString("phone"),
                     rs.getString("city"),rs.getString("country")));       //Onomata metavliton apo vasi
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
        col_id.setCellValueFactory(new PropertyValueFactory<>("admins_id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));    //Onomata metavliton apo klasi Model_Cust_Table
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_district.setCellValueFactory(new PropertyValueFactory<>("district"));
        col_pcode.setCellValueFactory(new PropertyValueFactory<>("pcode"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        
        admins_table.setItems(oblist);
        
       
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Model_Admins_Table> filteredData = new FilteredList<>(oblist, b -> true);    //SearchBar
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(admin -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				if (admin.getAdmins_id().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (admin.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (admin.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getGender().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getSalary().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getDistrict().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getPcode().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
                                else if (admin.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1){
				     return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Model_Admins_Table> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(admins_table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		admins_table.setItems(sortedData);
                
                 try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist4.add(rs.getString("city"));       //Onomata metavliton apo vasi
            }
           
            } catch (SQLException ex) {
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
                try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT city_id FROM city ORDER BY city ASC");
            
            while(rs.next()){
                oblist1.add(rs.getInt("city_id"));       //Onomata metavliton apo vasi
            }
           
            } catch (SQLException ex) {
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
            city_options.setItems(oblist4);
    }

    @FXML
    private void add_employee(ActionEvent event) {
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
        
         String query ="DELETE FROM administrator WHERE address_id = '"+update_address_id+"'";
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
    private void show_stoixeia(MouseEvent event) {
        index = admins_table.getSelectionModel().getSelectedIndex();
       if(index<=-1){
           update_button.setDisable(true);
           delete_button.setDisable(true);
           make_employee_button.setDisable(true);
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
       salary1.setText(col_salary.getCellData(index).toString());
       cust_phone = Long.parseLong(col_phone.getCellData(index).toString()); // arxikopoiisi pou tha xrisimopoiithei sti methodo update
       cust_id = Integer.parseInt(col_id.getCellData(index).toString());
       curr_cust_city1 = col_city.getCellData(index).toString();
       update_button.setDisable(false);
       delete_button.setDisable(false);
       make_employee_button.setDisable(false);
       curr_cust_city.setText(curr_cust_city1);
       }
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
    private void submit(ActionEvent event) {
        oblist2.clear();
        oblist3.clear();
       if(address.getText().trim().isEmpty()||district.getText().trim().isEmpty()||pcode.getText().trim().isEmpty()||
                phone.getText().trim().isEmpty()||city_options.getValue()==null){
            //not_empty_label21.setOpacity(0);
            //not_empty_label12.setOpacity(1);
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
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address ORDER BY phone ASC"); //monadiko tilefono den tha iparxei sigxisi
            
            while(rs.next()){
                oblist3.add(rs.getInt("address_id"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
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
        if(email.getText().trim().isEmpty()||fname.getText().trim().isEmpty()||lname.getText().trim().isEmpty()||salary.getText().trim().isEmpty()
              ||gender.getValue()==null){
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
        
         String query = "INSERT INTO administrator VALUES (?,?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setString(2, fname.getText());
            stmt.setString(3, lname.getText());
            stmt.setString(4, email.getText());
            stmt.setString(5, gender.getValue());
            stmt.setString(6, ind_address_id);
            stmt.setString(7, salary.getText());
            
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
    private void cust_submit(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        String up_fname,up_lname,up_address,up_district,up_pcode,up_phone,up_salary;
         up_fname = fname1.getText().toString();
         up_lname = lname1.getText().toString();
         up_address = address1.getText().toString();
         up_district = district1.getText().toString();
         up_pcode = pcode1.getText().toString();
         up_phone = phone1.getText().toString();
         up_salary = salary1.getText().toString();
         
         if(fname1.getText().trim().isEmpty()||lname1.getText().trim().isEmpty()
                  ||address1.getText().trim().isEmpty()||district1.getText().trim().isEmpty()
                  ||pcode1.getText().trim().isEmpty()||phone1.getText().trim().isEmpty()||salary1.getText().trim().isEmpty()||combo_city.getValue()==null){
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
         String query ="UPDATE administrator SET first_name = '"+up_fname+"' ,last_name = '"+up_lname+"' ,salary='"+up_salary+"' WHERE address_id = '"+update_address_id+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
          
         String query1 ="UPDATE address SET address = '"+up_address+"' ,district = '"+up_district+"', postal_code='"+up_pcode+"',phone='"+up_phone+"',city_id='"+city_new+"' WHERE address_id =  '"+update_address_id+"'";
         stmt=conn.prepareStatement(query1);
          stmt.executeUpdate(query1);          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }    
             not_empty_label1.setOpacity(1);
             not_empty_label11.setOpacity(0);
         }
    }

    @FXML
    private void make_employee(ActionEvent event) {
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//lipsi torinis oras kai imerominias  
         LocalDateTime now = LocalDateTime.now();
         index = admins_table.getSelectionModel().getSelectedIndex();
        
        oblist2.clear();
        oblist3.clear();
        
        
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address FROM address ORDER BY phone ASC"); //monadiko tilefono den tha iparxei sigxisi
            
            while(rs.next()){
                oblist2.add(rs.getString("address"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
            ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address ORDER BY phone ASC"); //monadiko tilefono den tha iparxei sigxisi
            
            while(rs.next()){
                oblist3.add(rs.getInt("address_id"));       //Onomata metavliton apo vasi
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Admin_adminsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        address_phone = col_address.getCellData(index).toString();
        ind = oblist2.indexOf(address_phone);
        ind_address_id = Integer.toString(oblist3.get(ind));
         
         try{      
        String url="jdbc:mysql://localhost:3306/tvondemand";
        String uname="root";
        String password="root";
        
         String query = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?)";
         conn=DriverManager.getConnection(url,uname,password);
         stmt=conn.prepareStatement(query);
           
            stmt.setString(1, null);
            stmt.setString(2, col_fname.getCellData(index).toString());
            stmt.setString(3, col_lname.getCellData(index).toString());
            stmt.setString(4,col_mail.getCellData(index).toString());
            stmt.setString(5,col_gender.getCellData(index).toString());
            stmt.setString(6, ind_address_id);
            stmt.setString(7, dtf.format(now));
            stmt.setString(8, col_salary.getCellData(index).toString());
            stmt.setString(9, "C2");
            
            stmt.executeUpdate();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
          try{    
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
         ResultSet rs = conn.createStatement().executeQuery("SELECT address_id FROM address WHERE phone ='" + col_phone.getCellData(index) +"'");
          
         while(rs.next()){
             update_address_id = rs.getInt("address_id");
         }
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
             try{     
                 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tvondemand","root","root");
        
         String query ="DELETE FROM administrator WHERE address_id = '"+update_address_id+"'";
          stmt=conn.prepareStatement(query);
          stmt.executeUpdate(query);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }   
         
    }
    
}
