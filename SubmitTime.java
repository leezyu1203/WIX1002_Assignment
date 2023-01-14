package Brainstorming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubmitTime {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));
            String line = "";
            double totalUsec = 0;
            int jobsSubmitted = 0;

            while ((line = reader.readLine()) != null) {
                if (checkSubmitLine(line)) {
                    jobsSubmitted++;
                    totalUsec += getUsec(line);
                }
            }
            reader.close();
            System.out.printf("Average run time submitted: %.2f microseconds", (totalUsec/jobsSubmitted));
        } catch (IOException e) {
            System.out.println("error");
        }

    }

    public static boolean checkSubmitLine(String line) {
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. _slurm_rpc_submit_batch_job: JobId=\\d+ InitPrio=\\d+ usec=\\d+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

    public static int getUsec(String line) {
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. _slurm_rpc_submit_batch_job: JobId=\\d+ InitPrio=\\d+ usec=(\\d+)");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }
}
