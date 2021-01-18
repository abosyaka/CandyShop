package com.epam.candy.service;

import com.epam.candy.dao.OrderDao;
import com.epam.candy.dao.OrderDetailDao;
import com.epam.candy.dao.StatusDao;
import com.epam.candy.dao.impl.OrderDaoImpl;
import com.epam.candy.dao.impl.OrderDetailDaoImpl;
import com.epam.candy.dao.impl.StatusDaoImpl;
import com.epam.candy.entity.Order;
import com.epam.candy.entity.OrderDetail;
import com.epam.candy.entity.Status;
import com.epam.candy.service.constant.ServiceConstant;
import com.epam.candy.service.constant.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class EditOrderService implements Service {
    private final OrderDao orderDao = OrderDaoImpl.getInstance();
    private final StatusDao statusDao = StatusDaoImpl.getInstance();
    private final OrderDetailDao orderDetailDao = OrderDetailDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        ArrayList<OrderDetail> details = (ArrayList<OrderDetail>) orderDetailDao.findAllByOrderId(id);
        Long statusId = Long.parseLong(request.getParameter(ServiceConstant.STATUS));

        Status status = statusDao.findById(statusId);
        Order order = orderDao.findById(id);

        boolean isDetailsUpdated = true;
        for (int i = 0; i < details.size(); i++) {
            String countParam = ServiceConstant.COUNT + i;
            Integer count = Integer.parseInt(request.getParameter(countParam));
            OrderDetail detail = details.get(i);
            if (count <= 0) {
                orderDetailDao.delete(detail.getId());
            } else {
                detail.setCount(count);
                if (orderDetailDao.update(detail) == null) {
                    isDetailsUpdated = false;
                    break;
                }
            }
        }

        order.setStatus(status);

        String editStatus = ServiceConstant.FAIL;
        if (orderDao.update(order) != null && isDetailsUpdated) {
            editStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ID +
                "=" + order.getId() + "&" + ServiceConstant.EDIT + "=" + editStatus;

        response.sendRedirect(UrlConstant.ADMIN_SHOW_ORDER_EDIT + params);
    }
}
