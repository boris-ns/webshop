const BASE_URL = 'http://localhost:8080';

export const LOGIN_URL = `${BASE_URL}/auth/login`;

export const PRODUCT_CATEGORIES_URL = `${BASE_URL}/api/product-categories`;
export const PRODUCT_ALL_CATEGORIES_URL = `${BASE_URL}/api/product-categories/public`;

export const STORES_URL = `${BASE_URL}/api/stores`;

export const PRODUCTS_URL = `${BASE_URL}/api/products`;
export const MY_PRODUCTS_URL = `${PRODUCTS_URL}/my-store`;
export const ALL_PRODUCTS_URL = `${PRODUCTS_URL}/public`;

export const USERS_URL = `${BASE_URL}/api/users`;
export const REGISTER_URL = `${USERS_URL}/public/register`;
export const VERIFY_ACC_URL = `${USERS_URL}/public/verify-account`;

export const SEASON_DISCOUNTS_URL = `${BASE_URL}/api/season-discounts`;
export const CATEGORY_DISCOUNTS_URL = `${BASE_URL}/api/category-discounts`;
