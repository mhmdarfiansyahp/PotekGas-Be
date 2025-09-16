package com.potekgas.dao.impl;

import com.potekgas.dao.UserDao;
import com.potekgas.model.User;
import com.potekgas.repository.UserRepository;
import com.potekgas.vo.UserVo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Override
    public List<UserVo> getAllUser() {
        Iterable<User> users = userRepository.findAll();
        List<UserVo> userVos = new ArrayList<>();
        for (User item : users) {
            UserVo userVo = new UserVo(item);
            if("1".equals(userVo.getRole())) {
                userVo.setRole("Admin");
            } else if("2".equals(userVo.getRole())) {
                userVo.setRole("Kasir");
            }

            if("0".equals(userVo.getStatus())) {
                userVo.setStatus("Tidak Aktif");
            } else if("1".equals(userVo.getStatus())) {
                userVo.setStatus("Aktif");
            }

            if (item.getId_user() != null) {
                String foto = "http://localhost:8083/users/foto/" + item.getId_user();
                userVo.setFoto(foto);
            }

            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public List<UserVo> getUserActive() {
        Iterable<User> users = userRepository.getUserActive();
        List<UserVo> userVos = new ArrayList<>();
        for (User item : users) {
            UserVo userVo = new UserVo(item);
            if("1".equals(userVo.getRole())) {
                userVo.setRole("Admin");
            } else if("2".equals(userVo.getRole())) {
                userVo.setRole("Kasir");
            }

            if("0".equals(userVo.getStatus())) {
                userVo.setStatus("Tidak Aktif");
            } else if("1".equals(userVo.getStatus())) {
                userVo.setStatus("Aktif");
            }if (item.getId_user() != null) {
                String foto = "http://localhost:8083/users/foto/" + item.getId_user();
                userVo.setFoto(foto);
            }

            if (item.getId_user() != null) {
                String foto = "http://localhost:8083/users/foto/" + item.getId_user();
                userVo.setFoto(foto);
            }

            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public List<UserVo> getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        List<UserVo> userVos = new ArrayList<>();
        userOptional.ifPresent(user -> {
            UserVo userVo = new UserVo(user);

            if("1".equals(userVo.getRole())) {
                userVo.setRole("Admin");
            } else if("2".equals(userVo.getRole())) {
                userVo.setRole("Kasir");
            }

            if("0".equals(userVo.getStatus())) {
                userVo.setStatus("Tidak Aktif");
            } else if("1".equals(userVo.getStatus())) {
                userVo.setStatus("Aktif");
            }
            userVos.add(userVo);
        });
        return userVos;
    }

    @Override
    public ArrayList countAdmin() {
        return userRepository.countAdmin();
    }

    @Override
    public ArrayList countKasir() {
        return userRepository.countKasir();
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }
}
