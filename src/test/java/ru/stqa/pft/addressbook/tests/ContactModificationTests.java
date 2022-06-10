package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("For").withLastName("Modification").withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData contact_for_modification = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(contact_for_modification.getId()).withFirstName("edited").withLastName("edited");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withModified(contact_for_modification, contact)));
  }
}
