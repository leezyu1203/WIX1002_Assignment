public class Partitions {
    private int cpu_epyc;
    private int cpu_opteron;
    private int gpu_k10;
    private int gpu_k40c;
    private int gpu_titan;
    private int gpu_v100s;

    public Partitions(){}

    public void setPartition(String partition){
        switch(partition){
            case "Partition=cpu-epyc" -> cpu_epyc++;
            case "Partition=cpu-opteron" -> cpu_opteron++;
            case "Partition=gpu-k10" -> gpu_k10++;
            case "Partition=gpu-k40c" -> gpu_k40c++;
            case "Partition=gpu-titan" -> gpu_titan++;
            case "Partition=gpu-v100s" -> gpu_v100s++;
        }
    }
    public void displayPartition(){
        System.out.println("Number of cpu-epyc: " + cpu_epyc + "\n" +
                "Number of cpu-opteron: " + cpu_opteron + "\n" +
                "Number of gpu-k10: " + gpu_k10 + "\n" +
                "Number of gpu-k40c: " + gpu_k40c + "\n" +
                "Number of gpu-titan: " + gpu_titan + "\n" +
                "Number of gpu-v100s: " + gpu_v100s + "\n");
    }
}
