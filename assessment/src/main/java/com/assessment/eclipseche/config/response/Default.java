
package com.assessment.eclipseche.config.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "machines",
    "recipe"
})
public class Default {

    @JsonProperty("machines")
    private Machines machines;
    @JsonProperty("recipe")
    private Recipe recipe;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("machines")
    public Machines getMachines() {
        return machines;
    }

    @JsonProperty("machines")
    public void setMachines(Machines machines) {
        this.machines = machines;
    }

    @JsonProperty("recipe")
    public Recipe getRecipe() {
        return recipe;
    }

    @JsonProperty("recipe")
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
