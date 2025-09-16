package com.potekgas.rest;

import com.potekgas.response.DtoResponse;
import com.potekgas.service.DetailPembelianService;
import com.potekgas.vo.DetailPembelianVo;
import com.potekgas.vo.PembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/detailPembelian")
public class DetailPembelianRest {
    @Autowired
    DetailPembelianService detailPembelianService;

    public DetailPembelianRest(DetailPembelianService detailPembelianService) {
        this.detailPembelianService = detailPembelianService;
    }

    @GetMapping("/getDetailPembelians")
    public DtoResponse getDetailPembelian() {
        return detailPembelianService.getAllDetailPembelian();
    }

    @GetMapping("/getDetailPembelians/{id}")
    public DtoResponse findDetailPembelianByTrsId(@PathVariable int id) {
        return detailPembelianService.findDetailPembelianByTrsId(id);
    }

    @PostMapping("/saveDetailPembelian")
    public DtoResponse savePembelian(@RequestBody List<DetailPembelianVo> detailPembelianVo){
        return detailPembelianService.saveDetailPembelian(detailPembelianVo);
    }
}
