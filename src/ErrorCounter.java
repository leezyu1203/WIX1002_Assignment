package Assignmet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorCounter {
    public static void main(String[] args) {
        String logFile = "C:\\Users\\ADMIN\\Downloads\\extracted_log";
        Map<String, Integer> errorUsers = new HashMap<>();
        int errorCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Regular expression to match error lines
                Pattern errorPattern = Pattern.compile("^\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\] error:.*user='(\\w+)'");
                Matcher errorMatcher = errorPattern.matcher(line);

                if (errorMatcher.find()) {
                    String user = errorMatcher.group(1);
                    errorUsers.put(user, errorUsers.getOrDefault(user, 0) + 1);
                    errorCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of jobs causing error: " + errorCount);
        System.out.println("Users causing error: ");
        for (String user : errorUsers.keySet()) {
            System.out.println(user + ": " + errorUsers.get(user));
        }
    }
}
