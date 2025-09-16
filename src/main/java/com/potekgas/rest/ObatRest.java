package com.potekgas.rest;

import com.potekgas.model.Obat;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/obats")
public class ObatRest {
    @Autowired
    private ObatService obatService;

    public ObatRest(ObatService obatService) {
        this.obatService = obatService;
    }

    @GetMapping("/getObats")
    public DtoResponse getObats() {
        return obatService.getAllObat();
    }

    @GetMapping("/getObatActive")
    public DtoResponse getObatActive() {
        return obatService.getObatActive();
    }

    @GetMapping("/countObat")
    public DtoResponse countObat() {
        return obatService.countObat();
    }

    @GetMapping("/getObat/{id}")
    public DtoResponse getObatById(@PathVariable int id) {
        return obatService.getObatById(id);
    }

    @PostMapping("/saveObat")
    public DtoResponse createObat(@RequestParam("namaObat") String namaObat,
                                  @RequestParam("merkObat") String merkObat,
                                  @RequestParam("jenisObat") String jenisObat,
                                  @RequestParam("tglKadaluarsa") @DateTimeFormat(pattern = "yyyy-MM-dd") Date tglKadaluarsa,
                                  @RequestParam("harga") Float harga,
                                  @RequestParam("stok") Integer stok,
                                  @RequestParam("keterangan") String keterangan,
                                  @RequestParam("status") Integer status,
                                  @RequestParam(value = "gambar", required = false) MultipartFile gambar) {
        // Meneruskan nilai parameter yang diperlukan ke metode saveObat
        return obatService.saveObat(namaObat, merkObat, jenisObat, tglKadaluarsa, harga, stok, keterangan, status, gambar);
    }

    @PostMapping("/updateObat")
    public DtoResponse updateObat(
            @RequestParam("idObat") Integer idObat,
            @RequestParam("namaObat") String namaObat,
            @RequestParam("merkObat") String merkObat,
            @RequestParam("jenisObat") String jenisObat,
            @RequestParam("tglKadaluarsa") @DateTimeFormat(pattern = "yyyy-MM-dd") Date tglKadaluarsa,
            @RequestParam("harga") Float harga,
            @RequestParam("stok") Integer stok,
            @RequestParam("keterangan") String keterangan,
            @RequestParam("status") Integer status,
            @RequestParam(value = "gambar", required = false) MultipartFile gambar) {

        return obatService.updateObat(idObat, namaObat, merkObat, jenisObat, tglKadaluarsa, harga, stok, keterangan, status, gambar);
    }

    @GetMapping("/gambar/{id}")
    public ResponseEntity<byte[]> getGambarObat(@PathVariable int id) {
        try {
            // Dapatkan gambar berdasarkan ID obat dari repository atau penyimpanan gambar
            byte[] gambar = obatService.getGambarById(id);

            // Buat ResponseEntity untuk mengirimkan gambar sebagai byte array
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Sesuaikan dengan tipe media gambar yang digunakan
            headers.setContentLength(gambar.length);
            return new ResponseEntity<>(gambar, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/deleteObat")
    public DtoResponse deleteObat(@RequestBody Obat obat) {
        return obatService.deleteObat(obat);
    }

    @PostMapping("/kurangiStock/{idObat}/jumlah/{jumlahPemakaian}")
    public DtoResponse kurangiStock(@PathVariable("idObat") int idObat, @PathVariable("jumlahPemakaian") int jumlahPemakaian) {
        return obatService.kurangiStokObat(idObat, jumlahPemakaian);
    }
}
