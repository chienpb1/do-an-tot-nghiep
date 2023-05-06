package com.chienpb.dao;

import com.chienpb.model.ImpactLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpactLogRepo extends JpaRepository<ImpactLog, Long> {
}
