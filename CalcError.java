import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CalcError {

    public static void main(String[] args) {
            ArrayList<String> line = new ArrayList<String>();
        try{
            BufferedReader fileInput = new BufferedReader(new FileReader("C:\\Users\\huier\\OneDrive\\Desktop\\Java Project\\Assignment\\resources\\extracted_log.txt"));

            String sentence;
            while((sentence = fileInput.readLine())!=null){
              line.add(sentence);
            }
                fileInput.close();      
            }catch(IOException e){
                System.out.println("Error reading from file");
            }

            int count1=0;
            int count2=0;

            for(String i : line){
                if(i.contains("error:")){
                    count1++;
                }    
                if(i.contains("error: This association")){
                    count2++;
                }  
            }

            System.out.println("Job Error:" + count2);
            System.out.println("Other Error:" + (count1 - count2)); 
            System.out.println("Total Error:" + count1);        
    }       
}
