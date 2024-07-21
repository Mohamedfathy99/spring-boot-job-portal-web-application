package com.fathy.jobportal.services;

import com.fathy.jobportal.entity.*;
import com.fathy.jobportal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
