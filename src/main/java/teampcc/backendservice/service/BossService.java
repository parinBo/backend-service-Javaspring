package teampcc.backendservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teampcc.backendservice.entity.BossEntity;
import teampcc.backendservice.repository.BossRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BossService {
    private static final Logger logger = LoggerFactory.getLogger(BossService.class);

    @Autowired
    BossRepository bossRepository;
    //create
    public void addOrUpdateUser(BossEntity user){
        bossRepository.save(user);
    }
    //read
    public Object getAllUser(){
        return (Object) bossRepository.findAll();
    }

    public Object getUserById(Long id){
        return bossRepository.findById(id);
    }

    public List<BossEntity> getUserByFname(String name){
        try{
            Optional<List<BossEntity>> data = bossRepository.findUserByFname(name);
            return (data.isPresent())?data.get():null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<BossEntity> getUserByBirthDate(String date){
        try{
            Optional<List<BossEntity>> data = bossRepository.findUserBybirthdate(date);
            return (data.isPresent())?data.get():null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BossEntity> getUserByFullname(String name,String lname){
        try{
            Optional<List<BossEntity>> data = bossRepository.findUserByFullName(name,lname);
            return (data.isPresent())?data.get():null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BossEntity> getUserByAge(Integer age){
        try{
            Optional<List<BossEntity>> data = bossRepository.findUserByAge(age);
            return (data.isPresent())?data.get():null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //delete
    public void deleteUserById(Long id){
        bossRepository.deleteById(id);
    }
}
