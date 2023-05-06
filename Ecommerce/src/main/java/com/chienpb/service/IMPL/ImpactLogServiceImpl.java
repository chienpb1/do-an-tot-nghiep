package com.chienpb.service.IMPL;

import com.chienpb.dao.ImpactLogRepo;
import com.chienpb.model.ImpactLog;
import com.chienpb.service.ImpactLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpactLogServiceImpl implements ImpactLogService {

    @Autowired
    ImpactLogRepo repo;

    @Override
    public ImpactLog save(ImpactLog entity) {
        return repo.save(entity);
    }
}
