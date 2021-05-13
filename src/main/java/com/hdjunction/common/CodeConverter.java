package com.hdjunction.common;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CodeConverter implements AttributeConverter<CodeGroup.Code, String> {
    @Override
    public String convertToDatabaseColumn(final CodeGroup.Code code) {
        return code.getDetailCode();
    }

    @Override
    public CodeGroup.Code convertToEntityAttribute(final String dbData) {
        return CodeGroup.Code.findCodeBy(dbData);
    }
}
