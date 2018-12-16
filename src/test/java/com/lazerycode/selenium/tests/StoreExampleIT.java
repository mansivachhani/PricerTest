package com.lazerycode.selenium.tests;

import static org.junit.Assert.assertEquals;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.StorePageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.Test;

public class StoreExampleIT extends DriverBase {

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    @Test
    public void buyTwoAppleFromStore() throws Exception {
        // Create a new WebDriver instance
        WebDriver driver = getDriver();

        // Go to Store URL
        driver.get("https://hoff.is/store/index.html");

        StorePageObjects storePage = new StorePageObjects();

        // Select Form values
        storePage.selectProduct("Apple");
        storePage.enterQuantity("2");
        storePage.submit();

        assertEquals("Remaining Balance =>", "9970", storePage.getRemainingBalance());
        assertEquals("Receipt Total =>", "30", storePage.getReceiptItemsTotal());
        assertEquals("Receipt VAT =>", "7.5", storePage.getReceiptTotalVAT());

        printTestLog(storePage);
    }

    @Test
    public void shouldNotBeBoughIfNotHavingEnoughBalance() throws Exception {
        // Create a new WebDriver instance
        WebDriver driver = getDriver();

        // Go to Store URL
        driver.get("https://hoff.is/store/index.html");

        StorePageObjects storePage = new StorePageObjects();

        // Select Form values
        storePage.selectProduct("TV");
        storePage.enterQuantity("3");
        storePage.submit();

        assertEquals("Message :", "Insufficient funds!", storePage.getMessage());

        assertEquals("Remaining Balance =>", "10000", storePage.getRemainingBalance());
        assertEquals("Receipt Total =>", "0", storePage.getReceiptItemsTotal());
        assertEquals("Receipt VAT =>", "0", storePage.getReceiptTotalVAT());

        printTestLog(storePage);
    }

    @Test
    public void buyAndSellTwoBananFromStore() throws Exception {
        // Create a new WebDriver instance
        WebDriver driver = getDriver();

        // Go to Store URL
        driver.get("https://hoff.is/store/index.html");

        StorePageObjects storePage = new StorePageObjects();

        // Select Form values
        storePage.selectProduct("Banana");
        storePage.enterQuantity("2");
        storePage.submit();

        assertEquals("Message :", "You bought 2 x Banana for a total of 46", storePage.getMessage());

        assertEquals("Remaining Balance =>", "9954", storePage.getRemainingBalance());
        assertEquals("Receipt Total =>", "46", storePage.getReceiptItemsTotal());
        assertEquals("Receipt VAT =>", "11.5", storePage.getReceiptTotalVAT());

        storePage.sellProduct("Banana");
        assertEquals("Message :", "You sold 2 x Banana for a total of 46", storePage.getMessage());

        assertEquals("Remaining Balance =>", "10000", storePage.getRemainingBalance());
        assertEquals("Receipt Total =>", "0", storePage.getReceiptItemsTotal());
        assertEquals("Receipt VAT =>", "0", storePage.getReceiptTotalVAT());
    }

    private void printTestLog(final StorePageObjects storePage) {
        System.out.println("====================");

        System.out.println("Remaining money is: " + storePage.getRemainingBalance());
        System.out.println("Receipt Total: " + storePage.getReceiptItemsTotal());
        System.out.println("Total Vat: " + storePage.getReceiptTotalVAT());

        System.out.println("====================");
    }
}