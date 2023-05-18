package com.chienpb.controller;

import com.chienpb.dto.CurrentUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.chienpb.model.Account;
import com.chienpb.model.Role;
import com.chienpb.service.AccountService;
import com.chienpb.service.BrandService;
import com.chienpb.service.CategoryService;
import com.chienpb.service.MailerService;
import com.chienpb.service.ProductService;
import com.chienpb.service.SessionService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class HomeController {
    @Autowired
    BrandService bService;
    @Autowired
    CategoryService cService;
    @Autowired
    ProductService pService;
    @Autowired
    SessionService session;
    @Autowired
    AccountService aService;
    @Autowired
    MailerService mailer;

    @RequestMapping("/admin")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("current-username")
    public ResponseEntity<?> getCurrentUsername(Authentication authentication) {
        // Lấy thông tin xác thực từ đối tượng Authentication
        CurrentUsername currentUsername = new CurrentUsername();
        currentUsername.setUsername(authentication.getName());
        return ResponseEntity.ok().body(currentUsername);
    }

    @GetMapping("get-my-account")
    public ResponseEntity<?> getMyAccount(Authentication authentication) {
        Account account = null;
        if(authentication != null){
            account = aService.findByUsername(authentication.getName());
        }
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/brand/list")
    public String brandList(Model model) {
        return "brand/list";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute Account account) {
        return "register";
    }

    @GetMapping("/my-account")
    public String myAccount(Model model) {
        Account account =  session.get("user");
        model.addAttribute("userDetail", account);
        return "account/my-account";
    }

    @PutMapping("edit-my-account/{username}")
    public ResponseEntity<Account> putAccount(@PathVariable("username") String username, @RequestBody Account Account) {
        if (!aService.existsById(username)) {
            return ResponseEntity.notFound().build();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(Account.getPassword());
            Account.setPassword(encodedPassword);
            Account.setUpdateDate(LocalDateTime.now());
            return ResponseEntity.ok(aService.save(Account));
        }
    }

    @PostMapping("/register")
    public String signup(Model model,
                         @ModelAttribute Account account) {
        if (aService.existsById(account.getUsername())) {
            model.addAttribute("error", "Đã tồn tại username " + account.getUsername());
            return "register";
        } else if (aService.existsByEmail(account.getEmail())) {
            model.addAttribute("error", "Email " + account.getEmail() + " đã được đăng ký với tài khoản khác");
            return "register";
        } else if (aService.existsByPhoneNumber(account.getPhoneNumber())) {
            model.addAttribute("error", "Số điện thoại " + account.getPhoneNumber() + " đã được đăng ký với tài khoản khác");
            return "register";
        } else {
            account.setActivated(true);
            account.setCreateDate(LocalDateTime.now());
            account.setPhoto("logo.png");
            Role r = new Role();
            r.setRole("user");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(encodedPassword);
            account.setRole(r);
//            r.setRole("user");
//            RoleDetail rd = new RoleDetail();
//            rd.setAccount(account);
//            rd.setRole(r);

            aService.save(account);
            return "redirect:/register/success";
        }
    }

    @RequestMapping("/register/success")
    public String registerSuccess(Model model) {
        model.addAttribute("message", "Đăng ký thành công");
        return "login";
    }

    @GetMapping("/login")
    public String formLogin(Model model, @RequestParam(value = "message", required = false) String message) {
        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String currentUserName(
            Model model, Authentication authentication) {
        model.addAttribute("db", pService.findProductByCreateDateDESC());
        try {
            Account account = aService.findByUsername(authentication.getName());
            System.out.println(account + " ACCOUNT");
            if (account == null) {
                model.addAttribute("message", "Tên đăng nhập hoặc mật khẩu không đúng ");
                return "login";
            } else {
                String uri = session.get("security-uri");
//				if(uri != null) {
//					return "redirect:"+uri;
//				}
//				else {
                session.set("user", account);
                if (this.checkAdmin(account)) {
                    session.set("userAdmin", "admin");
                }
                if (this.checkStaff(account)) {
                    session.set("userStaff", "staff");
                }
                model.addAttribute("message", "Login success");
//				}
            }
        } catch (Exception e) {
            // TODO: handle exception
            model.addAttribute("message", "Invalid username");
        }
        return "home/index";

    }


    //	public Boolean checkAdmin(Account account) {
//		for(RoleDetail roleDetail : account.getRoleDetails()) {
//			if(roleDetail.getRole().getRole().equals("staff") || roleDetail.getRole().getRole().equals("director")) {
//				return true;
//			}
//		}
//		return false;
//	}
    public Boolean checkAdmin(Account account) {
        if (account.getRole().getRole().equals("staff") || account.getRole().getRole().equals("director")) {
            return true;
        }
        return false;
    }

    public Boolean checkStaff(Account account) {
        if (account.getRole().getRole().equals("staff") & !account.getRole().getRole().equals("director")) {
            return true;
        }
        return false;
    }

    @RequestMapping("/logout")
    public String logoutSuccess(Model model) {
        session.remove("user");
        session.remove("userAdmin");
        session.remove("security-uri");
        session.remove("uri");
        model.addAttribute("message", "Đăng xuất thành công");
        return "login";
    }

    @GetMapping("forgot-password")
    public String forgot() {
        return "forgot";
    }

    @PostMapping("forgot-password")
    public String forgot(@RequestParam("username") String username, Model model) {
        try {
            Account account = aService.findByUsername(username);
            String to = account.getEmail();
            String email = to.substring(0, 2);

            String generatePassword = aService.generatePassword();

            String subject = "Lấy lại mật khẩu";
            String body = "Mật khẩu của bạn là:" + generatePassword;
            mailer.send(to, subject, body);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(generatePassword);
            account.setPassword(encodedPassword);
            aService.save(account);

            model.addAttribute("message", "Mật khẩu mới đã được gửi đến mail " + email + "***");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            model.addAttribute("message", "Tài khoản không tồn tại");
        }
        return "forgot";
    }
}
