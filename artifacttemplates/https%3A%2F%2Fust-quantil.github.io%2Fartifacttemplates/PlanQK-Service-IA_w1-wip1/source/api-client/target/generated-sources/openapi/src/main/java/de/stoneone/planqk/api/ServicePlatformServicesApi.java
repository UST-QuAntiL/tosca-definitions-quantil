package de.stoneone.planqk.api;

import de.stoneone.planqk.api.invoker.ApiClient;
import de.stoneone.planqk.api.invoker.EncodingUtils;
import de.stoneone.planqk.api.model.ApiResponse;

import de.stoneone.planqk.api.model.BuildJobDto;
import java.io.File;
import de.stoneone.planqk.api.model.IndustryDto;
import de.stoneone.planqk.api.model.ServiceDefinitionCommand;
import de.stoneone.planqk.api.model.ServiceDefinitionDto;
import de.stoneone.planqk.api.model.ServiceDto;
import java.util.Set;
import de.stoneone.planqk.api.model.SubscriptionDto;
import java.util.UUID;
import de.stoneone.planqk.api.model.UpdateVersionRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public interface ServicePlatformServicesApi extends ApiClient.Api {


  /**
   * 
   * This endpoint is used to execute certain commands for a specific service definition. The \&quot;UNPUBLISH\&quot; command tries to unpublish a service (service definition), however it returns a 409 Conflict in case it still has active subscriptions or is used in use case relations. The \&quot;UNPUBLISH_FORCE\&quot; command will forcefully unpublish a service definition, i.e., it cancels all active subscriptions and all use case relations will be removed.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param serviceDefinitionCommand  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/commands")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  void commands(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, ServiceDefinitionCommand serviceDefinitionCommand, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>commands</code> but it also returns the http response headers .
   * This endpoint is used to execute certain commands for a specific service definition. The \&quot;UNPUBLISH\&quot; command tries to unpublish a service (service definition), however it returns a 409 Conflict in case it still has active subscriptions or is used in use case relations. The \&quot;UNPUBLISH_FORCE\&quot; command will forcefully unpublish a service definition, i.e., it cancels all active subscriptions and all use case relations will be removed.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param serviceDefinitionCommand  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/commands")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Void> commandsWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, ServiceDefinitionCommand serviceDefinitionCommand, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Creates a new external service within your organization or for yourself, which is running somewhere (e.g., on your own infrastructure) and you just want the PlanQK platform to manage the access to it.
   * @param name  (required)
   * @param url  (required)
   * @param description  (optional)
   * @param quantumBackend  (optional, default to NONE)
   * @param securityConfiguration  (optional, default to NONE)
   * @param username  (optional)
   * @param password  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param apiDefinition  (optional)
   * @return ServiceDto
   */
  @RequestLine("POST /v2/external-services?name={name}&description={description}&quantumBackend={quantumBackend}&url={url}&securityConfiguration={securityConfiguration}&username={username}&password={password}")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDto createExternalService(@Param("name") String name, @Param("url") String url, @Param("description") String description, @Param("quantumBackend") String quantumBackend, @Param("securityConfiguration") String securityConfiguration, @Param("username") String username, @Param("password") String password, @Param("xOrganizationId") String xOrganizationId, @Param("apiDefinition") File apiDefinition);

  /**
   * 
   * Similar to <code>createExternalService</code> but it also returns the http response headers .
   * Creates a new external service within your organization or for yourself, which is running somewhere (e.g., on your own infrastructure) and you just want the PlanQK platform to manage the access to it.
   * @param name  (required)
   * @param url  (required)
   * @param description  (optional)
   * @param quantumBackend  (optional, default to NONE)
   * @param securityConfiguration  (optional, default to NONE)
   * @param username  (optional)
   * @param password  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param apiDefinition  (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /v2/external-services?name={name}&description={description}&quantumBackend={quantumBackend}&url={url}&securityConfiguration={securityConfiguration}&username={username}&password={password}")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDto> createExternalServiceWithHttpInfo(@Param("name") String name, @Param("url") String url, @Param("description") String description, @Param("quantumBackend") String quantumBackend, @Param("securityConfiguration") String securityConfiguration, @Param("username") String username, @Param("password") String password, @Param("xOrganizationId") String xOrganizationId, @Param("apiDefinition") File apiDefinition);


  /**
   * 
   * Creates a new external service within your organization or for yourself, which is running somewhere (e.g., on your own infrastructure) and you just want the PlanQK platform to manage the access to it.
   * Note, this is equivalent to the other <code>createExternalService</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link CreateExternalServiceQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param apiDefinition  (optional)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>name -  (required)</li>
   *   <li>description -  (optional)</li>
   *   <li>quantumBackend -  (optional, default to NONE)</li>
   *   <li>url -  (required)</li>
   *   <li>securityConfiguration -  (optional, default to NONE)</li>
   *   <li>username -  (optional)</li>
   *   <li>password -  (optional)</li>
   *   </ul>
   * @return ServiceDto
   */
  @RequestLine("POST /v2/external-services?name={name}&description={description}&quantumBackend={quantumBackend}&url={url}&securityConfiguration={securityConfiguration}&username={username}&password={password}")
  @Headers({
  "Content-Type: multipart/form-data",
  "Accept: application/json",
      "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDto createExternalService(@Param("xOrganizationId") String xOrganizationId, @Param("apiDefinition") File apiDefinition, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
  * 
  * Creates a new external service within your organization or for yourself, which is running somewhere (e.g., on your own infrastructure) and you just want the PlanQK platform to manage the access to it.
  * Note, this is equivalent to the other <code>createExternalService</code> that receives the query parameters as a map,
  * but this one also exposes the Http response headers
              * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
              * @param apiDefinition  (optional)
      * @param queryParams Map of query parameters as name-value pairs
      *   <p>The following elements may be specified in the query map:</p>
      *   <ul>
          *   <li>name -  (required)</li>
          *   <li>description -  (optional)</li>
          *   <li>quantumBackend -  (optional, default to NONE)</li>
          *   <li>url -  (required)</li>
          *   <li>securityConfiguration -  (optional, default to NONE)</li>
          *   <li>username -  (optional)</li>
          *   <li>password -  (optional)</li>
      *   </ul>
          * @return ServiceDto
      */
      @RequestLine("POST /v2/external-services?name={name}&description={description}&quantumBackend={quantumBackend}&url={url}&securityConfiguration={securityConfiguration}&username={username}&password={password}")
      @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
          "X-OrganizationId: {xOrganizationId}"
      })
   ApiResponse<ServiceDto> createExternalServiceWithHttpInfo(@Param("xOrganizationId") String xOrganizationId, @Param("apiDefinition") File apiDefinition, @QueryMap(encoded=true) Map<String, Object> queryParams);


   /**
   * A convenience class for generating query parameters for the
   * <code>createExternalService</code> method in a fluent style.
   */
  public static class CreateExternalServiceQueryParams extends HashMap<String, Object> {
    public CreateExternalServiceQueryParams name(final String value) {
      put("name", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams description(final String value) {
      put("description", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams quantumBackend(final String value) {
      put("quantumBackend", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams url(final String value) {
      put("url", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams securityConfiguration(final String value) {
      put("securityConfiguration", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams username(final String value) {
      put("username", EncodingUtils.encode(value));
      return this;
    }
    public CreateExternalServiceQueryParams password(final String value) {
      put("password", EncodingUtils.encode(value));
      return this;
    }
  }

  /**
   * 
   * Creates a new service within your organization or for yourself, must be either of type MANAGED or EXTERNAL. For managed services, it is required to upload a \&quot;userCode\&quot; file within a request. For external services, you must supply a valid \&quot;productionEndpoint\&quot; URL.
   * @param name  (required)
   * @param type  (required)
   * @param quantumBackend  (required)
   * @param description  (optional)
   * @param productionEndpoint  (optional)
   * @param usePlatformToken  (optional, default to FALSE)
   * @param cpu  (optional)
   * @param memory  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param userCode  (optional)
   * @param apiDefinition  (optional)
   * @return ServiceDto
   */
  @RequestLine("POST /v2/services?name={name}&description={description}&type={type}&quantumBackend={quantumBackend}&productionEndpoint={productionEndpoint}&usePlatformToken={usePlatformToken}&cpu={cpu}&memory={memory}")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDto createService(@Param("name") String name, @Param("type") String type, @Param("quantumBackend") String quantumBackend, @Param("description") String description, @Param("productionEndpoint") String productionEndpoint, @Param("usePlatformToken") String usePlatformToken, @Param("cpu") Integer cpu, @Param("memory") Integer memory, @Param("xOrganizationId") String xOrganizationId, @Param("userCode") File userCode, @Param("apiDefinition") File apiDefinition);

  /**
   * 
   * Similar to <code>createService</code> but it also returns the http response headers .
   * Creates a new service within your organization or for yourself, must be either of type MANAGED or EXTERNAL. For managed services, it is required to upload a \&quot;userCode\&quot; file within a request. For external services, you must supply a valid \&quot;productionEndpoint\&quot; URL.
   * @param name  (required)
   * @param type  (required)
   * @param quantumBackend  (required)
   * @param description  (optional)
   * @param productionEndpoint  (optional)
   * @param usePlatformToken  (optional, default to FALSE)
   * @param cpu  (optional)
   * @param memory  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param userCode  (optional)
   * @param apiDefinition  (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /v2/services?name={name}&description={description}&type={type}&quantumBackend={quantumBackend}&productionEndpoint={productionEndpoint}&usePlatformToken={usePlatformToken}&cpu={cpu}&memory={memory}")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDto> createServiceWithHttpInfo(@Param("name") String name, @Param("type") String type, @Param("quantumBackend") String quantumBackend, @Param("description") String description, @Param("productionEndpoint") String productionEndpoint, @Param("usePlatformToken") String usePlatformToken, @Param("cpu") Integer cpu, @Param("memory") Integer memory, @Param("xOrganizationId") String xOrganizationId, @Param("userCode") File userCode, @Param("apiDefinition") File apiDefinition);


  /**
   * 
   * Creates a new service within your organization or for yourself, must be either of type MANAGED or EXTERNAL. For managed services, it is required to upload a \&quot;userCode\&quot; file within a request. For external services, you must supply a valid \&quot;productionEndpoint\&quot; URL.
   * Note, this is equivalent to the other <code>createService</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link CreateServiceQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param userCode  (optional)
   * @param apiDefinition  (optional)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>name -  (required)</li>
   *   <li>description -  (optional)</li>
   *   <li>type -  (required)</li>
   *   <li>quantumBackend -  (required)</li>
   *   <li>productionEndpoint -  (optional)</li>
   *   <li>usePlatformToken -  (optional, default to FALSE)</li>
   *   <li>cpu -  (optional)</li>
   *   <li>memory -  (optional)</li>
   *   </ul>
   * @return ServiceDto
   */
  @RequestLine("POST /v2/services?name={name}&description={description}&type={type}&quantumBackend={quantumBackend}&productionEndpoint={productionEndpoint}&usePlatformToken={usePlatformToken}&cpu={cpu}&memory={memory}")
  @Headers({
  "Content-Type: multipart/form-data",
  "Accept: application/json",
      "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDto createService(@Param("xOrganizationId") String xOrganizationId, @Param("userCode") File userCode, @Param("apiDefinition") File apiDefinition, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
  * 
  * Creates a new service within your organization or for yourself, must be either of type MANAGED or EXTERNAL. For managed services, it is required to upload a \&quot;userCode\&quot; file within a request. For external services, you must supply a valid \&quot;productionEndpoint\&quot; URL.
  * Note, this is equivalent to the other <code>createService</code> that receives the query parameters as a map,
  * but this one also exposes the Http response headers
              * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
              * @param userCode  (optional)
              * @param apiDefinition  (optional)
      * @param queryParams Map of query parameters as name-value pairs
      *   <p>The following elements may be specified in the query map:</p>
      *   <ul>
          *   <li>name -  (required)</li>
          *   <li>description -  (optional)</li>
          *   <li>type -  (required)</li>
          *   <li>quantumBackend -  (required)</li>
          *   <li>productionEndpoint -  (optional)</li>
          *   <li>usePlatformToken -  (optional, default to FALSE)</li>
          *   <li>cpu -  (optional)</li>
          *   <li>memory -  (optional)</li>
      *   </ul>
          * @return ServiceDto
      */
      @RequestLine("POST /v2/services?name={name}&description={description}&type={type}&quantumBackend={quantumBackend}&productionEndpoint={productionEndpoint}&usePlatformToken={usePlatformToken}&cpu={cpu}&memory={memory}")
      @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
          "X-OrganizationId: {xOrganizationId}"
      })
   ApiResponse<ServiceDto> createServiceWithHttpInfo(@Param("xOrganizationId") String xOrganizationId, @Param("userCode") File userCode, @Param("apiDefinition") File apiDefinition, @QueryMap(encoded=true) Map<String, Object> queryParams);


   /**
   * A convenience class for generating query parameters for the
   * <code>createService</code> method in a fluent style.
   */
  public static class CreateServiceQueryParams extends HashMap<String, Object> {
    public CreateServiceQueryParams name(final String value) {
      put("name", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams description(final String value) {
      put("description", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams type(final String value) {
      put("type", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams quantumBackend(final String value) {
      put("quantumBackend", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams productionEndpoint(final String value) {
      put("productionEndpoint", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams usePlatformToken(final String value) {
      put("usePlatformToken", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams cpu(final Integer value) {
      put("cpu", EncodingUtils.encode(value));
      return this;
    }
    public CreateServiceQueryParams memory(final Integer value) {
      put("memory", EncodingUtils.encode(value));
      return this;
    }
  }

  /**
   * 
   * Deletes a service. Published services need to be unpublish before it can be deleted.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /services/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  void deleteService(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>deleteService</code> but it also returns the http response headers .
   * Deletes a service. Published services need to be unpublish before it can be deleted.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /services/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Void> deleteServiceWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets the API definition of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @return String
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/api-spec")
  @Headers({
    "Accept: application/json",
  })
  String getApiDefinition(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId);

  /**
   * 
   * Similar to <code>getApiDefinition</code> but it also returns the http response headers .
   * Gets the API definition of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/api-spec")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<String> getApiDefinitionWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId);



  /**
   * 
   * Gets the build status of a managed service. For example, only after a successful build the service can be published, either internally or publicly.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return BuildJobDto
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/build-status")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  BuildJobDto getBuildStatus(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getBuildStatus</code> but it also returns the http response headers .
   * Gets the build status of a managed service. For example, only after a successful build the service can be published, either internally or publicly.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/build-status")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<BuildJobDto> getBuildStatusWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all industries. 
   * @return List&lt;IndustryDto&gt;
   */
  @RequestLine("GET /industries")
  @Headers({
    "Accept: application/json",
  })
  List<IndustryDto> getIndustries();

  /**
   * 
   * Similar to <code>getIndustries</code> but it also returns the http response headers .
   * Gets a list of all industries. 
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /industries")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<List<IndustryDto>> getIndustriesWithHttpInfo();



  /**
   * 
   * Gets a specific service and its basic properties.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDto
   */
  @RequestLine("GET /services/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDto getService(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getService</code> but it also returns the http response headers .
   * Gets a specific service and its basic properties.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDto> getServiceWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all active subscriptions to a specific service version.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return List&lt;SubscriptionDto&gt;
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/subscriptions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  List<SubscriptionDto> getServiceSubscriptions(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getServiceSubscriptions</code> but it also returns the http response headers .
   * Gets a list of all active subscriptions to a specific service version.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/subscriptions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<List<SubscriptionDto>> getServiceSubscriptionsWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a specific service version and its basic properties.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto getServiceVersion(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getServiceVersion</code> but it also returns the http response headers .
   * Gets a specific service version and its basic properties.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> getServiceVersionWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all service versions. Note: At the moment, there is only one service version per service.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return Set&lt;ServiceDefinitionDto&gt;
   */
  @RequestLine("GET /services/{id}/versions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  Set<ServiceDefinitionDto> getServiceVersions(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getServiceVersions</code> but it also returns the http response headers .
   * Gets a list of all service versions. Note: At the moment, there is only one service version per service.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{id}/versions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Set<ServiceDefinitionDto>> getServiceVersionsWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all services. Use the \&quot;lifecycle\&quot; parameter to filter the resulting list based on the service&#39; lifecycle state.
   * @param lifecycle  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return List&lt;ServiceDto&gt;
   */
  @RequestLine("GET /services?lifecycle={lifecycle}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  List<ServiceDto> getServices(@Param("lifecycle") String lifecycle, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getServices</code> but it also returns the http response headers .
   * Gets a list of all services. Use the \&quot;lifecycle\&quot; parameter to filter the resulting list based on the service&#39; lifecycle state.
   * @param lifecycle  (optional)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services?lifecycle={lifecycle}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<List<ServiceDto>> getServicesWithHttpInfo(@Param("lifecycle") String lifecycle, @Param("xOrganizationId") String xOrganizationId);


  /**
   * 
   * Gets a list of all services. Use the \&quot;lifecycle\&quot; parameter to filter the resulting list based on the service&#39; lifecycle state.
   * Note, this is equivalent to the other <code>getServices</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link GetServicesQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>lifecycle -  (optional)</li>
   *   </ul>
   * @return List&lt;ServiceDto&gt;
   */
  @RequestLine("GET /services?lifecycle={lifecycle}")
  @Headers({
  "Accept: application/json",
      "X-OrganizationId: {xOrganizationId}"
  })
  List<ServiceDto> getServices(@Param("xOrganizationId") String xOrganizationId, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
  * 
  * Gets a list of all services. Use the \&quot;lifecycle\&quot; parameter to filter the resulting list based on the service&#39; lifecycle state.
  * Note, this is equivalent to the other <code>getServices</code> that receives the query parameters as a map,
  * but this one also exposes the Http response headers
              * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
      * @param queryParams Map of query parameters as name-value pairs
      *   <p>The following elements may be specified in the query map:</p>
      *   <ul>
          *   <li>lifecycle -  (optional)</li>
      *   </ul>
          * @return List&lt;ServiceDto&gt;
      */
      @RequestLine("GET /services?lifecycle={lifecycle}")
      @Headers({
    "Accept: application/json",
          "X-OrganizationId: {xOrganizationId}"
      })
   ApiResponse<List<ServiceDto>> getServicesWithHttpInfo(@Param("xOrganizationId") String xOrganizationId, @QueryMap(encoded=true) Map<String, Object> queryParams);


   /**
   * A convenience class for generating query parameters for the
   * <code>getServices</code> method in a fluent style.
   */
  public static class GetServicesQueryParams extends HashMap<String, Object> {
    public GetServicesQueryParams lifecycle(final String value) {
      put("lifecycle", EncodingUtils.encode(value));
      return this;
    }
  }

  /**
   * 
   * Gets the source code of service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @return byte[]
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/source-code")
  @Headers({
    "Accept: application/octet-stream",
  })
  byte[] getSourceCode(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId);

  /**
   * 
   * Similar to <code>getSourceCode</code> but it also returns the http response headers .
   * Gets the source code of service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /services/{serviceId}/versions/{versionId}/source-code")
  @Headers({
    "Accept: application/octet-stream",
  })
  ApiResponse<byte[]> getSourceCodeWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId);



  /**
   * 
   * Publishes a service publicly to the marketplace. Publicly published services can be subscribed and consumed by other users of the PlanQK platform.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/publish")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto publishService(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>publishService</code> but it also returns the http response headers .
   * Publishes a service publicly to the marketplace. Publicly published services can be subscribed and consumed by other users of the PlanQK platform.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/publish")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> publishServiceWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Publishes a service internally. Internally published services can only be subscribed and consumed by yourself or within your organization.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/publish-internal")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto publishServiceInternal(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>publishServiceInternal</code> but it also returns the http response headers .
   * Publishes a service internally. Internally published services can only be subscribed and consumed by yourself or within your organization.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/publish-internal")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> publishServiceInternalWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * This endpoint is deprecated and will be replaced by the \&quot;/commands\&quot; endpoint. However, it unpublishes a service forcefully and cancels all active subscriptions while removing all use case relations.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   * @deprecated
   */
  @Deprecated
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/unpublish")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto unpublishService(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>unpublishService</code> but it also returns the http response headers .
   * This endpoint is deprecated and will be replaced by the \&quot;/commands\&quot; endpoint. However, it unpublishes a service forcefully and cancels all active subscriptions while removing all use case relations.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   * @deprecated
   */
  @Deprecated
  @RequestLine("POST /services/{serviceId}/versions/{versionId}/unpublish")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> unpublishServiceWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Updates the API definition of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param file  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("PUT /{serviceId}/versions/{versionId}/api-spec")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  void updateApiDefinition(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("file") File file, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>updateApiDefinition</code> but it also returns the http response headers .
   * Updates the API definition of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param file  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("PUT /{serviceId}/versions/{versionId}/api-spec")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Void> updateApiDefinitionWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("file") File file, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Updates a specific service version&#39;s description or industry assignments.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param updateVersionRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   */
  @RequestLine("PUT /services/{serviceId}/versions/{versionId}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto updateServiceVersion(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, UpdateVersionRequest updateVersionRequest, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>updateServiceVersion</code> but it also returns the http response headers .
   * Updates a specific service version&#39;s description or industry assignments.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param updateVersionRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PUT /services/{serviceId}/versions/{versionId}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> updateServiceVersionWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, UpdateVersionRequest updateVersionRequest, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Updates the source code of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param sourceCode  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ServiceDefinitionDto
   */
  @RequestLine("PUT /{serviceId}/versions/{versionId}/source-code")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ServiceDefinitionDto updateSourceCode(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("sourceCode") File sourceCode, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>updateSourceCode</code> but it also returns the http response headers .
   * Updates the source code of a service.
   * @param serviceId  (required)
   * @param versionId  (required)
   * @param sourceCode  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("PUT /{serviceId}/versions/{versionId}/source-code")
  @Headers({
    "Content-Type: multipart/form-data",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ServiceDefinitionDto> updateSourceCodeWithHttpInfo(@Param("serviceId") UUID serviceId, @Param("versionId") UUID versionId, @Param("sourceCode") File sourceCode, @Param("xOrganizationId") String xOrganizationId);


}
