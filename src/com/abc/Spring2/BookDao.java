package com.abc.Spring2;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends BaseDao<Book>{
    @Override
    public void save() {
        System.out.println("bookDao save");
    }
}
