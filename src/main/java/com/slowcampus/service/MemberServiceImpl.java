package com.slowcampus.service;

import com.slowcampus.dao.MemberDao;
import com.slowcampus.dto.Authority;
import com.slowcampus.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Member loginMember(Member member) {
        return memberDao.loginMember(member);
    }

    @Override
    public void logoutMember() {

    }

    @Override
    @Transactional
    public int signupMember(Member member) {
        return memberDao.signupMember(member);
    }

    @Override
    @Transactional
    public List<Authority> getMemberAuthority(Member member) {
        return memberDao.getMemberAuthority(member);
    }

    @Override
    @Transactional
    public int setMemberAuthority(Member member, Authority authority) {
        return memberDao.setMemberAuthority(member, authority);
    }

    @Override
    @Transactional
    public int deleteMemberAuthority(Member member, Authority authority) {
        return memberDao.deleteMemberAuthority(member, authority);
    }
}
