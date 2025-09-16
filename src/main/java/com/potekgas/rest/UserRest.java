package com.potekgas.rest;

import com.potekgas.constant.UserConstant;
import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRest {

    @Autowired
    private UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public DtoResponse getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserActive")
    public DtoResponse getUserActive() {
        return userService.getUserActive();
    }

    @GetMapping("/getUser/{id}")
    public DtoResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getUser")
    public DtoResponse getUser(@RequestParam("id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/countAdmin")
    public DtoResponse countAdmin() {
        return userService.countAdmin();
    }

    @GetMapping("/countKasir")
    public DtoResponse countKasir() {
        return userService.countKasir();
    }

    @PostMapping("/saveUser")
    public DtoResponse saveUser(
            @RequestParam("namaUser") String namaUser,
            @RequestParam("noTelp") String noTelp,
            @RequestParam("role") Integer role,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("status") Integer status,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {

        return userService.saveUser(namaUser, noTelp, role, username, password, status, foto);
    }

    @PostMapping("/updateUser")
    public DtoResponse updateUser(@RequestParam("idUser") Integer idUser,
                                  @RequestParam("namaUser") String namaUser,
                                  @RequestParam("noTelp") String noTelp,
                                  @RequestParam("role") Integer role,
                                  @RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("status") Integer status,
                                  @RequestParam(value = "foto", required = false) MultipartFile foto) {
        return userService.updateUser(idUser, namaUser, noTelp, role, username, password, status, foto);
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> getGambarObat(@PathVariable int id) {
        try {
            // Dapatkan gambar berdasarkan ID obat dari repository atau penyimpanan gambar
            byte[] gambar = userService.getGambarById(id);

            // Buat ResponseEntity untuk mengirimkan gambar sebagai byte array
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Sesuaikan dengan tipe media gambar yang digunakan
            headers.setContentLength(gambar.length);
            return new ResponseEntity<>(gambar, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/deleteUser")
    public DtoResponse deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/login")
    public DtoResponse LoginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping("/decodeToken")
    public DtoResponse decodeToken(@RequestParam String token) {
        return userService.decodeToken(token);
    }
}
