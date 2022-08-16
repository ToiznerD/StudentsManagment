package students;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import admin.StudentData;
import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentsController implements Initializable{
	@FXML
	private TableView<CourseData> student_course;
	
	@FXML
	private TableView<GradeData> grade_sheet;
	
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
	private Label greet;
	
	@FXML
	private TableColumn<GradeData, String> course_col;
	
	@FXML
	private TableColumn<GradeData, String> grade_col;
	
	private ObservableList<CourseData> data_courses;
	
	private ObservableList<GradeData> data_grades;
	
	private int SID;

	
	public void setSID(int sid) {
		this.SID = sid;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setGreet() {
		//Update top label to greet the student
		String qry = "SELECT fname FROM students WHERE id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection connect = dbConnection.getConnection();
			ps = connect.prepareStatement(qry);
			ps.setInt(1, SID);
			rs = ps.executeQuery();
			if(rs.next()) {
				greet.setText("Hello " + rs.getString(1) + ". This is your listed courses:");
			}
			else greet.setText("Connection failed.");
			ps.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadStudentCourses() {
		//Load all the courses that student id is assigned to
		
		try {
			Connection connection = dbConnection.getConnection();
			this.data_courses = FXCollections.observableArrayList();
			String qry = "SELECT * FROM course C, student_course SC WHERE SC.SID = ? AND C.CID = SC.CID";
			PreparedStatement ps = connection.prepareStatement(qry);
			ps.setInt(1, SID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.data_courses.add(new CourseData(rs.getString(1), rs.getString(2),	rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch(SQLException e) {
			System.err.println("Error " + e);
		}
		
		//Apply values
		this.cid_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("CID"));
		this.cname_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Name"));
		this.lec_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Lecturer"));
		this.cred_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Credits"));
		this.lec_t_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Lecture"));
		this.prc_col.setCellValueFactory(new PropertyValueFactory<CourseData, String>("Practice"));
		
		//Show data
		this.student_course.setItems(null);
		this.student_course.setItems(this.data_courses);
	}
	
	public void loadStudentGrades() {
		//Show all the available grades for the student
		
		try {
			Connection connection = dbConnection.getConnection();
			this.data_grades = FXCollections.observableArrayList();
			String qry = "SELECT C.name, G.grade FROM course C, grades G WHERE G.SID = ? AND C.CID = G.CID";
			PreparedStatement ps = connection.prepareStatement(qry);
			ps.setInt(1, SID);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				this.data_grades.add(new GradeData(rs.getString(1), rs.getString(2)));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		//Apply values
		this.course_col.setCellValueFactory(new PropertyValueFactory<GradeData, String>("Name"));
		this.grade_col.setCellValueFactory(new PropertyValueFactory<GradeData, String>("Grade"));
		
		//Show data
		this.grade_sheet.setItems(null);
		this.grade_sheet.setItems(data_grades);
	}
	
}
