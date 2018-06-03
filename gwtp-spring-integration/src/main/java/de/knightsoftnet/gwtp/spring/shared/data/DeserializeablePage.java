package de.knightsoftnet.gwtp.spring.shared.data;

import org.eclipse.jetty.client.util.DeferredContentProvider.Chunk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Deserializeable {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 */
public class DeserializeablePage<T> implements Page<T> {

  private int number;
  private int size;
  private int totalPages;
  private int numberOfElements;
  private long totalElements;
  private boolean previousPage;
  private boolean first;
  private boolean nextPage;
  private boolean last;
  private List<T> content;
  private Sort sort;
  // private Pageable pageable;

  @SuppressWarnings("PMD.UnusedFormalParameter")
  public DeserializeablePage(final List<T> content, final Pageable pageable, final long total) {
    this.content = content;
    // this.pageable = pageable;
    this.totalElements = total;
  }

  public DeserializeablePage(final List<T> content) {
    this(content, Pageable.unpaged(), null == content ? 0 : content.size());
  }

  public DeserializeablePage() {
    this(new ArrayList<T>());
  }

  public PageImpl<T> pageImpl() {
    return new PageImpl<>(getContent(), PageRequest.of(getNumber(), getSize(), getSort()),
        getTotalElements());
  }

  @Override
  public final int getNumber() {
    return this.number;
  }

  public final void setNumber(final int pnumber) {
    this.number = pnumber;
  }

  @Override
  public final int getSize() {
    return this.size;
  }

  public final void setSize(final int psize) {
    this.size = psize;
  }

  @Override
  public final int getTotalPages() {
    return this.totalPages;
  }

  public final void setTotalPages(final int ptotalPages) {
    this.totalPages = ptotalPages;
  }

  @Override
  public final int getNumberOfElements() {
    return this.numberOfElements;
  }

  public final void setNumberOfElements(final int pnumberOfElements) {
    this.numberOfElements = pnumberOfElements;
  }

  @Override
  public final long getTotalElements() {
    return this.totalElements;
  }

  public final void setTotalElements(final long ptotalElements) {
    this.totalElements = ptotalElements;
  }

  public final boolean isPreviousPage() {
    return this.previousPage;
  }

  public final void setPreviousPage(final boolean ppreviousPage) {
    this.previousPage = ppreviousPage;
  }

  @Override
  public final boolean isFirst() {
    return this.first;
  }

  public final void setFirst(final boolean pfirst) {
    this.first = pfirst;
  }

  public final boolean isNextPage() {
    return this.nextPage;
  }

  public final void setNextPage(final boolean pnextPage) {
    this.nextPage = pnextPage;
  }

  @Override
  public final boolean isLast() {
    return this.last;
  }

  public final void setLast(final boolean plast) {
    this.last = plast;
  }

  @Override
  public final List<T> getContent() {
    return this.content;
  }

  public final void setContent(final List<T> pcontent) {
    this.content = pcontent;
  }

  @Override
  public final Sort getSort() {
    return this.sort;
  }

  public final void setSort(final Sort psort) {
    this.sort = psort;
  }

  // public final Pageable getPageable() {
  // return this.pageable;
  // }
  //
  // public final void setPageable(Pageable ppageable) {
  // this.pageable = ppageable;
  // }

  @Override
  public boolean hasContent() {
    return !CollectionUtils.isEmpty(content);
  }

  @Override
  public boolean hasNext() {
    return getNumber() + 1 < this.getTotalPages();
  }

  @Override
  public boolean hasPrevious() {
    return getNumber() > 0;
  }

  @Override
  public Pageable nextPageable() {
    // return hasNext() ? this.pageable.next() : Pageable.unpaged();
    return null;
  }

  @Override
  public Pageable previousPageable() {
    // return this.hasPrevious() ? this.pageable.previousOrFirst() : Pageable.unpaged();
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    return this.content.iterator();
  }

  @Override
  public <U> Page<U> map(final Function<? super T, ? extends U> pconverter) {
    return new DeserializeablePage<>(this.getConvertedContent(pconverter), getPageable(),
        this.totalElements);
  }

  /**
   * Applies the given {@link Function} to the content of the {@link Chunk}.
   *
   * @param converter must not be {@literal null}.
   * @return
   */
  protected <U> List<U> getConvertedContent(final Function<? super T, ? extends U> converter) {

    Assert.notNull(converter, "Function must not be null!");

    return stream().map(converter::apply).collect(Collectors.toList());
  }
}
