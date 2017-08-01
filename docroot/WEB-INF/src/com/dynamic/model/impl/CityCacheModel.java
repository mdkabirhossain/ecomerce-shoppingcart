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

import com.dynamic.model.City;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing City in entity cache.
 *
 * @author Abhishek
 * @see City
 * @generated
 */
public class CityCacheModel implements CacheModel<City>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{stateId=");
		sb.append(stateId);
		sb.append(", cityId=");
		sb.append(cityId);
		sb.append(", cityName=");
		sb.append(cityName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public City toEntityModel() {
		CityImpl cityImpl = new CityImpl();

		cityImpl.setStateId(stateId);
		cityImpl.setCityId(cityId);

		if (cityName == null) {
			cityImpl.setCityName(StringPool.BLANK);
		}
		else {
			cityImpl.setCityName(cityName);
		}

		cityImpl.resetOriginalValues();

		return cityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		stateId = objectInput.readLong();
		cityId = objectInput.readLong();
		cityName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(stateId);
		objectOutput.writeLong(cityId);

		if (cityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(cityName);
		}
	}

	public long stateId;
	public long cityId;
	public String cityName;
}