package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.demo.services.EmailSender;


@Controller
@RequestMapping("/email")
@CrossOrigin(origins="http://localhost:3000")
public class EmailSendController {
	
	@Autowired
	EmailSender sender;
	
	@PostMapping("/send")
	public ResponseEntity<String>sendEmail(@RequestParam("to")String to) {
		
		try {
		String [] toEmails=to.split(",");
		String subject="regarding job application";
		String text="Dear Madam,\r\n"
				+ "I am writing to express my strong interest in the Trainee Software engineer position at Cross Country Infotech Pvt Ltd as advertised on LinkedIn career portal job listing. I am recent graduate of CDAC, in my academics and project experience at CDAC, I have gained valuable knowledge and skills in java, spring framework, spring boot, MySQL, NoSQL, C#, ASP.NET Core, React.js, Node.js, JavaScript, HTML, CSS. Apart from these Docker, Kubernetes, AWS & Git. These skills have equipped me to excel in a fast-paced and dynamic environment, making me a perfect fit for the Trainee Software engineer position at Cross Country Infotech Pvt Ltd.\r\n"
				+ "I recently completed a challenging project during my tenure at CDAC, where I actively contributed to the development of application related to trekking event named ‘TrekHub’. This experience not only honed my technical skills in Java, Spring Boot, MySQL, ASP.NET core and React.js but also enhanced my ability to collaborate effectively within a team.  \r\n"
				+ "I believed that my technical skills and eagerness to learn align with the company values. I am excited about the prospect of contributing to the growth of Cross Country Infotech while further developing my own skills and knowledge.\r\n"
				+ "Enclosed is my resume, which provides additional details about my educational background and work experiences. I would welcome the opportunity to discuss how my skills and background align with Cross Country Infotech needs in more detail.\r\n"
				+ "\r\n"
				+ "Sincerely,\r\n"
				+ "Kedar Chandrakant Jagtap\r\n"
				+ " \r\n"
				+ " \r\n"
				+ "";
		String filename="Kedar_Jagtap_Resume.pdf";
		 
		
		sender.sendEmail(toEmails, subject, text,filename);
		return ResponseEntity.status(HttpStatus.OK).body("Email Sent Successfully");
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email.please try again !");
		}
	}

}
