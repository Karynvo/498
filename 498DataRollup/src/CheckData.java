import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class CheckData {

	public static void main(String[] args) {
		String currFile = "C:\\xampp\\htdocs\\Thesis\\newData.csv";
		
		HashMap<String, Student> students = new HashMap<String, Student>();
		
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		int sidPosition = -1;
		int semesterPosition = -1;
		int majorPosition = -1;
		int ethnicityPosition = -1;
		int genderPosition = -1;
		int agePosition = -1;
		
		try{
			br = new BufferedReader(new FileReader(currFile));
			String[] header = br.readLine().replace("\"", "").split(csvSplitBy);
			
			// find position of sid
			for(int i = 0; i < header.length; i++){
				if(header[i].equalsIgnoreCase("PERSON_SID"))
					sidPosition = i;
				else if(header[i].equalsIgnoreCase("strm"))
					semesterPosition = i;
				else if(header[i].equalsIgnoreCase("plan_descr"))
					majorPosition = i;
				else if(header[i].equalsIgnoreCase("Ethnicity Code EPM SP"))
					ethnicityPosition = i;
				else if(header[i].equalsIgnoreCase("GENDER_CD"))
					genderPosition = i;
				else if(header[i].equalsIgnoreCase("UA_AGE"))
					agePosition = i;
			}
			
			while((line = br.readLine()) != null){
				//line = line.replace("\"", "");
				String[] student = line.split(csvSplitBy);
				
				String newKey = student[sidPosition];
				Student newStudent = new Student(student[sidPosition], student[genderPosition], student[ethnicityPosition], student[agePosition]);
				newStudent.addSemester(new Semester());
				
				if(students.containsKey(newKey))
					/*System.out.println(newKey)*/;
				else
					/*students.put(newKey, newList)*/;
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
