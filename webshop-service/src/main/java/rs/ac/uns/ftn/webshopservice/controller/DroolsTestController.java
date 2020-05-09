package rs.ac.uns.ftn.webshopservice.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.webshopservice.dto.response.UserDTO;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.Item;

@RestController
@RequestMapping("/api")
public class DroolsTestController {

    @Autowired
    private KieContainer kieContainer;

    @RequestMapping(value = "/public/drools/item", method = RequestMethod.GET, produces = "application/json")
    public Item getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name,
                             @RequestParam(required = true) double cost, @RequestParam(required = true) double salePrice) {

        Item newItem = new Item(Long.parseLong(id), name, cost, salePrice);
        Item i2 = this.getClassifiedItem(newItem);

        return i2;
    }

    public Item getClassifiedItem(Item i) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(i);
        kieSession.fireAllRules();
        kieSession.dispose();
        return i;
    }

    @GetMapping(value = "/drools/buyer-category")
    public UserDTO buyerCategory() {
        Buyer buyer = (Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        buyer = getClassifiedBuyer(buyer);
        System.out.println(buyer.getCategory());
        return new UserDTO(buyer);
    }

    public Buyer getClassifiedBuyer(Buyer buyer) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(buyer);
        int fired = kieSession.fireAllRules();
        System.out.println("Fired rules: " + fired);
        kieSession.dispose();
        return buyer;
    }


}
