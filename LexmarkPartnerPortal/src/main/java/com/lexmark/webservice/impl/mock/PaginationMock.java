package com.lexmark.webservice.impl.mock;

public class PaginationMock {
	private String requestNumber;
    private double amount;
    private String currencyType;

    public PaginationMock() {
    }

    public PaginationMock(String requestNumber,double amount,String currencyType) {
           this.requestNumber = requestNumber;
           this.amount = amount;
           this.currencyType = currencyType;
    }

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
    
	public static PaginationMock[] getPagingObj()
	{
		PaginationMock[] pagingObj =  new PaginationMock[100];
		for(int i=0;i<100;i++)
		{
			PaginationMock paginationMock = new PaginationMock("ABCD"+i,210+i,"USD");
			pagingObj[i] =  paginationMock;
		}
		return pagingObj;
	}
}
