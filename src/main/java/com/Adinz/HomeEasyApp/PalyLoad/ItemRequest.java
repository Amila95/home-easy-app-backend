package com.Adinz.HomeEasyApp.PalyLoad;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    @NotBlank(message= "Item name is requried")
    private String name;
    private String quantity;
    private String status;
    private String shop;
}
