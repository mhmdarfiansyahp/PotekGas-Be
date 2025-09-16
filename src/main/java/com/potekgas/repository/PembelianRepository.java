package com.potekgas.repository;

import com.potekgas.model.Pembelian;
import com.potekgas.model.PembelianPK;
import com.potekgas.vo.PembelianVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, PembelianPK> {
    @Procedure(name = "findPembelianByTrsId")
    List<Pembelian> findPembelianByTrsId(@Param("id") int id);
    @Procedure(procedureName = "countPembelian")
    ArrayList countPembelian();
    @Procedure(procedureName = "getBestSellerObat")
    ArrayList getBestSellerObat();
}
