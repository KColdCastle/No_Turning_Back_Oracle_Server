package NoTurningBack.jinddobey.service;

import NoTurningBack.jinddobey.domain.Admin;
import NoTurningBack.jinddobey.domain.Member;
import NoTurningBack.jinddobey.repository.AdminRepository;
import NoTurningBack.jinddobey.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    MemberRepository memberRepository;

    @Override
    public void join(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public List<Member> memberList() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> blackMemberListS() {
        //블랙멤버 읽어오는 메소드
        return memberRepository.findByState(false);
    }


}
