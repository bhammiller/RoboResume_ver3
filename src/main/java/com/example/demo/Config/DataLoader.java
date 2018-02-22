package com.example.demo.Config;



import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{



    @Autowired
    AppRoleRepository appRoleRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data ...");
        AppRole role = new AppRole();
        role.setRoleName("APPLICANT");
        appRoleRepository.save(role);

        role = new AppRole();
        role.setRoleName("EMPLOYER");
        appRoleRepository.save(role);

        role = new AppRole();
        role.setRoleName("RECRUITERS");
        appRoleRepository.save(role);
    }

}