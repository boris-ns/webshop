export default interface AddProductDTO {
    name: string;
    categoryId: number;
    price: number;
    shippingPrice: number;
    quantity: number;
    maxQuantity: number;
    maxOrderQuantity: number;
    quantityDiscount: number;
    orderQuantityDiscount: number;
    discount: number;
    maxDiscount: number;
    coupon: string;
    couponDiscount: number;
}
