package com.abc.spring.service;

import com.abc.spring.dao.MemberDAOImpl;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl {
    private MemberDAOImpl memberDAO;

    public void setMemberDAO(MemberDAOImpl memberDAO) {
        this.memberDAO = memberDAO;
    }

    public MemberDAOImpl getMemberDAO() {
        return memberDAO;
    }

    public MemberServiceImpl() {
        System.out.println("MemberServiceImpl construct");
    }

    public void add() {
        System.out.println("MemberServiceImpl add");
        memberDAO.add();
    }
}
