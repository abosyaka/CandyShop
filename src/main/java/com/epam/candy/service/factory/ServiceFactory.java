package com.epam.candy.service.factory;

import com.epam.candy.service.*;
import com.epam.candy.service.constant.UrlConstant;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    private static final Map<String, Service> SERVICE_MAP = new HashMap<>();

    private ServiceFactory(){

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
    }

    public Service getService(String request){
        Service service = SERVICE_MAP.get(UrlConstant.ERROR);

        for(Map.Entry<String, Service> pair: SERVICE_MAP.entrySet()){
            if(request.equalsIgnoreCase(pair.getKey())){
                service = SERVICE_MAP.get(pair.getKey());
            }
        }

        return service;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
}
