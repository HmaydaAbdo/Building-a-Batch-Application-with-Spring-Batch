package example.billingjob.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;

public class BillingJob implements Job {

    private JobRepository jobRepository;

    public BillingJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public String getName() {
        return "BillingJob";
    }


    // creating jobInstances  by passing job params

    @Override
    public void execute(JobExecution execution) {
        JobParameters jobParameters = execution.getJobParameters();
        String inputFile = jobParameters.getString("input.file");
        System.out.println("processing billing information from file " + inputFile);
        execution.setStatus(BatchStatus.COMPLETED);
        execution.setExitStatus(ExitStatus.COMPLETED);
        this.jobRepository.update(execution);
    }

    // Exception handling

//    @Override
//    public void execute(JobExecution execution) {
//        try {
//            throw new Exception("Unable to process billing information");
//        } catch (Exception exception) {
//            execution.addFailureException(exception);
//            execution.setStatus(BatchStatus.COMPLETED);
//            execution.setExitStatus(ExitStatus.FAILED.addExitDescription(exception.getMessage()));
//        } finally {
//            this.jobRepository.update(execution);
//        }
//    }

    // status update

//    @Override
//    public void execute(JobExecution execution) {
//        System.out.println("processing billing information");
//        execution.setStatus(BatchStatus.COMPLETED);
//        execution.setExitStatus(ExitStatus.COMPLETED);
//        this.jobRepository.update(execution);
//    }


}
