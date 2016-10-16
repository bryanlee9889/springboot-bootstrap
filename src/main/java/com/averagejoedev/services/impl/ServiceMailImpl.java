package com.averagejoedev.services.impl;

import com.averagejoedev.models.form.FormMail;
import com.averagejoedev.services.ServiceMail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;

@Service
public class ServiceMailImpl implements ServiceMail {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceMailImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Configuration configuration;

	public void send(FormMail form, String template) {
		try {
			Template 	templateEngine 	= configuration.getTemplate(template, new Locale(form.getLocale()));
			
			String[] 			cc 		= form.getCc();
			String[] 			bcc 	= form.getBcc();
			File[] 				files 	= form.getFiles();
			
			MimeMessage 		mail 	= mailSender.createMimeMessage();
			MimeMessageHelper helper 	= new MimeMessageHelper(mail, true, "UTF-8");
			helper.setFrom(form.getFrom());
			helper.setTo(form.getTo());
			helper.setSubject(form.getSubject());
			helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(templateEngine, form.getParams()), true);
			
			if (cc != null) {
				helper.setCc(cc);
			}
			if (bcc != null) {
				helper.setBcc(bcc);
			}
			if (files != null) {
				Arrays.stream(files).forEach(f -> {
					try {
						helper.addAttachment(f.getName(), f);
					} catch (MessagingException e) {
						logger.error(e.getMessage(), e);
					}
				});
			}
						
			mailSender.send(mail);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
