package Assignmet;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Partition {
    public static void main(String[] args) {
        String logFile = "C:\\Users\\ADMIN\\Downloads\\extracted_log";
        Map<String, Integer> jobPartitions = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                // Regular expression to match job partitions
                Pattern partitionPattern = Pattern.compile("^\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\] sched: Allocate JobId=\\d+ NodeList=[\\w\\[\\]\\d-]+ #CPUs=\\d+ Partition=(.+)");
                Matcher partitionMatcher = partitionPattern.matcher(line);

                if (partitionMatcher.find()) {
                    String partition = partitionMatcher.group(1);
                    jobPartitions.put(partition, jobPartitions.getOrDefault(partition, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> partitionsList = Arrays.asList("gpu-v100s","cpu-opteron","cpu-epyc","gpu-k10","gpu-k40c","gpu-titan");
        System.out.printf("%-20s %-10s\n", "Partition", "Number of Jobs");
        jobPartitions.entrySet().stream()
                .filter(e -> partitionsList.contains(e.getKey()))
                .forEach(e -> System.out.printf("%-20s %-10d\n",e.getKey(), e.getValue()));


    }
    }
