package com.slowcampus.service;

import com.slowcampus.dto.Authority;
import com.slowcampus.dto.Member;

import java.util.List;

public interface MemberService {
    public Member loginMember(Member member);

    public void logoutMember();

    public int signupMember(Member member);

    public List<Authority> getMemberAuthority(Member member);

    public int setMemberAuthority(Member member, Authority authority);

    public int deleteMemberAuthority(Member member, Authority authority);
}
