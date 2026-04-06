package com.d288.jordan;

import com.d288.jordan.dao.CustomerRepository;
import com.d288.jordan.dao.DivisionRepository;
import com.d288.jordan.entities.Customer;
import com.d288.jordan.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootstrapData(CustomerRepository customerRepository,
                         DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) {

        // fetch 5 divisions that actually exist in deterministic order
        var divisions = divisionRepository.findTop5ByOrderByIdAsc();

        if (divisions.size() < 5) {
            throw new RuntimeException("BootstrapData: expected at least 5 divisions to exist. Verify divisions table.");
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
    }
}
