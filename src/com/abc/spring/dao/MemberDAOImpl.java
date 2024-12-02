package com.abc.spring.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl {
    public MemberDAOImpl() {
        System.out.println("构造器执行");
    }

    public void add() {
        System.out.println("add method");
    }
}
