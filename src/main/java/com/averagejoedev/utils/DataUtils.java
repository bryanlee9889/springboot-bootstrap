package com.averagejoedev.utils;

import com.averagejoedev.mappers.Mapper;
import com.averagejoedev.models.Page;
import com.averagejoedev.models.domains.Domain;
import com.averagejoedev.models.form.Form;

import java.util.List;

/**
 * Created by voncount on 10/7/16.
 */
public class DataUtils {

    public static <T extends Domain> Page<T> filter(Form form, Mapper<T> mapper) {
        List<T> items       = mapper.find(form);
        int totalElement    = mapper.count(form);

        int pageSize        = form.getPageSize();
        int pageIndex       = form.getPageIndex();

        Page<T> p           = new Page<>();
        p.setData(items);
        p.setPageIndex(form.getPageIndex());
        p.setPageSize(form.getPageSize());

        int totalPage = (int) Math.ceil((double) totalElement / pageSize);

        // set additional fields for pagination
        p.setTotalPage(totalPage);
        p.setTotalElement(totalElement);
        p.setOrders(form.getOrders());
        p.setDirection(form.getDirection());
        p.setFirst(totalPage - pageIndex >= totalPage);
        p.setLast(1 >= totalPage - pageIndex);

        return p;
    }

}
