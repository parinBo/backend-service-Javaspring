package teampcc.backendservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rangCtrl")
public class RangController {
    @Autowired

    @GetMapping("/test")
    public Object get(){
        return "";
    }
}
