package com.fathy.jobportal.services;

import com.fathy.jobportal.entity.*;
import com.fathy.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity){
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter){
        List<IRecruiterJobs> recruiterJobsDtos = jobPostActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobsDto> recruiterJobsDtoList = new ArrayList<>();

        for (IRecruiterJobs recruiterJobs : recruiterJobsDtos){
            JobLocation location = new JobLocation(recruiterJobs.getLocationId(),
                    recruiterJobs.getCity(), recruiterJobs.getState(), recruiterJobs.getCountry());
            JobCompany company = new JobCompany(recruiterJobs.getCompanyId(),
                    recruiterJobs.getName(), "");
            recruiterJobsDtoList.add(new RecruiterJobsDto(recruiterJobs.getTotalCandidates(),
                    recruiterJobs.getJob_post_id(), recruiterJobs.getJob_title(), location, company));
        }
        return recruiterJobsDtoList;
    }

    public JobPostActivity getOne(int id) {

        return jobPostActivityRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();
    }

    public List<JobPostActivity> search(String job, String location,
                                        List<String> type,
                                        List<String> remote, LocalDate searchDate) {

        return Objects.isNull(searchDate)?jobPostActivityRepository.sesearchWithoutDate(job, location, remote, type):
                jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }
}
