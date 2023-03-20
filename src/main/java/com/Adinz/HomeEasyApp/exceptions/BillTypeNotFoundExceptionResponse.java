package com.Adinz.HomeEasyApp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillTypeNotFoundExceptionResponse {
    private String BillTypeNotFound;
}
