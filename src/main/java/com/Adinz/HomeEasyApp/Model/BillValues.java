package com.Adinz.HomeEasyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
public class BillValues {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @Column(updatable = false)
//    private String billTypeId;
    private String values;
    private boolean status;
    private String month;
    private Date payDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="billTypes_id", updatable = false, nullable = false)
    @JsonIgnore
    private BillTypes billTypes;

}
