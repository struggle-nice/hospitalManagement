package cn.com.scitc.bysj.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Introduce {
    private int id;
    private Integer doctorId;
    private String doctorName;
    private String introduce;
    private String doctorImg;

    @Transient
    private Doctor doctors;
    @Transient
    public Doctor getDoctors() {
        return doctors;
    }

    @Transient
    public void setDoctors(Doctor doctors) {
        this.doctors = doctors;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doctor_id", nullable = true)
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "doctor_name", nullable = true, length = 32)
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Basic
    @Column(name = "introduce", nullable = true, length = 64)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Basic
    @Column(name = "doctorImg", nullable = true, length = 64)
    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Introduce introduce1 = (Introduce) o;
        return id == introduce1.id &&
                Objects.equals(doctorId, introduce1.doctorId) &&
                Objects.equals(doctorName, introduce1.doctorName) &&
                Objects.equals(introduce, introduce1.introduce) &&
                Objects.equals(doctorImg, introduce1.doctorImg);
    }
    @Override
    public String toString() {
        return "Introduce{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", introduce='" + introduce + '\'' +
                ", doctorImg='" + doctorImg + '\'' +
                ", doctors=" + doctors +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, doctorName, introduce, doctorImg);
    }
}
