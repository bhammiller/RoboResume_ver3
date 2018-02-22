package com.example.demo.Repositories;

import com.example.demo.Models.WholeResume;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WholeResumeRepository extends CrudRepository<WholeResume,Long>{
    WholeResume findById(Long id);
}
