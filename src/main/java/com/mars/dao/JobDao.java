package com.mars.dao;

import com.mars.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDao extends JpaRepository<Job,String> {

    Job getJobByStudentid(String sid);
}
