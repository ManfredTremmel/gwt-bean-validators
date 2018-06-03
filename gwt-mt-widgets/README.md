# gwt-mt-widgets
A set of widgets and handlers for gwt applications using gwt-bean-validators

Maven integraten
----------------

The dependency itself for GWT-Projects:

```
    <dependency>
      <groupId>de.knightsoft-net</groupId>
      <artifactId>gwt-mt-widgets</artifactId>
      <version>0.55.3</version>
    </dependency>
```
GWT Integration
---------------

What you still have to do, inherit GwtMtWidgets into your project .gwt.xml file:

```
<inherits name="de.knightsoftnet.mtwidgets.GwtMtWidgets" />
```

All widgets implement HasEditorErrors, so they directly can handle bean validation results without additional decorators (but you can use one if you want). The validation results are handled the html5 way and added with `setCustomValidity()` to the widget, so you can format the input field using the `:valid`, `:invalid` and `:required` CSS pseudo-classes.
The validation error messages can be put into a HTMLPanel widget, you can place it where ever you want in the page, all you have to do is, add it with `setValidationMessageElement(HTMLPanel)` to the widget which messages should it display. 
All widgets have html5 improvements, instead of the original gwt widgets, they understand parameters like required or placeholder, which can be simply added to the ui-binder xml file.

Included Widgets
----------------

|Widget | Usage
|-------|------
|BicSuggestBox | input widgets for SEPA BIC inputs, showing suggestions for all available numbers
|CheckBox | replacement of the GWT CheckBox, label is not included and has to be set manually
|ColorBox | a html5 color selection input widget
|CountryListBox | list box with all available countries, can be limited if needed
|CurrencyDoubleBox | input field for numeric Double values formated in the current local
|CurrencyBigDecimalleBox | input field for numeric BigDecimal values formated in the current local
|DateListWidget | a html5 date list to add suggestions to a input widget
|DateBox | a html5 date input widget
|DateTimeLocalBox | a html5 date and time input widget
|DecimalDoubleBox | a numeric input field for Double values formated in the current local
|DecimalBigDecimalBox | a numeric input field for BigDecimal values formated in the current local
|EmailTextBox | a html5 email input widget
|IbanTextBox | a input widget for IBAN numbers
|ImageLazyLoading | a image widget which adds the url into the image tag only when it's in the viewport, so no images are loaded, which are not visible. When scrolling or resizing the browser, test is done again and image loads as far it's coming into the viewport
|IbanTextBox | a input widget for IBAN numbers
|InputLabel | a display widget which generates a label tag for a widget
|IntegerBoxWithoutSeparator | a input widget for Integer values formated without separators
|Isbn10TextBox | a input widget for ISBN 10 numbers
|Isbn13TextBox | a input widget for ISBN 13 numbers
|IsbnTextBox | a input widget for ISBN 10 and 13 numbers
|LongBox | a input widget for Long values formated in the current local
|LongBoxWithoutSeparator | a input widget for Long values formated without separators
|MonthBox | a html5 month input widget
|PasswodTextBox | a input widget for passwords
|PercentDoubleBox | input field for numeric Double values formated as percentage
|PercentBigDecimalBox | input field for numeric BigDecimal values formated as percentage
|PhoneNumberCommonInterSuggestBox | phone number input widget with suggestions in the common international format
|PhoneNumberCommonSuggestBox | phone number input widget with suggestions in the common national and international format
|PhoneNumberDin5008InterSuggestBox | phone number input widget with suggestions in the DIN 5008 international format
|PhoneNumberDin5008SuggestBox | phone number input widget with suggestions in the DIN 5008 national and international format
|PhoneNumberE123InterSuggestBox | phone number input widget with suggestions in the E123 international format
|PhoneNumberE123SuggestBox | phone number input widget with suggestions in the E123 national and international format
|PhoneNumberMsSuggestBox | phone number input widget with suggestions in the Microsoft international format
|PhoneNumberUriSuggestBox | phone number input widget with suggestions in the URI international format
|PostalCodeTextBox | a input widget for postal codes in the formats of various countries
|RadioButton | replacement of the GWT RadioButton, label is not included and has to be set manually
|RangeBox | a html5 range input widget
|RatingDisplayWidget | a widget to display rating with stars, no input field!
|RatingInputWidget | a input widget for ratings with stars
|RegExCheckedTextBox | a input widget for regular expressions
|SearchTextBox | a html5 search input widget
|SimpleTagPanel | same as GWTs SimplePanel, but with tag parameter, so you can create something else then div elements
|SortableIdAndNameListBox | a list box widget to display a id list with localizations
|SortableIdAndNameRadioButton | a radio button widget to display a id list with localizations
|SuggestBoxWithEditorErrors | replacement of the gwt SuggestBox with error handling and html5 features
|TaxNumberTextBox | a input widget for Tax Numbers in the formats of various countries
|TextArea | replacement of the gwt TextArea with error handling and html5 features
|TextBox | replacement of the gwt TextBox with error handling and html5 features
|TinTextBox | a input widget for Tax Identification Numbers in the formats of various countries
|TimeBox | a html5 time input widget
|UiLanguageListBox | a list box widget, filled with all supported languages of the application
|UrlTextBox | a html5 URL input widget
|VatIdTextBox | a input widget for Vat Id's in the formats of various countries
