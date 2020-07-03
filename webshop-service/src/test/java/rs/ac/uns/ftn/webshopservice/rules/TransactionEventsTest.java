package rs.ac.uns.ftn.webshopservice.rules;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.webshopservice.config.beans.cepsession.CepSession;
import rs.ac.uns.ftn.webshopservice.model.events.TransactionEvent;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionEventsTest {

    @Autowired
    private CepSession cepSession;

    @Test
    public void test20OrMoreTransactionIn1Hour() {
        KieSession kieSession = cepSession.getCepSession();

        TransactionEvent event = new TransactionEvent(3L, 40.0);
        cepSession.getEvents().add(event);
        kieSession.insert(event);

        int fired = kieSession.fireAllRules();
        assertEquals(0, fired);

        for (int i = 0; i < 19; ++i) {
            TransactionEvent e = new TransactionEvent(3L, 40.0);
            cepSession.getEvents().add(e);
            kieSession.insert(e);
        }

        fired = kieSession.fireAllRules();
        assertEquals(1, fired);
    }

    @Test
    public void test5OrMoreTransactionsWithValueOver100Within1Hour() {
        KieSession kieSession = cepSession.getCepSession();

        TransactionEvent event = new TransactionEvent(3L, 140.0);
        cepSession.getEvents().add(event);
        kieSession.insert(event);

        int fired = kieSession.fireAllRules();
        assertEquals(0, fired);

        for (int i = 0; i < 4; ++i) {
            TransactionEvent e = new TransactionEvent(3L, 140.0);
            cepSession.getEvents().add(e);
            kieSession.insert(e);
        }

        fired = kieSession.fireAllRules();
        assertEquals(1, fired);
    }

    @Test
    @Ignore
    public void test3OrMoreSuspiciousEventsInTheLast12Hours() {
        KieSession kieSession = cepSession.getCepSession();

        // Create first suspicious event
        for (int i = 0; i < 5; ++i) {
            TransactionEvent event = new TransactionEvent(3L, 140.0);
            cepSession.getEvents().add(event);
            kieSession.insert(event);
        }

        int fired = kieSession.fireAllRules();
        assertEquals(1, fired);

        // Create second suspicious event
        for (int i = 0; i < 20; ++i) {
            TransactionEvent event = new TransactionEvent(3L, 40.0);
            cepSession.getEvents().add(event);
            kieSession.insert(event);
        }

        fired = kieSession.fireAllRules();
        assertEquals(1, fired);

        // @NOTE!! Here we need to make the clock wait for 1h so another suspicious event can occur
        // And because of that this test wont work

        // Create third suspicious event
        for (int i = 0; i < 5; ++i) {
            TransactionEvent event = new TransactionEvent(3L, 140.0);
            cepSession.getEvents().add(event);
            kieSession.insert(event);
        }

        fired = kieSession.fireAllRules();

        // We except 2 because first fired rule will be third suspicious event and second fired rule
        // will be the one that we want to test
        assertEquals(2, fired);
    }

}
