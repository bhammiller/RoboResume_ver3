package com.example.demo;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import com.example.demo.Config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

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
    AppUserRepository appUserRepository;
    @Autowired
    SiteApplicantsRepository siteApplicantsRepository;
    @Autowired
    CoverLetterRepository coverLetterRepository;
    @Autowired
    SummaryResumeRepository summaryResumeRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    SiteOrganizationsRepository siteOrganizationsRepository;
    @Autowired
    SiteJobsRepository siteJobsRepository;
    @Autowired
    JobSkillsRepository jobSkillsRepository;

    SiteApplicants siteApplicants;

    SiteJobs sitejobs;

    SiteOrganizations siteOrganizations;

    public WholeResume wholeResumeM =new WholeResume();


    // Security Methods
    @RequestMapping("/login")
    public String login(){
        return "login";
    }



    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newUser",new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String addNewUser(@Valid @ModelAttribute("NewUser") AppUser newUser,  BindingResult result, Model model)
    {

        if(result.hasErrors())
        {
            System.out.println(result.toString());
            return "registration";
        }
        else{

            if(newUser.getUserType().equals("applicant")) {
                //Create a new ordinary user
                model.addAttribute(newUser.getAppUsername() + " created");
                appUserRepository.save(newUser);
                AppRole r = appRoleRepository.findAppRoleByRoleName("APPLICANT");
                newUser.addRole(r);
                appUserRepository.save(newUser);
                siteApplicants = new SiteApplicants();
                siteApplicants.addCredentials(newUser);
                siteApplicantsRepository.save(siteApplicants);
            }else if(newUser.getUserType().equals("employer")){
                model.addAttribute(newUser.getAppUsername() + " created");
                appUserRepository.save(newUser);
                AppRole r = appRoleRepository.findAppRoleByRoleName("EMPLOYER");
                newUser.addRole(r);
                appUserRepository.save(newUser);
            }else if(newUser.getUserType().equals("recruiter")){
                model.addAttribute(newUser.getAppUsername() + " created");
                appUserRepository.save(newUser);
                AppRole r = appRoleRepository.findAppRoleByRoleName("RECRUITER");
                newUser.addRole(r);
                appUserRepository.save(newUser);
            }
            return "redirect:/";
        }
    }


    // Homepage Methods
    @GetMapping("/")
    public String resumeStarter(){
        return "homepage";
    }


    // Contact Information Methods
    @GetMapping("/contact")
    public String startContact(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("whole", siteApplicants.getWholeResumeList());
        model.addAttribute("addwhole", new WholeResume());
        return "resumebuilder";
    }

    @PostMapping("/process")
    public String processBegin(@Valid @ModelAttribute("addwhole") WholeResume wholeResume,
                               BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "resumebuilder";
        }
        wholeResumeRepository.save(wholeResume);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addWhole(wholeResume);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/contact";
    }

    @RequestMapping("/updatecontact/{id}")
    public String updateContact(@PathVariable("id") long id, Model model , Authentication authentication) {
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("whole", siteApplicants.getWholeResumeList());
        model.addAttribute("addwhole", wholeResumeRepository.findOne(id));
        return "resumebuilder";
    }


    // Education Methods
    @GetMapping("/addeducation")
    public String educationForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("education", siteApplicants.getEducationResumeList());
        model.addAttribute("addeducation", new EducationResume());
        return "inputeducation";
    }

    @PostMapping("/addeducation")
    public String postedEducation(@Valid @ModelAttribute("addeducation") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("education", siteApplicants.getEducationResumeList());
        model.addAttribute("addeducation", new EducationResume());
        return "inputeducation";
    }

    @PostMapping("/processeducation")
    public String processEducation(@Valid @ModelAttribute("addeducation") EducationResume educationResume,
                                   BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "inputeducation";
        }
        educationRepository.save(educationResume);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addEducation(educationResume);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addeducation";
    }

    @RequestMapping("/updateeducation/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("education", siteApplicants.getEducationResumeList());
        model.addAttribute("addeducation", educationRepository.findOne(id));
        return "inputeducation";
    }

    /*@RequestMapping("/deleteeducation/{id}")
    public String delEducation(@PathVariable("id") long id){
        educationRepository.delete(id);
        return "redirect:/addeducation";
    }*/

    // Experience Methods
    @GetMapping("/addexp")
    public String expForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("experience", siteApplicants.getExpResumeList());
        model.addAttribute("addexp", new ExpResume());
        return "inputexp";
    }

    @PostMapping("/addexp")
    public String postedExp(@Valid @ModelAttribute("addexp") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("experience", siteApplicants.getExpResumeList());
        model.addAttribute("addexp", new ExpResume());
        return "inputexp";
    }

    @PostMapping("/processexp")
    public String processExp(@Valid @ModelAttribute("addexp") ExpResume expresume,
                             BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "inputexp";
        }
        expRepository.save(expresume);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addExperience(expresume);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addexp";
    }

    @RequestMapping("/updateexp/{id}")
    public String updateExp(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("experience", siteApplicants.getExpResumeList());
        model.addAttribute("addexp", expRepository.findOne(id));
        return "inputexp";
    }

   /* @RequestMapping("/deleteexp/{id}")
    public String delExp(@PathVariable("id") long id){
        expRepository.delete(id);
        return "redirect:/addexp";
    }*/

    // Skills Methods
    @GetMapping("/addskills")
    public String skillsForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("skills", siteApplicants.getSkillsResumeList());
        model.addAttribute("addskill", new SkillsResume());
        return "inputskills";
    }

    @PostMapping("/addskills")
    public String postedSkills(@Valid @ModelAttribute("addskill") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("skills", siteApplicants.getSkillsResumeList());
        model.addAttribute("addskill", new SkillsResume());
        return "inputskills";
    }

    @PostMapping("/processskill")
    public String processSkills(@Valid @ModelAttribute("addskill") SkillsResume skillsResume,
                                BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "inputskills";
        }
        skillRepository.save(skillsResume);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addSkills(skillsResume);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addskills";
    }

    @RequestMapping("/updateskill/{id}" )
    public String updateSkill(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("skills", siteApplicants.getSkillsResumeList());
        model.addAttribute("addskills", skillRepository.findOne(id));
        return "inputskills";
    }

    /*@RequestMapping("/deleteskill/{id}")
    public String delSkill(@PathVariable("id") long id){
        skillRepository.delete(id);
        return "redirect:/addskills";
    }*/

    // References Methods
    @GetMapping("/addreferals")
    public String refForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("referes", siteApplicants.getResumeReferencesList());
        model.addAttribute("addreferals", new ResumeReferences());
        return "referalpage";
    }

    @PostMapping("/addreferals")
    public String postedRef(@Valid @ModelAttribute("addreferals") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("referes", siteApplicants.getResumeReferencesList());
        model.addAttribute("addreferals", new ResumeReferences());
        return "referalpage";
    }

    @PostMapping("/processreferals")
    public String processref(@Valid @ModelAttribute("addreferals") ResumeReferences references,
                             BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "referalpage";
        }
        refrepository.save(references);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addReferances(references);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addreferals";
    }

    @RequestMapping("/updateref/{id}")
    public String updateRef(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("referes", siteApplicants.getResumeReferencesList());
        model.addAttribute("addreferals", refrepository.findOne(id));
        return "referalpage";
    }

    /*@RequestMapping("/deleteref/{id}")
    public String delRef(@PathVariable("id") long id){
        refrepository.delete(id);
        return "redirect:/addreferals";
    }*/


    // Cover Letter Methods
    @GetMapping("/addcover")
    public String coverForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("covery", siteApplicants.getCoverLetterList());
        model.addAttribute("addcover", new CoverLetter());
        return "coverletter";
    }

    @PostMapping("/addcover")
    public String postedCover(@Valid @ModelAttribute("addcover") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("covery", siteApplicants.getCoverLetterList());
        model.addAttribute("addcover", new CoverLetter());
        return "coverletter";
    }

    @PostMapping("/processcover")
    public String processCover(@Valid @ModelAttribute("addcover") CoverLetter coverLetter,
                               BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "coverletter";
        }
        coverLetterRepository.save(coverLetter);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addCover(coverLetter);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addcover";
    }

    @RequestMapping("/updatecover/{id}")
    public String updateCover(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("covery", siteApplicants.getCoverLetterList());
        model.addAttribute("addcover", coverLetterRepository.findOne(id));
        return "coverletter";
    }

    /*@RequestMapping("/deletecover/{id}")
    public String delCover(@PathVariable("id") long id){
        wholeResumeRepository.delete(id);
        return "redirect:/addcover";
    }*/


    // Summary Methods
    @GetMapping("/addsummary")
    public String summaryForm(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("summery", siteApplicants.getSummaryResumeList());
        model.addAttribute("addsummary", new SummaryResume());
        return "summary";
    }

    @PostMapping("/addsummary")
    public String postedSummary(@Valid @ModelAttribute("addsummary") Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("summery", siteApplicants.getSummaryResumeList());
        model.addAttribute("addsummary", new SummaryResume());
        return "summary";
    }

    @PostMapping("/processsummary")
    public String processSummary(@Valid @ModelAttribute("addsummary") SummaryResume summaryResume,
                                 BindingResult result, Authentication authentication){
        if (result.hasErrors()){
            return "summary";
        }
        summaryResumeRepository.save(summaryResume);
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        siteApplicants.addSummary(summaryResume);
        siteApplicantsRepository.save(siteApplicants);
        return "redirect:/addsummary";
    }

    @RequestMapping("/updatesummary/{id}")
    public String updateSummary(@PathVariable("id") long id, Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model.addAttribute("summery", siteApplicants.getSummaryResumeList());
        model.addAttribute("addsummary", summaryResumeRepository.findOne(id));
        return "summary";
    }

   /* @RequestMapping("/deletesummary/{id}")
    public String delSummary(@PathVariable("id") long id){
        wholeResumeRepository.delete(id);
        return "redirect:/addsummary";
    }*/

    // Jobs Methods
    @RequestMapping("/joblist")
    public String listJobs(Model model){
        model.addAttribute("orglist",siteOrganizationsRepository.findAll());
        return "joblist";
    }

    @GetMapping("/addjob")
    public String jobForm(Model model){
        model.addAttribute("orgdetail", siteOrganizations);
        model.addAttribute("addjob", new SiteJobs());
        return "organizationdetails";
    }

    @PostMapping("/addjob")
    public String postedJob(@Valid @ModelAttribute("addjob") Model model){
        model.addAttribute("orgdetail", siteOrganizations);
        model.addAttribute("addjob", new SiteJobs());
        return "organizationdetails";
    }

    @PostMapping("/processjob")
    public String processJob(@Valid @ModelAttribute("addjob") SiteJobs siteJobs1,
                               BindingResult result){
        if (result.hasErrors()){
            return "organization";
        }
        siteJobsRepository.save(siteJobs1);
        siteOrganizations.addSiteJobs(siteJobs1);
        siteOrganizationsRepository.save(siteOrganizations);
        return "redirect:/addjob";

    }

    @RequestMapping("/jobdetail/{id}")
    public String jobDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("siteJobs", siteJobsRepository.findOne(id));
        sitejobs=siteJobsRepository.findOne(id);
        return "redirect:/addjobskill";
    }

    // JobSkills Methods
    @GetMapping("/addjobskill")
    public String jobskillForm(Model model){
        model.addAttribute("siteJobs", sitejobs);
        model.addAttribute("addjobskill", new JobSkills());
        return "jobdetails";

    }

    @PostMapping("/addjobskill")
    public String postedJobskill(@Valid @ModelAttribute("addjobskill") Model model){
        model.addAttribute("siteJobs", sitejobs);
        model.addAttribute("addjobskill", new JobSkills());
        return "jobdetails";

    }

    @PostMapping("/processjobskill")
    public String processJobskill(@Valid @ModelAttribute("addjobskill") JobSkills jobSkills,
                             BindingResult result){
        if (result.hasErrors()){
            return "jobdetails";
        }
        jobSkillsRepository.save(jobSkills);
        sitejobs.addJobSkills(jobSkills);
        siteJobsRepository.save(sitejobs);
        return "redirect:/addjobskill";

    }


    // Organization Methods
    @GetMapping("/addorg")
    public String orgForm(Model model){
        model.addAttribute("orglist", siteOrganizationsRepository.findAll());
        model.addAttribute("addorg",new SiteOrganizations());
        return "organizationlist";
    }

    @PostMapping("/addorg")
    public String postedOrg(@Valid @ModelAttribute("addorg") Model model){
        model.addAttribute("orglist", siteOrganizationsRepository.findAll());
        model.addAttribute("addorg",new SiteOrganizations());
        return "organizationlist";
    }

    @PostMapping("/processorg")
    public String processOrg(@Valid @ModelAttribute("addorg") SiteOrganizations siteOrganizations,
                             BindingResult result){
        if (result.hasErrors()){
            return "organizationlist";
        }
        siteOrganizationsRepository.save(siteOrganizations);
        return "redirect:/addorg";

    }

    @RequestMapping("/orgdetail/{id}")
    public String orgDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("orgdetail", siteOrganizationsRepository.findOne(id));
        siteOrganizations=siteOrganizationsRepository.findOne(id);
        return "redirect:/addjob";
    }


    // Completed Resume
    @RequestMapping("/complete")
    public String listAddresses(Model model1, Model model2, Model model3, Model model4, Model model5 , Authentication authentication){
        AppUser user = appUserRepository.findAppUserByAppUsername(authentication.getName());
        siteApplicants= siteApplicantsRepository.findByAppUserListContaining(user);
        model1.addAttribute("whole", siteApplicants.getWholeResumeList());
        model2.addAttribute("education", siteApplicants.getEducationResumeList());
        model3.addAttribute("experience", siteApplicants.getExpResumeList());
        model4.addAttribute("skills", siteApplicants.getSkillsResumeList());
        model5.addAttribute("summery",siteApplicants.getSummaryResumeList());
        return "resumeouput";
    }
    // Shows all Applicants Resume
    @RequestMapping("/rlist")
    public String listResumes(Model model){
        model.addAttribute("applicants", siteApplicantsRepository.findAll());
        return "resumelist";
    }


    // Search Methods
    @PostMapping("/searchorg")
    public String showSearchOrgResults(HttpServletRequest request, Model model)
    {
        //Get the search string from the result form
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("orglist",siteOrganizationsRepository.findAllByOrganizationNameContainingIgnoreCase(searchString));
        return "joblist";
    }


}
