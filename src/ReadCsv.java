import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * reading the csv file that contains the keys
 * @author z003p4sp
 *
 */
public class ReadCsv {
    private static final String COMMA_DELIMITER = ",";
    
    public ArrayList<String> readFile(File file) {
        BufferedReader br = null;
        ArrayList<String> listOfKeys = new ArrayList<String>(); 
        String line = "";
        
        try {
            br = new BufferedReader(new FileReader(file));
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null) 
            {
                String[] KeyDetails = line.split(COMMA_DELIMITER); 
                if(KeyDetails.length>0){
                    listOfKeys.add(KeyDetails[10]);
                }
                
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listOfKeys;
    }

}
