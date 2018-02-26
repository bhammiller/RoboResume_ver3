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

    @Autowired
    SiteApplicantsRepository siteApplicantsRepository;


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

        // Users /////////////////////////////////////////////////////////////

        // User 1
        AppUser user = new AppUser();
        user.setAppUsername("John");
        user.setAppPassword("password1");
        user.setFullName("John Doe");
        user.setUserEmail("g1@gmail.com");
        user.setUserType("applicant");
        user.addRole(appRoleRepository.findAppRoleByRoleName("APPLICANT"));
        appUserRepository.save(user);
        // User 2
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
        user.addRole(appRoleRepository.findAppRoleByRoleName("APPLICANT"));
        appUserRepository.save(user);

        // User 4
        user = new AppUser();
        user.setAppUsername("Brandon");
        user.setAppPassword("password");
        user.setFullName("Brandon H");
        user.setUserEmail("b@gmail.com");
        user.setUserType("recruiter");
        user.addRole(appRoleRepository.findAppRoleByRoleName("RECRUITER"));
        appUserRepository.save(user);

        // User 5
        user = new AppUser();
        user.setAppUsername("Jane");
        user.setAppPassword("password4");
        user.setFullName("Jane Pane");
        user.setUserEmail("g4@gmail.com");
        user.setUserType("applicant");
        user.addRole(appRoleRepository.findAppRoleByRoleName("APPLICANT"));
        appUserRepository.save(user);

        // Applicants //////////////////////////////////////////////////////////////////////

        // Applicant 1
        SiteApplicants siteApplicants1 = new SiteApplicants();
        siteApplicants1.addCredentials(appUserRepository.findOne(new Long(1)));
        siteApplicantsRepository.save(siteApplicants1);

        // Applicant 2
        SiteApplicants siteApplicants2= new SiteApplicants();
        siteApplicants2.addCredentials(appUserRepository.findOne(new Long(3)));
        siteApplicantsRepository.save(siteApplicants2);

        // Applicant 3
        SiteApplicants siteApplicants3= new SiteApplicants();
        siteApplicants3.addCredentials(appUserRepository.findOne(new Long(3)));
        siteApplicantsRepository.save(siteApplicants3);

        // Contact Information /////////////////////////////////////////////////////////////

        // Applicant 1
        WholeResume wholeResume = new WholeResume();
        wholeResume.setEmail("g1@gmail.com");
        wholeResume.setName("John Doe");
        wholeResume.setYouimage("");
        siteApplicants1.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants1);

        // Applicant 2
        wholeResume = new WholeResume();
        wholeResume.setEmail("g3@gmail.com");
        wholeResume.setName("Joe Blow");
        wholeResume.setYouimage("");
        siteApplicants2.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants2);

        // Applicant 3
        wholeResume = new WholeResume();
        wholeResume.setEmail("g4@gmail.com");
        wholeResume.setName("Jane Pane");
        wholeResume.setYouimage("");
        siteApplicants2.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants3);

        // Education /////////////////////////////////////////////////////////////////////////
        // Applicant 1
        EducationResume educationResume=new EducationResume();
        educationResume.getDegreesubject();
        educationResume.getDegreetype();
        educationResume.getDegreeyear();
        educationResume.getSchoolplace();
        siteApplicants1.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants1);

        // Applicant 2
        educationResume=new EducationResume();
        educationResume.getDegreesubject();
        educationResume.getDegreetype();
        educationResume.getDegreeyear();
        educationResume.getSchoolplace();
        siteApplicants2.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants2);

        // Applicant 3

        // Experience ////////////////////////////////////////////////////////////////////////
        // Applicant 1
        ExpResume expResume= new ExpResume();
        expResume.getJobtitle();
        expResume.getEmployer();
        expResume.getYearsstart();
        expResume.getYearsend();
        expResume.getDuties();
        siteApplicants1.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants1);

        // Applicant 2
        expResume= new ExpResume();
        expResume.getJobtitle();
        expResume.getEmployer();
        expResume.getYearsstart();
        expResume.getYearsend();
        expResume.getDuties();
        siteApplicants1.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants1);

        // Resume Skills /////////////////////////////////////////////////////////////////////
        // Applicant 1

        // Applicant 2

        // Cover Letter //////////////////////////////////////////////////////////////////////
        // Applicant 1

        // Applicant 2

        // Summary ///////////////////////////////////////////////////////////////////////////
        // Applicant 1

        // Applicant 2

        // References ////////////////////////////////////////////////////////////////////////
        // Applicant 1

        // Applicant 2

        //////////////////////////////////////////////////////////////////////////////////////
        // Jobs and Skills ///////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////
        // Organizations /////////////////////////////////////////////////////////////////////

        // Jobs //////////////////////////////////////////////////////////////////////////////

        // Job Skills ////////////////////////////////////////////////////////////////////////
















    }

}