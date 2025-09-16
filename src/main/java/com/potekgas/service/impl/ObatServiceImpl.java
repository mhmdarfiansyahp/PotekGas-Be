package com.potekgas.service.impl;

import com.fasterxml.jackson.databind.util.NativeImageUtil;
import com.potekgas.dao.ObatDao;
import com.potekgas.model.Obat;
import com.potekgas.model.User;
import com.potekgas.repository.ObatRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.ObatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import static com.potekgas.constant.ObatConstant.*;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDao obatDao;

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public DtoResponse getAllObat() {
        if (obatDao.getAllObat() != null) return new DtoResponse(200, obatDao.getAllObat());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getObatActive() {
        if (obatDao.getObatActive() != null) return new DtoResponse(200, obatDao.getObatActive());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse countObat() {
        return new DtoResponse(200, obatDao.countObat());
    }

    @Override
    public DtoResponse getObatById(int id) {
        if(obatDao.getObatById(id) != null){
            return new DtoResponse(200, obatDao.getObatById(id));
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    public static String convertDateFormat(String dateString) {
        try {
            DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Parsing tanggal dari format input
            Date date = inputFormat.parse(dateString);

            // Mengonversi tanggal ke format output
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Mengembalikan null jika terjadi kesalahan parsing
        }
    }

    @Override
    public DtoResponse saveObat(String namaObat, String merkObat, String jenisObat, Date tglKadaluarsa, Float harga, Integer stok, String keterangan, Integer status, MultipartFile gambar) {
        // Validasi obat duplikat ketika create
        if (namaObat == null || namaObat.isBlank() || merkObat == null || merkObat.isBlank() || jenisObat == null || jenisObat.isBlank() ||
                harga == 0 || tglKadaluarsa.toString() == "0001-01-01" || stok == 0 || keterangan == null || keterangan.isBlank()) {
            return new DtoResponse(400, null, mNullReq);
        }

        for (Obat existingObat : obatRepository.findAll()) {
            if (existingObat.getNama_obat().equals(namaObat)) {
                return new DtoResponse(400, null, mDuplicateObat);
            }
        }

        try {
            byte[] imageData = null;
            // Periksa apakah ada gambar yang dikirimkan
            if (gambar != null && !gambar.isEmpty()) {
                // Jika ada gambar, ambil data biner dari gambar
                imageData = gambar.getBytes();
            }

            Obat obat = new Obat(namaObat, merkObat, jenisObat, tglKadaluarsa, harga, stok, keterangan, status, imageData);
            obat.setStatus(1);
            obatRepository.save(obat);
            return new DtoResponse(200, obat, mCreateSuccess);
        } catch (IOException e) {
            return new DtoResponse(500, null, mCreateFailed);
        }
    }

    @Override
    public DtoResponse updateObat(Integer idObat, String namaObat, String merkObat, String jenisObat, Date tglKadaluarsa, Float harga, Integer stok, String keterangan, Integer status, MultipartFile gambar) {
        // Periksa apakah parameter yang diberikan valid
        if (namaObat == null || namaObat.isBlank() || merkObat == null || merkObat.isBlank() || jenisObat == null || jenisObat.isBlank() ||
                harga == null || tglKadaluarsa == null || stok == null || keterangan == null || keterangan.isBlank()) {
            return new DtoResponse(400, null, mNullReq);
        }

        // Cari obat yang sudah ada dalam database
        Optional<Obat> existingObatOptional = obatRepository.findById(idObat);
        if (!existingObatOptional.isPresent()) {
            return new DtoResponse(404, null, mNotFound);
        }
        Obat existingObat = existingObatOptional.get();

        if (gambar != null && !gambar.isEmpty()) {
            try {
                byte[] imageData = gambar.getBytes();
                existingObat.setGambar(imageData);
            } catch (IOException e) {
                return new DtoResponse(500, null, mUpdateFailed);
            }
        } else {
            // Jika tidak ada gambar yang dikirimkan, atur gambar ke null
            existingObat.setGambar(existingObat.getGambar());
        }

        // Periksa apakah nama obat telah berubah
        if (!existingObat.getNama_obat().equals(namaObat)) {
            for (Obat checkObat : obatRepository.findAll()) {
                if (checkObat.getNama_obat().equals(namaObat)) {
                    return new DtoResponse(400, null, mDuplicateObat);
                }
            }
        }

        // Perbarui data obat yang ada
        existingObat.setNama_obat(namaObat);
        existingObat.setMerk_obat(merkObat);
        existingObat.setJenis_obat(jenisObat);
        existingObat.setTgl_kadaluarsa(tglKadaluarsa);
        existingObat.setHarga(harga);
        existingObat.setStok(stok);
        existingObat.setKeterangan(keterangan);

        // Simpan data obat yang diperbarui
        try {
            Obat updatedObat = obatRepository.save(existingObat);
            if (updatedObat != null) {
                return new DtoResponse(200, updatedObat, mUpdateSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mUpdateFailed);
        }
    }

    @Override
    public DtoResponse deleteObat(Obat obat) {
        try {
            Optional<Obat> optionalObat = obatRepository.findById(obat.getId_obat());

            if (optionalObat.isPresent()) {
                Obat existingObat = optionalObat.get();

                existingObat.setStatus(0);

                Obat deleteObat = obatRepository.save(existingObat);
                return new DtoResponse(200, deleteObat, mDeleteSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mDeleteFailed);
        }
    }

    @Override
    public byte[] getGambarById(int id) {
        Optional<Obat> obatOptional = obatRepository.findById(id);
        if (obatOptional.isPresent()) {
            Obat obat = obatOptional.get();
            return obat.getGambar();
        } else {
            // Handle case where obat with the given ID is not found
            return null;
        }
    }

    @Override
    public DtoResponse kurangiStokObat(int idObat, int jumlahPemakaian) {
        return new DtoResponse(200, obatDao.kurangiStokObat(idObat,jumlahPemakaian));
    }
}
