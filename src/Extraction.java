public class Extraction {
    private int jobCreated;   //Allocate
    private int jobError;       //error:
    private int jobDone;        //_job_complete: JobId=XXXXX done
    private int jobKill;        //_slurm_rpc_kill_job:

    Partitions p = new Partitions();

    //constructor
    public Extraction(){}
    public void extract(String[] data){
        if(data[2].equals("Allocate")) {
            jobCreated++;
            p.setPartition(data[6]);
        }
        else if (data[1].equals("error:"))
            jobError++;
        else if(data[3].equals("done"))
            jobDone++;
        else if(data[1].equals("_slurm_rpc_kill_job:"))
            jobKill++;
    }

    public void displayResult(){
        System.out.println("Number of job scheduled: " + jobCreated + "\n" +
                "Number of job error: " + jobError + "\n" +
                "Number of job done: " + jobDone + "\n" +
                "Number of kob killed: " + jobKill + "\n");
        p.displayPartition();
    }
}
