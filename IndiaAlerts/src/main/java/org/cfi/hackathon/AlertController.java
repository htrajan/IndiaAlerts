package org.cfi.hackathon;

import java.io.IOException;

import javax.mail.MessagingException;

import org.cfi.hackathon.alert.IndiaAlert;
import org.cfi.hackathon.alert.IndiaAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {
    
	@Autowired IndiaAlertService service;
    
    @RequestMapping(value = "/broadcastAlert", method = RequestMethod.POST)
    public void broadcast(@RequestBody IndiaAlert alert) throws MessagingException, IOException
    {
    	System.out.println(alert);
    	service.sendIndiaAlert(alert);
    }
}
