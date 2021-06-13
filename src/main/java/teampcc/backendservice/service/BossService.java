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




}
