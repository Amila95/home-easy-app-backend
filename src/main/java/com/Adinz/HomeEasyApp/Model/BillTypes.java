package com.Adinz.HomeEasyApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message= "bill name is requried")
    private String name;

    //oneToMany
    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "billTypes")
    @JsonIgnore
    private List<BillValues> billValuesList = new ArrayList<>();
}
