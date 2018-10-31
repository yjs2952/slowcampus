package com.slowcampus.dao;

import com.slowcampus.dto.Member;

public interface MemberDao {
    public Member loginMember(Member member);

    public void logoutMember();

    public int signupMember(Member member);
}
