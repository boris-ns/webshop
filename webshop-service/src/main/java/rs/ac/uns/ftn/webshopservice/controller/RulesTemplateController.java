package rs.ac.uns.ftn.webshopservice.controller;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.internal.utils.KieHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.webshopservice.dto.request.AddClassifyBuyerTemplateDTO;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;
import rs.ac.uns.ftn.webshopservice.model.template.ClassifyBuyerTemplate;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rule-templates")
public class RulesTemplateController {

    @PostMapping("/classify-buyers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity createClassifyBuyersRules(@Valid @RequestBody List<AddClassifyBuyerTemplateDTO> templates) {
        InputStream templateFile = RulesTemplateController.class
                .getResourceAsStream("/webshop/templates/classify-buyers-template.drt");

        List<ClassifyBuyerTemplate> data = new ArrayList<>();

        templates.stream().forEach(t -> {
            ClassifyBuyerTemplate template = new ClassifyBuyerTemplate(
                    BuyerCategory.valueOf(t.getBuyerCategory()),
                    BuyerCategory.valueOf(t.getNewCategory()),
                    t.getOrdersSize(),
                    t.getMoneySpent()
            );
            data.add(template);
        });

        ObjectDataCompiler compiler = new ObjectDataCompiler();
        String drl = compiler.compile(data, templateFile);

        System.out.print(drl);

        // DRL string se lepo kreira, ali kako to ukuponovati u kiecontainer
        // koji ce praviti sve sledece sesije u kojima zelim da imam
        // taj novi DRL fajl
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

//        return kieHelper.build().newKieSession();

        return ResponseEntity.ok().build();
    }
}
