package teampcc.backendservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teampcc.backendservice.entity.BossEntity;

import java.util.List;
import java.util.Optional;


public interface BossRepository extends JpaRepository<BossEntity,Long> {

    Optional<List<BossEntity>> findUserByAge(Integer age);
    Optional<List<BossEntity>> findUserBySex(String sex);

    Optional<List<BossEntity>> findUserById(Long id);
    Optional<List<BossEntity>> findUserByFname(String firstname);
    @Query(value = "SELECT * FROM bossuser WHERE birth = :birth ", nativeQuery = true)
    Optional<List<BossEntity>> findUserBybirthdate(String birth);
    @Query(value = "SELECT * FROM bossuser WHERE fname = :firstname and lname = :lastname ", nativeQuery = true)
    Optional<List<BossEntity>> findUserByFullName(String firstname,String lastname);
}