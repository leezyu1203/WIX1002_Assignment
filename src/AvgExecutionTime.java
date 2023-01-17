import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.time.Duration;


public class AvgExecutionTime {
    public static void main(String[] args) {
        ArrayList<String> line = new ArrayList<String>();
        try{
            BufferedReader fileInput = new BufferedReader(new FileReader("C:\\Users\\huier\\OneDrive\\Desktop\\Java Project\\Assignment\\resources\\extracted_log"));

            String sentence;
            while((sentence = fileInput.readLine())!=null){
              line.add(sentence);
            }
                fileInput.close();      
        }catch(IOException e){
                System.out.println("Error reading from file");
        }

        int job=0;
        Instant startTime;
        Instant endTime;
        double totalTime=0;

        for(String i : line){
            if(i.contains("_job_complete:") && i.contains("done")){
                String[] data = i.split(" ");
                String id = data[2];
                String time = data[0].substring(1,23)+'Z';    
                endTime = Instant.parse(time);
                for (String j : line){
                    if(j.contains(id) && j.contains("Allocate") ){
                        String[] data1 = j.split(" ");
                        time = data1[0].substring(1,23)+'Z';                       
                        startTime = Instant.parse(time);    
                        Duration duration = Duration.between(startTime,endTime);
                        totalTime += duration.toMinutes();
                        job++;
                        break;
                    }
                }
            }
        }    
        double average = totalTime/(double)job;
        System.out.printf("Average execution time: %.2f minutes\n", average);             
    }       
}
