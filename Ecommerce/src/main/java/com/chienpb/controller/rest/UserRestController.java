package com.chienpb.controller.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.chienpb.dto.CurrentUsername;
import com.chienpb.model.ImpactLog;
import com.chienpb.model.Role;
import com.chienpb.service.ImpactLogService;
import com.chienpb.service.SessionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chienpb.model.Account;
import com.chienpb.service.AccountService;
import com.chienpb.service.UploadService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rest/accounts")
public class UserRestController {
    @Autowired
    AccountService aService;
    @Autowired
    UploadService uService;
    @Autowired
    SessionService session;
    @Autowired
    ImpactLogService iService;

    @GetMapping("")
    public List<Account> getAllAccount() {
        return aService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccount(@PathVariable("username") String username) {
        if (!aService.existsById(username)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(aService.findByUsername(username));
        }
    }

    @GetMapping("current-username")
    public ResponseEntity<?> getCurrentUsername(Authentication authentication) {
        // Lấy thông tin xác thực từ đối tượng Authentication
        CurrentUsername currentUsername = new CurrentUsername();
        currentUsername.setUsername(authentication.getName());
        return ResponseEntity.ok().body(currentUsername);
    }

    @GetMapping("/authorities")
    public Map<String, Object> getAuthority() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("accounts", aService.findAll());
        map.put("roles", aService.findAllRole());
//		map.put("authorities",aService.findAllAuthorities());
        return map;
    }

    @GetMapping("/search")
    public List<Account> searchAccount(@RequestParam("kw") Optional<String> kw) {
        String keyword = kw.orElse(null);
        if (keyword != null) {
            return aService.findByFullname("%" + keyword + "%");
        } else {
            return this.getAllAccount();
        }
    }

    @GetMapping("/authorities/search")
    public List<Account> searchAccountByUsername(@RequestParam("kw") Optional<String> kw) {
        String keyword = kw.orElse(null);
        if (keyword != null) {
            return aService.findByUsernameLike("%" + keyword + "%");
        } else {
            return this.getAllAccount();
        }
    }

    @PostMapping("")
    public ResponseEntity<Account> postAccount(@RequestBody Account Account) {
        if (aService.existsById(Account.getUsername())) {
            return ResponseEntity.badRequest().build();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(Account.getPassword());
            Account.setPassword(encodedPassword);
            Account.setActivated(true);
            Account.setCreateDate(LocalDateTime.now());
            Account account = session.get("user");
            ImpactLog impactLog = new ImpactLog();
            impactLog.setUsername(account.getUsername());
            impactLog.setDescription("Thêm mới người dùng bởi " + account.getUsername());
            impactLog.setImpactTime(LocalDateTime.now());
            iService.save(impactLog);
            return ResponseEntity.ok(aService.save(Account));
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<Account> putAccount(@PathVariable("username") String username, @RequestBody Account Account) {
        if (!aService.existsById(username)) {
            return ResponseEntity.notFound().build();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(Account.getPassword());
            Account.setPassword(encodedPassword);
            Account.setUpdateDate(LocalDateTime.now());
            Account account = session.get("user");
            ImpactLog impactLog = new ImpactLog();
            impactLog.setUsername(account.getUsername());
            impactLog.setDescription("Cập nhật người dùng bởi " + account.getUsername());
            impactLog.setImpactTime(LocalDateTime.now());
            iService.save(impactLog);
            return ResponseEntity.ok(aService.save(Account));
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("username") String username) {
        if (!aService.existsById(username)) {
            return ResponseEntity.notFound().build();
        } else {
            Account Account = aService.findByUsername(username);
            Account.setActivated(Boolean.FALSE);
            aService.save(Account);
            Account account = session.get("user");
            ImpactLog impactLog = new ImpactLog();
            impactLog.setUsername(account.getUsername());
            impactLog.setDescription("Xóa người dùng bởi " + account.getUsername());
            impactLog.setImpactTime(LocalDateTime.now());
            iService.save(impactLog);
            return ResponseEntity.ok().build();
        }
    }
}

