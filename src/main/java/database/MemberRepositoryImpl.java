package database;

import database.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository{
    private Map<Long, Member> memberHashMap = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public void save(Member member) {
        memberHashMap.put(++sequence, member);
    }

    @Override
    public Member getMemberById(Long id) {
        return memberHashMap.get(id);
    }

    @Override
    public Member getMemberByUsername(String username) {
        return memberHashMap.values().stream().filter(member -> member.getUsername().equals(username)).findFirst().get();
    }
}
