/*
 * Copyright 2008-2017 the original author or authors.
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

import java.util.List;
import java.util.function.Function;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 * @author Mark Paluch
 */
public class PageImpl<T> extends Chunk<T> implements Page<T> {

  private static final long serialVersionUID = 867755909294344406L;

  private final long total;

  /**
   * Constructor of {@code PageImpl}.
   *
   * @param content the content of this page, must not be {@literal null}.
   * @param pageable the paging information, must not be {@literal null}.
   * @param total the total amount of items available. The total might be adapted considering the
   *        length of the content given, if it is going to be the content of the last page. This is
   *        in place to mitigate inconsistencies.
   */
  public PageImpl(final List<T> content, final Pageable pageable, final long total) {

    super(content, pageable);

    this.total = pageable.toOptional().filter(it -> !content.isEmpty())//
        .filter(it -> it.getOffset() + it.getPageSize() > total)//
        .map(it -> it.getOffset() + content.size())//
        .orElse(total);
  }

  /**
   * Creates a new {@link PageImpl} with the given content. This will result in the created
   * {@link Page} being identical to the entire {@link List}.
   *
   * @param content must not be {@literal null}.
   */
  public PageImpl(final List<T> content) {
    this(content, Pageable.unpaged(), null == content ? 0 : content.size());
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Page#getTotalPages()
   */
  @Override
  public int getTotalPages() {
    return getSize() == 0 ? 1 : (int) Math.ceil((double) this.total / (double) getSize());
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Page#getTotalElements()
   */
  @Override
  public long getTotalElements() {
    return this.total;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#hasNext()
   */
  @Override
  public boolean hasNext() {
    return getNumber() + 1 < this.getTotalPages();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Slice#isLast()
   */
  @Override
  public boolean isLast() {
    return !this.hasNext();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.springframework.data.domain.Slice#transform(org.springframework.core.convert.converter.
   * Converter)
   */
  @Override
  public <U> Page<U> map(final Function<? super T, ? extends U> converter) {
    return new PageImpl<>(this.getConvertedContent(converter), getPageable(), this.total);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {

    String contentType = "UNKNOWN";
    final List<T> content = getContent();

    if (content.size() > 0) {
      contentType = content.get(0).getClass().getName();
    }

    return "Page " + Integer.toString(getNumber() + 1) + " of "
        + Integer.toString(this.getTotalPages()) + " containing " + contentType + " instances";
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

    if (!(obj instanceof PageImpl<?>)) {
      return false;
    }

    final PageImpl<?> that = (PageImpl<?>) obj;

    return this.total == that.total && super.equals(obj);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {

    int result = 17;

    result += 31 * (int) (this.total ^ this.total >>> 32);
    result += 31 * super.hashCode();

    return result;
  }
}
