package wizut.tpsi.ogloszenia.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer {

    private Integer id;

    private String title;

    private Integer year;

    private Integer mileage;

    private BigDecimal engineSize;

    private Integer enginePower;

    private Integer doors;

    private String colour;

    private String description;

    private Integer price;

    private CarModel model;

    private BodyStyle bodyStyle;

    private FuelType fuelType;


}
