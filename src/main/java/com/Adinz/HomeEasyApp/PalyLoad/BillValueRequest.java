package com.Adinz.HomeEasyApp.PalyLoad;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillValueRequest {
    private String id;
    private String values;
    private boolean status;
    private String month;
    private Date payDate;
}
