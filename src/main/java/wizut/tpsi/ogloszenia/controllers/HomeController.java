package wizut.tpsi.ogloszenia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.services.OffersService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public String home(Model model){
        List<CarManufacturer> carManufacturersList = offersService.getCarManufacturer();
        model.addAttribute("carManufacturersList", carManufacturersList);

        List<BodyStyle> bodyStylesList = offersService.getBodyStyles();
        model.addAttribute("bodyStylesList", bodyStylesList);

        List<FuelType> fuelTypesList = offersService.getFuelTypes();
        model.addAttribute("fuelTypesList", fuelTypesList);

        List<CarModel> carModelsList = offersService.getCarModels();
        model.addAttribute("carModelsList", carModelsList);

        return "offersList.html";
    }

}
