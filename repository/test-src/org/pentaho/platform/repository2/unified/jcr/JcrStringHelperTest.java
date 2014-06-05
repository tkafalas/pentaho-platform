package org.pentaho.platform.repository2.unified.jcr;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JcrStringHelperTest {
  String testString = "{}!@#$%^&*()_+-=[]:\";'<>,./?|\\'abcdefghijklmnopqrstuvwxyz";

  @Test
  public void testEncode() {
    String encoded = JcrStringHelper.pathEncode( testString );
    //System.out.println( encoded );
    String decoded = JcrStringHelper.pathDecode( encoded );
    assertEquals( testString, decoded);
  }
}
