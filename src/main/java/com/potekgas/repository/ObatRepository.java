package com.potekgas.repository;

import com.potekgas.model.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ObatRepository extends JpaRepository<Obat, Integer> {
////    @Procedure(name = "findObatByNamaAndMerk")
////    Obat findByNamaObatAndMerk(@Param("namaObat") String namaObat, @Param("merkObat") String merkObat);
//
//    Optional<Obat> findByNamaAndMerk(String namaObat, String merkObat);

    @Procedure(procedureName = "getObatActive")
    List<Obat> getObatActive();
    @Procedure(procedureName = "countObat")
    ArrayList countObat();
    @Modifying
    @Query("UPDATE Obat o SET o.stok = o.stok - :jumlah WHERE o.id_obat = :obatId")
    void kurangiStokObat(@Param("obatId") int obatId, @Param("jumlah") int jumlah);
//    @Procedure(procedureName = "KurangiStokObat")
//    void kurangiStokObat(@Param("id_obat") int idObat, @Param("jumlah_pemakaian") int jumlahPemakaian);
}
