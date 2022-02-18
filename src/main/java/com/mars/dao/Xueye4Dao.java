package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye4Dao extends JpaRepository<Xueye4,String> {



    Xueye4 findByStudentid(String sid);


}
