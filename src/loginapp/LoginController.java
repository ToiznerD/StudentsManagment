package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

public class LoginController implements Initializable{
	
	LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label dbstatus;
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private ComboBox<option> combobox;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label loginStatus;
	
	private int SID;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(this.loginModel.isDatabaseConnected()) {
			this.dbstatus.setText("Connected");
		}
		else
			this.dbstatus.setText("Not Connected");
		
		this.combobox.setItems(FXCollections.observableArrayList(option.values()));
	}
	
	@FXML
	void Login(ActionEvent event) {
		try {
			//Get login SID
			this.SID = this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.combobox.getValue()).toString());
			if(SID > 0) {
				Stage stage = (Stage)this.loginButton.getScene().getWindow();
				stage.close();
				
				//Open the relevant stage
				switch(((option)this.combobox.getValue()).toString()) {
					case "Admin":
						adminLogin();
						break;
					case "Student":
						studentLogin();
						break;
				}
			}
			else {
				this.loginStatus.setText("Wrong Details.");
			}
		}
		catch(Exception localException) {
			localException.printStackTrace();
		}
	}
	
	public void studentLogin() {
		try {
			//Create student stage
			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane studentRoot = (Pane)loader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
			
			StudentsController studentsController = (StudentsController)loader.getController();
			Scene scene = new Scene(studentRoot);
			userStage.setScene(scene);
			userStage.setTitle("Student Dashboard");
			userStage.setResizable(false);
			
			//Initialize stage data
			studentsController.setSID(this.SID);
			studentsController.setGreet();
			studentsController.loadStudentCourses();
			
			//Show stage
			userStage.show();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void adminLogin() {
		try {
			//Create admin stage
			Stage adminStage = new Stage();
			FXMLLoader adminLoader = new FXMLLoader();
			Pane adminroot = (Pane)adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());
			
			AdminController adminController = (AdminController)adminLoader.getController();
			Scene scene = new Scene(adminroot);
			adminStage.setScene(scene);
			adminStage.setTitle("Admin Dashboard");
			adminStage.setResizable(false);
			
			//Initialize stage data
			adminController.loadStudentData(new ActionEvent());
			adminStage.show();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
