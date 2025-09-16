package com.potekgas.service;

import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    DtoResponse getAllUsers();
    DtoResponse getUserActive();
    DtoResponse getUserById(int id);
    DtoResponse findUserById(int id);
    DtoResponse countAdmin();
    DtoResponse countKasir();
    DtoResponse saveUser(String namaUser, String noTelp, Integer role, String username, String password, Integer status, MultipartFile foto);
    DtoResponse updateUser(Integer idUser, String namaUser, String noTelp, Integer role, String username, String password, Integer status, MultipartFile foto);
    DtoResponse deleteUser(User user);
    DtoResponse loginUser(User user);
    DtoResponse generateToken(User user);
    DtoResponse decodeToken(String token);
    public byte[] getGambarById(int id);
}
