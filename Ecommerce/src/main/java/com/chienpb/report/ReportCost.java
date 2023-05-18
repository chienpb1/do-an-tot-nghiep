package com.chienpb.report;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReportCost implements Serializable{
	@Id
	public LocalDateTime date;
	public Long sumOrder;
	public Double cost;
}
