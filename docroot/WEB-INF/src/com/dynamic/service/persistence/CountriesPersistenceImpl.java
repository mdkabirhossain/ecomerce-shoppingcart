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

import com.dynamic.NoSuchCountriesException;

import com.dynamic.model.Countries;
import com.dynamic.model.impl.CountriesImpl;
import com.dynamic.model.impl.CountriesModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the countries service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Abhishek
 * @see CountriesPersistence
 * @see CountriesUtil
 * @generated
 */
public class CountriesPersistenceImpl extends BasePersistenceImpl<Countries>
	implements CountriesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CountriesUtil} to access the countries persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CountriesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesModelImpl.FINDER_CACHE_ENABLED, CountriesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesModelImpl.FINDER_CACHE_ENABLED, CountriesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_COUNTRYNAME = new FinderPath(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesModelImpl.FINDER_CACHE_ENABLED, CountriesImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBycountryName",
			new String[] { String.class.getName() },
			CountriesModelImpl.COUNTRYNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COUNTRYNAME = new FinderPath(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycountryName",
			new String[] { String.class.getName() });

	/**
	 * Returns the countries where countryName = &#63; or throws a {@link com.dynamic.NoSuchCountriesException} if it could not be found.
	 *
	 * @param countryName the country name
	 * @return the matching countries
	 * @throws com.dynamic.NoSuchCountriesException if a matching countries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries findBycountryName(String countryName)
		throws NoSuchCountriesException, SystemException {
		Countries countries = fetchBycountryName(countryName);

		if (countries == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("countryName=");
			msg.append(countryName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCountriesException(msg.toString());
		}

		return countries;
	}

	/**
	 * Returns the countries where countryName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param countryName the country name
	 * @return the matching countries, or <code>null</code> if a matching countries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries fetchBycountryName(String countryName)
		throws SystemException {
		return fetchBycountryName(countryName, true);
	}

	/**
	 * Returns the countries where countryName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param countryName the country name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching countries, or <code>null</code> if a matching countries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries fetchBycountryName(String countryName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { countryName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
					finderArgs, this);
		}

		if (result instanceof Countries) {
			Countries countries = (Countries)result;

			if (!Validator.equals(countryName, countries.getCountryName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_COUNTRIES_WHERE);

			boolean bindCountryName = false;

			if (countryName == null) {
				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_1);
			}
			else if (countryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_3);
			}
			else {
				bindCountryName = true;

				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountryName) {
					qPos.add(countryName);
				}

				List<Countries> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CountriesPersistenceImpl.fetchBycountryName(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Countries countries = list.get(0);

					result = countries;

					cacheResult(countries);

					if ((countries.getCountryName() == null) ||
							!countries.getCountryName().equals(countryName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
							finderArgs, countries);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Countries)result;
		}
	}

	/**
	 * Removes the countries where countryName = &#63; from the database.
	 *
	 * @param countryName the country name
	 * @return the countries that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries removeBycountryName(String countryName)
		throws NoSuchCountriesException, SystemException {
		Countries countries = findBycountryName(countryName);

		return remove(countries);
	}

	/**
	 * Returns the number of countrieses where countryName = &#63;.
	 *
	 * @param countryName the country name
	 * @return the number of matching countrieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBycountryName(String countryName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COUNTRYNAME;

		Object[] finderArgs = new Object[] { countryName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_COUNTRIES_WHERE);

			boolean bindCountryName = false;

			if (countryName == null) {
				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_1);
			}
			else if (countryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_3);
			}
			else {
				bindCountryName = true;

				query.append(_FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCountryName) {
					qPos.add(countryName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_1 = "countries.countryName IS NULL";
	private static final String _FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_2 = "countries.countryName = ?";
	private static final String _FINDER_COLUMN_COUNTRYNAME_COUNTRYNAME_3 = "(countries.countryName IS NULL OR countries.countryName = '')";

	public CountriesPersistenceImpl() {
		setModelClass(Countries.class);
	}

	/**
	 * Caches the countries in the entity cache if it is enabled.
	 *
	 * @param countries the countries
	 */
	@Override
	public void cacheResult(Countries countries) {
		EntityCacheUtil.putResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesImpl.class, countries.getPrimaryKey(), countries);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
			new Object[] { countries.getCountryName() }, countries);

		countries.resetOriginalValues();
	}

	/**
	 * Caches the countrieses in the entity cache if it is enabled.
	 *
	 * @param countrieses the countrieses
	 */
	@Override
	public void cacheResult(List<Countries> countrieses) {
		for (Countries countries : countrieses) {
			if (EntityCacheUtil.getResult(
						CountriesModelImpl.ENTITY_CACHE_ENABLED,
						CountriesImpl.class, countries.getPrimaryKey()) == null) {
				cacheResult(countries);
			}
			else {
				countries.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all countrieses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CountriesImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CountriesImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the countries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Countries countries) {
		EntityCacheUtil.removeResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesImpl.class, countries.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(countries);
	}

	@Override
	public void clearCache(List<Countries> countrieses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Countries countries : countrieses) {
			EntityCacheUtil.removeResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
				CountriesImpl.class, countries.getPrimaryKey());

			clearUniqueFindersCache(countries);
		}
	}

	protected void cacheUniqueFindersCache(Countries countries) {
		if (countries.isNew()) {
			Object[] args = new Object[] { countries.getCountryName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COUNTRYNAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COUNTRYNAME, args,
				countries);
		}
		else {
			CountriesModelImpl countriesModelImpl = (CountriesModelImpl)countries;

			if ((countriesModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_COUNTRYNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { countries.getCountryName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COUNTRYNAME,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_COUNTRYNAME,
					args, countries);
			}
		}
	}

	protected void clearUniqueFindersCache(Countries countries) {
		CountriesModelImpl countriesModelImpl = (CountriesModelImpl)countries;

		Object[] args = new Object[] { countries.getCountryName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRYNAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COUNTRYNAME, args);

		if ((countriesModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_COUNTRYNAME.getColumnBitmask()) != 0) {
			args = new Object[] { countriesModelImpl.getOriginalCountryName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COUNTRYNAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_COUNTRYNAME, args);
		}
	}

	/**
	 * Creates a new countries with the primary key. Does not add the countries to the database.
	 *
	 * @param countryId the primary key for the new countries
	 * @return the new countries
	 */
	@Override
	public Countries create(long countryId) {
		Countries countries = new CountriesImpl();

		countries.setNew(true);
		countries.setPrimaryKey(countryId);

		return countries;
	}

	/**
	 * Removes the countries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param countryId the primary key of the countries
	 * @return the countries that was removed
	 * @throws com.dynamic.NoSuchCountriesException if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries remove(long countryId)
		throws NoSuchCountriesException, SystemException {
		return remove((Serializable)countryId);
	}

	/**
	 * Removes the countries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the countries
	 * @return the countries that was removed
	 * @throws com.dynamic.NoSuchCountriesException if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries remove(Serializable primaryKey)
		throws NoSuchCountriesException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Countries countries = (Countries)session.get(CountriesImpl.class,
					primaryKey);

			if (countries == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCountriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(countries);
		}
		catch (NoSuchCountriesException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Countries removeImpl(Countries countries)
		throws SystemException {
		countries = toUnwrappedModel(countries);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(countries)) {
				countries = (Countries)session.get(CountriesImpl.class,
						countries.getPrimaryKeyObj());
			}

			if (countries != null) {
				session.delete(countries);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (countries != null) {
			clearCache(countries);
		}

		return countries;
	}

	@Override
	public Countries updateImpl(com.dynamic.model.Countries countries)
		throws SystemException {
		countries = toUnwrappedModel(countries);

		boolean isNew = countries.isNew();

		Session session = null;

		try {
			session = openSession();

			if (countries.isNew()) {
				session.save(countries);

				countries.setNew(false);
			}
			else {
				session.merge(countries);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CountriesModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
			CountriesImpl.class, countries.getPrimaryKey(), countries);

		clearUniqueFindersCache(countries);
		cacheUniqueFindersCache(countries);

		return countries;
	}

	protected Countries toUnwrappedModel(Countries countries) {
		if (countries instanceof CountriesImpl) {
			return countries;
		}

		CountriesImpl countriesImpl = new CountriesImpl();

		countriesImpl.setNew(countries.isNew());
		countriesImpl.setPrimaryKey(countries.getPrimaryKey());

		countriesImpl.setCountryId(countries.getCountryId());
		countriesImpl.setShortname(countries.getShortname());
		countriesImpl.setCountryName(countries.getCountryName());

		return countriesImpl;
	}

	/**
	 * Returns the countries with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the countries
	 * @return the countries
	 * @throws com.dynamic.NoSuchCountriesException if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCountriesException, SystemException {
		Countries countries = fetchByPrimaryKey(primaryKey);

		if (countries == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCountriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return countries;
	}

	/**
	 * Returns the countries with the primary key or throws a {@link com.dynamic.NoSuchCountriesException} if it could not be found.
	 *
	 * @param countryId the primary key of the countries
	 * @return the countries
	 * @throws com.dynamic.NoSuchCountriesException if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries findByPrimaryKey(long countryId)
		throws NoSuchCountriesException, SystemException {
		return findByPrimaryKey((Serializable)countryId);
	}

	/**
	 * Returns the countries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the countries
	 * @return the countries, or <code>null</code> if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Countries countries = (Countries)EntityCacheUtil.getResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
				CountriesImpl.class, primaryKey);

		if (countries == _nullCountries) {
			return null;
		}

		if (countries == null) {
			Session session = null;

			try {
				session = openSession();

				countries = (Countries)session.get(CountriesImpl.class,
						primaryKey);

				if (countries != null) {
					cacheResult(countries);
				}
				else {
					EntityCacheUtil.putResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
						CountriesImpl.class, primaryKey, _nullCountries);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CountriesModelImpl.ENTITY_CACHE_ENABLED,
					CountriesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return countries;
	}

	/**
	 * Returns the countries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param countryId the primary key of the countries
	 * @return the countries, or <code>null</code> if a countries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Countries fetchByPrimaryKey(long countryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)countryId);
	}

	/**
	 * Returns all the countrieses.
	 *
	 * @return the countrieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Countries> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the countrieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CountriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of countrieses
	 * @param end the upper bound of the range of countrieses (not inclusive)
	 * @return the range of countrieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Countries> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the countrieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CountriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of countrieses
	 * @param end the upper bound of the range of countrieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of countrieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Countries> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Countries> list = (List<Countries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_COUNTRIES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COUNTRIES;

				if (pagination) {
					sql = sql.concat(CountriesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Countries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Countries>(list);
				}
				else {
					list = (List<Countries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the countrieses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Countries countries : findAll()) {
			remove(countries);
		}
	}

	/**
	 * Returns the number of countrieses.
	 *
	 * @return the number of countrieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COUNTRIES);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the countries persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.dynamic.model.Countries")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Countries>> listenersList = new ArrayList<ModelListener<Countries>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Countries>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CountriesImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_COUNTRIES = "SELECT countries FROM Countries countries";
	private static final String _SQL_SELECT_COUNTRIES_WHERE = "SELECT countries FROM Countries countries WHERE ";
	private static final String _SQL_COUNT_COUNTRIES = "SELECT COUNT(countries) FROM Countries countries";
	private static final String _SQL_COUNT_COUNTRIES_WHERE = "SELECT COUNT(countries) FROM Countries countries WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "countries.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Countries exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Countries exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CountriesPersistenceImpl.class);
	private static Countries _nullCountries = new CountriesImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Countries> toCacheModel() {
				return _nullCountriesCacheModel;
			}
		};

	private static CacheModel<Countries> _nullCountriesCacheModel = new CacheModel<Countries>() {
			@Override
			public Countries toEntityModel() {
				return _nullCountries;
			}
		};
}