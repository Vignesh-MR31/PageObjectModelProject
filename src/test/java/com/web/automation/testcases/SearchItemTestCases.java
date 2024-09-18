package com.web.automation.testcases;

import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.web.automation.basepages.InitiateDriver;
import com.web.automation.pages.HomePage;
import com.web.automation.pages.SearchPage;
import com.web.automation.utils.DataProviderClass;

public class SearchItemTestCases extends InitiateDriver{
	HomePage homePage = null;
	SearchPage searchPage = null;
	
	@Test(dataProvider="Data")
	public void verifyTheItemFiltered(String product) throws InterruptedException {
		homePage = new HomePage(driver);
		homePage.searchInputTextBox().sendKeys(product);
		homePage.searchButton();
		Thread.sleep(3000);
		searchPage = new SearchPage(driver);
		for(WebElement searchedProduct: searchPage.products()) {
			String actualProductText = searchedProduct.getText();
			boolean actualResult = actualProductText.contains(product);
			Assert.assertEquals(actualResult, true);
		}
	}
	
	@DataProvider(name="Data")
	public Object[][] dataProvider(Method met) throws Exception {
		Object[][] credentials = null;
		if(met.getName().equalsIgnoreCase("verifyTheItemFiltered")) {
			credentials = DataProviderClass.dataProviderMethod("Products");
		}
		return credentials;
	}

}
