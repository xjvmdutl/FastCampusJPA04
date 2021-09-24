package com.fastcampus.jpa.FastCampusJPA04.service;

import com.fastcampus.jpa.FastCampusJPA04.domain.User;
import com.fastcampus.jpa.FastCampusJPA04.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");

        entityManager.persist(user);
        //entityManager.detach(user);
        user.setName("newUserAfterPersist");

        entityManager.merge(user);
        //entityManager.flush();//flush로 반영 하고 clear를 해야 반영이 된다.
        //entityManager.clear();


        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

        //user1.setName("marrrrrrtin");
        //entityManager.merge(user1);

    }

}
