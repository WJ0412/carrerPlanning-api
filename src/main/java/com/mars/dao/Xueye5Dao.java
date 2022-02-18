package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye5Dao extends JpaRepository<Xueye5,String> {

    Xueye5 findByStudentid(String sid);


}
