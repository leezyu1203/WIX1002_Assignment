import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeList {
    public static void main(String[] args) {
        String[] cpu = {"01","03","04","05","07","08","09","10","11","12","13","14","15"};
        int[] cpuNum = new int[cpu.length];
        String[] gpu = {"01","02","03","04","05"};
        int[] gpuNum = new int[gpu.length];

        try{
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));

            String line = "";
            String node = "";
            while((line = reader.readLine()) != null){
                if(checkCpu(line)){
                    node = getNode(line);
                    categorise(node,cpu,cpuNum);
                } if(checkGpu(line)){
                    node = getNode(line);
                    for(int i = 0; i < gpu.length; i++) {
                        if (node.equals(gpu[i]))
                            gpuNum[i]++;
                    }
                }
            }
            for(int i = 0; i < cpu.length; i++){
                System.out.println("cpu" + cpu[i] + " = " + cpuNum[i]);
            } for(int i = 0; i < gpu.length; i++){
                System.out.println("gpu" + gpu[i] + " = " + gpuNum[i]);
            }
            reader.close();
        } catch(IOException e){
            System.out.println("Error");
        }
    }
    public static boolean checkCpu(String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=cpu.+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static boolean checkGpu (String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=gpu.+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static String getNode(String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=\\w\\w\\w(.+) #.+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher.group(1);
    }
    public static void categorise(String node, String[] cpu, int[] num){
        if(node.length()!=2){
            String multiNode = node.substring(1,(node.length()-1));
            String[] separate = multiNode.split(",");
            for(int i = 0; i < separate.length; i++){
                if(separate[i].contains("-")){
                    String[] range = separate[i].split("-");
                    int low = 0;
                    int high = 0;
                    for(int j = 0; j < cpu.length; j++){
                        if(range[0].equals(cpu[j]))
                            low = j;
                        if(range[1].equals(cpu[j]))
                            high = j;
                    } for(int j = low; j <= high; j++)
                        num[j]++;
                }else if(separate[i].length() == 2){
                    for(int j = 0; j < cpu.length; j++){
                        if(separate[i].equals(cpu[j]))
                            num[j]++;
                    }
                }
            }
        } else{
            for(int i = 0; i < cpu.length; i++) {
                if (node.equals(cpu[i]))
                    num[i]++;
            }
        }
    }
}