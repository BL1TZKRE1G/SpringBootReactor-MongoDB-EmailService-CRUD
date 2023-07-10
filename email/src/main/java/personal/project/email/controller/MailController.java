package personal.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import personal.project.email.model.ApprovalRequest;
import personal.project.email.model.InviteRequest;
import personal.project.email.service.MailService;

@Slf4j
@RestController
@RequestMapping(value = "/mail-service")
public class MailController {

    @Autowired
    MailService mailService;

    public String sendInvitation(InviteRequest inviteRequest) {
        try {
            log.info("Sending Invitation Mail!");
            mailService.sendInvitationMail(inviteRequest);
        } catch (Exception e) {
            log.info("Exception Encountered!");
            return "ABORTED!";
        }
        return "Mail Sent Successfully!\nPORT Terminated for further use!";
    }

    public String sendApprovalMail(ApprovalRequest approvalRequest) {
        try {
            log.info("Sending Approval Mail for: {}", approvalRequest.getFirstName());
            mailService.sendApprovalMail(approvalRequest);
        } catch (Exception e) {
            log.info("Exception Encountered");
            return "ABORTED";
        }
        return "Mail Sent Successfully\nPort terminated for further use";
    }
}
