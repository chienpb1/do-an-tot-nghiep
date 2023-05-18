package com.chienpb.service.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chienpb.dao.ReportCostRepo;
import com.chienpb.dao.ReportProductRepo;
import com.chienpb.report.ReportCost;
import com.chienpb.report.ReportProduct;
import com.chienpb.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired ReportCostRepo rpcRepo;
	@Autowired ReportProductRepo rprRepo;
	@Override
	public List<ReportCost> reportCostInMonth(Integer month) {
		return rpcRepo.reportCost(month);
	}
	@Override
	public List<ReportProduct> reportProductInMonth(Integer month) {
		List<ReportProduct> lst = rprRepo.reportProduct(month);
		return lst;
	}

}
