package teampcc.backendservice.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import teampcc.backendservice.entity.PremUser;

@Repository
@Transactional

public interface PremUserJPARepository extends JpaRepository<PremUser,Long>{

	Optional<List<PremUser>> findUserById(Long Id);
	
}