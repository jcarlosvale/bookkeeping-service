package com.bookkeeping.interfaces.rest;

import com.bookkeeping.application.dto.VehicleCreateOrUpdateDTO;
import com.bookkeeping.application.dto.VehicleDTO;
import com.bookkeeping.application.service.VehicleService;
import io.smallrye.mutiny.Uni;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/vehicles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehicleResource {

    private final VehicleService vehicleService;

    public VehicleResource(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GET
    @Path("/{id}")
    public Uni<VehicleDTO> getById(@PathParam("id") final UUID id) {
        return vehicleService.findById(id);
    }

    @GET
    public Uni<List<VehicleDTO>> getAll(@QueryParam("page") final Integer page,
                                        @QueryParam("size") final Integer size) {
        return vehicleService.findAllPaged(page, size);
    }

    @POST
    public Uni<VehicleDTO> create(@Valid final VehicleCreateOrUpdateDTO dto,
                                  @HeaderParam("X-User-Id") final String userId) {
        return vehicleService.create(dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @PUT
    @Path("/{id}")
    public Uni<VehicleDTO> update(@PathParam("id") final UUID id,
                                  @Valid final VehicleCreateOrUpdateDTO dto,
                                  @HeaderParam("X-User-Id") final String userId) {
        return vehicleService.update(id, dto, userId != null ? userId : UUID.randomUUID().toString());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") final UUID id,
                            @HeaderParam("X-User-Id") final String userId) {
        return vehicleService.delete(id, userId != null ? userId : UUID.randomUUID().toString());
    }
}
