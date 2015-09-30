package com.wandrell.tabletop.dreadball.ws.toolkit.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class IdValidator implements ConstraintValidator<ValidId, String> {

    @Override
    public void initialize(final ValidId ann) {}

    @Override
    public boolean isValid(final String id,
            final ConstraintValidatorContext ctx) {
        return (id != null) && (id.matches("[0-9]*"));
    }

}
