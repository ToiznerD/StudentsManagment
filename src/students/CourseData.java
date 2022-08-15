package students;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseData {
	private StringProperty CID;
	private StringProperty Name;
	private StringProperty Lecturer;
	private StringProperty Credits;
	private StringProperty Lecture;
	private StringProperty Practice;
	
	public CourseData(String cid, String name, String lec, String cred, 
			String lect, String prc) {
		this.CID = new SimpleStringProperty(cid);
		this.Name = new SimpleStringProperty(name);
		this.Lecturer = new SimpleStringProperty(lec);
		this.Credits = new SimpleStringProperty(cred);
		this.Lecture = new SimpleStringProperty(lect);
		this.Practice = new SimpleStringProperty(prc);
	}

	public StringProperty getCID() {
		return CID;
	}

	public void setCID(StringProperty cid) {
		CID = cid;
	}

	public StringProperty getName() {
		return Name;
	}

	public void setName(StringProperty Name) {
		this.Name = Name;
	}

	public StringProperty getLecturer() {
		return Lecturer;
	}

	public void setLecturer(StringProperty lecturer) {
		this.Lecturer = lecturer;
	}

	public StringProperty getCredits() {
		return Credits;
	}

	public void setCredits(StringProperty credits) {
		this.Credits = credits;
	}

	public StringProperty getLecture() {
		return Lecture;
	}

	public void setLecture(StringProperty lecture) {
		Lecture = lecture;
	}
	
	public StringProperty CIDProperty() {
		return this.CID;
	}
	
	public StringProperty LecturerProperty() {
		return Lecturer;
	}
	
	public StringProperty NameProperty() {
		return this.Name;
	}
	
	public StringProperty CreditsProperty() {
		return this.Credits;
	}
	
	public StringProperty LectureProperty() {
		return this.Lecture;
	}
	
	public StringProperty PracticeProperty() {
		return this.Practice;
	}
}
