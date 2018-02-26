package com.example.demo.Repositories;

import com.example.demo.Models.JobSkills;
import org.springframework.data.repository.CrudRepository;

public interface JobSkillsRepository extends CrudRepository<JobSkills,Long>{

    Iterable<JobSkills> findAllByJobSkillNameContainingIgnoreCase(String search);

}
