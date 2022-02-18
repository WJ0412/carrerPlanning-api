package com.mars.dao;

import com.mars.entity.Xueye1;
import com.mars.entity.Xueye8;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Xueye8Dao extends JpaRepository<Xueye8, String> {

    Xueye8 findByStudentid(String sid);


}
