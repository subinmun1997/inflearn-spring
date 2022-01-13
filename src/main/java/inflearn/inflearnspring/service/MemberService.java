package inflearn.inflearnspring.service;

import inflearn.inflearnspring.domain.Member;
import inflearn.inflearnspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService { // ctrl + shift + t
    
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { // alt + shift + enter
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // ctrl + alt + v 반환형 자동
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증
        memberRepository.findByName(member.getName()) // ctrl + alt + m
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> fineOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
