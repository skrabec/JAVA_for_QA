package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
            .withId(contact_for_modification.getId()).withFirstName("eblan").withLastName("eblanych");
    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withModified(contact_for_modification, contact)));
  }
}
