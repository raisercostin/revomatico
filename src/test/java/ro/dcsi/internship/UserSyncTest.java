package ro.dcsi.internship;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import static ro.dcsi.internship.OpenIdConfig.*;

public class UserSyncTest {
  @Test
  public void csvBackupTest() {
    CsvExporter exporter = new CsvExporter("target/csvBackupTest.csv");
    ForgeRockDB db = new ForgeRockDB(openIDMServer, openIDMUsername, openIDMPassword);

    exporter.export(db.iterator());
  }

  @Test
  public void csvRestoreTest() {
    CsvReader reader = new CsvReader("src/test/resources/CSV/csvRestoreTest.csv", ',');
    List<User> users = reader.readUsers();
    ForgeRockDB db = new ForgeRockDB(openIDMServer, openIDMUsername, openIDMPassword);

    for (User user : users) {
      db.deleteUser(user.getId());
    }
    for (User user : users) {
      assertFalse(db.userExists(user.getId()));
    }
    for (User user : users) {
      db.addUser(user);
    }
    for (User user : users) {
      User user2 = db.getUser(user.getId()).get();
      for (String attr : user2.getAttributeSet()) {
        if (!attr.equals("_rev")) {
          assertEquals(user.getAttributeValue(attr), user2.getAttributeValue(attr));
        }
      }
      for (String attr : user.getAttributeSet()) {
        if (!attr.equals("_rev")) {
          assertEquals(user.getAttributeValue(attr), user2.getAttributeValue(attr));
        }
      }
    }
  }
}
