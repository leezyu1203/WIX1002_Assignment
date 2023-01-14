package Brainstorming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartitionTester {
    public static void main(String[] args) {
        int cpu_epyc = 0;
        int cpu_opteron = 0;
        int gpu_k10 = 0;
        int gpu_k40c = 0;
        int gpu_titan = 0;
        int gpu_v100s = 0;

        try{
            BufferedReader reader = new BufferedReader(new FileReader("extracted_log"));

            String line = "";

            while ((line = reader.readLine()) != null) {
                if(checkJobCreated(line)) {
                    if (checkCpu_epyc(line)) cpu_epyc++;
                    else if (checkCpu_opteron(line)) cpu_opteron++;
                    else if (checkGpu_k10(line)) gpu_k10++;
                    else if (checkGpu_k40c(line)) gpu_k40c++;
                    else if (checkGpu_titan(line)) gpu_titan++;
                    else if (checkGpu_v100s(line)) gpu_v100s++;
                }
            }
            System.out.println("cpu_epyc=" + cpu_epyc +
                    "\ncpu_opteron=" + cpu_opteron +
                    "\ngpu_k10=" + gpu_k10 +
                    "\ngpu_k40c=" + gpu_k40c +
                    "\ngpu_titan=" + gpu_titan +
                    "\ngpu_v100s=" + gpu_v100s);
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
    protected static String getPartition(String line){
        Pattern pattern = Pattern.compile(".+ sched: Allocate .+ Partition=(.+)");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher.group(1);
    }
    protected static boolean checkCpu_epyc(String line){
        return getPartition(line).equals("cpu-epyc");
    }
    protected static boolean checkCpu_opteron(String line){
        return getPartition(line).equals("cpu-opteron");
    }
    protected static boolean checkGpu_k10(String line){
        return getPartition(line).equals("gpu-k10");
    }
    protected static boolean checkGpu_k40c(String line){
        return getPartition(line).equals("gpu-k40c");
    }
    protected static boolean checkGpu_titan(String line){
        return getPartition(line).equals("gpu-titan");
    }
    protected static boolean checkGpu_v100s(String line){
        return getPartition(line).equals("gpu-v100s");
    }
}
