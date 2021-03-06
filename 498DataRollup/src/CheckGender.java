import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CheckGender {

	public static void main(String[] args) {

		String currFile = "C:\\xampp\\htdocs\\Thesis\\newData.csv";
		String newFile = "C:\\xampp\\htdocs\\Thesis\\newData2.csv";
		HashMap<String, String> directGender = new HashMap<String, String>();
		HashMap<String, ArrayList<String>> dataTable = new HashMap<String, ArrayList<String>>();
		ArrayList<String> headerCG = null;
		
		BufferedReader br = null;
		BufferedReader br2 = null;
		String line = "";
		String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
		
		try{
			br = new BufferedReader(new FileReader(currFile));
			String[] header1 = br.readLine().replace("\"", "").split(csvSplitBy);
			while((line = br.readLine()) != null){
				//line = line.replace("\"", "");
				String[] student = line.split(csvSplitBy);
				
				String newKey = student[1];
				String newValue = student[7];
				//ArrayList<String> newList = new ArrayList<String>(Arrays.asList(student));
				if(directGender.containsKey(newKey))
					/*System.out.println(newKey)*/;
				else{
					if(newValue.equals("M") || newValue.equals("F"))
						directGender.put(newKey, newValue);
				}
			}

			br2 = new BufferedReader(new FileReader(currFile));
			br2.readLine();
			while((line = br2.readLine()) != null){
				//line = line.replace("\"", "");
				String[] student = line.split(csvSplitBy);
				
				String checkGender = student[7];
				if(directGender.containsKey(student[1])){
					if(checkGender.equals(directGender.get(student[1]))){
						;// data had correct value
					}else
						student[7] = directGender.get(student[1]);

					String newKey = student[0] + student[1] + student[3];
					ArrayList<String> values = new ArrayList<String>(Arrays.asList(student));

					if(dataTable.containsKey(newKey))
						/*System.out.println(newKey)*/;
					else
						dataTable.put(newKey, values);
				}else
					/*System.out.println(newKey)*/;
			}
			
			headerCG = new ArrayList<String>(Arrays.asList(header1));
			
			// Write file
			File fout = new File(newFile);
			FileOutputStream fos = new FileOutputStream(fout);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < headerCG.size(); i++){
				sb.append(headerCG.get(i));
				if(i != headerCG.size()-1)
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
		}catch(Exception e){
			e.printStackTrace();
		}finally {
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
