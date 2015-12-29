import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MergeData {
	
	static HashMap<String, ArrayList<String>> dataTable = new HashMap<String, ArrayList<String>>();
	static ArrayList<String> header = null;
//	static HashMap<String, String> genderMap = new HashMap<String, String>();
	
	public static void main(String[] args) {
		String shorterFile = "C:\\xampp\\htdocs\\Thesis\\CarlosData-SET 12.csv";
		String longerFile = "C:\\xampp\\htdocs\\Thesis\\CarlosData-SET 2.csv";
		String newFile = "C:\\xampp\\htdocs\\Thesis\\newData.csv";
		
		BufferedReader br = null;
		BufferedReader br2 = null;
		String line = "";
		String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		
		try{
			br = new BufferedReader(new FileReader(shorterFile));
			String[] header1 = br.readLine().replace("\"", "").split(csvSplitBy);
			while((line = br.readLine()) != null){
				//line = line.replace("\"", "");
				String[] student = line.split(csvSplitBy);
				
				String newKey = student[0] + student[1] + student[3];
				ArrayList<String> newList = new ArrayList<String>(Arrays.asList(student));
				if(dataTable.containsKey(newKey))
					/*System.out.println(newKey)*/;
				else
					dataTable.put(newKey, newList);
			}
			
			br2 = new BufferedReader(new FileReader(longerFile));
			String[] header2 = br2.readLine().replace("\"", "").split(csvSplitBy);
			while((line = br2.readLine()) != null){
				line = line.replace("\"", "");
				String[] student = line.split(csvSplitBy);
				
				String newKey = student[0] + student[1] + student[2];
				if(dataTable.containsKey(newKey)){
					ArrayList<String> values = dataTable.get(newKey);
					for(String s : student){
						if(!values.contains(s))
							values.add(s);
					}
					dataTable.put(newKey, values);
				}else
					/*System.out.println(newKey)*/;
			}
			
			header = new ArrayList<String>(Arrays.asList(header1));
			for(String s : header2){
				if(!header.contains(s))
					header.add(s);
			}
			
			// Write file
			File fout = new File(newFile);
			FileOutputStream fos = new FileOutputStream(fout);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < header.size(); i++){
				sb.append(header.get(i));
				if(i != header.size()-1)
					sb.append(",");
				else
					sb.append("\n");
			}
			
			bw.write(sb.toString());
			sb.setLength(0);
			
			for(Map.Entry<String, ArrayList<String>> entry : dataTable.entrySet()){
				ArrayList<String> content = entry.getValue();	
				
				for(int i = 0; i < content.size(); i++){
					sb.append(content.get(i));
					if(i != content.size()-1)
						sb.append(",");
					else
						sb.append("\n");
				}
				
				bw.write(sb.toString());
				
				sb.setLength(0);
			}
			
			bw.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
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
			
			if (br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
