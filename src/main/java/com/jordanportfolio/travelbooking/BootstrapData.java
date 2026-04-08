package com.jordanportfolio.travelbooking;

import com.jordanportfolio.travelbooking.dao.CountryRepository;
import com.jordanportfolio.travelbooking.dao.CustomerRepository;
import com.jordanportfolio.travelbooking.dao.DivisionRepository;
import com.jordanportfolio.travelbooking.dao.ExcursionRepository;
import com.jordanportfolio.travelbooking.dao.VacationRepository;
import com.jordanportfolio.travelbooking.entities.Country;
import com.jordanportfolio.travelbooking.entities.Customer;
import com.jordanportfolio.travelbooking.entities.Division;
import com.jordanportfolio.travelbooking.entities.Excursion;
import com.jordanportfolio.travelbooking.entities.Vacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("!test")
public class BootstrapData implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(BootstrapData.class);

    private final CountryRepository countryRepository;
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;
    private final VacationRepository vacationRepository;
    private final ExcursionRepository excursionRepository;

    public BootstrapData(CountryRepository countryRepository,
                         CustomerRepository customerRepository,
                         DivisionRepository divisionRepository,
                         VacationRepository vacationRepository,
                         ExcursionRepository excursionRepository) {
        this.countryRepository = countryRepository;
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
        this.vacationRepository = vacationRepository;
        this.excursionRepository = excursionRepository;
    }

    @Override
    public void run(String... args) {
        seedGeography();
        seedCatalog();
        seedCustomers();
    }

    private void seedGeography() {
        if (countryRepository.count() == 0) {
            countryRepository.save(createCountry("United States"));
            countryRepository.save(createCountry("Canada"));
            countryRepository.save(createCountry("Mexico"));
            log.info("Seeded default countries for local development.");
        }

        if (divisionRepository.count() > 0) {
            return;
        }

        List<Country> countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        if (countries.size() < 3) {
            log.warn("Skipping division seed because fewer than three countries are available.");
            return;
        }

        Country usa = countries.get(0);
        Country canada = countries.get(1);
        Country mexico = countries.get(2);

        divisionRepository.saveAll(List.of(
                createDivision("Kansas", usa),
                createDivision("Missouri", usa),
                createDivision("Colorado", usa),
                createDivision("Ontario", canada),
                createDivision("British Columbia", canada),
                createDivision("Jalisco", mexico)
        ));

        log.info("Seeded default divisions for local development.");
    }

    private void seedCatalog() {
        if (vacationRepository.count() > 0 || excursionRepository.count() > 0) {
            return;
        }

        Vacation hawaii = vacationRepository.save(createVacation(
                "Hawaiian Escape",
                "Seven-day island itinerary with beachfront stays and guided snorkeling.",
                "1899.00",
                "https://images.unsplash.com/photo-1507525428034-b723cf961d3e"
        ));
        Vacation banff = vacationRepository.save(createVacation(
                "Banff Adventure",
                "Mountain lodge package with lake tours and hiking excursions.",
                "1599.00",
                "https://images.unsplash.com/photo-1510798831971-661eb04b3739"
        ));
        Vacation yucatan = vacationRepository.save(createVacation(
                "Yucatan Discovery",
                "Beach resort stay paired with cenote visits and cultural day trips.",
                "1399.00",
                "https://images.unsplash.com/photo-1519046904884-53103b34b206"
        ));

        excursionRepository.saveAll(List.of(
                createExcursion("Sunset Catamaran", "149.00", hawaii,
                        "https://images.unsplash.com/photo-1500375592092-40eb2168fd21"),
                createExcursion("Volcano Hike", "129.00", hawaii,
                        "https://images.unsplash.com/photo-1469474968028-56623f02e42e"),
                createExcursion("Lake Louise Canoe Tour", "119.00", banff,
                        "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee"),
                createExcursion("Gondola Summit Pass", "89.00", banff,
                        "https://images.unsplash.com/photo-1501785888041-af3ef285b470"),
                createExcursion("Cenote Swim Experience", "99.00", yucatan,
                        "https://images.unsplash.com/photo-1506744038136-46273834b3fb"),
                createExcursion("Mayan Ruins Day Trip", "139.00", yucatan,
                        "https://images.unsplash.com/photo-1526772662000-3f88f10405ff")
        ));

        log.info("Seeded default vacations and excursions for local development.");
    }

    private void seedCustomers() {
        // fetch 5 divisions that actually exist in deterministic order
        var divisions = divisionRepository.findTop5ByOrderByIdAsc();

        if (divisions.size() < 5) {
            log.warn("Skipping customer seed because fewer than five divisions are available.");
            return;
        }

        Division d1 = divisions.get(0);
        Division d2 = divisions.get(1);
        Division d3 = divisions.get(2);
        Division d4 = divisions.get(3);
        Division d5 = divisions.get(4);

        Customer c1 = new Customer();
        c1.setFirstName("Jane");
        c1.setLastName("Smith");
        c1.setAddress("456 Oak Ave");
        c1.setPostal_code("66210");
        c1.setPhone("(913)111-2222");
        c1.setDivision(d1);

        Customer c2 = new Customer();
        c2.setFirstName("Alex");
        c2.setLastName("Johnson");
        c2.setAddress("789 Pine Rd");
        c2.setPostal_code("64111");
        c2.setPhone("(816)222-3333");
        c2.setDivision(d2);

        Customer c3 = new Customer();
        c3.setFirstName("Maria");
        c3.setLastName("Garcia");
        c3.setAddress("101 Maple Dr");
        c3.setPostal_code("66062");
        c3.setPhone("(913)333-4444");
        c3.setDivision(d3);

        Customer c4 = new Customer();
        c4.setFirstName("Chris");
        c4.setLastName("Lee");
        c4.setAddress("202 Cedar Ln");
        c4.setPostal_code("64108");
        c4.setPhone("(816)444-5555");
        c4.setDivision(d4);

        Customer c5 = new Customer();
        c5.setFirstName("Sam");
        c5.setLastName("Patel");
        c5.setAddress("303 Birch Blvd");
        c5.setPostal_code("64030");
        c5.setPhone("(816)555-0101");
        c5.setDivision(d5);

        if (!customerRepository.existsByPhone("(913)111-2222")) {
            customerRepository.save(c1);
        }

        if (!customerRepository.existsByPhone("(816)222-3333")) {
            customerRepository.save(c2);
        }

        if (!customerRepository.existsByPhone("(913)333-4444")) {
            customerRepository.save(c3);
        }

        if (!customerRepository.existsByPhone("(816)444-5555")) {
            customerRepository.save(c4);
        }

        if (!customerRepository.existsByPhone("(816)555-0101")) {
            customerRepository.save(c5);
        }

        log.info("Verified seeded customer records for checkout flow.");
    }

    private Country createCountry(String countryName) {
        Country country = new Country();
        country.setCountry_name(countryName);
        return country;
    }

    private Division createDivision(String divisionName, Country country) {
        Division division = new Division();
        division.setDivision_name(divisionName);
        division.setCountry(country);
        return division;
    }

    private Vacation createVacation(String title, String description, String travelPrice, String imageUrl) {
        Vacation vacation = new Vacation();
        vacation.setVacation_title(title);
        vacation.setDescription(description);
        vacation.setTravelPrice(new BigDecimal(travelPrice));
        vacation.setImageURL(imageUrl);
        return vacation;
    }

    private Excursion createExcursion(String title, String excursionPrice, Vacation vacation, String imageUrl) {
        Excursion excursion = new Excursion();
        excursion.setExcursion_title(title);
        excursion.setExcursion_price(new BigDecimal(excursionPrice));
        excursion.setVacation(vacation);
        excursion.setImageURL(imageUrl);
        return excursion;
    }
}
