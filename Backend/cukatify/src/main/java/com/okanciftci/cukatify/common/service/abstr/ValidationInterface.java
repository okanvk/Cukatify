package com.okanciftci.cukatify.common.service.abstr;

import com.okanciftci.cukatify.common.ValidationMessage;
import com.okanciftci.cukatify.common.ValidationType;

public interface ValidationInterface<T> {
    ValidationMessage validation(T model, ValidationType validationType);
}
