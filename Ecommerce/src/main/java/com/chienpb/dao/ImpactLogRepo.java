package com.chienpb.dao;

import com.chienpb.model.ImpactLog;
import com.chienpb.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ImpactLogRepo extends JpaRepository<ImpactLog, Long> {

    @Query("SELECT i FROM ImpactLog i WHERE (?1 IS NULL OR i.impactTime = ?1) ORDER BY i.impactTime DESC")
    Page<ImpactLog> doSearch(LocalDateTime impactTime, Pageable pageable);

    @Query(value = "SELECT i FROM ImpactLog i WHERE (?1 IS NULL OR i.impactTime = ?1) ORDER BY i.impactTime DESC")
    List<ImpactLog> findAllImpactLog(LocalDateTime impactTime);
}
