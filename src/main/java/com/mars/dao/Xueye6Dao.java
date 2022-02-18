package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye6Dao extends JpaRepository<Xueye6,String> {


    Xueye6 findByStudentid(String sid);

}
