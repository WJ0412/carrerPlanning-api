package com.mars.dao;

import com.mars.entity.Shiye1;
import com.mars.entity.Shiye2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Shiye2Dao extends JpaRepository<Shiye2,String> {
    @Query(value = "select * from shiye2 where studentid=?1",nativeQuery = true)
    Shiye2 getShiyeByid(String id);
}
