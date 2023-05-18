package com.chienpb.service.IMPL;

import com.chienpb.dao.ImpactLogRepo;
import com.chienpb.model.ImpactLog;
import com.chienpb.service.ImpactLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImpactLogServiceImpl implements ImpactLogService {

    @Autowired
    ImpactLogRepo repo;

    @Override
    public ImpactLog save(ImpactLog entity) {
        return repo.save(entity);
    }

    @Override
    public List<ImpactLog> findAll(String impactTime) {
        return repo.findAllImpactLog(null);
    }
}
