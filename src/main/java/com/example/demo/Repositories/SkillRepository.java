package com.example.demo.Repositories;

import com.example.demo.Models.SkillsResume;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SkillRepository extends CrudRepository<SkillsResume,Long> {
    SkillsResume findById(Long id);
}
