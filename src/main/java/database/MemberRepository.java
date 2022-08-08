package database;

import database.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Member getMemberById(Long id);

    Member getMemberByUsername(String username);
}
