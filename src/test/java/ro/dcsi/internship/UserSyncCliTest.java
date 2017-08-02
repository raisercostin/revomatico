package ro.dcsi.internship;

import java.net.UnknownHostException;

import org.junit.Test;

public class UserSyncCliTest {

  @Test
  public void test() {
    UserSyncCli.main(new String[] { "--forgerock", "http://dcs-xps:8080", "openidm-admin", "openidm-admin", "--csv",
        "target/export2.csv" });
  }

  @Test
  public void testRestoreToForgeRock() {
    UserSyncCli.main(new String[] { "--csv", "src/test/resources/export3.csv", "--forgerock", "http://dcs-xps:8080",
        "openidm-admin", "openidm-admin" });
  }
  //@Test(expected=UnknownHostException.class)
  @Test(expected=WrappedCheckedException.class)
  public void testHostInvalid() {
    UserSyncCli.main(new String[] { "--csv", "src/test/resources/export3.csv", "--forgerock", "http://dcs-x2ps:1080",
        "openidm-admin", "openidm-admin" });
  }
  //@Test(expected=UnknownHostException.class)
  @Test(expected=WrappedCheckedException.class)
  public void testPortInvalid() {
    UserSyncCli.main(new String[] { "--csv", "src/test/resources/export3.csv", "--forgerock", "http://dcs-xps:8082",
        "openidm-admin", "openidm-admin" });
  }

  @Test(expected = NullPointerException.class)
  public void testAppDirectly() {
    new UserSyncApp().export(null, null);
  }
}
