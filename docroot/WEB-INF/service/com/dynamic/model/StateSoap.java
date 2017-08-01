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
public class StateSoap implements Serializable {
	public static StateSoap toSoapModel(State model) {
		StateSoap soapModel = new StateSoap();

		soapModel.setCountryId(model.getCountryId());
		soapModel.setStateId(model.getStateId());
		soapModel.setStateName(model.getStateName());

		return soapModel;
	}

	public static StateSoap[] toSoapModels(State[] models) {
		StateSoap[] soapModels = new StateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StateSoap[][] toSoapModels(State[][] models) {
		StateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StateSoap[] toSoapModels(List<State> models) {
		List<StateSoap> soapModels = new ArrayList<StateSoap>(models.size());

		for (State model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StateSoap[soapModels.size()]);
	}

	public StateSoap() {
	}

	public long getPrimaryKey() {
		return _stateId;
	}

	public void setPrimaryKey(long pk) {
		setStateId(pk);
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public long getStateId() {
		return _stateId;
	}

	public void setStateId(long stateId) {
		_stateId = stateId;
	}

	public String getStateName() {
		return _stateName;
	}

	public void setStateName(String stateName) {
		_stateName = stateName;
	}

	private long _countryId;
	private long _stateId;
	private String _stateName;
}