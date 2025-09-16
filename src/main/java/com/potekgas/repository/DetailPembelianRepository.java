package com.potekgas.repository;

import com.potekgas.model.DetailPembelian;
import com.potekgas.model.DetailPembelianPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DetailPembelianRepository extends JpaRepository<DetailPembelian, DetailPembelianPK> {
    @Procedure(name = "findDetailPembelianByTrsId")
    Iterable<DetailPembelian> findDetailPembelianByTrsId(@Param("id") int id);
    @Procedure(name = "findLastIdDetail")
    ArrayList findLastIdDetail();
}
