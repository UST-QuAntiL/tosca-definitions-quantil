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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * ApplicationDto
 */
@JsonPropertyOrder({
  ApplicationDto.JSON_PROPERTY_ID,
  ApplicationDto.JSON_PROPERTY_NAME,
  ApplicationDto.JSON_PROPERTY_DESCRIPTION,
  ApplicationDto.JSON_PROPERTY_ATTRIBUTES,
  ApplicationDto.JSON_PROPERTY_GROUPS,
  ApplicationDto.JSON_PROPERTY_SUBSCRIPTION_COUNT,
  ApplicationDto.JSON_PROPERTY_ACCESS_PERMISSION_OF_LOGGED_IN_USER
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public class ApplicationDto {
  public static final String JSON_PROPERTY_ID = "id";
  private UUID id;

  public static final String JSON_PROPERTY_NAME = "name";
  private String name;

  public static final String JSON_PROPERTY_DESCRIPTION = "description";
  private String description;

  public static final String JSON_PROPERTY_ATTRIBUTES = "attributes";
  private Object attributes;

  public static final String JSON_PROPERTY_GROUPS = "groups";
  private List<String> groups = null;

  public static final String JSON_PROPERTY_SUBSCRIPTION_COUNT = "subscriptionCount";
  private Integer subscriptionCount;

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

  public ApplicationDto() { 
  }

  public ApplicationDto id(UUID id) {
    
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


  public ApplicationDto name(String name) {
    
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


  public ApplicationDto description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getDescription() {
    return description;
  }


  @JsonProperty(JSON_PROPERTY_DESCRIPTION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setDescription(String description) {
    this.description = description;
  }


  public ApplicationDto attributes(Object attributes) {
    
    this.attributes = attributes;
    return this;
  }

   /**
   * Get attributes
   * @return attributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ATTRIBUTES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getAttributes() {
    return attributes;
  }


  @JsonProperty(JSON_PROPERTY_ATTRIBUTES)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAttributes(Object attributes) {
    this.attributes = attributes;
  }


  public ApplicationDto groups(List<String> groups) {
    
    this.groups = groups;
    return this;
  }

  public ApplicationDto addGroupsItem(String groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<>();
    }
    this.groups.add(groupsItem);
    return this;
  }

   /**
   * Get groups
   * @return groups
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_GROUPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public List<String> getGroups() {
    return groups;
  }


  @JsonProperty(JSON_PROPERTY_GROUPS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setGroups(List<String> groups) {
    this.groups = groups;
  }


  public ApplicationDto subscriptionCount(Integer subscriptionCount) {
    
    this.subscriptionCount = subscriptionCount;
    return this;
  }

   /**
   * Get subscriptionCount
   * @return subscriptionCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_SUBSCRIPTION_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getSubscriptionCount() {
    return subscriptionCount;
  }


  @JsonProperty(JSON_PROPERTY_SUBSCRIPTION_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setSubscriptionCount(Integer subscriptionCount) {
    this.subscriptionCount = subscriptionCount;
  }


  public ApplicationDto accessPermissionOfLoggedInUser(AccessPermissionOfLoggedInUserEnum accessPermissionOfLoggedInUser) {
    
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
    ApplicationDto applicationDto = (ApplicationDto) o;
    return Objects.equals(this.id, applicationDto.id) &&
        Objects.equals(this.name, applicationDto.name) &&
        Objects.equals(this.description, applicationDto.description) &&
        Objects.equals(this.attributes, applicationDto.attributes) &&
        Objects.equals(this.groups, applicationDto.groups) &&
        Objects.equals(this.subscriptionCount, applicationDto.subscriptionCount) &&
        Objects.equals(this.accessPermissionOfLoggedInUser, applicationDto.accessPermissionOfLoggedInUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, attributes, groups, subscriptionCount, accessPermissionOfLoggedInUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicationDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    subscriptionCount: ").append(toIndentedString(subscriptionCount)).append("\n");
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

