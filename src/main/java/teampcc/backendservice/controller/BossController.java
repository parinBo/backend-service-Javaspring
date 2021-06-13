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
    @GetMapping(value = "/getAllUser")
    public Object getAllUser(){
    return "whadtss";
        }

    @GetMapping(value = "/getuserById")
    public Object getuserById(){
        return "test";
    }

    @PostMapping(value = "/createuser")
    public void createUser(@RequestBody String userID) {
        try {
//            JSONObject id = new JSONObject(userID);
            System.out.println(Integer.parseInt((userID)));
//            bossService.deleteUserByID(id.getInt("userID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping(value = "/deleteUserById")
    public void deleteUserByID(@RequestBody String userID) {
        try {
//            JSONObject id = new JSONObject(userID);
            System.out.println(Integer.parseInt((userID)));
//            bossService.deleteUserByID(id.getInt("userID"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
