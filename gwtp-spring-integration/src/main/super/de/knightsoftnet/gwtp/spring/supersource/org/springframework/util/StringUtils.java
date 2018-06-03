/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springframework.util;

import com.google.gwt.core.shared.GwtIncompatible;

import org.springframework.lang.Nullable;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * Miscellaneous {@link String} utility methods.
 *
 * <p>
 * Mainly for internal use within the framework; consider
 * <a href="http://commons.apache.org/proper/commons-lang/">Apache's Commons Lang</a> for a more
 * comprehensive suite of {@code String} utilities.
 *
 * <p>
 * This class delivers some simple functionality that should really be provided by the core Java
 * {@link String} and {@link StringBuilder} classes. It also provides easy-to-use methods to convert
 * between delimited strings, such as CSV strings, and collections and arrays.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Keith Donald
 * @author Rob Harrop
 * @author Rick Evans
 * @author Arjen Poutsma
 * @author Sam Brannen
 * @author Brian Clozel
 * @since 16 April 2001
 */
public abstract class StringUtils {

  private static final String FOLDER_SEPARATOR = "/";

  private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

  private static final String TOP_PATH = "..";

  private static final String CURRENT_PATH = ".";

  private static final char EXTENSION_SEPARATOR = '.';


  // ---------------------------------------------------------------------
  // General convenience methods for working with Strings
  // ---------------------------------------------------------------------

  /**
   * Check whether the given {@code String} is empty.
   * <p>
   * This method accepts any Object as an argument, comparing it to {@code null} and the empty
   * String. As a consequence, this method will never return {@code true} for a non-null non-String
   * object.
   * <p>
   * The Object signature is useful for general attribute handling code that commonly deals with
   * Strings but generally has to iterate over Objects since attributes may e.g. be primitive value
   * objects as well.
   *
   * @param str the candidate String
   * @since 3.2.1
   */
  public static boolean isEmpty(@Nullable final Object str) {
    return str == null || "".equals(str);
  }

  /**
   * Check that the given {@code CharSequence} is neither {@code null} nor of length 0.
   * <p>
   * Note: this method returns {@code true} for a {@code CharSequence} that purely consists of
   * whitespace.
   * <p>
   *
   * <pre class="code">
   * StringUtils.hasLength(null) = false
   * StringUtils.hasLength("") = false
   * StringUtils.hasLength(" ") = true
   * StringUtils.hasLength("Hello") = true
   * </pre>
   *
   * @param str the {@code CharSequence} to check (may be {@code null})
   * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
   * @see #hasText(String)
   */
  public static boolean hasLength(@Nullable final CharSequence str) {
    return str != null && str.length() > 0;
  }

  /**
   * Check that the given {@code String} is neither {@code null} nor of length 0.
   * <p>
   * Note: this method returns {@code true} for a {@code String} that purely consists of whitespace.
   *
   * @param str the {@code String} to check (may be {@code null})
   * @return {@code true} if the {@code String} is not {@code null} and has length
   * @see #hasLength(CharSequence)
   * @see #hasText(String)
   */
  public static boolean hasLength(@Nullable final String str) {
    return str != null && !str.isEmpty();
  }

  /**
   * Check whether the given {@code CharSequence} contains actual <em>text</em>.
   * <p>
   * More specifically, this method returns {@code true} if the {@code CharSequence} is not
   * {@code null}, its length is greater than 0, and it contains at least one non-whitespace
   * character.
   * <p>
   *
   * <pre class="code">
   * StringUtils.hasText(null) = false
   * StringUtils.hasText("") = false
   * StringUtils.hasText(" ") = false
   * StringUtils.hasText("12345") = true
   * StringUtils.hasText(" 12345 ") = true
   * </pre>
   *
   * @param str the {@code CharSequence} to check (may be {@code null})
   * @return {@code true} if the {@code CharSequence} is not {@code null}, its length is greater
   *         than 0, and it does not contain whitespace only
   * @see Character#isWhitespace
   */
  public static boolean hasText(@Nullable final CharSequence str) {
    return str != null && str.length() > 0 && StringUtils.containsText(str);
  }

  /**
   * Check whether the given {@code String} contains actual <em>text</em>.
   * <p>
   * More specifically, this method returns {@code true} if the {@code String} is not {@code null},
   * its length is greater than 0, and it contains at least one non-whitespace character.
   *
   * @param str the {@code String} to check (may be {@code null})
   * @return {@code true} if the {@code String} is not {@code null}, its length is greater than 0,
   *         and it does not contain whitespace only
   * @see #hasText(CharSequence)
   */
  public static boolean hasText(@Nullable final String str) {
    return str != null && !str.isEmpty() && StringUtils.containsText(str);
  }

  private static boolean containsText(final CharSequence str) {
    final int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check whether the given {@code CharSequence} contains any whitespace characters.
   *
   * @param str the {@code CharSequence} to check (may be {@code null})
   * @return {@code true} if the {@code CharSequence} is not empty and contains at least 1
   *         whitespace character
   * @see Character#isWhitespace
   */
  public static boolean containsWhitespace(@Nullable final CharSequence str) {
    if (!StringUtils.hasLength(str)) {
      return false;
    }

    final int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check whether the given {@code String} contains any whitespace characters.
   *
   * @param str the {@code String} to check (may be {@code null})
   * @return {@code true} if the {@code String} is not empty and contains at least 1 whitespace
   *         character
   * @see #containsWhitespace(CharSequence)
   */
  public static boolean containsWhitespace(@Nullable final String str) {
    return StringUtils.containsWhitespace((CharSequence) str);
  }

  /**
   * Trim leading and trailing whitespace from the given {@code String}.
   *
   * @param str the {@code String} to check
   * @return the trimmed {@code String}
   * @see java.lang.Character#isWhitespace
   */
  public static String trimWhitespace(final String str) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    int beginIndex = 0;
    int endIndex = str.length() - 1;

    while (beginIndex <= endIndex && Character.isWhitespace(str.charAt(beginIndex))) {
      beginIndex++;
    }

    while (endIndex > beginIndex && Character.isWhitespace(str.charAt(endIndex))) {
      endIndex--;
    }

    return str.substring(beginIndex, endIndex + 1);
  }

  /**
   * Trim <i>all</i> whitespace from the given {@code String}: leading, trailing, and in between
   * characters.
   *
   * @param str the {@code String} to check
   * @return the trimmed {@code String}
   * @see java.lang.Character#isWhitespace
   */
  public static String trimAllWhitespace(final String str) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final int len = str.length();
    final StringBuilder sb = new StringBuilder(str.length());
    for (int i = 0; i < len; i++) {
      final char c = str.charAt(i);
      if (!Character.isWhitespace(c)) {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * Trim leading whitespace from the given {@code String}.
   *
   * @param str the {@code String} to check
   * @return the trimmed {@code String}
   * @see java.lang.Character#isWhitespace
   */
  public static String trimLeadingWhitespace(final String str) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }

  /**
   * Trim trailing whitespace from the given {@code String}.
   *
   * @param str the {@code String} to check
   * @return the trimmed {@code String}
   * @see java.lang.Character#isWhitespace
   */
  public static String trimTrailingWhitespace(final String str) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * Trim all occurrences of the supplied leading character from the given {@code String}.
   *
   * @param str the {@code String} to check
   * @param leadingCharacter the leading character to be trimmed
   * @return the trimmed {@code String}
   */
  public static String trimLeadingCharacter(final String str, final char leadingCharacter) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }

  /**
   * Trim all occurrences of the supplied trailing character from the given {@code String}.
   *
   * @param str the {@code String} to check
   * @param trailingCharacter the trailing character to be trimmed
   * @return the trimmed {@code String}
   */
  public static String trimTrailingCharacter(final String str, final char trailingCharacter) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * Test if the given {@code String} starts with the specified prefix, ignoring upper/lower case.
   *
   * @param str the {@code String} to check
   * @param prefix the prefix to look for
   * @see java.lang.String#startsWith
   */
  public static boolean startsWithIgnoreCase(@Nullable final String str,
      @Nullable final String prefix) {
    return str != null && prefix != null && str.length() >= prefix.length()
        && str.regionMatches(true, 0, prefix, 0, prefix.length());
  }

  /**
   * Test if the given {@code String} ends with the specified suffix, ignoring upper/lower case.
   *
   * @param str the {@code String} to check
   * @param suffix the suffix to look for
   * @see java.lang.String#endsWith
   */
  public static boolean endsWithIgnoreCase(@Nullable final String str,
      @Nullable final String suffix) {
    return str != null && suffix != null && str.length() >= suffix.length()
        && str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length());
  }

  /**
   * Test whether the given string matches the given substring at the given index.
   *
   * @param str the original string (or StringBuilder)
   * @param index the index in the original string to start matching against
   * @param substring the substring to match at the given index
   */
  public static boolean substringMatch(final CharSequence str, final int index,
      final CharSequence substring) {
    if (index + substring.length() > str.length()) {
      return false;
    }
    for (int i = 0; i < substring.length(); i++) {
      if (str.charAt(index + i) != substring.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Count the occurrences of the substring {@code sub} in string {@code str}.
   *
   * @param str string to search in
   * @param sub string to search for
   */
  public static int countOccurrencesOf(final String str, final String sub) {
    if (!StringUtils.hasLength(str) || !StringUtils.hasLength(sub)) {
      return 0;
    }

    int count = 0;
    int pos = 0;
    int idx;
    while ((idx = str.indexOf(sub, pos)) != -1) {
      ++count;
      pos = idx + sub.length();
    }
    return count;
  }

  /**
   * Replace all occurrences of a substring within a string with another string.
   *
   * @param inString {@code String} to examine
   * @param oldPattern {@code String} to replace
   * @param newPattern {@code String} to insert
   * @return a {@code String} with the replacements
   */
  public static String replace(final String inString, final String oldPattern,
      @Nullable final String newPattern) {
    if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(oldPattern)
        || newPattern == null) {
      return inString;
    }
    int index = inString.indexOf(oldPattern);
    if (index == -1) {
      // no occurrence -> can return input as-is
      return inString;
    }

    int capacity = inString.length();
    if (newPattern.length() > oldPattern.length()) {
      capacity += 16;
    }
    final StringBuilder sb = new StringBuilder(capacity);

    int pos = 0; // our position in the old string
    final int patLen = oldPattern.length();
    while (index >= 0) {
      sb.append(inString.substring(pos, index));
      sb.append(newPattern);
      pos = index + patLen;
      index = inString.indexOf(oldPattern, pos);
    }

    // append any characters to the right of a match
    sb.append(inString.substring(pos));
    return sb.toString();
  }

  /**
   * Delete all occurrences of the given substring.
   *
   * @param inString the original {@code String}
   * @param pattern the pattern to delete all occurrences of
   * @return the resulting {@code String}
   */
  public static String delete(final String inString, final String pattern) {
    return StringUtils.replace(inString, pattern, "");
  }

  /**
   * Delete any character in a given {@code String}.
   *
   * @param inString the original {@code String}
   * @param charsToDelete a set of characters to delete. E.g. "az\n" will delete 'a's, 'z's and new
   *        lines.
   * @return the resulting {@code String}
   */
  public static String deleteAny(final String inString, @Nullable final String charsToDelete) {
    if (!StringUtils.hasLength(inString) || !StringUtils.hasLength(charsToDelete)) {
      return inString;
    }

    final StringBuilder sb = new StringBuilder(inString.length());
    for (int i = 0; i < inString.length(); i++) {
      final char c = inString.charAt(i);
      if (charsToDelete.indexOf(c) == -1) {
        sb.append(c);
      }
    }
    return sb.toString();
  }


  // ---------------------------------------------------------------------
  // Convenience methods for working with formatted Strings
  // ---------------------------------------------------------------------

  /**
   * Quote the given {@code String} with single quotes.
   *
   * @param str the input {@code String} (e.g. "myString")
   * @return the quoted {@code String} (e.g. "'myString'"), or {@code null} if the input was
   *         {@code null}
   */
  @Nullable
  public static String quote(@Nullable final String str) {
    return str != null ? "'" + str + "'" : null;
  }

  /**
   * Turn the given Object into a {@code String} with single quotes if it is a {@code String};
   * keeping the Object as-is else.
   *
   * @param obj the input Object (e.g. "myString")
   * @return the quoted {@code String} (e.g. "'myString'"), or the input object as-is if not a
   *         {@code String}
   */
  @Nullable
  public static Object quoteIfString(@Nullable final Object obj) {
    return obj instanceof String ? StringUtils.quote((String) obj) : obj;
  }

  /**
   * Unqualify a string qualified by a '.' dot character. For example, "this.name.is.qualified",
   * returns "qualified".
   *
   * @param qualifiedName the qualified name
   */
  public static String unqualify(final String qualifiedName) {
    return StringUtils.unqualify(qualifiedName, '.');
  }

  /**
   * Unqualify a string qualified by a separator character. For example, "this:name:is:qualified"
   * returns "qualified" if using a ':' separator.
   *
   * @param qualifiedName the qualified name
   * @param separator the separator
   */
  public static String unqualify(final String qualifiedName, final char separator) {
    return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
  }

  /**
   * Capitalize a {@code String}, changing the first letter to upper case as per
   * {@link Character#toUpperCase(char)}. No other letters are changed.
   *
   * @param str the {@code String} to capitalize
   * @return the capitalized {@code String}
   */
  public static String capitalize(final String str) {
    return StringUtils.changeFirstCharacterCase(str, true);
  }

  /**
   * Uncapitalize a {@code String}, changing the first letter to lower case as per
   * {@link Character#toLowerCase(char)}. No other letters are changed.
   *
   * @param str the {@code String} to uncapitalize
   * @return the uncapitalized {@code String}
   */
  public static String uncapitalize(final String str) {
    return StringUtils.changeFirstCharacterCase(str, false);
  }

  private static String changeFirstCharacterCase(final String str, final boolean capitalize) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }

    final char baseChar = str.charAt(0);
    char updatedChar;
    if (capitalize) {
      updatedChar = Character.toUpperCase(baseChar);
    } else {
      updatedChar = Character.toLowerCase(baseChar);
    }
    if (baseChar == updatedChar) {
      return str;
    }

    final char[] chars = str.toCharArray();
    chars[0] = updatedChar;
    return new String(chars, 0, chars.length);
  }

  /**
   * Extract the filename from the given Java resource path, e.g.
   * {@code "mypath/myfile.txt" -> "myfile.txt"}.
   *
   * @param path the file path (may be {@code null})
   * @return the extracted filename, or {@code null} if none
   */
  @Nullable
  public static String getFilename(@Nullable final String path) {
    if (path == null) {
      return null;
    }

    final int separatorIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    return separatorIndex != -1 ? path.substring(separatorIndex + 1) : path;
  }

  /**
   * Extract the filename extension from the given Java resource path, e.g. "mypath/myfile.txt" ->
   * "txt".
   *
   * @param path the file path (may be {@code null})
   * @return the extracted filename extension, or {@code null} if none
   */
  @Nullable
  public static String getFilenameExtension(@Nullable final String path) {
    if (path == null) {
      return null;
    }

    final int extIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    if (extIndex == -1) {
      return null;
    }

    final int folderIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    if (folderIndex > extIndex) {
      return null;
    }

    return path.substring(extIndex + 1);
  }

  /**
   * Strip the filename extension from the given Java resource path, e.g. "mypath/myfile.txt" ->
   * "mypath/myfile".
   *
   * @param path the file path
   * @return the path with stripped filename extension
   */
  public static String stripFilenameExtension(final String path) {
    final int extIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    if (extIndex == -1) {
      return path;
    }

    final int folderIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    if (folderIndex > extIndex) {
      return path;
    }

    return path.substring(0, extIndex);
  }

  /**
   * Apply the given relative path to the given Java resource path, assuming standard Java folder
   * separation (i.e. "/" separators).
   *
   * @param path the path to start from (usually a full file path)
   * @param relativePath the relative path to apply (relative to the full file path above)
   * @return the full file path that results from applying the relative path
   */
  public static String applyRelativePath(final String path, final String relativePath) {
    final int separatorIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    if (separatorIndex != -1) {
      String newPath = path.substring(0, separatorIndex);
      if (!relativePath.startsWith(StringUtils.FOLDER_SEPARATOR)) {
        newPath += StringUtils.FOLDER_SEPARATOR;
      }
      return newPath + relativePath;
    } else {
      return relativePath;
    }
  }

  /**
   * Normalize the path by suppressing sequences like "path/.." and inner simple dots.
   * <p>
   * The result is convenient for path comparison. For other uses, notice that Windows separators
   * ("\") are replaced by simple slashes.
   *
   * @param path the original path
   * @return the normalized path
   */
  public static String cleanPath(final String path) {
    if (!StringUtils.hasLength(path)) {
      return path;
    }
    String pathToUse = StringUtils.replace(path, StringUtils.WINDOWS_FOLDER_SEPARATOR,
        StringUtils.FOLDER_SEPARATOR);

    // Strip prefix from path to analyze, to not treat it as part of the
    // first path element. This is necessary to correctly parse paths like
    // "file:core/../core/io/Resource.class", where the ".." should just
    // strip the first "core" directory while keeping the "file:" prefix.
    final int prefixIndex = pathToUse.indexOf(':');
    String prefix = "";
    if (prefixIndex != -1) {
      prefix = pathToUse.substring(0, prefixIndex + 1);
      if (prefix.contains("/")) {
        prefix = "";
      } else {
        pathToUse = pathToUse.substring(prefixIndex + 1);
      }
    }
    if (pathToUse.startsWith(StringUtils.FOLDER_SEPARATOR)) {
      prefix = prefix + StringUtils.FOLDER_SEPARATOR;
      pathToUse = pathToUse.substring(1);
    }

    final String[] pathArray =
        StringUtils.delimitedListToStringArray(pathToUse, StringUtils.FOLDER_SEPARATOR);
    final List<String> pathElements = new LinkedList<>();
    int tops = 0;

    for (int i = pathArray.length - 1; i >= 0; i--) {
      final String element = pathArray[i];
      if (StringUtils.CURRENT_PATH.equals(element)) { // NOPMD
        // Points to current directory - drop it.
      } else if (StringUtils.TOP_PATH.equals(element)) {
        // Registering top path found.
        tops++;
      } else {
        if (tops > 0) {
          // Merging path element with element corresponding to top path.
          tops--;
        } else {
          // Normal path element found.
          pathElements.add(0, element);
        }
      }
    }

    // Remaining top paths need to be retained.
    for (int i = 0; i < tops; i++) {
      pathElements.add(0, StringUtils.TOP_PATH);
    }

    return prefix
        + StringUtils.collectionToDelimitedString(pathElements, StringUtils.FOLDER_SEPARATOR);
  }

  /**
   * Compare two paths after normalization of them.
   *
   * @param path1 first path for comparison
   * @param path2 second path for comparison
   * @return whether the two paths are equivalent after normalization
   */
  public static boolean pathEquals(final String path1, final String path2) {
    return StringUtils.cleanPath(path1).equals(StringUtils.cleanPath(path2));
  }

  /**
   * Decode the given encoded URI component value. Based on the following rules:
   * <ul>
   * <li>Alphanumeric characters {@code "a"} through {@code "z"}, {@code "A"} through {@code "Z"},
   * and {@code "0"} through {@code "9"} stay the same.</li>
   * <li>Special characters {@code "-"}, {@code "_"}, {@code "."}, and {@code "*"} stay the
   * same.</li>
   * <li>A sequence "{@code %<i>xy</i>}" is interpreted as a hexadecimal representation of the
   * character.</li>
   * </ul>
   *
   * @param source the encoded String
   * @param charset the character set
   * @return the decoded value
   * @throws IllegalArgumentException when the given source contains invalid encoded sequences
   * @since 5.0
   * @see java.net.URLDecoder#decode(String, String)
   */
  public static String uriDecode(final String source, final Charset charset) {
    final int length = source.length();
    if (length == 0) {
      return source;
    }
    Assert.notNull(charset, "Charset must not be null");

    final ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
    boolean changed = false;
    for (int i = 0; i < length; i++) {
      final int ch = source.charAt(i);
      if (ch == '%') {
        if (i + 2 < length) {
          final char hex1 = source.charAt(i + 1);
          final char hex2 = source.charAt(i + 2);
          final int u = Character.digit(hex1, 16);
          final int l = Character.digit(hex2, 16);
          if (u == -1 || l == -1) {
            throw new IllegalArgumentException(
                "Invalid encoded sequence \"" + source.substring(i) + "\"");
          }
          bos.write((char) ((u << 4) + l));
          i += 2;
          changed = true;
        } else {
          throw new IllegalArgumentException(
              "Invalid encoded sequence \"" + source.substring(i) + "\"");
        }
      } else {
        bos.write(ch);
      }
    }
    return changed ? new String(bos.toByteArray(), charset) : source;
  }

  /**
   * Parse the given {@code String} value into a {@link Locale}, accepting the
   * {@link Locale#toString} format as well as BCP 47 language tags.
   *
   * @param localeValue the locale value: following either {@code Locale's} {@code toString()}
   *        format ("en", "en_UK", etc), also accepting spaces as separators (as an alternative to
   *        underscores), or BCP 47 (e.g. "en-UK") as specified by {@link Locale#forLanguageTag} on
   *        Java 7+
   * @return a corresponding {@code Locale} instance, or {@code null} if none
   * @throws IllegalArgumentException in case of an invalid locale specification
   * @since 5.0.4
   * @see #parseLocaleString
   * @see Locale#forLanguageTag
   */
  @GwtIncompatible("incompatible method")
  @Nullable
  public static Locale parseLocale(final String localeValue) {
    final String[] tokens = StringUtils.tokenizeLocaleSource(localeValue);
    if (tokens.length == 1) {
      return Locale.ENGLISH;
    }
    return StringUtils.parseLocaleTokens(localeValue, tokens);
  }

  /**
   * Parse the given {@code String} representation into a {@link Locale}.
   * <p>
   * This is the inverse operation of {@link Locale#toString Locale's toString}.
   *
   * @param localeString the locale {@code String}: following {@code Locale's} {@code toString()}
   *        format ("en", "en_UK", etc), also accepting spaces as separators (as an alternative to
   *        underscores)
   *        <p>
   *        Note: This variant does not accept the BCP 47 language tag format. Please use
   *        {@link #parseLocale} for lenient parsing of both formats.
   * @return a corresponding {@code Locale} instance, or {@code null} if none
   * @throws IllegalArgumentException in case of an invalid locale specification
   */
  @GwtIncompatible("incompatible method")
  @Nullable
  public static Locale parseLocaleString(final String localeString) {
    return StringUtils.parseLocaleTokens(localeString,
        StringUtils.tokenizeLocaleSource(localeString));
  }

  @GwtIncompatible("incompatible method")
  private static String[] tokenizeLocaleSource(final String localeSource) {
    return StringUtils.tokenizeToStringArray(localeSource, "_ ", false, false);
  }

  @GwtIncompatible("incompatible method")
  @Nullable
  private static Locale parseLocaleTokens(final String localeString, final String[] tokens) {
    final String language = tokens.length > 0 ? tokens[0] : "";
    final String country = tokens.length > 1 ? tokens[1] : "";
    StringUtils.validateLocalePart(language);
    StringUtils.validateLocalePart(country);

    String variant = "";
    if (tokens.length > 2) {
      // There is definitely a variant, and it is everything after the country
      // code sans the separator between the country code and the variant.
      final int endIndexOfCountryCode =
          localeString.indexOf(country, language.length()) + country.length();
      // Strip off any leading '_' and whitespace, what's left is the variant.
      variant = StringUtils.trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
      if (variant.startsWith("_")) {
        variant = StringUtils.trimLeadingCharacter(variant, '_');
      }
    }
    return language.length() > 0 ? Locale.ENGLISH : null;
  }

  @GwtIncompatible("incompatible method")
  private static void validateLocalePart(final String localePart) {
    for (int i = 0; i < localePart.length(); i++) {
      final char ch = localePart.charAt(i);
      if (ch != ' ' && ch != '_' && ch != '#' && !Character.isLetterOrDigit(ch)) {
        throw new IllegalArgumentException(
            "Locale part \"" + localePart + "\" contains invalid characters");
      }
    }
  }

  /**
   * Determine the RFC 3066 compliant language tag, as used for the HTTP "Accept-Language" header.
   *
   * @param locale the Locale to transform to a language tag
   * @return the RFC 3066 compliant language tag as {@code String}
   * @deprecated as of 5.0.4, in favor of {@link Locale#toLanguageTag()}
   */
  @GwtIncompatible("incompatible method")
  @Deprecated
  public static String toLanguageTag(final Locale locale) {
    return locale.toLanguageTag();
  }

  /**
   * Parse the given {@code timeZoneString} value into a {@link TimeZone}.
   *
   * @param timeZoneString the time zone {@code String}, following
   *        {@link TimeZone#getTimeZone(String)} but throwing {@link IllegalArgumentException} in
   *        case of an invalid time zone specification
   * @return a corresponding {@link TimeZone} instance
   * @throws IllegalArgumentException in case of an invalid time zone specification
   */
  @GwtIncompatible("incompatible method")
  public static TimeZone parseTimeZoneString(final String timeZoneString) {
    final TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
    if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
      // We don't want that GMT fallback...
      throw new IllegalArgumentException(
          "Invalid time zone specification '" + timeZoneString + "'");
    }
    return timeZone;
  }


  // ---------------------------------------------------------------------
  // Convenience methods for working with String arrays
  // ---------------------------------------------------------------------

  /**
   * Append the given {@code String} to the given {@code String} array, returning a new array
   * consisting of the input array contents plus the given {@code String}.
   *
   * @param array the array to append to (can be {@code null})
   * @param str the {@code String} to append
   * @return the new array (never {@code null})
   */
  public static String[] addStringToArray(@Nullable final String[] array, final String str) {
    if (ObjectUtils.isEmpty(array)) {
      return new String[] {str};
    }

    final String[] newArr = new String[array.length + 1];
    System.arraycopy(array, 0, newArr, 0, array.length);
    newArr[array.length] = str;
    return newArr;
  }

  /**
   * Concatenate the given {@code String} arrays into one, with overlapping array elements included
   * twice.
   * <p>
   * The order of elements in the original arrays is preserved.
   *
   * @param array1 the first array (can be {@code null})
   * @param array2 the second array (can be {@code null})
   * @return the new array ({@code null} if both given arrays were {@code null})
   */
  @Nullable
  public static String[] concatenateStringArrays(@Nullable final String[] array1,
      @Nullable final String[] array2) {
    if (ObjectUtils.isEmpty(array1)) {
      return array2;
    }
    if (ObjectUtils.isEmpty(array2)) {
      return array1;
    }

    final String[] newArr = new String[array1.length + array2.length];
    System.arraycopy(array1, 0, newArr, 0, array1.length);
    System.arraycopy(array2, 0, newArr, array1.length, array2.length);
    return newArr;
  }

  /**
   * Merge the given {@code String} arrays into one, with overlapping array elements only included
   * once.
   * <p>
   * The order of elements in the original arrays is preserved (with the exception of overlapping
   * elements, which are only included on their first occurrence).
   *
   * @param array1 the first array (can be {@code null})
   * @param array2 the second array (can be {@code null})
   * @return the new array ({@code null} if both given arrays were {@code null})
   * @deprecated as of 4.3.15, in favor of manual merging via {@link LinkedHashSet} (with every
   *             entry included at most once, even entries within the first array)
   */
  @Deprecated
  @Nullable
  public static String[] mergeStringArrays(@Nullable final String[] array1,
      @Nullable final String[] array2) {
    if (ObjectUtils.isEmpty(array1)) {
      return array2;
    }
    if (ObjectUtils.isEmpty(array2)) {
      return array1;
    }

    final List<String> result = new ArrayList<>();
    result.addAll(Arrays.asList(array1));
    for (final String str : array2) {
      if (!result.contains(str)) {
        result.add(str);
      }
    }
    return StringUtils.toStringArray(result);
  }

  /**
   * Turn given source {@code String} array into sorted array.
   *
   * @param array the source array
   * @return the sorted array (never {@code null})
   */
  public static String[] sortStringArray(final String[] array) {
    if (ObjectUtils.isEmpty(array)) {
      return new String[0];
    }

    Arrays.sort(array);
    return array;
  }

  /**
   * Copy the given {@code Collection} into a {@code String} array.
   * <p>
   * The {@code Collection} must contain {@code String} elements only.
   *
   * @param collection the {@code Collection} to copy
   * @return the {@code String} array
   */
  public static String[] toStringArray(final Collection<String> collection) {
    return collection.toArray(new String[0]);
  }

  /**
   * Copy the given Enumeration into a {@code String} array. The Enumeration must contain
   * {@code String} elements only.
   *
   * @param enumeration the Enumeration to copy
   * @return the {@code String} array
   */
  public static String[] toStringArray(final Enumeration<String> enumeration) {
    return StringUtils.toStringArray(Collections.list(enumeration));
  }

  /**
   * Trim the elements of the given {@code String} array, calling {@code String.trim()} on each of
   * them.
   *
   * @param array the original {@code String} array
   * @return the resulting array (of the same size) with trimmed elements
   */
  public static String[] trimArrayElements(@Nullable final String[] array) {
    if (ObjectUtils.isEmpty(array)) {
      return new String[0];
    }

    final String[] result = new String[array.length];
    for (int i = 0; i < array.length; i++) {
      final String element = array[i];
      result[i] = element != null ? element.trim() : null;
    }
    return result;
  }

  /**
   * Remove duplicate strings from the given array.
   * <p>
   * As of 4.2, it preserves the original order, as it uses a {@link LinkedHashSet}.
   *
   * @param array the {@code String} array
   * @return an array without duplicates, in natural sort order
   */
  public static String[] removeDuplicateStrings(final String[] array) {
    if (ObjectUtils.isEmpty(array)) {
      return array;
    }

    final Set<String> set = new LinkedHashSet<>();
    for (final String element : array) {
      set.add(element);
    }
    return StringUtils.toStringArray(set);
  }

  /**
   * Split a {@code String} at the first occurrence of the delimiter. Does not include the delimiter
   * in the result.
   *
   * @param toSplit the string to split
   * @param delimiter to split the string up with
   * @return a two element array with index 0 being before the delimiter, and index 1 being after
   *         the delimiter (neither element includes the delimiter); or {@code null} if the
   *         delimiter wasn't found in the given input {@code String}
   */
  @Nullable
  public static String[] split(@Nullable final String toSplit, @Nullable final String delimiter) {
    if (!StringUtils.hasLength(toSplit) || !StringUtils.hasLength(delimiter)) {
      return null;
    }
    final int offset = toSplit.indexOf(delimiter);
    if (offset < 0) {
      return null;
    }

    final String beforeDelimiter = toSplit.substring(0, offset);
    final String afterDelimiter = toSplit.substring(offset + delimiter.length());
    return new String[] {beforeDelimiter, afterDelimiter};
  }

  /**
   * Take an array of strings and split each element based on the given delimiter. A
   * {@code Properties} instance is then generated, with the left of the delimiter providing the
   * key, and the right of the delimiter providing the value.
   * <p>
   * Will trim both the key and value before adding them to the {@code Properties}.
   *
   * @param array the array to process
   * @param delimiter to split each element using (typically the equals symbol)
   * @return a {@code Properties} instance representing the array contents, or {@code null} if the
   *         array to process was {@code null} or empty
   */
  @GwtIncompatible("incompatible method")
  @Nullable
  public static Properties splitArrayElementsIntoProperties(final String[] array,
      final String delimiter) {
    return StringUtils.splitArrayElementsIntoProperties(array, delimiter, null);
  }

  /**
   * Take an array of strings and split each element based on the given delimiter. A
   * {@code Properties} instance is then generated, with the left of the delimiter providing the
   * key, and the right of the delimiter providing the value.
   * <p>
   * Will trim both the key and value before adding them to the {@code Properties} instance.
   *
   * @param array the array to process
   * @param delimiter to split each element using (typically the equals symbol)
   * @param charsToDelete one or more characters to remove from each element prior to attempting the
   *        split operation (typically the quotation mark symbol), or {@code null} if no removal
   *        should occur
   * @return a {@code Properties} instance representing the array contents, or {@code null} if the
   *         array to process was {@code null} or empty
   */
  @GwtIncompatible("incompatible method")
  @Nullable
  public static Properties splitArrayElementsIntoProperties(final String[] array,
      final String delimiter, @Nullable final String charsToDelete) {

    if (ObjectUtils.isEmpty(array)) {
      return null;
    }

    final Properties result = new Properties();
    for (String element : array) {
      if (charsToDelete != null) {
        element = StringUtils.deleteAny(element, charsToDelete);
      }
      final String[] splittedElement = StringUtils.split(element, delimiter);
      if (splittedElement == null) {
        continue;
      }
      result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
    }
    return result;
  }

  /**
   * Tokenize the given {@code String} into a {@code String} array via a {@link StringTokenizer}.
   * <p>
   * Trims tokens and omits empty tokens.
   * <p>
   * The given {@code delimiters} string can consist of any number of delimiter characters. Each of
   * those characters can be used to separate tokens. A delimiter is always a single character; for
   * multi-character delimiters, consider using {@link #delimitedListToStringArray}.
   *
   * @param str the {@code String} to tokenize
   * @param delimiters the delimiter characters, assembled as a {@code String} (each of the
   *        characters is individually considered as a delimiter)
   * @return an array of the tokens
   * @see java.util.StringTokenizer
   * @see String#trim()
   * @see #delimitedListToStringArray
   */
  @GwtIncompatible("incompatible method")
  public static String[] tokenizeToStringArray(@Nullable final String str,
      final String delimiters) {
    return StringUtils.tokenizeToStringArray(str, delimiters, true, true);
  }

  /**
   * Tokenize the given {@code String} into a {@code String} array via a {@link StringTokenizer}.
   * <p>
   * The given {@code delimiters} string can consist of any number of delimiter characters. Each of
   * those characters can be used to separate tokens. A delimiter is always a single character; for
   * multi-character delimiters, consider using {@link #delimitedListToStringArray}.
   *
   * @param str the {@code String} to tokenize
   * @param delimiters the delimiter characters, assembled as a {@code String} (each of the
   *        characters is individually considered as a delimiter)
   * @param trimTokens trim the tokens via {@link String#trim()}
   * @param ignoreEmptyTokens omit empty tokens from the result array (only applies to tokens that
   *        are empty after trimming; StringTokenizer will not consider subsequent delimiters as
   *        token in the first place).
   * @return an array of the tokens
   * @see java.util.StringTokenizer
   * @see String#trim()
   * @see #delimitedListToStringArray
   */
  @GwtIncompatible("incompatible method")
  public static String[] tokenizeToStringArray(@Nullable final String str, final String delimiters,
      final boolean trimTokens, final boolean ignoreEmptyTokens) {

    if (str == null) {
      return new String[0];
    }

    final StringTokenizer st = new StringTokenizer(str, delimiters);
    final List<String> tokens = new ArrayList<>();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if (!ignoreEmptyTokens || token.length() > 0) {
        tokens.add(token);
      }
    }
    return StringUtils.toStringArray(tokens);
  }

  /**
   * Take a {@code String} that is a delimited list and convert it into a {@code String} array.
   * <p>
   * A single {@code delimiter} may consist of more than one character, but it will still be
   * considered as a single delimiter string, rather than as bunch of potential delimiter
   * characters, in contrast to {@link #tokenizeToStringArray}.
   *
   * @param str the input {@code String}
   * @param delimiter the delimiter between elements (this is a single delimiter, rather than a
   *        bunch individual delimiter characters)
   * @return an array of the tokens in the list
   * @see #tokenizeToStringArray
   */
  public static String[] delimitedListToStringArray(@Nullable final String str,
      @Nullable final String delimiter) {
    return StringUtils.delimitedListToStringArray(str, delimiter, null);
  }

  /**
   * Take a {@code String} that is a delimited list and convert it into a {@code String} array.
   * <p>
   * A single {@code delimiter} may consist of more than one character, but it will still be
   * considered as a single delimiter string, rather than as bunch of potential delimiter
   * characters, in contrast to {@link #tokenizeToStringArray}.
   *
   * @param str the input {@code String}
   * @param delimiter the delimiter between elements (this is a single delimiter, rather than a
   *        bunch individual delimiter characters)
   * @param charsToDelete a set of characters to delete; useful for deleting unwanted line breaks:
   *        e.g. "\r\n\f" will delete all new lines and line feeds in a {@code String}
   * @return an array of the tokens in the list
   * @see #tokenizeToStringArray
   */
  public static String[] delimitedListToStringArray(@Nullable final String str,
      @Nullable final String delimiter, @Nullable final String charsToDelete) {

    if (str == null) {
      return new String[0];
    }
    if (delimiter == null) {
      return new String[] {str};
    }

    final List<String> result = new ArrayList<>();
    if ("".equals(delimiter)) {
      for (int i = 0; i < str.length(); i++) {
        result.add(StringUtils.deleteAny(str.substring(i, i + 1), charsToDelete));
      }
    } else {
      int pos = 0;
      int delPos;
      while ((delPos = str.indexOf(delimiter, pos)) != -1) {
        result.add(StringUtils.deleteAny(str.substring(pos, delPos), charsToDelete));
        pos = delPos + delimiter.length();
      }
      if (str.length() > 0 && pos <= str.length()) {
        // Add rest of String, but not in case of empty input.
        result.add(StringUtils.deleteAny(str.substring(pos), charsToDelete));
      }
    }
    return StringUtils.toStringArray(result);
  }

  /**
   * Convert a comma delimited list (e.g., a row from a CSV file) into an array of strings.
   *
   * @param str the input {@code String}
   * @return an array of strings, or the empty array in case of empty input
   */
  public static String[] commaDelimitedListToStringArray(@Nullable final String str) {
    return StringUtils.delimitedListToStringArray(str, ",");
  }

  /**
   * Convert a comma delimited list (e.g., a row from a CSV file) into a set.
   * <p>
   * Note that this will suppress duplicates, and as of 4.2, the elements in the returned set will
   * preserve the original order in a {@link LinkedHashSet}.
   *
   * @param str the input {@code String}
   * @return a set of {@code String} entries in the list
   * @see #removeDuplicateStrings(String[])
   */
  public static Set<String> commaDelimitedListToSet(@Nullable final String str) {
    final Set<String> set = new LinkedHashSet<>();
    final String[] tokens = StringUtils.commaDelimitedListToStringArray(str);
    for (final String token : tokens) {
      set.add(token);
    }
    return set;
  }

  /**
   * Convert a {@link Collection} to a delimited {@code String} (e.g. CSV).
   * <p>
   * Useful for {@code toString()} implementations.
   *
   * @param coll the {@code Collection} to convert
   * @param delim the delimiter to use (typically a ",")
   * @param prefix the {@code String} to start each element with
   * @param suffix the {@code String} to end each element with
   * @return the delimited {@code String}
   */
  public static String collectionToDelimitedString(@Nullable final Collection<?> coll,
      final String delim, final String prefix, final String suffix) {

    if (CollectionUtils.isEmpty(coll)) {
      return "";
    }

    final StringBuilder sb = new StringBuilder();
    final Iterator<?> it = coll.iterator();
    while (it.hasNext()) {
      sb.append(prefix).append(it.next()).append(suffix);
      if (it.hasNext()) {
        sb.append(delim);
      }
    }
    return sb.toString();
  }

  /**
   * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
   * <p>
   * Useful for {@code toString()} implementations.
   *
   * @param coll the {@code Collection} to convert
   * @param delim the delimiter to use (typically a ",")
   * @return the delimited {@code String}
   */
  public static String collectionToDelimitedString(@Nullable final Collection<?> coll,
      final String delim) {
    return StringUtils.collectionToDelimitedString(coll, delim, "", "");
  }

  /**
   * Convert a {@code Collection} into a delimited {@code String} (e.g., CSV).
   * <p>
   * Useful for {@code toString()} implementations.
   *
   * @param coll the {@code Collection} to convert
   * @return the delimited {@code String}
   */
  public static String collectionToCommaDelimitedString(final Collection<?> coll) {
    return StringUtils.collectionToDelimitedString(coll, ",");
  }

  /**
   * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
   * <p>
   * Useful for {@code toString()} implementations.
   *
   * @param arr the array to display
   * @param delim the delimiter to use (typically a ",")
   * @return the delimited {@code String}
   */
  public static String arrayToDelimitedString(@Nullable final Object[] arr, final String delim) {
    if (ObjectUtils.isEmpty(arr)) {
      return "";
    }
    if (arr.length == 1) {
      return ObjectUtils.nullSafeToString(arr[0]);
    }

    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      if (i > 0) {
        sb.append(delim);
      }
      sb.append(arr[i]);
    }
    return sb.toString();
  }

  /**
   * Convert a {@code String} array into a comma delimited {@code String} (i.e., CSV).
   * <p>
   * Useful for {@code toString()} implementations.
   *
   * @param arr the array to display
   * @return the delimited {@code String}
   */
  public static String arrayToCommaDelimitedString(@Nullable final Object[] arr) {
    return StringUtils.arrayToDelimitedString(arr, ",");
  }

}
