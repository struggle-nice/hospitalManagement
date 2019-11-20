package cn.com.scitc.bysj.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Hospital {
    private int hospitalid;
    private String department;
    private int floor;
    private String room;
    private String bed;
    private Integer pId;
    private String doctorName;
    private String chuFang;
    private String pName;

    @Id
    @Column(name = "hospitalid", nullable = false)
    public int getHospitalid() {
        return hospitalid;
    }

    public void setHospitalid(int hospitalid) {
        this.hospitalid = hospitalid;
    }

    @Basic
    @Column(name = "department", nullable = false, length = 16)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "floor", nullable = false)
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "room", nullable = false, length = 20)
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
    @Column(name = "pId", nullable = true)
    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Basic
    @Column(name = "doctorName", nullable = true, length = 32)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Basic
    @Column(name = "chuFang", nullable = true, length = 64)
    public String getChuFang() {
        return chuFang;
    }

    public void setChuFang(String chuFang) {
        this.chuFang = chuFang;
    }

    @Basic
    @Column(name = "pName", nullable = true, length = 32)
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalid=" + hospitalid +
                ", department='" + department + '\'' +
                ", floor=" + floor +
                ", room='" + room + '\'' +
                ", bed='" + bed + '\'' +
                ", pId=" + pId +
                ", doctorName='" + doctorName + '\'' +
                ", chuFang='" + chuFang + '\'' +
                ", pName='" + pName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return hospitalid == hospital.hospitalid &&
                floor == hospital.floor &&
                Objects.equals(department, hospital.department) &&
                Objects.equals(room, hospital.room) &&
                Objects.equals(bed, hospital.bed) &&
                Objects.equals(pId, hospital.pId) &&
                Objects.equals(doctorName, hospital.doctorName) &&
                Objects.equals(chuFang, hospital.chuFang) &&
                Objects.equals(pName, hospital.pName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospitalid, department, floor, room, bed, pId, doctorName, chuFang, pName);
    }
}
