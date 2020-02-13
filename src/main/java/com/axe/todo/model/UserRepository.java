package com.axe.todo.model;

import com.axe.todo.entity.Todo;
import com.axe.todo.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

//    @Override
//    User select(User user);

    @Override
    @SuppressWarnings("unchecked")
    User save(User user);

//    @Modifying
//    @Transactional(rollbackOn = Exception.class)
//    @Query("update Todo as t set t.status = ?2 where t.id=?1")
//    void updateById(Long id, int status);

    @Query("select id from User as t where t.name = ?1 and t.password = ?2")
    List<Long> validUser(String name, String password);

    @Query("select id from User as t where t.name = ?1")
    List<Long> existUser(String name);
}


