package de.knightsoftnet.validators.shared.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

public class IbanUtil {

  private static final int BLOCK_LENGTH = 4;

  /**
   * format iban to four character blocks.
   *
   * @param pstring string to format
   * @return formated string
   */
  public static String ibanFormat(final String pstring) {
    if (pstring == null) {
      return null;
    }
    final StringBuilder ibanSb = new StringBuilder();
    int pos = 0;
    for (final char charCode : pstring.toCharArray()) {
      if (CharUtils.isAsciiAlphaUpper(charCode) || CharUtils.isAsciiNumeric(charCode)) {
        if (pos > 0 && pos % BLOCK_LENGTH == 0) {
          ibanSb.append(' ');
        }
        ibanSb.append(charCode);
        pos++;
      }
    }
    return ibanSb.toString();
  }

  /**
   * compress iban, remove all blanks inside.
   *
   * @param pstring string to compress
   * @return iban without spaces
   */
  public static String ibanCompress(final String pstring) {
    if (pstring == null) {
      return null;
    }
    return pstring.replaceAll("\\s", StringUtils.EMPTY);
  }
}
