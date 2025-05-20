package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.AccountingEntryTypeCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryTypeDTO;
import com.bookkeeping.domain.enums.TransactionType;
import com.bookkeeping.domain.model.AccountingEntryType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountingEntryTypeMapperTest {

    private final AccountingEntryTypeMapper mapper = new AccountingEntryTypeMapperImpl();

    @Test
    void testToDTOAndBack() {
        UUID id = UUID.randomUUID();
        AccountingEntryType entity = new AccountingEntryType();
        entity.setId(id);
        entity.setCode("SALES");
        entity.setTransactionType(TransactionType.INCOME);
        entity.setDescription("Sales income");

        AccountingEntryTypeDTO dto = mapper.toDTO(entity);

        assertEquals("SALES", dto.code());
        assertEquals(TransactionType.INCOME, dto.transactionType());
        assertEquals("Sales income", dto.description());

        AccountingEntryTypeCreateOrUpdateDTO createDTO = mapper.toCreateDTO(dto);
        AccountingEntryType converted = mapper.toEntity(createDTO);

        assertEquals("SALES", converted.getCode());
        assertEquals(TransactionType.INCOME, converted.getTransactionType());
        assertEquals("Sales income", converted.getDescription());
    }
}
