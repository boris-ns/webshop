import UserRegistrationDTO from './user-registration-dto.model';

export interface StoreRegistrationDTO {
    user: UserRegistrationDTO;
    name: String;
    frequentBuyerDiscount: Number;
}
