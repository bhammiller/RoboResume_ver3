package com.example.demo.Repositories;

import com.example.demo.Models.AppUser;
import com.example.demo.Models.SiteApplicants;
import org.springframework.data.repository.CrudRepository;

public interface SiteApplicantsRepository extends CrudRepository<SiteApplicants,Long> {
    SiteApplicants findById(Long id);
    SiteApplicants findByAppUserListContaining(AppUser username);
}
