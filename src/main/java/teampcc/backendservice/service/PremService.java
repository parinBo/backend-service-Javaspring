package teampcc.backendservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import teampcc.backendservice.entity.PremUser;

import teampcc.backendservice.repository.PremUserJPARepository;

@Service
@Transactional
public class PremService {
	private static final Logger logger = LoggerFactory.getLogger(PremService.class);
	
	@Autowired
	private PremUserJPARepository premUserJPARepository;
	
	public void insertUser(PremUser premUser) {
			premUserJPARepository.save(premUser);
	}
	//Read
		public Object findAllUser() {
			return(Object)premUserJPARepository.findAll();
		}
		public Object findUserByID(Long id) {
			return premUserJPARepository.findById(id);
		}
		public void deleteUserByID(Long id) {
			premUserJPARepository.deleteById(id);
		}
		public Object getUserById(Long Id) {
			Optional<List<PremUser>> list =premUserJPARepository.findUserById(Id);
			if(list.isPresent()) {
				return list.get();
			}
			else {
				return null;
			}
		}
}
