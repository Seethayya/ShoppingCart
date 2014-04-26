package com.seethayya.shoppingcart.mail.impl;

import com.seethayya.shoppingcart.mail.MailService;
import com.seethayya.shoppingcart.mail.VelocityTemplate;
import javassist.compiler.Javac;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Anish
 * Date: 4/26/14
 * Time: 10:46 PM
 */
@Service
public class MailServiceImpl implements MailService {
    private static Logger LOGGER = Logger.getLogger(MailServiceImpl.class);

    private VelocityTemplate velocityTemplate;
    private JavaMailSender mailSender;

    public void sendMail(String toMailId, String ccMailId, String vmFileLocation, Object... data) {
        Calendar calendar = Calendar.getInstance();
        LOGGER.debug("Started :: sending mail to:"+toMailId);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            /*use the true flag to indicate you need a multipart message*/
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(toMailId);
            String subject = velocityTemplate.prepareStringFromVelocityTemplate(vmFileLocation+"Subject.vm", data);
            mimeMessageHelper.setSubject(subject);
            String text = velocityTemplate.prepareStringFromVelocityTemplate(vmFileLocation+".vm", data);
            /* Use the true flag to indicate the text included is HTML */
            mimeMessageHelper.setText(text, true);
            FileSystemResource fileSystemResource = null;
            String fileName = StringUtils.EMPTY;
            for(Object object : data) {
                if (object instanceof File) {
                    File file = (File)object;
                    fileSystemResource = new FileSystemResource(file);
                    fileName = file.getName();
                }
            }
            if(null != fileSystemResource) {
                mimeMessageHelper.addAttachment(fileName, fileSystemResource);
            }
            mailSender.send(mimeMessage);
            LOGGER.debug("End :: mail sendded successfully to:"+toMailId +": time taken ::"+(Calendar.getInstance().getTimeInMillis()-calendar.getTimeInMillis()));
        } catch (Exception exception) {
            LOGGER.error("ERROR While Sending Mail To::"+toMailId + "Error::"+exception.getMessage());
        }

    }

    @Resource
    public void setVelocityTemplate(VelocityTemplate velocityTemplate) {
        this.velocityTemplate = velocityTemplate;
    }

    @Resource
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
