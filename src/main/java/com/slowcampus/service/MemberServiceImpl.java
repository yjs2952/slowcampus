package com.slowcampus.service;

import com.slowcampus.dao.MemberDao;
import com.slowcampus.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;

    @Override
    public void loginMember() {

    }

    @Override
    public void logoutMember() {

    }

    @Override
    @Transactional
    public int signupMember(Member member) {
        int resultCount = 0;
        resultCount = memberDao.signupMember(member);

        return resultCount;
    }
}
