package com.slowcampus.service;

import com.slowcampus.dto.Member;

public interface MemberService {
    public Member loginMember(Member member);

    public void logoutMember();

    public int signupMember(Member member);
}
