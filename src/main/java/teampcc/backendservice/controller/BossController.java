package teampcc.backendservice.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teampcc.backendservice.entity.BossEntity;
import teampcc.backendservice.service.BossService;
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/bossCtrl")
public class BossController {
    @Autowired
    BossService bossService;
    //create
    @PostMapping(value = "/addOrUpdateUser")
    public void addOrUpdateUser(@RequestBody BossEntity user){
        bossService.addOrUpdateUser(user);
    }
    //read
    @GetMapping(value = "/getAllUser")
    public Object getAllUser(){
        Object userList = bossService.getAllUser();
        return userList;
    }

    @GetMapping(value = "/getuserById")
    public Object getuserById(@RequestParam("id") Long id){
        Object user = bossService.getUserById(id);
        return user;
    }


    //delete
    @PostMapping(value = "/deleteUserById")
    public void deleteUserByID(@RequestBody Long id) {
        try {
            bossService.deleteUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
