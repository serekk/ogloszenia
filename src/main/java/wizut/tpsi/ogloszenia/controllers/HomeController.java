package wizut.tpsi.ogloszenia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.services.OffersService;

@Controller
public class HomeController {

    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public String home(Model model){
        CarManufacturer carManufacturer = offersService.getCarManufacturer(2);
        CarModel carModel = offersService.getModel(3);

        model.addAttribute("carManufacturer", carManufacturer.getName());
        model.addAttribute("carModel", carMfodel.getManufacturer().getName());
        return "index.html";
    }

}
