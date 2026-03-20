package com.krishnasai.jobportal.repository;

import com.krishnasai.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByLocation(String location);

    List<Job> findByTitleContaining(String keyword);
}
