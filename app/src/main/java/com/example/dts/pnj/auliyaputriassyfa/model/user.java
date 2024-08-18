package com.example.dts.pnj.auliyaputriassyfa.model;


public class user {
    private long id;
    private String nama;
    private String alamat;
    private String npm;
    private String tempatLahir;
    private String tglLahir;
    private String thnMasuk;
    private String thnLulus;
    private String pekerjaan;
    private String jabatan;
    private String noHP;

    public user(long id, String nama, String alamat, String npm, String tempatLahir, String tglLahir, String thnMasuk, String thnLulus, String pekerjaan, String jabatan, String noHP) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.npm = npm;
        this.tempatLahir = tempatLahir;
        this.tglLahir = tglLahir;
        this.thnMasuk = thnMasuk;
        this.thnLulus = thnLulus;
        this.pekerjaan = pekerjaan;
        this.jabatan = jabatan;
        this.noHP = noHP;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNpm() {
        return npm;
    }
    public void setNpm(String npm){ this.npm = npm;}

    public String getTempatLahir() {
        return tempatLahir;
    }
    public void setTempatLahir(String tempatLahir){ this.tempatLahir = tempatLahir;}

    public String getTglLahir() {
        return tglLahir;
    }
    public void setTglLahir(String tglLahir){ this.tglLahir = tglLahir;}

    public String getThnMasuk() {
        return thnMasuk;
    }
    public void setThnMasuk(String thnMasuk){ this.thnMasuk = thnMasuk;}

    public String getThnLulus() {
        return thnLulus;
    }
    public void setThnLulus(String thnLulus){ this.thnLulus = thnLulus;}

    public String getPekerjaan() {
        return pekerjaan;
    }
    public void setPekerjaan(String pekerjaan){ this.pekerjaan = pekerjaan;}

    public String getJabatan() {
        return jabatan;
    }
    public void setJabatan(String jabatan){ this.jabatan = jabatan;}

    public String getNoHP(){ return  noHP;}
    public void setNoHP(String noHP){ this.noHP = noHP;}

}
