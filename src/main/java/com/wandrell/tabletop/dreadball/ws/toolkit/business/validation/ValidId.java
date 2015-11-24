/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.dreadball.ws.toolkit.business.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Annotation which marks an id which should receive a basic validation.
 * 
 * @author Bernardo Martínez Garrido
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Constraint(validatedBy = IdValidator.class)
@NotBlank
@ReportAsSingleViolation
public @interface ValidId {

    /**
     * Returns the bean validation groups.
     * 
     * @return the bean validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Returns the bean validation message code.
     * 
     * @return the bean validation message code
     */
    String message() default "{not.valid.id}";

    /**
     * Returns the bean validation payload.
     * 
     * @return the bean validation payload
     */
    Class<? extends Payload>[] payload() default {};

}
