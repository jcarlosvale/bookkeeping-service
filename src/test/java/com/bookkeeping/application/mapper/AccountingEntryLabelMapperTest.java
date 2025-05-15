package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.AccountingEntryLabelCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryLabelDTO;
import com.bookkeeping.domain.model.AccountingEntryLabel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountingEntryLabelMapperTest {

    private final AccountingEntryLabelMapper mapper = new AccountingEntryLabelMapperImpl();

    @Test
    void testToDTOAndBack() {
        UUID id = UUID.randomUUID();
        AccountingEntryLabel label = new AccountingEntryLabel();
        label.setId(id);
        label.setCode("RENT");
        label.setDescription("Monthly rent");

        AccountingEntryLabelDTO dto = mapper.toDTO(label);

        assertEquals("RENT", dto.code());
        assertEquals("Monthly rent", dto.description());
        assertEquals(id, dto.id());

        AccountingEntryLabelCreateOrUpdateDTO createDTO = mapper.toCreateDTO(dto);

        AccountingEntryLabel convertedBack = mapper.toEntity(createDTO);
        assertEquals("RENT", convertedBack.getCode());
        assertEquals("Monthly rent", convertedBack.getDescription());
    }
}
