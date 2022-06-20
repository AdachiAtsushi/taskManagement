package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MstStatus;

@Repository
public interface MstStatusRepository extends JpaRepository<MstStatus, String> {

}
