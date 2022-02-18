package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye2Dao extends JpaRepository<Xueye2,String> {
    Xueye2 findByStudentid(String sid);



}
