import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JobError {
    public static void main(String[] args) {

        //Read the line and put in ArrayList (line)
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
       
        //get the username from the line "error: This association"
        //put them into ArrayList (userlist)
        ArrayList<String> userlist = new ArrayList<String>();
        for(String i : line){
            if(i.contains("error: This association")){
                String[] data = i.split(" ");
                String i_username = data[5];
                String[] username = i_username.split("'");
                String f_username = username[1];
                userlist.add(f_username);
            }
        }  
        System.out.println(userlist);
        System.out.println(userlist.size());

        //count the occurence of each elements (username) in the ArrayList (userlist)
        //countMap holds the count details of each element
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        for (int j = 0; j < userlist.size(); j++)
        {
            String key = userlist.get(j);
            if (countMap.containsKey(key)){
                int count = countMap.get(key);
                count++;
                countMap.put(key, count);
            } else {
                countMap.put(key, 1);
            }
        }
        
        //Printing the Element and its occurrence in the array
        for(Entry<String,Integer> val : countMap.entrySet())
        {
            System.out.println(val.getKey() + " occurs " + val.getValue() + " time(s)");
        }
        
    }    
}
