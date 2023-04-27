package com.chienpb.service;

import java.util.List;

import com.chienpb.report.ReportCost;
import com.chienpb.report.ReportProduct;

public interface ReportService {
	List<ReportCost> reportCostInMonth(Integer month);
	List<ReportProduct> reportProductInMonth(Integer month);
}
