/*
 * PlanQK Platform OpenAPI definition
 * OpenAPI definition for the Platform and Ecosystem for Quantum-assisted Artificial Intelligence Platform
 *
 * The version of the OpenAPI document: v1
 * Contact: info@stoneone.de
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package de.stoneone.planqk.api.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import de.stoneone.planqk.api.model.ServiceDefinitionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * ServiceDto
 */
@JsonPropertyOrder({
  ServiceDto.JSON_PROPERTY_ID,
  ServiceDto.JSON_PROPERTY_NAME,
  ServiceDto.JSON_PROPERTY_SERVICE_DEFINITIONS,
  ServiceDto.JSON_PROPERTY_ACCESS_PERMISSION_OF_LOGGED_IN_USER
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public class ServiceDto {
  public static final String JSON_PROPERTY_ID = "id";
  private UUID id;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_SERVICE_DEFINITIONS = "serviceDefinitions";
  private List<ServiceDefinitionDto> serviceDefinitions = null;

  /**
   * Gets or Sets accessPermissionOfLoggedInUser
   */
  public enum AccessPermissionOfLoggedInUserEnum {
    VIEWER("VIEWER"),
    
    MAINTAINER("MAINTAINER"),
    
    OWNER("OWNER");

    private String value;

    AccessPermissionOfLoggedInUserEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccessPermissionOfLoggedInUserEnum fromValue(String value) {
      for (AccessPermissionOfLoggedInUserEnum b : AccessPermissionOfLoggedInUserEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_ACCESS_PERMISSION_OF_LOGGED_IN_USER = "accessPermissionOfLoggedInUser";
  private AccessPermissionOfLoggedInUserEnum accessPermissionOfLoggedInUser;

  public ServiceDto() { 
  }

  public ServiceDto id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public UUID getId() {
    return id;
  }


  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setId(UUID id) {
    this.id = id;
  }


  public ServiceDto name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getName() {
    return name;
  }


  @JsonProperty(JSON_PROPERTY_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setName(String name) {
    this.name = name;
  }


  public ServiceDto serviceDefinitions(List<ServiceDefinitionDto> serviceDefinitions) {
    
    this.serviceDefinitions = serviceDefinitions;
    return this;
  }

  public ServiceDto addServiceDefinitionsItem(ServiceDefinitionDto serviceDefinitionsItem) {
    if (this.serviceDefinitions == null) {
      this.serviceDefinitions = new ArrayList<>();
    }
    this.serviceDefinitions.add(serviceDefinitionsItem);
    return this;
  }

   /**
   * Get serviceDefinitions
   * @return serviceDefinitions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SERVICE_DEFINITIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<ServiceDefinitionDto> getServiceDefinitions() {
    return serviceDefinitions;
  }


  @JsonProperty(JSON_PROPERTY_SERVICE_DEFINITIONS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setServiceDefinitions(List<ServiceDefinitionDto> serviceDefinitions) {
    this.serviceDefinitions = serviceDefinitions;
  }


  public ServiceDto accessPermissionOfLoggedInUser(AccessPermissionOfLoggedInUserEnum accessPermissionOfLoggedInUser) {
    
    this.accessPermissionOfLoggedInUser = accessPermissionOfLoggedInUser;
    return this;
  }

   /**
   * Get accessPermissionOfLoggedInUser
   * @return accessPermissionOfLoggedInUser
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ACCESS_PERMISSION_OF_LOGGED_IN_USER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public AccessPermissionOfLoggedInUserEnum getAccessPermissionOfLoggedInUser() {
    return accessPermissionOfLoggedInUser;
  }


  @JsonProperty(JSON_PROPERTY_ACCESS_PERMISSION_OF_LOGGED_IN_USER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAccessPermissionOfLoggedInUser(AccessPermissionOfLoggedInUserEnum accessPermissionOfLoggedInUser) {
    this.accessPermissionOfLoggedInUser = accessPermissionOfLoggedInUser;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceDto serviceDto = (ServiceDto) o;
    return Objects.equals(this.id, serviceDto.id) &&
        Objects.equals(this.name, serviceDto.name) &&
        Objects.equals(this.serviceDefinitions, serviceDto.serviceDefinitions) &&
        Objects.equals(this.accessPermissionOfLoggedInUser, serviceDto.accessPermissionOfLoggedInUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, serviceDefinitions, accessPermissionOfLoggedInUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    serviceDefinitions: ").append(toIndentedString(serviceDefinitions)).append("\n");
    sb.append("    accessPermissionOfLoggedInUser: ").append(toIndentedString(accessPermissionOfLoggedInUser)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
