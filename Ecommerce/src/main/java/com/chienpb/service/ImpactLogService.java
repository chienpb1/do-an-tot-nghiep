package com.chienpb.service;

import com.chienpb.dao.ImpactLogRepo;
import com.chienpb.model.ImpactLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ImpactLogService {

    ImpactLog save(ImpactLog entity);



    List<ImpactLog> findAll(String impactTime);
}
