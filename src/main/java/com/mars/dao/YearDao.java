package com.mars.dao;

import com.mars.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huangzhongwen
 */
@Repository
public interface YearDao extends JpaRepository<Year,String> {

}
