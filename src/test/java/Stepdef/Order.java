package Stepdef;

import java.util.Map;

import org.openqa.selenium.interactions.Actions;

import Base.testBase;
import Pages.CheckOutPage;
import Pages.CheckOutSummaryPage;
import Pages.HomePage;
import Pages.ListOfProductAfterSearchPage;
import Pages.SelectProduct;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Order {

	HomePage homePage = new HomePage();
	ListOfProductAfterSearchPage productList = new ListOfProductAfterSearchPage();
	SelectProduct selectProduct = new SelectProduct();
	CheckOutPage checkOut = new CheckOutPage();
	CheckOutSummaryPage checkOutSummary = new CheckOutSummaryPage();
	testBase testbase = new testBase();

	@Given("^I launch the page$")
	public void i_launch_the_page() {
		testBase.driver.get(testBase.reader.getUrl());
	}

	@When("^i hover on Women$")
	public void i_hover_on_Women() {

		Actions action = new Actions(testbase.driver);
		action.moveToElement(homePage.HoverWomen).clickAndHold().perform();
		

	}

	@And("^i select T-shirt$")
	public void i_select_T_shirt() throws InterruptedException {
		Thread.sleep(2000);
		homePage.SelectTshirt.click();


	}

	@And("^i can see list of T-shirt page$")
	public void i_can_see_list_of_T_shirt_page() {
		Assert.assertTrue(productList.searchPageTitle.isDisplayed());
	}
	@And("^click on a product$")
	public void click_on_a_product() throws InterruptedException {
		productList.TshirtThumbnail.click();
		Thread.sleep(6000);


	}

	@And("^buy \"([^\"]*)\" T-shirt$")
	public void buy_T_shirt(String number) throws InterruptedException {

		// testBase.driver.switchTo().frame("fancybox-frame1587024148811");
		selectProduct.numberOfProduct.clear();

		selectProduct.numberOfProduct.sendKeys(number);

	}



	@And("^select \"([^\"]*)\" color$")
	public void select_color(String color) {
		String x = selectProduct.selectColor.getAttribute("name");
		if (color == x) {
			selectProduct.selectColor.click();
		}


	}

	@And("^click on Add to card$")
	public void click_on_Add_to_card() throws InterruptedException {
		selectProduct.addToCard.click();
		Thread.sleep(3000);

	}

	@And("^app shows \"([^\"]*)\" on checkout popup$")
	public void app_shows_number_of_product_is_on_checkout_popup(String expected) {
		Assert.assertEquals(expected, checkOut.TitleOfProduct.getText());
	}

	@And("^click on Proceed to checkout$")
	public void click_on_Proceed_to_checkout() {
		checkOut.clickOnCheckOutBtn.click();
	}

	@Then("^Verify information of product is$")
	public void verify_information_of_product_is(DataTable dataTable) throws Throwable {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		Assert.assertEquals(map.get("ProductNumber"), checkOutSummary.ProductNumber.getAttribute("value"));
		Assert.assertEquals(map.get("otherInfor"), checkOutSummary.otherInfor.getText());
		Assert.assertEquals(map.get("TotalPrice"), checkOutSummary.totalPrice.getText());
		

	}

	@And("^Buy one more product$")
	public void buy_more_product() throws InterruptedException {
		checkOutSummary.addMore.click();
		Thread.sleep(3000);
	}



	@And("^minus one production$")
	public void minus_one_production() throws InterruptedException {

		checkOutSummary.MinusProduct.click();
		Thread.sleep(3000);
	}


}
