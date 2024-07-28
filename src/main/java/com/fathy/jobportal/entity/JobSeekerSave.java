package com.fathy.jobportal.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId","jobPostActivity"})
})
public class JobSeekerSave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "user_account_id")
    private JobSeekerProfile userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobPostActivity", referencedColumnName = "jobPostId")
    private JobPostActivity jobPostActivity;

    public JobSeekerSave() {
    }

    public JobSeekerSave(Integer id, JobSeekerProfile userId,
                         JobPostActivity jobPostActivity) {
        this.id = id;
        this.userId = userId;
        this.jobPostActivity = jobPostActivity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobSeekerProfile getUserId() {
        return userId;
    }

    public void setUserId(JobSeekerProfile userId) {
        this.userId = userId;
    }

    public JobPostActivity getJobPostActivity() {
        return jobPostActivity;
    }

    public void setJobPostActivity(JobPostActivity jobPostActivity) {
        this.jobPostActivity = jobPostActivity;
    }

    @Override
    public String toString() {
        return "JobSeekerSave{" +
                "id=" + id +
                ", userId=" + userId +
                ", jobPostActivity=" + jobPostActivity +
                '}';
    }
}
