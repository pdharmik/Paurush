package com.lexmark.service.impl.real.domain;

/**
 * mapping file: do-partneraccounttype-mapping.xml
 */
public class PartnerAccountTypeDo extends AccountType {
	
	@Override
	public int hashCode() {
		String type = getType();
		final int prime = 31;
		int result = 17;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PartnerAccountTypeDo))
			return false;
		String type = getType();
		PartnerAccountTypeDo other = (PartnerAccountTypeDo) obj;
		if (type == null) {
			if (other.getType() != null)
				return false;
		} else if (!type.equalsIgnoreCase(other.getType()))
			return false;
		return true;
	}
}
