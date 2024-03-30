package ma.yc.api.myrhapi;

import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.myrhapi.entity.*;
import ma.yc.api.myrhapi.enums.Contract;
import ma.yc.api.myrhapi.repository.ApplicantRepository;
import ma.yc.api.myrhapi.repository.CompanyRepository;
import ma.yc.api.myrhapi.repository.JobApplicationRepository;
import ma.yc.api.myrhapi.repository.JobOfferRepository;
import ma.yc.api.myrhapi.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import static java.lang.Thread.sleep;

@Configuration
public class ApplicationRunner implements CommandLineRunner {

    private final JobOfferRepository jobOfferRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final CompanyRepository companyRepository;
    private final ApplicantRepository applicantRepository;
    private Logger logger = LoggerFactory.getLogger("CommandRunner");

    public ApplicationRunner(
            JobOfferRepository jobOfferRepository,
            JobApplicationRepository jobApplicationRepository,
            ApplicantRepository applicantRepository,
            CompanyRepository companyRepository


    ) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.applicantRepository = applicantRepository;
        this.companyRepository = companyRepository;

    }

    @Override
    public void run(String... args) {
        System.out.println("TEST TEST");
        int level = 1;
        Company company = new Company();
        company.setName("ux4mation");
        company.setAddress("Casablanca");
        company.setPhoneNumber("0606060606");
        company.setEmail("ux4mation@ux_4mation.com");
        company.setWebsite("www.ux_4mation.com");
        company.setImagePath("https://ux4mation.com/wp-content/uploads/2020/04/ux4mation-logo.png");
        company = this.companyRepository.save(company);



        for (String job : new String[]{"job-1", "job-2", "job-3", "job-5", "job-6", "job-7", "job-8", "job-9", "job-10", "job-11", "job-12", "job-13", "job-14", "job-15", "job-16", "job-17", "job-18", "job-19", "job-20"}) {
            logger.info("SAVING NEW JOBS FROM THE RUNNER " + job);
            JobOffer jobOffer = new JobOffer();
            jobOffer.setTitle(job);
            jobOffer.setDescription("description for " + job);
            jobOffer.setContract(Contract.CDI);
            jobOffer.setEducation("BAC-" + level);
            level = level + 2;
            jobOffer.setLocation("Casablanca");
            jobOffer.setSalary(10000.0);
            jobOffer.setVisibility(true);
            jobOffer.setCompany(company);
            jobOfferRepository.save(jobOffer);
        }

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        saveFakeJobOffer();
    }


    public void saveFakeJobOffer() {

        JobOffer jobOffer = this.jobOfferRepository.findById(2L).orElseThrow(() -> new NotFoundException("Job Offer Not Found"));

        Applicant applicant = new Applicant();
        applicant.setLastName("Youssef");
        applicant.setFirstName("Cherkaoui");
        applicant.setEmail("mehdi@gmail.com");
        applicant.setAddress("Casablanca");
        applicant.setEducation("BAC+5");
        applicant.setExperience("2 years");
        applicant.setPhoneNumber("0606060606");
        applicant.setPassword("123");
        applicant = this.applicantRepository.save(applicant);
        applicant.getJobOffers().add(jobOffer);

        logger.info("APPLICANT SAVED WITH ID : " + applicant.getId());
        logger.info("JOB OFFER SAVED WITH ID : " + jobOffer.getId());

    }
}
