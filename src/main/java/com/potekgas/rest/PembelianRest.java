package com.potekgas.rest;

import com.potekgas.model.Pembelian;
import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.PembelianService;
import com.potekgas.vo.PembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/pembelian")
public class PembelianRest {
    @Autowired
    PembelianService pembelianService;

    public PembelianRest(PembelianService pembelianService) {
        this.pembelianService = pembelianService;
    }

    @GetMapping("/getPembelians")
    public DtoResponse getPembelian() {
        return pembelianService.getAllPembelian();
    }

    @GetMapping("/getPembelians/{id}")
    public DtoResponse getPembelianById(@PathVariable int id) {
        return pembelianService.findPembelianByTrsId(id);
    }

    @PostMapping("/savePembelian")
    public DtoResponse savePembelian(@RequestBody PembelianVo pembelianVo){
        return pembelianService.savePembelian(pembelianVo);
    }

    @GetMapping("/countPembelian")
    public DtoResponse countPembelian() {
        return pembelianService.countPembelian();
    }

    @GetMapping("/getBestSellerObat")
    public DtoResponse getBestSellerObat() {
        return pembelianService.getBestSellerObat();
    }
}
