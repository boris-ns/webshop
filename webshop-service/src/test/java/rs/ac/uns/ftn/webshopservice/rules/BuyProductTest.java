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
import rs.ac.uns.ftn.webshopservice.model.Order;
import rs.ac.uns.ftn.webshopservice.model.Product;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;
import rs.ac.uns.ftn.webshopservice.utils.ModelFactory;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuyProductTest {

    @Autowired
    private KieContainer kieContainer;

    private KieSession createSession(Buyer buyer, Order order, Product product) {
        KieSession kieSession = kieContainer.newKieSession("DefaultSession");
        kieSession.getAgenda().getAgendaGroup(KieAgendaGroups.ORDER_DISCOUNTS).setFocus();
        kieSession.insert(buyer);
        kieSession.insert(order);
        kieSession.insert(product);
        return kieSession;
    }

    @Test
    public void testGoldBuyersDiscount() {
        Buyer buyer = ModelFactory.getBuyerForDiamondCategory();
        Product product = ModelFactory.products.get(0);
        Order order = new Order(product, "coupon", 1);

        double productPrice = product.getPrice();

        KieSession kieSession = createSession(buyer, order, product);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        double discount = 0.05 + product.getStore().getFrequentBuyerDiscount();
        assertEquals(productPrice - (productPrice * discount), order.getPrice(), 0.1);
        assertEquals(3, firedRules);
    }

    @Test
    public void testDiamondBuyersDiscount() {
        Buyer buyer = ModelFactory.getBuyerForDiamondCategory();
        buyer.setCategory(BuyerCategory.DIAMOND);
        Product product = ModelFactory.products.get(0);
        Order order = new Order(product, "coupon", 1);

        double productPrice = product.getPrice();

        KieSession kieSession = createSession(buyer, order, product);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        double discount = 0.1 + product.getStore().getFrequentBuyerDiscount();
        assertEquals(productPrice - (productPrice * discount), order.getPrice(), 0.1);
        assertEquals(3, firedRules);
    }

    /**
     * Rules "User orders more than 1 product" and "Quantity discount" always go together
     * so there is no need to write separate tests for them.
     */
    @Test
    public void testUserOrdersMoreThan1ProductAndQuantityDiscount() {
        Buyer buyer = ModelFactory.getBuyerForSilverCategory();
        Product product = ModelFactory.products.get(0);
        final int quantity = 3;
        Order order = new Order(product, "coupon", quantity);

        double productPrice = product.getPrice() * quantity;

        KieSession kieSession = createSession(buyer, order, product);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        double discount = product.getStore().getFrequentBuyerDiscount() + product.getQuantityDiscount();
        assertEquals(productPrice - (productPrice * discount), order.getPrice(), 0.1);
        assertEquals(4, firedRules);
    }

    @Test
    public void testRegularUsersPayShipments() {
        Buyer buyer = ModelFactory.getBuyerForSilverCategory();
        Product product = ModelFactory.products.get(1);
        Order order = new Order(product, "coupon", 1);

        double productPrice = product.getPrice() + product.getShippingPrice();

        KieSession kieSession = createSession(buyer, order, product);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals(productPrice, order.getPrice(), 0.1);
        assertEquals(2, firedRules);
    }

    @Test
    public void testAddProductDiscount() {
        Buyer buyer = ModelFactory.getBuyerForSilverCategory();
        Product product = ModelFactory.products.get(2);
        Order order = new Order(product, "coupon", 1);

        double productPrice = product.getPrice() - product.getPrice() * product.getDiscount();

        KieSession kieSession = createSession(buyer, order, product);
        int firedRules = kieSession.fireAllRules();
        kieSession.dispose();

        assertEquals(productPrice, order.getPrice(), 0.1);
        assertEquals(2, firedRules);
    }
}
