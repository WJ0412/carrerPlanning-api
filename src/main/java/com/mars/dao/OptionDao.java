package com.mars.dao;

import com.mars.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionDao extends JpaRepository<Options,String> {

    @Query(value = "select * from options where bh_name=?1",nativeQuery = true)
    List<Options> findNameByBh_name(String bh_name);
}
