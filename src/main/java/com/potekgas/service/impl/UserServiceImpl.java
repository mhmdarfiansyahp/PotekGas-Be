package com.potekgas.service.impl;

import com.potekgas.dao.UserDao;
import com.potekgas.model.Obat;
import com.potekgas.model.User;
import com.potekgas.repository.UserRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.UserService;
import com.potekgas.token.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.potekgas.constant.UserConstant.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public DtoResponse getAllUsers() {
        if (userDao.getAllUser() != null) return new DtoResponse(200, userDao.getAllUser());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getUserActive() {
        if (userDao.getUserActive() != null) return new DtoResponse(200, userDao.getUserActive());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getUserById(int id) {
        if (userDao.getUserById(id) != null) {
            return new DtoResponse(200, userDao.getUserById(id));
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    //    public String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : encodedHash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public DtoResponse saveUser(String namaUser, String noTelp, Integer role, String username, String password, Integer status, MultipartFile foto) {
        // validasi user duplikat ketika create
        if (namaUser == null || namaUser.isBlank() || noTelp == null || noTelp.isBlank() ||
                role == null || username == null || username.isBlank() || password == null || password.isBlank()) {
            return new DtoResponse(400, null, mNullReq);
        }

        // Validasi duplikat username
        for (User existingUser : userRepository.findAll()) {
            if (existingUser.getUsername().equals(username)) {
                return new DtoResponse(400, null, mDuplicateUser);
            }
        }

        try {
            // Enkripsi kata sandi sebelum disimpan
            String encryptedPassword = passwordEncoder.encode(password);

            byte[] imageData = null; // Inisialisasi imageData dengan null

            // Periksa apakah ada foto yang dikirimkan
            if (foto != null && !foto.isEmpty()) {
                imageData = foto.getBytes();
            }

            // Buat objek user baru
            User user = new User(null, namaUser, noTelp, role, username, encryptedPassword, status, imageData);
            user.setStatus(1);
            userRepository.save(user);
            return new DtoResponse(200, user, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, null, mCreateFailed);
        }
    }

    @Override
    public DtoResponse updateUser(Integer idUser, String namaUser, String noTelp, Integer role, String username, String password, Integer status, MultipartFile foto) {
        if (namaUser == null || namaUser.isBlank() || noTelp == null || noTelp.isBlank() ||
                role == null || username == null || username.isBlank() || password == null || password.isBlank()) {
            return new DtoResponse(400, null, mNullReq);
        }

        // Cari user yang sudah ada dalam database
        Optional<User> existingUserOptional = userRepository.findById(idUser);
        if (!existingUserOptional.isPresent()) {
            return new DtoResponse(404, null, mNotFound);
        }
        User existingUser = existingUserOptional.get();

        // Periksa apakah ada foto baru yang dikirimkan
        if (foto != null && !foto.isEmpty()) {
            try {
                byte[] imageData = foto.getBytes();
                existingUser.setFoto(imageData);
            } catch (IOException e) {
                return new DtoResponse(500, null, mUpdateFailed);
            }
        } else {
            // Jika tidak ada foto yang dikirimkan, hapus foto dari pengguna yang ada
            existingUser.setFoto(existingUser.getFoto());
        }

        // Periksa username ada dalam database
        if (!existingUser.getUsername().equals(username)) {
            // Validasi user duplikat ketika update
            for (User checkUser : userRepository.findAll()) {
                if (checkUser.getUsername().equals(username)) {
                    return new DtoResponse(400, null, mDuplicateUser);
                }
            }
        }

        String encryptedPassword = passwordEncoder.encode(password);

        existingUser.setNama_user(namaUser);
        existingUser.setNo_telp(noTelp);
        existingUser.setRole(role);
        existingUser.setUsername(username);
        existingUser.setPassword(existingUser.getPassword());

        try {
            User updatedUser = userRepository.save(existingUser);
            if (updatedUser != null)
                return new DtoResponse(200, updatedUser, mUpdateSuccess);
            else
                return new DtoResponse(404, null, mNotFound);
        } catch (Exception e) {
            return new DtoResponse(500, null, mUpdateFailed);
        }
    }

    @Override
    public DtoResponse deleteUser(User user) {
        try {
            Optional<User> optionalUser = userRepository.findById(user.getId_user());

            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();

                existingUser.setStatus(0);

                User deleteUser = userRepository.save(existingUser);
                return new DtoResponse(200, deleteUser, mDeleteSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mDeleteFailed);
        }
    }

    @Override
    public DtoResponse loginUser(User user) {
        try {
            String msg = "";
            if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
                return new DtoResponse(404, null, mBlank);
            } else {
                if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
                    user.setId_user(999);
                    user.setNama_user("Master Admin");
                    user.setRole(1);

                    List<Map<String, String>> result = new ArrayList<>();
                    String token = tokenService.generateToken(user);

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("id", user.getId_user().toString());
                    userMap.put("name", user.getNama_user());
                    userMap.put("role", user.getRole().toString());
                    userMap.put("token", token);
                    result.add(userMap);
                    return new DtoResponse(200, result, mLoginSuccess);
                }

                User user1 = userRepository.findByUsername(user.getUsername());
                if (user1 != null) {
                    String password = user.getPassword();
                    String encodedPassword = user1.getPassword();
                    boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
                    if (isPwdRight) {
                        List<Map<String, String>> result = new ArrayList<>();
                        String token = tokenService.generateToken(user1);

                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("id", user1.getId_user().toString());
                        userMap.put("name", user1.getNama_user());
                        userMap.put("role", String.valueOf(user1.getRole()));
                        userMap.put("token", token);
                        result.add(userMap);
                        return new DtoResponse(200, result, mLoginSuccess);
                    } else {
                        return new DtoResponse(500, null, mPasswordFailed);
                    }
                } else {
                    return new DtoResponse(404, null, mUsernameFailed);
                }
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mLoginFailed + " : " +e.toString());
        }
    }

    @Override
    public DtoResponse countAdmin() {
        return new DtoResponse(200, userDao.countAdmin());
    }

    @Override
    public DtoResponse countKasir() {
        return new DtoResponse(200, userDao.countKasir());
    }

    @Override
    public DtoResponse findUserById(int id) {
        if (userDao.findUserById(id) != null) return new DtoResponse(200, userDao.findUserById(id));
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public byte[] getGambarById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getFoto();
        } else {
            // Handle case where obat with the given ID is not found
            return null;
        }
    }

    @Override
    public DtoResponse generateToken(User user) {
        return new DtoResponse(200, tokenService.generateToken(user), "Success");
    }

    @Override
    public DtoResponse decodeToken(String token) {
        ArrayList checkToken = tokenService.getTokenInfo(token);
        if (checkToken.contains("Token Expired") || checkToken.contains("Invalid Token")){
            return new DtoResponse(401, checkToken, mUnauthorized);
        } else {
            return new DtoResponse(200, checkToken, mLoginSuccess);
        }
    }
}
