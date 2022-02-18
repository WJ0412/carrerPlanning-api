package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye3Dao extends JpaRepository<Xueye3,String> {
    Xueye3 findByStudentid(String sid);



}
