/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.dynamic.model.impl;

import com.dynamic.model.Countries;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Countries in entity cache.
 *
 * @author Abhishek
 * @see Countries
 * @generated
 */
public class CountriesCacheModel implements CacheModel<Countries>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{countryId=");
		sb.append(countryId);
		sb.append(", shortname=");
		sb.append(shortname);
		sb.append(", countryName=");
		sb.append(countryName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Countries toEntityModel() {
		CountriesImpl countriesImpl = new CountriesImpl();

		countriesImpl.setCountryId(countryId);

		if (shortname == null) {
			countriesImpl.setShortname(StringPool.BLANK);
		}
		else {
			countriesImpl.setShortname(shortname);
		}

		if (countryName == null) {
			countriesImpl.setCountryName(StringPool.BLANK);
		}
		else {
			countriesImpl.setCountryName(countryName);
		}

		countriesImpl.resetOriginalValues();

		return countriesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		countryId = objectInput.readLong();
		shortname = objectInput.readUTF();
		countryName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(countryId);

		if (shortname == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(shortname);
		}

		if (countryName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(countryName);
		}
	}

	public long countryId;
	public String shortname;
	public String countryName;
}