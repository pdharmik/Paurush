package com.lexmark.service.impl.real.orderSuppliesAssetService;

import java.util.ArrayList;
import java.util.List;

import com.lexmark.service.impl.real.domain.AgreementSoldToNumberDo;
import com.lexmark.service.impl.real.domain.SoldToNumberDo;
import com.lexmark.util.LangUtil;

public class AgreementSoldToNumberUtil {

	public static List<String> convertSoldToNumberDoToSoldToNumberList(List<AgreementSoldToNumberDo> agreementSoldToDOs) {
		List<String> soldToNumbers = new ArrayList<String>();
		
		for (AgreementSoldToNumberDo agreementSoldToDo : agreementSoldToDOs) {
			
			List<SoldToNumberDo> soldToDOs = LangUtil.notNull(agreementSoldToDo.getSoldToNumbers());
			
			for (SoldToNumberDo soldToNumberDo : soldToDOs) {
				soldToNumbers.add(soldToNumberDo.getSoldToNumber());
			}
		
		}
		
		return soldToNumbers;
	}
}
