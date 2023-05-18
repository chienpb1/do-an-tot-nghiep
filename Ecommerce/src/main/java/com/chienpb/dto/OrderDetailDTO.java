package com.chienpb.dto;

import com.chienpb.model.Account;
import com.chienpb.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO implements Serializable {
    private Long id;
    private LocalDateTime orderDate;
    private String username;
    private Integer status;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String address;
    private String note;
    private Double total;
    List<OrderProductDTO> orderProduct;
}
