package personal.project.email.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequest implements Request {
    @Id // @Id for the Database to identify this field as ID
    private String _id;
    private String firstName;
    private String lastName;
    private String mailId;
    private String secondaryEmail;

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
