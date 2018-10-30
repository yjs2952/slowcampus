package com.slowcampus.service;

import com.slowcampus.dto.Member;

public interface MemberService {
    public void loginMember();

    public void logoutMember();

    public int signupMember(Member member);
}
