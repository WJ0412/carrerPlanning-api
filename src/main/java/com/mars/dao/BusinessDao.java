package com.mars.dao;

import com.mars.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDao extends JpaRepository<Business,String> {
    Business findBusinessByStudentid(String id);
}
