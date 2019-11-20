package cn.com.scitc.bysj.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Ordering {
    private int oid;
    private Integer uid;
    private String doctorName;
    private Date orderingTime;
    private Date bookingTime;

    @Id
    @Column(name = "oid", nullable = false)
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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
    @Column(name = "ordering_time", nullable = false)
    public Date getOrderingTime() {
        return orderingTime;
    }

    public void setOrderingTime(Date orderingTime) {
        this.orderingTime = orderingTime;
    }

    @Basic
    @Column(name = "booking_time", nullable = false)
    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", doctorName='" + doctorName + '\'' +
                ", orderingTime=" + orderingTime +
                ", bookingTime=" + bookingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordering ordering = (Ordering) o;
        return oid == ordering.oid &&
                Objects.equals(uid, ordering.uid) &&
                Objects.equals(doctorName, ordering.doctorName) &&
                Objects.equals(orderingTime, ordering.orderingTime) &&
                Objects.equals(bookingTime, ordering.bookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oid, uid, doctorName, orderingTime, bookingTime);
    }
}
