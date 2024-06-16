package cloudclub.blog.users.controller;

import cloudclub.blog.global.annotation.LoginInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @GetMapping()
    @ResponseBody
    public String healthCheck(Principal principal) {
        return principal.getName();
    }
    @GetMapping("/annotation")
    @ResponseBody
    public String healthCheckAnnotation(@LoginInfo String userId) {
        return userId;
    }
}
