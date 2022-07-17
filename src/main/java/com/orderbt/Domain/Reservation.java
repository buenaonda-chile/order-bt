package com.orderbt.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "web", name = "reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name ="type")
    private String type;

    @Column(name ="type_dtl")
    private String typeDtl;

    @Column(name ="date")
    private LocalDate date;

    @Column(name ="time")
    private String time;

    @Column(name = "consult_yn")
    private String consultYn;

    @Column(name = "consult_dt")
    private String consultDt;

    @OneToOne(mappedBy = "reservation")
    @JsonBackReference
    private Estimate estimate;

    public Reservation(String type, String typeDtl, LocalDate date, String time, String consultYn) {
        this.type = type;
        this.typeDtl = typeDtl;
        this.date = date;
        this.time = time;
        this.consultYn = consultYn;
    }
}
