/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assigment;

/**
 *
 * @author user10
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.time.Duration;


public class Assigment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            ArrayList<String> line = new ArrayList<String>();
        try{
            BufferedReader fileInput = new BufferedReader(new FileReader("C:\\Users\\user10\\Downloads\\extracted_log"));
            //DateTimeFormatter set = new DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS"); //public static final DateTimeFormatter ISO_LOCAL_DATE_TIME
           
            String sentence;
            while((sentence = fileInput.readLine())!=null){
              line.add(sentence);
                
            }
                fileInput.close();      
                          
            }catch(IOException e){
                System.out.println("Error reading from file");
            }
        
            int job =0;
            Instant startTime;
            Instant endTime;
            double totalTime=0;
            
            for(String i : line){
                if(i.contains("_job_complete:") && i.contains("done")){
                    String[] data = i.split(" ");
                    String id = data[2];
                    String time = data[0].substring(1,22)+'Z';    
                    endTime = Instant.parse(time);
                    for (String j : line){
                        if(j.contains(id) && j.contains("Allocate") ){
                            String[] data1 = j.split(" ");
                            time = data1[0].substring(1,22)+'Z';                       
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
            System.out.printf("Average execution time :%.2f\n", average);             
    }       
}


           
                

    
 
