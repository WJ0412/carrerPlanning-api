package com.mars.dao;

import com.mars.entity.Family;
import com.mars.entity.Other;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherDao extends JpaRepository<Other,String > {
    List<Other> findOthersByStudentid(String id);


    @Modifying
    @Query(value = "delete from other where studentid=?1",nativeQuery = true)
    void removeOtherBySid(String studentid);
}
