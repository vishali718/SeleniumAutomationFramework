package com.orangehrm.test;

import org.testng.annotations.Test;
import com.orangehrm.base.BaseClass;

public class DummyClass2 extends BaseClass {

    @Test
    public void dummyMethod2() {
        String title = driver.getTitle();
        assert title.equals("OrangeHRM") : "Title does not match!";
        System.out.println("DummyClass2 Title: " + title);
    }
}
