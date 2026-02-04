package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
public class DummyClass extends BaseClass {

    @Test
    public void dummyMethod() {
        String title = driver.getTitle();
        assert title.equals("OrangeHRM") : "Title does not match!";
        System.out.println("Title matches: " + title);
    }
}