package com.mars.dao;


import com.mars.entity.Renzhi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenzhiDao extends JpaRepository<Renzhi,String> {

    Renzhi findRenzhiByStudentid(String id);
}
