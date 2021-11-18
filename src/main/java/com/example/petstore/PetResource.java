package com.example.petstore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	List<Pet> pets = new ArrayList<Pet>();

	public PetResource () {
		Pet pet1 = new Pet();
		pet1.setPetId(0);
		pet1.setPetAge(3);
		pet1.setPetName("Boola");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(1);
		pet2.setPetAge(4);
		pet2.setPetName("Sudda");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(2);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0 && petId < pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getPetId() == petId) {
				pet = pets.get(i);
			}
		}
		return Response.ok(pet).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Get Pet for name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("/getByName/{petName}")
	public Response getPetByName(@PathParam("petName") String petName) {
		if (petName.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		List<Pet> filterPets = new ArrayList<Pet>();
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getPetName().equals(petName)) {
				filterPets.add(pets.get(i));
			}
		}
		return Response.ok(filterPets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Get Pet for name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("/getByAge/{petAge}")
	public Response getPetByAge(@PathParam("petAge") int petAge) {
		if (petAge < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		List<Pet> filterPets = new ArrayList<Pet>();
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getPetAge() == petAge) {
				filterPets.add(pets.get(i));
			}
		}
		return Response.ok(filterPets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Create a Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Can't create a pet") })
	@POST
	public Response createPet(@RequestBody Pet newPet) {
		Pet pet = new Pet();
		pet.setPetId(pets.size()+1);
		pet.setPetAge(newPet.getPetAge());
		pet.setPetName(newPet.getPetName());
		pet.setPetType(newPet.getPetType());
		pets.add(pet);
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@PUT
	@Path("{petId}")
	public Response updatePet(@PathParam("petId") int petId, @RequestBody Pet updatePet) {
		if (petId < 0 && petId < pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(updatePet.getPetAge());
		pet.setPetName(updatePet.getPetName());
		pet.setPetType(updatePet.getPetType());
		int petIdIndex = 0;
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getPetId() == petId) {
				petIdIndex = i;
			}
		}
		pets.set(petIdIndex, pet);
		return Response.ok(pets).build();
	}

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@DELETE
	@Path("{petId}")
	public Response updatePet(@PathParam("petId") int petId) {
		if (petId < 0 && petId < pets.size()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		int petIdIndex = 0;
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getPetId() == petId) {
				petIdIndex = i;
			}
		}
		pets.remove(petIdIndex);
		return Response.ok(pets).build();
	}


}
