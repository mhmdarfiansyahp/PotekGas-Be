package com.potekgas.repository;

import com.potekgas.model.User;
import com.potekgas.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findOneByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    @Procedure(procedureName = "getUserActive")
    List<User> getUserActive();
    @Procedure(procedureName = "countAdmin")
    ArrayList countAdmin();
    @Procedure(procedureName = "countKasir")
    ArrayList countKasir();
    @Procedure(procedureName = "findUserById")
    User findUserById(int id);
}
