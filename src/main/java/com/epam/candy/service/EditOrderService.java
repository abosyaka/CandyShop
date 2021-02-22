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
    private final OrderDao ORDER_DAO = OrderDaoImpl.getInstance();
    private final StatusDao STATUS_DAO = StatusDaoImpl.getInstance();
    private final OrderDetailDao ORDER_DETAIL_DAO = OrderDetailDaoImpl.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException, SQLException {
        Long id = Long.parseLong(request.getParameter(ServiceConstant.ID));
        ArrayList<OrderDetail> details = (ArrayList<OrderDetail>) ORDER_DETAIL_DAO.findAllByOrderId(id);
        Long statusId = Long.parseLong(request.getParameter(ServiceConstant.STATUS));

        Status status = STATUS_DAO.findById(statusId);
        Order order = ORDER_DAO.findById(id);

        boolean isDetailsUpdated = true;
        for (int i = 0; i < details.size(); i++) {
            String countParam = ServiceConstant.COUNT + i;
            Integer count = Integer.parseInt(request.getParameter(countParam));
            OrderDetail detail = details.get(i);
            if (count <= 0) {
                ORDER_DETAIL_DAO.delete(detail.getId());
            } else {
                detail.setCount(count);
                if (ORDER_DETAIL_DAO.update(detail) == null) {
                    isDetailsUpdated = false;
                    break;
                }
            }
        }

        order.setStatus(status);

        String editStatus = ServiceConstant.FAIL;
        if (ORDER_DAO.update(order) != null && isDetailsUpdated) {
            editStatus = ServiceConstant.SUCCESS;
        }

        String params = "?" + ServiceConstant.ID +
                "=" + order.getId() + "&" + ServiceConstant.EDIT + "=" + editStatus;

        response.sendRedirect(UrlConstant.ADMIN_SHOW_ORDER_EDIT + params);
    }
}
