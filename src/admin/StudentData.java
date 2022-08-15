package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
	
	private StringProperty ID;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty DOB;
	
	public StudentData(String id, String firstname, String lastname, String email, 
			String dob) {
		this.ID = new SimpleStringProperty(id);
		this.firstName = new SimpleStringProperty(firstname);
		this.lastName = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.DOB = new SimpleStringProperty(dob);
		
	}
	
	public StudentData(String id, String firstname, String lastname) {
		this.ID = new SimpleStringProperty(id);
		this.firstName = new SimpleStringProperty(firstname);
		this.lastName = new SimpleStringProperty(lastname);
		
	}

	public StringProperty getID() {
		return ID;
	}

	public void setID(StringProperty iD) {
		ID = iD;
	}

	public StringProperty getFirstName() {
		return firstName;
	}

	public void setFirstName(StringProperty firstName) {
		this.firstName = firstName;
	}

	public StringProperty getLastName() {
		return lastName;
	}

	public void setLastName(StringProperty lastName) {
		this.lastName = lastName;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getDOB() {
		return DOB;
	}

	public void setDOB(StringProperty dOB) {
		DOB = dOB;
	}
	
	public StringProperty IDProperty() {
		return this.ID;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public StringProperty firstNameProperty() {
		return this.firstName;
	}
	
	public StringProperty lastNameProperty() {
		return this.lastName;
	}
	
	public StringProperty DOBProperty() {
		return this.DOB;
	}
	
}
