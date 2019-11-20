package cn.com.scitc.bysj.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Cii {
    private int cId;
    private int pId;
    private String name;
    private Date hospitalTime;
    private Integer doctorId;
    private String chuFang;
    private Date outTime;
    private String disease;
    private Integer hId;
    private String bed;
    private String charged;
    private String room;

    @Id
    @Column(name = "cId", nullable = false)
    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    @Basic
    @Column(name = "pId", nullable = false)
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "hospitalTime", nullable = false)
    public Date getHospitalTime() {
        return hospitalTime;
    }

    public void setHospitalTime(Date hospitalTime) {
        this.hospitalTime = hospitalTime;
    }

    @Basic
    @Column(name = "DoctorId", nullable = true)
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "chuFang", nullable = true, length = 255)
    public String getChuFang() {
        return chuFang;
    }

    public void setChuFang(String chuFang) {
        this.chuFang = chuFang;
    }

    @Basic
    @Column(name = "outTime", nullable = true)
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    @Basic
    @Column(name = "disease", nullable = false, length = 64)
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Basic
    @Column(name = "hId", nullable = true)
    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    @Basic
    @Column(name = "bed", nullable = false, length = 20)
    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    @Basic
    @Column(name = "charged", nullable = true, length = 64)
    public String getCharged() {
        return charged;
    }

    public void setCharged(String charged) {
        this.charged = charged;
    }

    @Basic
    @Column(name = "room", nullable = false, length = 11)
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cii cii = (Cii) o;
        return cId == cii.cId &&
                pId == cii.pId &&
                Objects.equals(name, cii.name) &&
                Objects.equals(hospitalTime, cii.hospitalTime) &&
                Objects.equals(doctorId, cii.doctorId) &&
                Objects.equals(chuFang, cii.chuFang) &&
                Objects.equals(outTime, cii.outTime) &&
                Objects.equals(disease, cii.disease) &&
                Objects.equals(hId, cii.hId) &&
                Objects.equals(bed, cii.bed) &&
                Objects.equals(charged, cii.charged) &&
                Objects.equals(room, cii.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, pId, name, hospitalTime, doctorId, chuFang, outTime, disease, hId, bed, charged, room);
    }
}
