package com.mars.dao;

import com.mars.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScoreDao extends JpaRepository<Score,String> {

    Score findByStudentid(String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen1=?1,inn1=?2,pro1=?3 where studentid=?4",nativeQuery = true)
    void addScore1(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen2=?1,inn2=?2,pro2=?3 where studentid=?4",nativeQuery = true)
    void addScore2(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen3=?1,inn3=?2,pro3=?3 where studentid=?4",nativeQuery = true)
    void addScore3(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen4=?1,inn4=?2,pro4=?3 where studentid=?4",nativeQuery = true)
    void addScore4(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen1=?5,inn5=?2,pro5=?3 where studentid=?4",nativeQuery = true)
    void addScore5(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen6=?1,inn6=?2,pro6=?3 where studentid=?4",nativeQuery = true)
    void addScore6(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen7=?1,inn7=?2,pro7=?3 where studentid=?4",nativeQuery = true)
    void addScore7(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "update score set gen8=?1,inn8=?2,pro8=?3 where studentid=?4",nativeQuery = true)
    void addScore8(String gen1, String inn1, String pro1,String sid);

    @Modifying
    @Transactional
    @Query(value = "insert into  score (studentid) values (?1) ",nativeQuery = true)
    void addStudent(String sid);
}
