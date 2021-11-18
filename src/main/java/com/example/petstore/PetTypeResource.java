package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/v1/petTypes")
@Produces("application/json")

public class PetTypeResource {
    List<PetType> petTypes = new ArrayList<PetType>();

    public PetTypeResource () {
        PetType petType1 = new PetType();
        petType1.setPetTypeId(0);
        petType1.setPetTypeName("Dog");

        PetType petType2 = new PetType();
        petType2.setPetTypeId(1);
        petType2.setPetTypeName("Cat");

        PetType petType3 = new PetType();
        petType3.setPetTypeId(2);
        petType3.setPetTypeName("Bird");

        petTypes.add(petType1);
        petTypes.add(petType2);
        petTypes.add(petType3);
    }


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
    @GET
    public Response getPetTypes() {
        return Response.ok(petTypes).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet TYpe for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @GET
    @Path("{petTypeId}")
    public Response getPet(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0 && petTypeId < petTypes.size()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PetType petType = new PetType();
        for (int i = 0; i < petTypes.size(); i++) {
            if(petTypes.get(i).getPetTypeId() == petTypeId) {
                petType = petTypes.get(i);
            }
        }
        return Response.ok(petType).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Create a Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "Can't create a pet") })
    @POST
    public Response createPet(@RequestBody PetType newPetType) {
        PetType petType = new PetType();
        petType.setPetTypeId(petTypes.size()+1);
        petType.setPetTypeName(newPetType.getPetTypeName());
        petTypes.add(petType);
        return Response.ok(petTypes).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Update Pet Type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @PUT
    @Path("{petTypeId}")
    public Response updatePet(@PathParam("petTypeId") int petTypeId, @RequestBody PetType updatePetType) {
        if (petTypeId < 0 && petTypeId < petTypes.size()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PetType petType = new PetType();
        petType.setPetTypeId(petTypeId);
        petType.setPetTypeName(updatePetType.getPetTypeName());
        int petTypeIdIndex = 0;
        for (int i = 0; i < petTypes.size(); i++) {
            if(petTypes.get(i).getPetTypeId() == petTypeId) {
                petTypeIdIndex = i;
            }
        }
        petTypes.set(petTypeIdIndex, petType);
        return Response.ok(petTypes).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Delete Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @DELETE
    @Path("{petTypeId}")
    public Response updatePet(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0 && petTypeId < petTypes.size()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        int petTypeIdIndex = 0;
        for (int i = 0; i < petTypes.size(); i++) {
            if(petTypes.get(i).getPetTypeId() == petTypeId) {
                petTypeIdIndex = i;
            }
        }
        petTypes.remove(petTypeIdIndex);
        return Response.ok(petTypes).build();
    }
}
