package ro.dcsi.internship;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

public class ExtendedUser extends RawUser {
  public ExtendedUser(String id) {
    super(id, Maps.newTreeMap());
  }
  public ExtendedUser(String id, Map<String, String> attributes) {
    super(id, attributes);
  }

  public ExtendedUser(String username, String email, String firstName, String lastName) {
    super("the-magical-id", toMap("username", username, "email", email, "firstname", firstName, "lastname", lastName));
  }

  String firstName() {
    return attributes.get("firstname");
  }

  private static Map<String, String> toMap(String... values) {
    Map<String, String> result = new HashMap<>();
    for (int i = 0; i < values.length; i += 2) {
      result.put(values[i], values[i + 1]);
    }
    return result;
  }

  public String username() {
    return attributes.get("userName");
  }
}
