package com.chienpb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Long id;
    private LocalDateTime createDate;
    private String username;
    private String address;
    private Double total;
    private Integer status;
}
