export interface EditProductDTO {
    id?: number;
    name?: string;
    price?: number;
    shippingPrice ?: number;
    quantity?: number;
    maxQuantity?: number;
    maxOrderQuantity?: number;
    quantityDiscount?: number;
    orderQuantityDiscount?: number;
    discount?: number;
    maxDiscount?: number;
    coupon?: string;
    couponDiscount?: number;
}
