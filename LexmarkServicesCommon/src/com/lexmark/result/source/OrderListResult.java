package com.lexmark.result.source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lexmark.domain.Order;


public class OrderListResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private int totalCount;
    private List<Order> orderList = new ArrayList<Order>();
    private boolean pendingDebriefFlag;

    public void setTotalCount(int totalcount) {
        this.totalCount = totalcount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

	public boolean isPendingDebriefFlag() {
		return pendingDebriefFlag;
	}

	public void setPendingDebriefFlag(boolean pendingDebriefFlag) {
		this.pendingDebriefFlag = pendingDebriefFlag;
	}
}
