package rs.ac.uns.ftn.webshopservice.rules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.webshopservice.config.consts.KieAgendaGroups;
import rs.ac.uns.ftn.webshopservice.model.Buyer;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;
import rs.ac.uns.ftn.webshopservice.utils.ModelFactory;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassifyBuyersTest {

    @Autowired
    private KieContainer kieContainer;

    private KieSession createSession(Buyer buyer) {
        KieSession kieClassifyBuyers = kieContainer.newKieSession("DefaultSession");
        kieClassifyBuyers.getAgenda().getAgendaGroup(KieAgendaGroups.CLASSIFY_BUYERS).setFocus();
        kieClassifyBuyers.insert(buyer);
        return kieClassifyBuyers;
    }

    @Test
    public void test_setBuyerCategoryToSilver() {
        Buyer buyer = ModelFactory.getBuyerForSilverCategory();
        KieSession kieSession = createSession(buyer);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals(1, firedRules);
        assertEquals(BuyerCategory.SILVER, buyer.getCategory());
    }

    @Test
    public void test_setBuyerCategoryToGold() {
        Buyer buyer = ModelFactory.getBuyerForGoldCategory();
        KieSession kieSession = createSession(buyer);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals(1, firedRules);
        assertEquals(BuyerCategory.GOLD, buyer.getCategory());
    }

    @Test
    public void test_setBuyerCategoryToDiamond() {
        Buyer buyer = ModelFactory.getBuyerForDiamondCategory();
        KieSession kieSession = createSession(buyer);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals(1, firedRules);
        assertEquals(BuyerCategory.DIAMOND, buyer.getCategory());
    }
}
