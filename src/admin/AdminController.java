package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import students.CourseData;
import students.GradeData;

public class AdminController implements Initializable{
	@FXML
	private TextField id;
	
	@FXML
	private TextField firstname;
	
	@FXML
	private TextField lastname;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dob;
	
	@FXML
	private TableView<StudentData> studenttable;
	
	@FXML
	private TableView<CourseData> course_table;
	
	@FXML
	private TableView<StudentData> as_table;
	
	@FXML
	private TableColumn<StudentData, String> as_id;
	
	@FXML
	private TableColumn<StudentData, String> as_fname;
	
	@FXML
	private TableColumn<StudentData, String> as_lname;
	
	@FXML
	private TableColumn<StudentData, String> idcolumn;
	
	@FXML
	private TableColumn<StudentData, String> firstnamecolumn;
	
	@FXML
	private TableColumn<StudentData, String> lastnamecolumn;
	
	@FXML
	private TableColumn<StudentData, String> emailcolumn;
	
	@FXML
	private TableColumn<StudentData, String> dobcolumn;
	
	@FXML
	private TableColumn<CourseData, String> cid_col;
	
	@FXML
	private TableColumn<CourseData, String> cname_col;
	
	@FXML
	private TableColumn<CourseData, String> lec_col;
	
	@FXML
	private TableColumn<CourseData, String> cred_col;
	
	@FXML
	private TableColumn<CourseData, String> lec_t_col;
	
	@FXML
	private TableColumn<CourseData, String> prc_col;
	
	@FXML
	private TextField as_cid;
	
	@FXML
	private Button submit;
	
	private ObservableList<CourseData> data_courses;
	
	private ObservableList<StudentData> data;
	
	private ObservableList<StudentData> as_data;
	
	private String sql = "SELECT * FROM students";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	public void loadStudentData(ActionEvent event) {
		try {
			Connection conn = dbConnection.getConnection();
			this.data = FXCollections.observableArrayList();
			
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()) 
				this.data.add(new StudentData(rs.getString(1), rs.getString(2),	rs.getString(3), rs.getString(4), rs.getString(5)));

			
		} catch(SQLException e) {
			System.err.println("Error " + e);
		}
		
		this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
		this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
		this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
		this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
		this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("DOB"));
		
		this.studenttable.setItems(null);
		this.studenttable.setItems(this.data);
		
	}
	
	@FXML
	private void addStudent(ActionEvent event) {
		String query = "INSERT INTO students(id,fname,lname,email,DOB) VALUES (?,?,?,?,?)";
		
		try {
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, this.id.getText());
			stmt.setString(2, this.firstname.getText());
			stmt.setString(3, this.lastname.getText());
			stmt.setString(4, this.email.getText());
			stmt.setString(5, this.dob.getEditor().getText());
			
			stmt.execute();
			clearFields(new ActionEvent());
			stmt.close();
			conn.close();
			loadStudentData(new ActionEvent());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void clearFields(ActionEvent event) {
		this.id.setText("");
		this.firstname.setText("");
		this.lastname.setText("");
		this.email.setText("");
		this.dob.setValue(null);
		
	}
	
	public void loadCourses() {
		try {
			Connection connection = dbConnection.getConnection();
			this.data_courses = FXCollections.observableArrayList();
			String qry = "SELECT * FROM course";
			PreparedStatement ps = connection.prepareStatement(qry);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.data_courses.add(new CourseData(rs.getString(1), rs.getString(2),	rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch(SQLException e) {
			System.err.println("Error " + e);
		}
		

		this.cid_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("CID"));
		this.cname_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Name"));
		this.lec_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Lecturer"));
		this.cred_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Credits"));
		this.lec_t_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Lecture"));
		this.prc_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Practice"));
		
		this.course_table.setItems(null);
		this.course_table.setItems(this.data_courses);
	}
	
	@FXML
	private void loadAssignedStudents(ActionEvent event) {
		int cid = Integer.valueOf(as_cid.getText());
		String query = "SELECT * FROM students S, student_course SC WHERE S.id = SC.SID AND SC.CID = ?";
		try {
			Connection conn = dbConnection.getConnection();
			this.as_data = FXCollections.observableArrayList();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.as_data.add(new StudentData(rs.getString(1), rs.getString(2),	rs.getString(3)));
			}
			
		} catch(SQLException e) {
			System.err.println("Error " + e);
		}
		
		this.as_id.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
		this.as_fname.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
		this.as_lname.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
		
		this.as_table.setItems(null);
		this.as_table.setItems(this.as_data);
	}
}
