
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
    "m2",
    "javadata"
})
public class Volumes {

    @JsonProperty("m2")
    private M2 m2;
    @JsonProperty("javadata")
    private Javadata javadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("m2")
    public M2 getM2() {
        return m2;
    }

    @JsonProperty("m2")
    public void setM2(M2 m2) {
        this.m2 = m2;
    }

    @JsonProperty("javadata")
    public Javadata getJavadata() {
        return javadata;
    }

    @JsonProperty("javadata")
    public void setJavadata(Javadata javadata) {
        this.javadata = javadata;
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
