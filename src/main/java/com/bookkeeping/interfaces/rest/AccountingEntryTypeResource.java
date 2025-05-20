package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.AccountingEntryTypeCreateOrUpdateDTO;
import com.bookkeeping.application.dto.AccountingEntryTypeDTO;
import com.bookkeeping.application.service.AccountingEntryTypeService;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/accounting-entry-types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountingEntryTypeResource {

    private final AccountingEntryTypeService service;

    public AccountingEntryTypeResource(final AccountingEntryTypeService service) {
        this.service = service;
    }

    @GET
    @Path("/{id}")
    public Uni<AccountingEntryTypeDTO> getById(@PathParam("id") final UUID id) {
        return service.findById(id);
    }

    @GET
    public Uni<List<AccountingEntryTypeDTO>> getAll(@QueryParam("page") final Integer page,
                                                    @QueryParam("size") final Integer size) {
        return service.findAllPaged(page, size);
    }

    @POST
    public Uni<AccountingEntryTypeDTO> create(@Valid final AccountingEntryTypeCreateOrUpdateDTO dto,
                                              @HeaderParam("X-User-Id") final String userId) {
        return service.create(dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @PUT
    @Path("/{id}")
    public Uni<AccountingEntryTypeDTO> update(@PathParam("id") final UUID id,
                                              @Valid final AccountingEntryTypeCreateOrUpdateDTO dto,
                                              @HeaderParam("X-User-Id") final String userId) {
        return service.update(id, dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") final UUID id,
                            @HeaderParam("X-User-Id") final String userId) {
        return service.delete(id, userId != null ? userId : UUID.randomUUID().toString());
    }
}
