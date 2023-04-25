package at.htlstp.spacehistory.presentation;


import at.htlstp.spacehistory.domain.Company;
import at.htlstp.spacehistory.persistence.RocketRepository;
import at.htlstp.spacehistory.domain.Launch;
import at.htlstp.spacehistory.domain.Rocket;
import at.htlstp.spacehistory.persistence.CompanyRepository;
import at.htlstp.spacehistory.persistence.LaunchRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("")
public record Controller(LaunchRepository launchRepository, CompanyRepository companyRepository, RocketRepository rocketRepository) {

    @GetMapping("all")
    public String getAllLaunches(Model model) {
        model.addAttribute("allLaunches", launchRepository.findAllOrderByDateDateAsc());
        System.out.println("launchRepository.findAllOrderByDateDateAsc()");
        System.out.println(launchRepository.findAllOrderByDateDateAsc());
        return "all";
    }

    @GetMapping("launches/{id}")
    public String getAllCompany(@PathVariable String id, Model model) {
        model.addAttribute("comp", id);
        model.addAttribute("allLaunchesOfComp", launchRepository.findAllCompanyNameOrderByDateDateAsc(id));

        return "allCompany";
    }
    @GetMapping("add")
    public String getNewLaunch(Model model) {
        Company company= new Company();
        Rocket rocket= new Rocket();
        rocket.setCompanyName(company);
        Launch launch = new Launch();
        launch.setRocket(rocket);
        model.addAttribute("company",company);
        model.addAttribute("rocket", rocket);
        model.addAttribute("launch", launch);

        model.addAttribute("companies",companyRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String addNewLaunch(@ModelAttribute("model") Launch launch, @ModelAttribute("rocket")  Rocket rocket,  @ModelAttribute("company")  Company company,BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){

            model.addAttribute("launch", new Launch());
            model.addAttribute("rocket", new Rocket());
            model.addAttribute("company", new Company());
            model.addAttribute("companies",companyRepository.findAll());
            return getNewLaunch(model);
        }
        var savedComp=companyRepository.save(company);
        rocket.setCompanyName(savedComp);
        var savedRocket= rocketRepository.save(rocket);
        launch.setRocket(savedRocket);
        var savedLaunch = launchRepository.save(launch);

        return "redirect:/all";
    }
}
