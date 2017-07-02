import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class KeyMapper {

    public static void main(String[] args) {
        Collection<File> files = SearchCodeBase.getAllFilesinDirectory(new File("E:\\keys\\csvKeys\\New folder"));
        for(File file : files){
            String[] parts = file.getName().split(Pattern.quote("."));
            System.out.println(parts[0]);
            runMain(parts[0]);
        }
        
    }

    public static void runMain(String fileNameCsv){

        ReadCsv readfile = new ReadCsv();
        ArrayList<String> listOfHeadsWithoutBody = new ArrayList<String>();
        List<String> allKeys = new ArrayList<String>();
        ArrayList<KeyMapEntry> fullDetails =new ArrayList<KeyMapEntry>();
/*        Collection<File> files = SearchCodeBase.getAllFilesinDirectory(new File("E:\\keys\\csvKeys"));
        for(File file : files){
            allKeys.addAll(readfile.readFile(file)); 
        }*/
        allKeys = readfile.readFile(new File("E:\\keys\\csvKeys\\New folder\\"+fileNameCsv+".csv"));
        fullDetails = SearchCodeBase.getAllFiles(allKeys, new File(
                "\\path\\to\\home\\dir\\of\\project"),fileNameCsv); 
        for (KeyMapEntry entry : fullDetails) {
            // System.out.println(entry.getKey()+","+entry.getFileName()+","+entry.getStartLocation()+","+entry.getStopLocation());
            if (entry.getStopLocation().equalsIgnoreCase("No End")) {
                // If the start location is in top comment area
                if (entry.getStartLocation().matches("^[A-z].*")) {
                    ArrayList<String> listKeys = new ArrayList<String>();
                    for (KeyMapEntry tempKey : fullDetails) {
                        if (tempKey.getFileName().equals(entry.getFileName())) {
                            if (!listKeys.contains(tempKey.getKey())) {
                                listKeys.add(tempKey.getKey());
                            }
                        }
                    }
                    if (!listKeys.contains(entry.getKey())) {
                        listOfHeadsWithoutBody.add(entry.getFileName());
                        entry.setComment("key not found please check");
                    }
                }
                if (entry.getStartLocation().contains("@requirement")) {
                    entry.setComment("requirement below - check in file");
                } else if (entry.getStartLocation().contains("@hazard")) {
                    entry.setComment("hazard below - check in file");
                }

            }
        }
        WriteToFile.writeKeyMapToFile(fullDetails, fileNameCsv+".csv");
        if (!listOfHeadsWithoutBody.isEmpty())
            WriteToFile.writeListToTxtFile(listOfHeadsWithoutBody, fileNameCsv+"ListOfHeadsWithoutBody.txt");
    
    }
}
