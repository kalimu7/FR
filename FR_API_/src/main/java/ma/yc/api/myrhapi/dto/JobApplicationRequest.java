package ma.yc.api.myrhapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.yc.api.myrhapi.enums.EducationLevel;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplicationRequest {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "last name is required")
    private String lastName;
    @NotNull(message = "phone is required")
    @Pattern(regexp = "(^$|[0-9]{10})" , message = "phone number must be 10 digits")
    private String phone;
    @NotNull(message = "email is required")
    @Email
    private String email;
    @NotNull(message = "address is required")
    private String address;
    @NotNull(message = "education is required")
    private String education;
    @NotNull(message = "experience is required")
    private String experience;
    @NotNull(message = "education level is required")
    private EducationLevel educationLevel;
    @NotNull(message = "resume is required")
    //TODO: CHECK IF THE FILE IS NOT EMPTY
    private MultipartFile resume;
    @NotNull(message = "cover letter  is required")
    private String coverLetter;
    @NotNull(message = "job offer id is required")
    private Long jobOfferId;
}
