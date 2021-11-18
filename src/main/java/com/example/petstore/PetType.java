package com.example.petstore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;

@Schema(name = "Pet")
public class PetType {
    @Schema(required = true, description = "PetType id")
    @JsonProperty("petType_id")
    private Integer petTypeId;


    @Schema(required = true, description = "PetType name")
    @JsonProperty("petType_name")
    private String petTypeName;

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }

}
