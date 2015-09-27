package org.cfi.hackathon;

import java.io.IOException;

import javax.mail.MessagingException;

import org.cfi.hackathon.alert.IndiaAlert;
import org.cfi.hackathon.alert.IndiaAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {
    
	@Autowired IndiaAlertService service;
	
    @RequestMapping("/")
    public String index() throws MessagingException, IOException {
    	service.sendAmberAlert();
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    @ResponseBody
    public String broadcast(@RequestBody IndiaAlert alert)
    {
    	System.out.println(alert);
    	return "SUCCESS";
    }
}
