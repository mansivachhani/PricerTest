package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StorePageObjects {

    private final RemoteWebDriver driver = DriverBase.getDriver();

    private Query quantityInput = new Query(By.id("buyAmount"), driver);
    private Query productSelectBar = new Query(By.className("form-control"), driver);
    private Query buyButton = new Query(By.className("btn-primary"), driver);

    private Query remainingMoney = new Query(By.cssSelector("span#money"), driver);
    private Query message = new Query(By.cssSelector("span#message"), driver);
    private Query receiptTotal = new Query(By.cssSelector("th#totalPrice"), driver);
    private Query totalVAT = new Query(By.cssSelector("th#totalVAT"), driver);

    public StorePageObjects() throws Exception {
    }

    public StorePageObjects selectProduct(String productTerm) throws Exception {
        productSelectBar.findSelectElement().selectByVisibleText(productTerm);

        return this;
    }

    public StorePageObjects enterQuantity(String quantity) {
        quantityInput.findWebElement().clear();
        quantityInput.findWebElement().sendKeys(quantity);

        return this;
    }

    public void submit() {
        buyButton.findWebElement().click();
    }

    public String getRemainingBalance() {
        return remainingMoney.findWebElement().getText();
    }

    public void sellProduct(String productTerm) {
        new Query(By.xpath("//tbody[@id='bought']//tr[@product-name='" + productTerm + "']//th//button"), driver)
                .findWebElement().click();
    }

    public String getMessage() {
        return message.findWebElement().getText();
    }

    public String getReceiptItemsTotal() {
        return receiptTotal.findWebElement().getText();
    }

    public String getReceiptTotalVAT() {
        return totalVAT.findWebElement().getText();
    }

}