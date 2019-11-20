package cn.com.scitc.bysj.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Patient {
    private int pId;
    private String name;
    private String gender;
    private Integer age;
    private Date hospitalTime;
    private Integer doctorId;
    private String disease;
    private String state;
    private Integer hId;
    @Transient
    private Hospital hospitals;

    @Transient
    public Hospital getHospitals() {
        return hospitals;
    }
    @Transient
    public void setHospitals(Hospital hospitals) {
        this.hospitals = hospitals;
    }

    @Id
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
    @Column(name = "gender", nullable = false, length = 2)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
    @Column(name = "disease", nullable = true, length = 255)
    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "hid" , nullable = true)
    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", hospitalTime=" + hospitalTime +
                ", doctorId=" + doctorId +
                ", disease='" + disease + '\'' +
                ", state='" + state + '\'' +
                ", hId=" + hId +
                ", hospitals=" + hospitals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return pId == patient.pId &&
                Objects.equals(name, patient.name) &&
                Objects.equals(gender, patient.gender) &&
                Objects.equals(age, patient.age) &&
                Objects.equals(hospitalTime, patient.hospitalTime) &&
                Objects.equals(doctorId, patient.doctorId) &&
                Objects.equals(disease, patient.disease) &&
                Objects.equals(state, patient.state) &&
                Objects.equals(hId,patient.hId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pId, name, gender, age, hospitalTime, doctorId, disease, state, hId,hospitals);
    }
}
