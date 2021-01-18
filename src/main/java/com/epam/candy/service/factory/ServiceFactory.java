package com.epam.candy.service.factory;

import com.epam.candy.service.*;
import com.epam.candy.service.constant.UrlConstant;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();

    private ServiceFactory() {

    }

    static {
        SERVICE_MAP.put(UrlConstant.HOME, new HomeService());
        SERVICE_MAP.put(UrlConstant.REGISTER, new RegisterService());
        SERVICE_MAP.put(UrlConstant.SHOW_REGISTER, new ShowRegisterService());
        SERVICE_MAP.put(UrlConstant.SHOW_LOGIN, new ShowLoginService());
        SERVICE_MAP.put(UrlConstant.LOGIN, new LoginService());
        SERVICE_MAP.put(UrlConstant.LOGOUT, new LogoutService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_USERS, new ShowUsersService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_GOODS, new ShowGoodsService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_CATEGORIES, new ShowCategoriesService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_ORDERS, new ShowOrdersService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_ROLES, new ShowRolesService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_STATUSES, new ShowStatusesService());
        SERVICE_MAP.put(UrlConstant.ERROR_403, new AccessErrorService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_ORDER_EDIT, new ShowOrderEditService());
        SERVICE_MAP.put(UrlConstant.ADMIN_ORDER_EDIT, new EditOrderService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_CATEGORY_EDIT, new ShowCategoryEditService());
        SERVICE_MAP.put(UrlConstant.ADMIN_CATEGORY_EDIT, new EditCategoryService());
        SERVICE_MAP.put(UrlConstant.ADMIN_CATEGORY_DELETE, new DeleteCategoryService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_USER_EDIT, new ShowUserEditService());
        SERVICE_MAP.put(UrlConstant.ADMIN_USER_EDIT, new EditUserService());
        SERVICE_MAP.put(UrlConstant.ADMIN_SHOW_GOOD_EDIT, new ShowGoodEditService());
        SERVICE_MAP.put(UrlConstant.ADMIN_GOOD_EDIT, new EditGoodService());
        SERVICE_MAP.put(UrlConstant.ADMIN_GOOD_DELETE, new DeleteGoodService());
        SERVICE_MAP.put(UrlConstant.ADMIN_USER_DELETE, new DeleteUserService());
        SERVICE_MAP.put(UrlConstant.GOOD_DETAILS, new ShowGoodDetailService());
        SERVICE_MAP.put(UrlConstant.SHOW_CART, new ShowCartService());
        SERVICE_MAP.put(UrlConstant.ADD_TO_CART, new AddToCartService());
        SERVICE_MAP.put(UrlConstant.DELETE_FROM_CART, new DeleteFromCartService());
        SERVICE_MAP.put(UrlConstant.MAKE_PURCHASE, new MakeOrderService());
        SERVICE_MAP.put(UrlConstant.SHOW_USER_ORDERS, new ShowUserOrdersService());
        SERVICE_MAP.put(UrlConstant.SHOW_ORDER_DETAILS, new ShowOrderDetailService());
    }

    public Service getService(String request) {
        Service service = SERVICE_MAP.get(UrlConstant.ERROR);

        for (Map.Entry<String, Service> pair : SERVICE_MAP.entrySet()) {
            if (request.equalsIgnoreCase(pair.getKey())) {
                service = SERVICE_MAP.get(pair.getKey());
            }
        }

        return service;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
