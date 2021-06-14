package teampcc.backendservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import teampcc.backendservice.entity.BossEntity;


public interface BossRepository extends JpaRepository<BossEntity,Long> {

}