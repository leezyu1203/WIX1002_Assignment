package Brainstorming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeTester {
    public static void main(String[] args) {
        ArrayList<String> dateCreated = new ArrayList<>();
        ArrayList<String> dateEnded = new ArrayList<>();
        ArrayList<String> reference1 = new ArrayList<>();
        ArrayList<String> reference2 = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));

            String line = "";

            while ((line = reader.readLine()) != null) {
                if(checkJobCreated(line)){
                    Pattern pattern = Pattern.compile(".(.+). sched: Allocate JobId=(\\d+) .+");
                    Matcher matcher = pattern.matcher(line);
                    matcher.find();
                    dateCreated.add(matcher.group(1));
                    reference1.add(matcher.group(2));
                } if(checkJobEnded(line)){
                    Pattern pattern = Pattern.compile(".(.+). _job_complete: JobId=(\\d+) done");
                    Matcher matcher = pattern.matcher(line);
                    matcher.find();
                    dateEnded.add(matcher.group(1));
                    reference2.add(matcher.group(2));
                }
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            double totalTime = 0;
            int numOfJob = 0;
            for(int i = 0; i < dateCreated.size(); i++){
                for(int j = 0; j < dateEnded.size(); j++){
                    if(reference1.get(i).equals(reference2.get(j))){
                        LocalDateTime d1 = LocalDateTime.parse(dateCreated.get(i), formatter);
                        LocalDateTime d2 = LocalDateTime.parse(dateEnded.get(j), formatter);
                        totalTime += java.time.Duration.between(d1,d2).toMinutes();
                        numOfJob++;
                        break;
                    }
                }
            }
            System.out.printf("Average execution time: %.2f", (totalTime/numOfJob));
            reader.close();

        } catch(IOException e){
            System.out.println("error");
        }
    }

    protected static boolean checkJobCreated(String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate .+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    protected static boolean checkJobEnded(String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. _job_complete: JobId=[0-9]+ done");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
