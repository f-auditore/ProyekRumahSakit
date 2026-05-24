package model;

import enums.StatusDokter;
import enums.StatusPasien;
import enums.StatusRuangan;

public class Ugd extends Ruangan {

    private StatusPasien statusPasien;
    private StatusDokter statusDokter;

    public Ugd(StatusPasien statusPasien, StatusDokter statusDokter, String idRuangan, StatusRuangan status) {
        super(idRuangan, status);
        this.statusPasien = statusPasien;
        this.statusDokter = statusDokter;
    }

    public void updateStatusRuangan(StatusRuangan status) {
        if (status != null) {
            this.status = status;
        }
    }

    public void updateStatusPasien(StatusPasien statusPasien) {
        if (statusPasien != null) {
            this.statusPasien = statusPasien;
        }
    }

    public void updateStatusDokter(StatusDokter statusDokter) {
        if (statusDokter != null) {
            this.statusDokter = statusDokter;
        }
    }


    public StatusPasien getStatusPasien() {
        return statusPasien;
    }

    public StatusDokter getStatusDokter() {
        return statusDokter;
    }

    public void outputInfoUgd() {
        System.out.println("ID Ruangan    : " + getIdRuangan());
        System.out.println("Status Ruangan: " + getStatus());
        System.out.println("Status Pasien : " + statusPasien);
        System.out.println("Status Dokter : " + statusDokter);
    }
}