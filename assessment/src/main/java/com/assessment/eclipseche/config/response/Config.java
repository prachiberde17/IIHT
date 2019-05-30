
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
    "defaultEnv",
    "environments",
    "projects",
    "name",
    "attributes",
    "commands",
    "links"
})
public class Config {

    @JsonProperty("defaultEnv")
    private String defaultEnv;
    @JsonProperty("environments")
    private Environments environments;
    @JsonProperty("projects")
    private List<Project> projects = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("attributes")
    private Attributes_____ attributes;
    @JsonProperty("commands")
    private List<Command> commands = null;
    @JsonProperty("links")
    private List<Object> links = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("defaultEnv")
    public String getDefaultEnv() {
        return defaultEnv;
    }

    @JsonProperty("defaultEnv")
    public void setDefaultEnv(String defaultEnv) {
        this.defaultEnv = defaultEnv;
    }

    @JsonProperty("environments")
    public Environments getEnvironments() {
        return environments;
    }

    @JsonProperty("environments")
    public void setEnvironments(Environments environments) {
        this.environments = environments;
    }

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("attributes")
    public Attributes_____ getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Attributes_____ attributes) {
        this.attributes = attributes;
    }

    @JsonProperty("commands")
    public List<Command> getCommands() {
        return commands;
    }

    @JsonProperty("commands")
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    @JsonProperty("links")
    public List<Object> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Object> links) {
        this.links = links;
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
