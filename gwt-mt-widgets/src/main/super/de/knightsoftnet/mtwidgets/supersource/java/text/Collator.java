package java.text;

public class Collator {

  private static final Collator INSTANCE = new Collator();

  public static final Collator getInstance() {
    return Collator.INSTANCE;
  }

  public native int compare(final String source, final String target) /*-{
    return source.localeCompare(target);
  }-*/;
}
