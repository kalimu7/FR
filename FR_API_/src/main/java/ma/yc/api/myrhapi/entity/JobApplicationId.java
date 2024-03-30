package ma.yc.api.myrhapi.entity;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobApplicationId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "applicant_id")
    private Long applicantId;
    @Column(name = "job_offer_id")
    private Long  jobOfferId;


}
