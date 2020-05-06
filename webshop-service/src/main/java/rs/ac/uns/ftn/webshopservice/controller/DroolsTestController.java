package rs.ac.uns.ftn.webshopservice.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.webshopservice.model.Item;

@RestController
@RequestMapping("/api/public/drools")
public class DroolsTestController {

    @Autowired
    private KieContainer kieContainer;

    @RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json")
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
}
