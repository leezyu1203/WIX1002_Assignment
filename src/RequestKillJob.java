import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestKillJob {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));

            String line = "";
            int numOfRequest = 0;
            while((line = reader.readLine())!= null){
                if(checkRequest(line))
                    numOfRequest++;
            }
            System.out.println("The number of requests to kill job: " + numOfRequest);
            reader.close();
        } catch (IOException e){
            System.out.println("Error");
        }
    }
    public static boolean checkRequest (String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. _slurm_rpc_kill_job: REQUEST_KILL_JOB JobId=\\d+ uid \\d+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
