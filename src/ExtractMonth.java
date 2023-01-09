import java.io.*;
public class ExtractMonth {
    public static void main(String[] args) {
        try{
            PrintWriter outputJune = new PrintWriter(new FileOutputStream("june_log"));
            PrintWriter outputJuly = new PrintWriter(new FileOutputStream("july_log"));
            PrintWriter outputAug = new PrintWriter(new FileOutputStream("august_log"));
            PrintWriter outputSep = new PrintWriter(new FileOutputStream("september_log"));
            PrintWriter outputOct = new PrintWriter(new FileOutputStream("october_log"));
            PrintWriter outputNov = new PrintWriter(new FileOutputStream("november_log"));
            PrintWriter outputDec = new PrintWriter(new FileOutputStream("december_log"));
            BufferedReader fileInput = new BufferedReader(new FileReader("C:/Users/leezh/Downloads/extracted_log"));
            String line = "";
            while((line = fileInput.readLine()) != null){
                String[] data = line.split(" ");
                String[] time = data[0].split("-");
                switch(time[1]){
                    case "06" -> outputJune.println(line);
                    case "07" -> outputJuly.println(line);
                    case "08" -> outputAug.println(line);
                    case "09" -> outputSep.println(line);
                    case "10" -> outputOct.println(line);
                    case "11" -> outputNov.println(line);
                    case "12" -> outputDec.println(line);
                }
            }
            fileInput.close();
            outputJune.close();
            outputJuly.close();
            outputAug.close();
            outputSep.close();
            outputOct.close();
            outputNov.close();
            outputDec.close();
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        } catch (IOException e){
            System.out.println("Error");
        }

    }
}
