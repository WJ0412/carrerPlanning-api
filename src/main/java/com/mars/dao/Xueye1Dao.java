package com.mars.dao;

import com.mars.entity.Xueye1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Xueye1Dao extends JpaRepository<Xueye1,String> {
    Xueye1 findByStudentid(String sid);

}
