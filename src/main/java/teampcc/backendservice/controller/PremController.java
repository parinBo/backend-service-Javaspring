package teampcc.backendservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import teampcc.backendservice.service.PremService;

@RestController
@RequestMapping("/prem")
public class PremController {
	
	@Autowired
	PremService premService;
		
	@GetMapping(value = "/getAllUser", headers = { "Authorization" })
	public Object getAllUser() {
		Object listUser = premService.findAllUser();
		
		return listUser;
	}
	

}
