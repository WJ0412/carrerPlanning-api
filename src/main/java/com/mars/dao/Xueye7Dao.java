package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye7Dao extends JpaRepository<Xueye7,String> {

    Xueye7 findByStudentid(String sid);

}
