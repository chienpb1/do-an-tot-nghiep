package com.chienpb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "impact_log")
public class ImpactLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "impact_time")
    private LocalDateTime impactTime;

    private String username;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;
}
