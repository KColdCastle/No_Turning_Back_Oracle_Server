package NoTurningBack.jinddobey.controller;


import NoTurningBack.jinddobey.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("member")
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
@RestController
public class MemberController {

    MemberService service;




}
