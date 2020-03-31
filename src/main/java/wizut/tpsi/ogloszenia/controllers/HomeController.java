package wizut.tpsi.ogloszenia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.*;
import wizut.tpsi.ogloszenia.services.OffersService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public String home(Model model) {
        List<CarManufacturer> carManufacturers = offersService.getCarManufacturer();
        List<CarModel> carModels = offersService.getCarModels();

        List<Offer> offers = offersService.getOffers();

        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id).get(0);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }
}
