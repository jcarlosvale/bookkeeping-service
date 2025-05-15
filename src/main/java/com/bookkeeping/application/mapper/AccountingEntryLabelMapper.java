package com.bookkeeping.application.mapper;

import com.bookkeeping.application.dto.AccountingEntryLabelCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryLabelDTO;
import com.bookkeeping.domain.model.AccountingEntryLabel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface AccountingEntryLabelMapper {

    AccountingEntryLabelDTO toDTO(AccountingEntryLabel entity);

    AccountingEntryLabelCreateOrUpdateDTO toCreateDTO(AccountingEntryLabelDTO accountingEntryLabelDTO);

    AccountingEntryLabel toEntity(AccountingEntryLabelCreateOrUpdateDTO dto);
}
