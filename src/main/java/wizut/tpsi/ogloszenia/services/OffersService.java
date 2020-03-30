package wizut.tpsi.ogloszenia.services;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    public CarManufacturer getCarManufacturer(int id){
        return entityManager.find(CarManufacturer.class, id);
    }

    public CarModel getModel(int id){
        return entityManager.find(CarModel.class, id);
    }

}
