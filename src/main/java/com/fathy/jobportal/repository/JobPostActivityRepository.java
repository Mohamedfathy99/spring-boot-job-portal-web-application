package com.fathy.jobportal.repository;

import com.fathy.jobportal.entity.JobPostActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostActivityRepository extends JpaRepository<JobPostActivity, Integer> {

    List<IRecruiterJobs> getRecruiterJobs(@Param("recruiter") int recruiter);
}
