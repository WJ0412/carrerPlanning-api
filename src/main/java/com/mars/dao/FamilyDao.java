package com.mars.dao;

import com.mars.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FamilyDao extends JpaRepository<Family,String > {
    List<Family> findFamiliesByStudentid(String id);

    @Modifying
    @Query(value = "delete from family where studentid=?1",nativeQuery = true)
    void removeByStuId(String studentid);
}
