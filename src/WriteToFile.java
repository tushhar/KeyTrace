import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {

    // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void writeListToTxtFile(List<String> keys, String fileName) {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            for (String str : keys) {
                writer.write(str);
                writer.write(NEW_LINE_SEPARATOR);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeKeyMapToFile(ArrayList<KeyMapEntry> keyDetails, String fileName) {
        // CSV file header
        final String FILE_HEADER = "key,FileName,startLine,Start Line Number,endLine, End Line Number,Comment";
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new student object list to the CSV file
            for (KeyMapEntry key : keyDetails) {
                fileWriter.append(key.getKey());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getFileName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getStartLocation());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getStartLineNumber());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getStopLocation());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getStopLineNumber());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(key.getComment());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }

}
