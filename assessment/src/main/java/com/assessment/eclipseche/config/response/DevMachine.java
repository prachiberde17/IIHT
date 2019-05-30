
package com.assessment.eclipseche.config.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "attributes",
    "servers",
    "volumes",
    "installers",
    "env"
})
public class DevMachine {

    @JsonProperty("attributes")
    private Attributes_ attributes;
    @JsonProperty("servers")
    private Servers servers;
    @JsonProperty("volumes")
    private Volumes volumes;
    @JsonProperty("installers")
    private List<String> installers = null;
    @JsonProperty("env")
    private Env env;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("attributes")
    public Attributes_ getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes_ attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("servers")
    public Servers getServers() {
        return servers;
    }

    @JsonProperty("servers")
    public void setServers(Servers servers) {
        this.servers = servers;
    }

    @JsonProperty("volumes")
    public Volumes getVolumes() {
        return volumes;
    }

    @JsonProperty("volumes")
    public void setVolumes(Volumes volumes) {
        this.volumes = volumes;
    }

    @JsonProperty("installers")
    public List<String> getInstallers() {
        return installers;
    }

    @JsonProperty("installers")
    public void setInstallers(List<String> installers) {
        this.installers = installers;
    }

    @JsonProperty("env")
    public Env getEnv() {
        return env;
    }

    @JsonProperty("env")
    public void setEnv(Env env) {
        this.env = env;
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
