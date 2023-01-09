import java.io.*;
public class CalJuneJob {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("june_log"));
            String line = "";
            String[] data = new String[5];
            Extraction june = new Extraction();
            while ((line = reader.readLine()) != null) {
                try {
                    data = line.split(" ");
                    june.extract(data);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            june.displayResult();
            reader.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
