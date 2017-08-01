package com.dynamic.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.dynamic.model.Countries;
import com.dynamic.service.CountriesLocalServiceUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

public class CreateLocation extends SimpleAction {
	
	
	
	
	private static final Log _log = LogFactoryUtil
	.getLog(CreateLocation.class);
	@Override
	public void run(String[] arg0) throws ActionException {
		// TODO Auto-generated method stub
		
		  createCountryStateCity();
		
		
	}
	private void createCountryStateCity(){
		_log.info("Inside creatingCountryStateCity");
		creatingCountry();
		
	}
	
	private void creatingCountry() {
		_log.info("Inside Country.....");
		String countriesFile = CreateLocation.class.getResource("Countries.txt").getPath();
		File citiesFile = new File(countriesFile.replace("%20", " "));
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(citiesFile));
			
			String currentLine = "";
			String[] values = null;
			Countries countries = null;
			long counter = 0;
			
			while((currentLine = br.readLine())!= null){
				
				 values = currentLine.split(",");
				
				 _log.info((counter++)+"Creating Country : "+values[2]);
				 countries  = CountriesLocalServiceUtil.fetchCountries(Long.valueOf(values[0]));
				
				 if(Validator.isNull(countries)){
					countries = CountriesLocalServiceUtil.createCountries(Long.valueOf(values[0]));
					countries.setShortname(values[1]);
					countries.setCountryName(values[2]);
					CountriesLocalServiceUtil.updateCountries(countries);
				 }     
			}
			
		} catch (IOException e) {
		_log.error(e.getMessage());
		}
		
		 catch (SystemException e) {
				_log.error(e.getMessage());
		}
		
	}

	
	

}
