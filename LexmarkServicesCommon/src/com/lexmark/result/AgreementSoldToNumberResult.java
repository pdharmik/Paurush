package com.lexmark.result;

import java.io.Serializable;
import java.util.List;

public class AgreementSoldToNumberResult implements Serializable {

	private static final long serialVersionUID = 1249400153230240361L;

	List<String> soldToNumbers;

	public List<String> getSoldToNumbers() {
		return soldToNumbers;
	}

	public void setSoldToNumbers(List<String> soldToNumbers) {
		this.soldToNumbers = soldToNumbers;
	}
}
