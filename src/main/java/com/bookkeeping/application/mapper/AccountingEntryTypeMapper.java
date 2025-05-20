package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.AccountingEntryTypeCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryTypeDTO;
import com.bookkeeping.domain.model.AccountingEntryType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface AccountingEntryTypeMapper {

    AccountingEntryTypeDTO toDTO(AccountingEntryType entity);

    AccountingEntryTypeCreateOrUpdateDTO toCreateDTO(AccountingEntryTypeDTO dto);

    AccountingEntryType toEntity(AccountingEntryTypeCreateOrUpdateDTO dto);
}
