package rs.ac.uns.ftn.webshopservice.model.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.webshopservice.model.enums.BuyerCategory;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassifyBuyerTemplate {

    private BuyerCategory buyerCategory;
    private BuyerCategory newCategory;
    private int ordersSize;
    private double moneySpent;
}
