package com.slowcampus.service;

import com.slowcampus.dto.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public void loginMember() {

    }

    @Override
    public void logoutMember() {

    }

    @Override
    @Transactional
    public int signupMember(Member member) {
        return 0;
    }
}
