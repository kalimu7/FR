package ma.yc.api.myrhapi.controller;

import ma.yc.api.myrhapi.dto.JobOfferApplicationsPageResponse;
import ma.yc.api.myrhapi.dto.JobOfferChangeVisibilityRequest;
import ma.yc.api.myrhapi.dto.JobOfferRequest;
import ma.yc.api.myrhapi.dto.JobOfferResponse;
import ma.yc.api.myrhapi.service.JobOfferService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/job_offers")
@CrossOrigin("*")
public class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

    @GetMapping
    public ResponseEntity<Page<JobOfferResponse>> getAllJobOffers(
            @RequestParam Map<String, String> queryParams
    ) {
        return ResponseEntity.ok(this.jobOfferService.getAllJobOffers(queryParams));
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobOfferResponse> getJobOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @GetMapping("company/{id}")
    public ResponseEntity<Page<JobOfferResponse>> getJobOffersByCompanyId(
            @PathVariable Long id,
            @RequestParam Map<String, String> queryParams
    ) {
        return ResponseEntity.ok(jobOfferService.getJobOffersByCompanyId(id, queryParams));
    }


    @GetMapping("company/{id}/applications/{jobOfferId}")
    public ResponseEntity<List<JobOfferApplicationsPageResponse>> getJobOfferApplicationByJobOfferIdAndCompanyId(
            @PathVariable String id,
            @PathVariable String jobOfferId,
            @RequestParam Map<String, String> queryParams
    ) {
        return ResponseEntity.ok(
                jobOfferService.getJobOfferApplicationByJobOfferIdAndCompanyId(id, jobOfferId, queryParams)
        );
    }






    @PostMapping("/change_visibility")
    public ResponseEntity<JobOfferResponse> changeJobOfferVisibility(
            @RequestBody JobOfferChangeVisibilityRequest jobOfferChangeVisibilityRequest
    ) {
        return ResponseEntity.ok(jobOfferService.changeJobOfferVisibility(
                jobOfferChangeVisibilityRequest.getJobOfferId(),
                jobOfferChangeVisibilityRequest.isVisibility()
        ));
    }

    @PostMapping
    public ResponseEntity<JobOfferResponse> addNewOffer(@RequestBody JobOfferRequest jobOfferRequest) {
        return ResponseEntity.ok(jobOfferService.addNewJobOffer(jobOfferRequest));
    }
}
