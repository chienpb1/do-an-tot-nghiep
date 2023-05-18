package com.chienpb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO implements Serializable {
    private String productName;
    private String img;
    private Double price;
    private Integer quantity;
    private Double amount;
}
