package com.abc.Spring2;

import org.springframework.stereotype.Repository;

@Repository
public class PhoneDao extends BaseDao<Phone>{
    @Override
    public void save() {
        System.out.println("phone dao");
    }
}
