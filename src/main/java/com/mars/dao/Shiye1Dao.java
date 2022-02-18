package com.mars.dao;

import com.mars.entity.Shiye1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Shiye1Dao extends JpaRepository<Shiye1,String> {
    @Query(value = "select * from shiye1 where studentid=?1",nativeQuery = true)
    Shiye1 getShiyeByid(String id);
}
