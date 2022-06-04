package ru.stqa.pft.addressbook.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @Before
  public void setUp() throws Exception {
    app.init();
  }

  @After
  public void tearDown() throws Exception {
    app.stop();
  }
}
