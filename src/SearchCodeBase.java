import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

/**
 * 
 * @author z003p4sp
 *
 */
public class SearchCodeBase {
    /**
     * 
     * @param keys
     * @param baseDirectory
     * @return All details in array list
     */
    public static ArrayList<KeyMapEntry> getAllFiles(List<String> keys, File baseDirectory ,String fileNameCsv) {

        ArrayList<KeyMapEntry> details = new ArrayList<KeyMapEntry>();
        Collection<File> files = getAllFilesinDirectory(baseDirectory);
        List<String> foundKeys = new ArrayList<String>();
        for (File file : files) {
            try {
                // read complete file in one shot
                List<String> lines;
                // XML files are in UTF-8 format
                // Java files are in cp1252 format(latin-1)
                try {
                    lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
                } catch (MalformedInputException e) {
                    lines = Files.readAllLines(file.toPath(), StandardCharsets.ISO_8859_1);
                }
                // loop over all keys
                for (String key : keys) {
                    String startLine = null;
                    String lineNumber = null;
                    int startIndex = 0;
                    // loop over lines read at one shot above
                    for (String line : lines) {
                        // increment the line index
                        startIndex++;
                        // check if the line has the present key
                        if (line.contains(key)) {
                            startLine = line ;
                            lineNumber = String.valueOf(startIndex);
                            // skip the files that end with keep (repeat files not required)
                            if (!file.getName().endsWith("keep")) {
                                if (details.isEmpty()) {
                                    details.add(new KeyMapEntry(key, file.getName(), startLine.trim(),lineNumber, "No End","-","-"));
                                } else {
                                    KeyMapEntry presentEntry = details.get(details.size() - 1);
                                    if ((presentEntry.getStartLocation().contains("BEGIN " + key) && line.contains("END " + key))||(details.get(details.size() - 1).getStartLocation().contains("BEGIN] " + key) && line.contains("END] " + key))){
                                        String comment = String.valueOf(Integer.parseInt(lineNumber) - Integer.parseInt(presentEntry.getStartLineNumber())); 
                                        presentEntry.setStopLocation(startLine.trim());
                                        presentEntry.setStopLineNumber(lineNumber);
                                        presentEntry.setComment(comment);
                                    }
                                    else
                                        details.add(new KeyMapEntry(key, file.getName(), startLine.trim(),lineNumber, "No End","-","-"));
                                }
                            }
                            //save the missing keys to file
                            if(!foundKeys.contains(key)){
                                foundKeys.add(key);
                            }
                        }
                    }
                 
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        getMissingSubList(keys,foundKeys,fileNameCsv);
        return details;

    }

    /**
     * creating a files collection in baseDirectory
     * 
     * @param baseDirectory
     * @return collection of files
     */
    public static Collection<File> getAllFilesinDirectory(File baseDirectory) {
        System.out.println("loading files .....");
        Collection<File> files = FileUtils.listFiles(baseDirectory, new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);
        return files;
    }
    
    public static void getMissingSubList(List<String> keys,List<String> foundKeys,String fileNameCsv) {
       keys.removeAll(foundKeys);
       WriteToFile.writeListToTxtFile(keys, fileNameCsv+"NotFoundKeys.txt");
    }
}
