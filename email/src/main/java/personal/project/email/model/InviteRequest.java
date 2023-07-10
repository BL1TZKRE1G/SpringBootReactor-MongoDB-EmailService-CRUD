package personal.project.email.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteRequest implements Request {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String mailId;

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
