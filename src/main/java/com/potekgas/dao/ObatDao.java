package com.potekgas.dao;

import com.potekgas.vo.ObatVo;

import java.util.ArrayList;
import java.util.List;

public interface ObatDao {
    List<ObatVo> getAllObat();
    List<ObatVo> getObatActive();
    ArrayList countObat();
    List<ObatVo> getObatById(int id);
    boolean kurangiStokObat(int idObat, int jumlahPemakaian);

}
