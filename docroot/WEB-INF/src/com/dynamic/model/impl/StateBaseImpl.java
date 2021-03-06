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

import com.dynamic.model.State;

import com.dynamic.service.StateLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the State service. Represents a row in the &quot;z_State&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StateImpl}.
 * </p>
 *
 * @author Abhishek
 * @see StateImpl
 * @see com.dynamic.model.State
 * @generated
 */
public abstract class StateBaseImpl extends StateModelImpl implements State {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a state model instance should use the {@link State} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			StateLocalServiceUtil.addState(this);
		}
		else {
			StateLocalServiceUtil.updateState(this);
		}
	}
}