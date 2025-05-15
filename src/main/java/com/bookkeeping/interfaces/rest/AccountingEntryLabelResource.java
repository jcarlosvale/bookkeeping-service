package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.AccountingEntryLabelCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryLabelDTO;
import com.bookkeeping.application.service.AccountingEntryLabelService;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/accounting-entry-labels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountingEntryLabelResource {

    private final AccountingEntryLabelService accountingEntryLabelService;

    public AccountingEntryLabelResource(final AccountingEntryLabelService accountingEntryLabelService) {
        this.accountingEntryLabelService = accountingEntryLabelService;
    }

    @GET
    @Path("/{id}")
    public Uni<AccountingEntryLabelDTO> getById(@PathParam("id") final UUID id) {
        return accountingEntryLabelService.findById(id);
    }

    @GET
    public Uni<List<AccountingEntryLabelDTO>> getAll(@QueryParam("page") final Integer page,
                                                     @QueryParam("size") final Integer size) {
        return accountingEntryLabelService.findAllPaged(page, size);
    }

    @POST
    public Uni<AccountingEntryLabelDTO> create(@Valid final AccountingEntryLabelCreateOrUpdateDTO dto,
                                               @HeaderParam("X-User-Id") final String userId) {
        return accountingEntryLabelService.create(dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @PUT
    @Path("/{id}")
    public Uni<AccountingEntryLabelDTO> update(@PathParam("id") final UUID id,
                                               @Valid final AccountingEntryLabelCreateOrUpdateDTO dto,
                                               @HeaderParam("X-User-Id") final String userId) {
        return accountingEntryLabelService.update(id, dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") final UUID id,
                            @HeaderParam("X-User-Id") final String userId) {
        return accountingEntryLabelService.delete(id, userId != null ? userId : UUID.randomUUID().toString());
    }
}
