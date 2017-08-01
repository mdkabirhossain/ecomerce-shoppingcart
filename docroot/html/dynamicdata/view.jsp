<%@page import="com.dynamic.service.CountriesLocalServiceUtil"%>
<%@page import="com.dynamic.model.Countries"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<portlet:defineObjects />
<%
List<Countries> allCountries=CountriesLocalServiceUtil.getCountrieses(-1, -1);
%>

<div class="selectBoxSize">
				<aui:select name="country" cssClass="countries" id="countryId" required="true" onchange='countrySelected(this.value)' >
				
				<aui:option  value="" >Select Country</aui:option>
				<%for(Countries eachCountry :allCountries){ %>
				<aui:option  value="<%=eachCountry.getCountryName() %>" ><%=eachCountry.getCountryName() %></aui:option>
				<%} %>
				</aui:select>
				<aui:select name="state" cssClass="states" id="stateId"  onchange="stateSelected(this.value);" required="true">
				<aui:option value="">Select State</aui:option>
				</aui:select>
				<aui:select name="city" cssClass="cities" id="cityId">
				<aui:option value="">Select City</aui:option>
				</aui:select>
				
				
			
			<div >
				<aui:button type="submit" cssClass="btn btn-primary cust-btn" name="" value="Save & Continue"  onclick="CategoryValidation_Post(); CategoryValidation_Post();"/>
			</div>
 	
</div>
</div>
</div>
