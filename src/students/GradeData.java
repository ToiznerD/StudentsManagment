package students;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GradeData {
	private StringProperty Name;
	private StringProperty Grade;

	
	public GradeData(String Name, String Grade) {
		this.Name = new SimpleStringProperty(Name);
		this.Grade = new SimpleStringProperty(Grade);
	}



	public StringProperty getName() {
		return Name;
	}

	public void setName(StringProperty Name) {
		this.Name = Name;
	}


	public StringProperty getGrade() {
		return Grade;
	}

	public void setGrade(StringProperty Grade) {
		this.Grade = Grade;
	}

	public StringProperty NameProperty() {
		return this.Name;
	}
	
	public StringProperty GradeProperty() {
		return this.Grade;
	}

}
