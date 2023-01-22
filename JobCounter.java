package Assignmet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JobCounter {
    public static void main(String[] args) {
        String logFile = "C:\\Users\\ADMIN\\Downloads\\extracted_log";
        Map<String, Integer> startJobs = new HashMap<>();
        Map<String, Integer> endJobs = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
               // Regular expression to match job start
               Pattern startPattern = Pattern.compile("^\\[(\\d{4}-\\d{2})-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\] sched: Allocate JobId=\\d+ NodeList=[\\w\\[\\]\\d-]+ #CPUs=\\d+ Partition=[-\\w+]");
               Matcher startMatcher = startPattern.matcher(line);

               // Regular expression to match job end
               Pattern endPattern = Pattern.compile("^\\[(\\d{4}-\\d{2})-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\] _job_complete: JobId=\\d+ done");
               Matcher endMatcher = endPattern.matcher(line);

            if (startMatcher.find()) {
                String month = startMatcher.group(1);
                startJobs.put(month, startJobs.getOrDefault(month, 0) + 1);
            }
            if (endMatcher.find()) {
                String month = endMatcher.group(1);
                endJobs.put(month, endJobs.getOrDefault(month, 0) + 1);
            }
            
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<String> months = new ArrayList<>(startJobs.keySet());
        Collections.sort(months);
        
        System.out.printf("%-10s %-25s %-25s \n", "Month", "Number of jobs created", "Number of jobs ended");
        for (String month : months) {
            System.out.printf("%-10s %-25d %-25d \n", month, startJobs.get(month), 
            endJobs.getOrDefault(month, 0));
        }
        
    }
}
