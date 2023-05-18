package com.chienpb.controller.rest;

import com.chienpb.dto.OrderDTO;
import com.chienpb.dto.PagingResponse;
import com.chienpb.dto.ResponseModel;
import com.chienpb.model.ImpactLog;
import com.chienpb.model.Product;
import com.chienpb.service.ImpactLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rest/logs")
public class ImpactLogRestController {

    @Autowired
    ImpactLogService service;

    @GetMapping("/get-all")
    public List<ImpactLog> searchProduct(@RequestParam("kw") Optional<String> kw){
        String keyword = kw.orElse(null);
        return service.findAll(keyword);
    }

}
