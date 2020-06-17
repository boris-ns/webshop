const BASE_URL = 'http://localhost:8080';

// Auth
export const LOGIN_URL = `${BASE_URL}/auth/login`;

// Product categories
export const PRODUCT_CATEGORIES_URL = `${BASE_URL}/api/product-categories`;
export const PRODUCT_ALL_CATEGORIES_URL = `${BASE_URL}/api/product-categories/public`;

// Stores
export const STORES_URL = `${BASE_URL}/api/stores`;
export const ADD_STORE_URL = `${STORES_URL}/public`;
export const MY_STORE_URL = `${STORES_URL}/my`;

// Orders
export const ORDERS_URL = `${BASE_URL}/api/orders`;
export const MY_ORDERS_URL = `${ORDERS_URL}/my`;

// Products
export const PRODUCTS_URL = `${BASE_URL}/api/products`;
export const MY_PRODUCTS_URL = `${PRODUCTS_URL}/my-store`;
export const ALL_PRODUCTS_URL = `${PRODUCTS_URL}/public`;
export const PLACE_ORDER_URL = `${PRODUCTS_URL}/place-order`;
export const RECOMMENDED_PRODUCTS_URL = `${PRODUCTS_URL}/recommendations`;
export const FILTER_PRODUCTS_URL = `${PRODUCTS_URL}/public/filter`;

// Users
export const USERS_URL = `${BASE_URL}/api/users`;
export const REGISTER_URL = `${USERS_URL}/public/register`;
export const VERIFY_ACC_URL = `${USERS_URL}/public/verify-account`;

// Discounts
export const SEASON_DISCOUNTS_URL = `${BASE_URL}/api/season-discounts`;
export const CATEGORY_DISCOUNTS_URL = `${BASE_URL}/api/category-discounts`;

// Rule templates
export const ADD_RULE_TEMPLATE_URL = `${BASE_URL}/api/rule-templates/classify-buyers`;
