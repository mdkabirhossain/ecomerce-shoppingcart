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

package com.dynamic.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Abhishek
 * @generated
 */
public class CountriesSoap implements Serializable {
	public static CountriesSoap toSoapModel(Countries model) {
		CountriesSoap soapModel = new CountriesSoap();

		soapModel.setCountryId(model.getCountryId());
		soapModel.setShortname(model.getShortname());
		soapModel.setCountryName(model.getCountryName());

		return soapModel;
	}

	public static CountriesSoap[] toSoapModels(Countries[] models) {
		CountriesSoap[] soapModels = new CountriesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CountriesSoap[][] toSoapModels(Countries[][] models) {
		CountriesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CountriesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CountriesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CountriesSoap[] toSoapModels(List<Countries> models) {
		List<CountriesSoap> soapModels = new ArrayList<CountriesSoap>(models.size());

		for (Countries model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CountriesSoap[soapModels.size()]);
	}

	public CountriesSoap() {
	}

	public long getPrimaryKey() {
		return _countryId;
	}

	public void setPrimaryKey(long pk) {
		setCountryId(pk);
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getShortname() {
		return _shortname;
	}

	public void setShortname(String shortname) {
		_shortname = shortname;
	}

	public String getCountryName() {
		return _countryName;
	}

	public void setCountryName(String countryName) {
		_countryName = countryName;
	}

	private long _countryId;
	private String _shortname;
	private String _countryName;
}