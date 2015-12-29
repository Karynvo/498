import java.util.ArrayList;


public class Student {
	
//	public enum Gender{
//		M, F
//	}
	
	private ArrayList<Semester> semesters;
	private String id;
	private String gender; // make enum?
	private String ethnicity;
	private String age;
	
	public Student(String id, String gender, String ethn, String age){
		semesters = new ArrayList<Semester>();
		setId(id);
		setGender(gender);
		setEthnicity(ethn);
		setAge(age);
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setGender(String g){
		this.gender = g;
	}
	
	public void setEthnicity(String ethn){
		this.ethnicity = ethn;
	}
	
	public void setAge(String age){
		this.age = age;
	}
	
	public void addSemester(Semester newSemester){
		semesters.add(newSemester);
	}
}
