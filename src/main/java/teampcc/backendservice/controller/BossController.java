package teampcc.backendservice.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teampcc.backendservice.entity.BossEntity;
import teampcc.backendservice.service.BossService;

@RestController
@RequestMapping("/bossCtrl")
public class BossController {
    @Autowired
    BossService bossService;
    //create
    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody BossEntity user){
        bossService.addUser(user);
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

    //update
    @PostMapping(value = "/updateUser")
    public void updateUser(@RequestBody BossEntity user) {
        try {
            bossService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
