/*
 * Copyright 2014-2017 the original author or authors.
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
package org.springframework.data.domain;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A chunk of data restricted by the configured {@link Pageable}.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @since 1.8
 */
abstract class Chunk<T> implements Slice<T>, Serializable {

  private static final long serialVersionUID = 867755909294344406L;

  private final List<T> content = new ArrayList<>();
  private final Pageable pageable;

  /**
   * Creates a new {@link Chunk} with the given content and the given governing {@link Pageable}.
   *
   * @param content must not be {@literal null}.
   * @param pageable must not be {@literal null}.
   */
  public Chunk(final List<T> content, final Pageable pageable) {

    Assert.notNull(content, "Content must not be null!");
    Assert.notNull(pageable, "Pageable must not be null!");

    this.content.addAll(content);
    this.pageable = pageable;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#getNumber()
   */
  @Override
  public int getNumber() {
    return this.pageable.isPaged() ? this.pageable.getPageNumber() : 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#getSize()
   */
  @Override
  public int getSize() {
    return this.pageable.isPaged() ? this.pageable.getPageSize() : 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#getNumberOfElements()
   */
  @Override
  public int getNumberOfElements() {
    return this.content.size();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#hasPrevious()
   */
  @Override
  public boolean hasPrevious() {
    return this.getNumber() > 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#isFirst()
   */
  @Override
  public boolean isFirst() {
    return !this.hasPrevious();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#isLast()
   */
  @Override
  public boolean isLast() {
    return !hasNext();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#nextPageable()
   */
  @Override
  public Pageable nextPageable() {
    return hasNext() ? this.pageable.next() : Pageable.unpaged();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#previousPageable()
   */
  @Override
  public Pageable previousPageable() {
    return this.hasPrevious() ? this.pageable.previousOrFirst() : Pageable.unpaged();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#hasContent()
   */
  @Override
  public boolean hasContent() {
    return !this.content.isEmpty();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#getContent()
   */
  @Override
  public List<T> getContent() {
    return Collections.unmodifiableList(this.content);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#getSort()
   */
  @Override
  public Sort getSort() {
    return this.pageable.getSort();
  }

  @Override
  public final Pageable getPageable() {
    return this.pageable;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    return this.content.iterator();
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

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(@Nullable final Object obj) {

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Chunk<?>)) {
      return false;
    }

    final Chunk<?> that = (Chunk<?>) obj;

    final boolean contentEqual = this.content.equals(that.content);
    final boolean pageableEqual = this.pageable.equals(that.pageable);

    return contentEqual && pageableEqual;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    int result = 17;

    result += 31 * this.pageable.hashCode();
    result += 31 * this.content.hashCode();

    return result;
  }
}
