package teampcc.backendservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teampcc.backendservice.entity.BossEntity;
import teampcc.backendservice.repository.BossRepository;

import java.util.List;

@Service
@Transactional
public class BossService {
    private static final Logger logger = LoggerFactory.getLogger(BossService.class);

    @Autowired
    BossRepository bossRepository;
    //create
    public void addUser(BossEntity user){
        bossRepository.save(user);
    }
    //read
    public Object getAllUser(){
        return (Object) bossRepository.findAll();
    }
    public Object getUserById(Long id){
        return bossRepository.findById(id);
    }
    //update
    public  void updateUser(BossEntity user){
        bossRepository.save(user);
    }
    //delete
    public void deleteUserById(Long id){
        bossRepository.deleteById(id);
    }





}
