package com.slowcampus.dao;

import com.slowcampus.dto.Member;

public interface MemberDao {
    public void loginMember();

    public void logoutMember();

    public int signupMember(Member member);
}
