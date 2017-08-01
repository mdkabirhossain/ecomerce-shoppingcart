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

package com.dynamic.service.persistence;

import com.dynamic.model.City;

import com.dynamic.service.CityLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Abhishek
 * @generated
 */
public abstract class CityActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public CityActionableDynamicQuery() throws SystemException {
		setBaseLocalService(CityLocalServiceUtil.getService());
		setClass(City.class);

		setClassLoader(com.dynamic.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("cityId");
	}
}