package org.pentaho.mantle.client.utils;

import org.pentaho.gwt.widgets.client.utils.string.StringUtils;

import com.google.gwt.regexp.shared.RegExp;

public class NameUtils {

//  public static boolean isRepositoryObjectNameValid(String name){
//    String prohib = "\\/\\:\\[\\]\\*'\"\\|,\\.\\?;\\\\"; //$NON-NLS-1$
//    String prohibited = "[^"+prohib+"]*["+prohib+"]+.*"; //$NON-NLS-1$
//    if ( name.matches( prohibited ) ) {
//      return false;
//    }
//    return true;
//  }
  
  private static final RegExp containsReservedCharsPattern = makePattern();
  
  /**
   * Checks for presence of reserved chars as well as illegal permutations of legal chars.
   */
  public static boolean isValidFolderName( final String name ) {
    if ( StringUtils.isEmpty( name ) || // not null, not empty, and not all whitespace
        !name.trim().equals( name ) || // no leading or trailing whitespace
        containsReservedCharsPattern.test( name ) || // no reserved characters
        ".".equals( name ) || // no . //$NON-NLS-1$
        "..".equals( name ) ) { // no .. //$NON-NLS-1$
      return false;
    }
    return true;
  }
  
  public static boolean isValidFileName( final String name) {
    if ( StringUtils.isEmpty( name ) || // not null, not empty, and not all whitespace
        !name.trim().equals( name ) || // no leading or trailing whitespace
        containsReservedCharsPattern.test( name ) ) { // no reserved characters
      return false;
    }
    return true;
  }

  private static RegExp makePattern() {
    // escape all reserved characters as they may have special meaning to regex engine
    StringBuilder buf = new StringBuilder();
    buf.append( ".*[" ); //$NON-NLS-1$
    for ( Character ch : getReservedChars().toCharArray() ) {
      buf.append( "\\" ); //$NON-NLS-1$
      buf.append( ch );
    }
    buf.append( "]+.*" ); //$NON-NLS-1$
    return RegExp.compile( buf.toString() );
  }
  
  /**
   * Returns human readable list of reserved characters with the separator string inserted between each
   * character started with the second character and ending after the n-1 character.
   * 
   * @param separatorString
   * @return
   */
  public static String ReservedCharListForDisplay( String separatorString ) {
    StringBuilder sb = new StringBuilder();
    String reservedChars = getReservedChars();
    for ( int i = 0; i < reservedChars.length(); i++ ) {
      if ( !String.valueOf( reservedChars.charAt( i ) ).trim().isEmpty() ) {
        sb.append( reservedChars.charAt( i ) );
        sb.append( separatorString );
      }
    }
    if ( sb.length() > 0 ) {
      sb.delete( sb.length() - separatorString.length(), sb.length() - 1 );
    }
    return sb.toString();
  }
  
  public static native String getReservedChars()
  /*-{
    return $wnd.RESERVED_CHARS; 
  }-*/;
        
  }

