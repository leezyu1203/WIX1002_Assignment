import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeList {
    public static void main(String[] args) {
        int cpu01 = 0;
        int cpu03 = 0;
        int cpu04 = 0;
        int cpu05 = 0;
        int cpu07 = 0;
        int cpu08 = 0;
        int cpu09 = 0;
        int cpu10 = 0;
        int cpu11 = 0;
        int cpu12 = 0;
        int cpu13 = 0;
        int cpu14 = 0;
        int cpu15 = 0;
        int gpu01 = 0;
        int gpu02 = 0;
        int gpu03 = 0;
        int gpu04 = 0;
        int gpu05 = 0;

        try{
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));

            String line = "";

            while((line = reader.readLine()) != null){
                if(checkCpu(line)){
                    String cpu = getNode(line);
                    if(cpu.length() == 2){
                        switch (cpu){
                            case "01" -> cpu01++;
                            case "03" -> cpu03++;
                            case "04" -> cpu04++;
                            case "05" -> cpu05++;
                            case "07" -> cpu07++;
                            case "08" -> cpu08++;
                            case "09" -> cpu09++;
                            case "10" -> cpu10++;
                            case "11" -> cpu11++;
                            case "12" -> cpu12++;
                            case "13" -> cpu13++;
                            case "14" -> cpu14++;
                            case "15" -> cpu15++;
                        }
                    } else{
                        String aString = cpu.substring(1,(cpu.length()-1));
                        String[] data = aString.split("[,-]");
                        for(int i = 0; i < data.length; i++){
                            switch (data[i]){
                                case "01" -> cpu01++;
                                case "03" -> cpu03++;
                                case "04" -> cpu04++;
                                case "05" -> cpu05++;
                                case "07" -> cpu07++;
                                case "08" -> cpu08++;
                                case "09" -> cpu09++;
                                case "10" -> cpu10++;
                                case "11" -> cpu11++;
                                case "12" -> cpu12++;
                                case "13" -> cpu13++;
                                case "14" -> cpu14++;
                                case "15" -> cpu15++;
                            }
                        }
                    }
                } if(checkGpu(line)){
                    String gpu = getNode(line);
                    switch (gpu){
                        case "01" -> gpu01++;
                        case "02" -> gpu02++;
                        case "03" -> gpu03++;
                        case "04" -> gpu04++;
                        case "05" -> gpu05++;
                    }
                }
            }
            System.out.printf("%-10s%-14s","NodeList","Number of node");
            System.out.printf("\n%-10s%-5d","cpu01",cpu01);
            System.out.printf("\n%-10s%-5d","cpu03",cpu03);
            System.out.printf("\n%-10s%-5d","cpu04",cpu04);
            System.out.printf("\n%-10s%-5d","cpu05",cpu05);
            System.out.printf("\n%-10s%-5d","cpu07",cpu07);
            System.out.printf("\n%-10s%-5d","cpu08",cpu08);
            System.out.printf("\n%-10s%-5d","cpu09",cpu09);
            System.out.printf("\n%-10s%-5d","cpu10",cpu10);
            System.out.printf("\n%-10s%-5d","cpu11",cpu11);
            System.out.printf("\n%-10s%-5d","cpu12",cpu12);
            System.out.printf("\n%-10s%-5d","cpu13",cpu13);
            System.out.printf("\n%-10s%-5d","cpu14",cpu14);
            System.out.printf("\n%-10s%-5d","cpu15",cpu15);
            System.out.printf("\n%-10s%-5d","gpu01",gpu01);
            System.out.printf("\n%-10s%-5d","gpu02",gpu02);
            System.out.printf("\n%-10s%-5d","gpu03",gpu03);
            System.out.printf("\n%-10s%-5d","gpu04",gpu04);
            System.out.printf("\n%-10s%-5d","gpu05",gpu05 );
            reader.close();
        } catch (IOException e){
            System.out.println("Error");
        }
    }

    public static boolean checkCpu (String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=cpu.+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static boolean checkGpu (String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=gpu.+");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static String getNode (String line){
        Pattern pattern = Pattern.compile(".[0-9T:.-]+. sched: Allocate JobId=\\d+ NodeList=[a-z]+(.+) #.+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher.group(1);
    }
}
