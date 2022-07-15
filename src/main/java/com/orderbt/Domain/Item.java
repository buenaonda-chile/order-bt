package com.orderbt.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "web", name = "item")
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "active")
    private String active;

    @Column(name = "remark")
    private String remark;

    @Column(name = "cret_dt")
    private Date cretDt;

    @Column(name = "updt_dt")
    private Date updtDt;

    @OneToMany
    @JoinColumn(name = "item_id")
    @JsonManagedReference
    private List<ItemInfo> itemInfos = new ArrayList<>();
}
