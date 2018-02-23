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

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExpRepository expRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    WholeResumeRepository wholeResumeRepository;

    @Autowired
    ResumeReferenceRepository refrepository;


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
        role.setRoleName("RECRUITER");
        appRoleRepository.save(role);

        // Users
        // User 1
        AppUser user = new AppUser();
        user.setAppUsername("Brandon");
        user.setAppPassword("password");
        user.setFullName("John Doe");
        user.setUserEmail("g1@gmail.com");
        user.setUserType("recruiter");
        user.addRole(appRoleRepository.findAppRoleByRoleName("RECRUITER"));
        appUserRepository.save(user);
        /*// User 2
        user = new AppUser();
        user.setAppUsername("Jacob");
        user.setAppPassword("password2");
        user.setFullName("Jacob Smith");
        user.setUserEmail("g2@gmail.com");
        user.addRole(appRoleRepository.findAppRoleByRoleName("EMPLOYER"));
        appUserRepository.save(user);
        // User 3
        user = new AppUser();
        user.setAppUsername("Joe");
        user.setAppPassword("password3");
        user.setFullName("Joe Blow");
        user.setUserEmail("g3@gmail.com");
        user.addRole(appRoleRepository.findAppRoleByRoleName("RECRUITER"));
        appUserRepository.save(user);*/

        /*// Applicants
        SiteApplicants siteApplicants = new SiteApplicants();
        siteApplicants.addCredentials(appUserRepository.findOne(new Long(1)));
        siteApplicants.addWhole(wholeResumeRepository.findById(new Long (1)));
        siteApplicants.addEducation(educationRepository);*/
    }

}