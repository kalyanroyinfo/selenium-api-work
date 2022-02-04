package com.learning.kr.pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OYOHomePage {
WebDriver driver;

    @FindBy(how= How.XPATH,xpath = "//input[@id='autoComplete__home']")
    WebElement input;

    @FindBy(how= How.XPATH,xpath = "//div[contains(@class,'guestRoomPicker')]")
    WebElement guestPicker;

    @FindBy(how= How.XPATH,xpath = "//button[@class='guestRoomPickerPopUp__addRoom']")
    WebElement addRoom;

    @FindBy(how= How.XPATH,xpath = "(//span[@class='guestRoomPickerPopUp__plus'])[2]")
    WebElement plusClick;

    @FindBy(how= How.XPATH,xpath = "//div[contains(@class,'datePickerDesktop__checkInOut')]")
    WebElement openDatePickerLocator;

    @FindBy(how= How.XPATH,xpath = "(//select[@class='DateRangePicker__MonthHeaderSelect'])[1]")
    WebElement selectMonth;

    @FindBy(how= How.XPATH,xpath = "(//span[contains(text(),'10')])[1]")
    WebElement startDate;

    @FindBy(how= How.XPATH,xpath = "(//span[contains(text(),'18')])[1]")
    WebElement endDate;

    @FindBy(how= How.XPATH,xpath = "//button[@class='u-textCenter searchButton searchButton--home']")
    WebElement search;

    @FindBy(how= How.XPATH,xpath = "(//span[contains(text(),'Book Now')])[2]")
    WebElement book;


public OYOHomePage(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);
}

    public void setPlace(String place) throws InterruptedException {
        input.sendKeys(place);
        Thread.sleep(1000);
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
    }

    public void addRoom() throws InterruptedException {
        guestPicker.click();
        addRoom.click();
        Thread.sleep(2000);
        plusClick.click();
    }

    public void selectDate() throws InterruptedException {
        openDatePickerLocator.click();
        Select select=new Select(selectMonth);
        select.selectByVisibleText("February");
        startDate.click();
        endDate.click();

    }
    public void clickOnSearch() throws InterruptedException {
        search.click();

    }

    public void clickOnBook() throws InterruptedException {
        book.click();
    }
}
