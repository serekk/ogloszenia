package wizut.tpsi.ogloszenia.services;

import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.*;
import wizut.tpsi.ogloszenia.web.OfferFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    public Offer createOffer(Offer offer) {
        entityManager.persist(offer);
        return offer;
    }

    public List<CarManufacturer> getCarManufacturer() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = entityManager.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }

    public List<BodyStyle> getBodyStyles() {
        String jpql = "select bs from BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = entityManager.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    public List<FuelType> getFuelTypes() {
        String jpql = "select ft from FuelType ft order by ft.name";
        TypedQuery<FuelType> query = entityManager.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels() {
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = entityManager.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels(int manufacturerId) {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";

        TypedQuery<CarModel> query = entityManager.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffers() {
        String jpql = "select off from Offer off order by off.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select off from Offer off where off.model.id  = :id order by off.title";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);

        return query.getResultList();
    }

    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        String jpql = "select off from Offer off where off.model.manufacturer.id  = :id order by off.title";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffer(int offerId) {
        String jpql = "select off from Offer off where off.id = :id";

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", offerId);
        query.getResultList().get(0);

        return query.getResultList();

    }

    public List<Integer> getYears() {
        String jpql = "select distinct off.year from Offer off";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> result = query.getResultList();


        return result;
    }

    public List<Offer> getOffers(OfferFilter filter) {
        String jpql = "select off from Offer off where ";
        boolean hasFilter = false;
        if (filter.getManufacturerId() != null) {
            jpql += "off.model.manufacturer.id = :manufId";
            hasFilter = true;
        }
        if (filter.getModelId() != null) {
            if (hasFilter) {
                jpql += " and ";
            }
            jpql += "off.model.id  = :modelId";
            hasFilter = true;
        }
        if (filter.getFuelTypeId() != null) {
            if (hasFilter) {
                jpql += " and ";
            }
            jpql += "off.fuelType.id = :fuelId";
            hasFilter = true;
        }
        if (filter.getYearMax() != null) {
            if (hasFilter) {
                jpql += " and ";
            }
            jpql += "off.year <= :yearMax";
            hasFilter = true;
        }
        if (filter.getYearMin() != null) {
            if (hasFilter) {
                jpql += " and ";
            }
            jpql += "off.year >= :yearMin";
            hasFilter = true;
        }

        if (filter.getDesc() != null) {
            if (hasFilter) {
                jpql += " and ";
            }
            jpql += "off.description like :desc";
            hasFilter = true;
        }


        if (!hasFilter) {
            jpql = "select off from Offer off order by off.title";
            TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
            List<Offer> result = query.getResultList();
            return query.getResultList();
        }

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        if (filter.getManufacturerId() != null) {
            query.setParameter("manufId", filter.getManufacturerId());
        }
        if (filter.getModelId() != null) {
            query.setParameter("modelId", filter.getModelId());
        }
        if (filter.getFuelTypeId() != null) {
            query.setParameter("fuelId", filter.getFuelTypeId());
        }
        if (filter.getYearMax() != null) {
            query.setParameter("yearMax", filter.getYearMax());
        }
        if (filter.getYearMin() != null) {
            query.setParameter("yearMin", filter.getYearMin());
        }

        if (filter.getDesc() != null) {
            query.setParameter("desc", "%" + filter.getDesc() + "%");
        }


        List<Offer> result = query.getResultList();
        return query.getResultList();
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = entityManager.find(Offer.class, id);
        entityManager.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return entityManager.merge(offer);
    }


    public CarModel getModel(int id) {
        return entityManager.find(CarModel.class, id);
    }

}
