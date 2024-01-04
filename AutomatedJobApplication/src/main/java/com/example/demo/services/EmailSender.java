package com.example.demo.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${resume.file.directory}")
	private String resumeFileDirectory;
	
	public void sendEmail(String to, String subject, String text, String filename) {
		
		//SimpleMailMessage mailMsg=new SimpleMailMessage();
		try {
		MimeMessage msg=javaMailSender.createMimeMessage();
		MimeMessageHelper mailMsg=new MimeMessageHelper(msg,true);
		mailMsg.setTo(to);
		mailMsg.setSubject(subject);
		mailMsg.setText(text);
		
		String filePath=Paths.get(resumeFileDirectory,filename).toString();
		byte [] fileBytes=Files.readAllBytes(Paths.get(filePath));
		
		InputStreamSource filesource=new ByteArrayResource(fileBytes);
		
		mailMsg.addAttachment(filename,filesource);
		javaMailSender.send(msg);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
