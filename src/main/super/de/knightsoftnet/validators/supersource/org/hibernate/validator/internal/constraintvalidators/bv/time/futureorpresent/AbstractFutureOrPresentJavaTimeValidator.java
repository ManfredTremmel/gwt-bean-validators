package org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent;

import org.hibernate.validator.internal.constraintvalidators.bv.time.AbstractJavaTimeValidator;

import javax.validation.constraints.FutureOrPresent;

public abstract class AbstractFutureOrPresentJavaTimeValidator<T>
    extends AbstractJavaTimeValidator<FutureOrPresent, T> {
  @Override
  protected boolean isValid(final int result) {
    throw new UnsupportedOperationException("Not supported by GWT.");
  }
}
