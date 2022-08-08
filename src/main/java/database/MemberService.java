package database;

import database.domain.Member;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findById(Long id) {
        return memberRepository.getMemberById(id);
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.getMemberByUsername(username);
    }

}
