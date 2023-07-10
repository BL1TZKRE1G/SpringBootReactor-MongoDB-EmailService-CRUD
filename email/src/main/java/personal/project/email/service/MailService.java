package personal.project.email.service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import personal.project.email.model.ApprovalRequest;
import personal.project.email.model.InviteRequest;

@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${fromEmail}") // Value fetched from the "application.properties"
    private String fromEmail;

    public void sendInvitationMail(InviteRequest inviteRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", inviteRequest.getFullName());
        context.setVariables(properties);

        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setSubject("INVITE");

        String htmlContent = templateEngine.process("invite-mail.html", context);
        mimeMessageHelper.setText(htmlContent, true);

        log.info("Sending Invitation mail to: {}", inviteRequest.getMailId());
        mailSender.send(mimeMessage);
    }

    public void sendApprovalMail(ApprovalRequest approvalRequest) throws MessagingException {
        // Created MimeMessage
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // MimeMessageHelper Object
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        // Set from, Subject and To
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setSubject("APPROVAL MAIL");
        mimeMessageHelper.setTo(approvalRequest.getMailId());

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", approvalRequest.getFullName());
        properties.put("firstName", approvalRequest.getFirstName());

        Context context = new Context();
        context.setVariables(properties);

        String htmlContent = templateEngine.process("approval-mail.html", context);
        mimeMessageHelper.setText(htmlContent, true);
        // 👆Added the HTML content to will automatically change with data

        log.info("Sending Approval Mail to: [{}]", approvalRequest.getMailId());
        mailSender.send(mimeMessage); // 📧Mail Sent!!!
    }
}
