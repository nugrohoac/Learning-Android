package com.example.nac017.crud_volley.Model;

/**
 * Created by nac017 on 01/11/17.
 */

public class LaporanModel {

    private Integer laporanHarga_id;
    private Integer user_id;
    private Integer komoditas_id;
    private Integer harga;
    private String alamat;
    private double latitude, longitude;

    public LaporanModel() {
        this.laporanHarga_id = laporanHarga_id;
        this.user_id = user_id;
        this.komoditas_id = komoditas_id;
        this.harga = harga;
        this.alamat = alamat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getLaporanHarga_id() {
        return laporanHarga_id;
    }

    public void setLaporanHarga_id(Integer laporanHarga_id) {
        this.laporanHarga_id = laporanHarga_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getKomoditas_id() {
        return komoditas_id;
    }

    public void setKomoditas_id(Integer komoditas_id) {
        this.komoditas_id = komoditas_id;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
