package com.Adinz.HomeEasyApp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message= "Item name is requried")
    private String name;
    private String quantity;
    private String status;
    private String shop;
    private boolean pickup = false;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date pickup_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    protected  void onUpdated(){
        this.updated_At = new Date();
    }






}
