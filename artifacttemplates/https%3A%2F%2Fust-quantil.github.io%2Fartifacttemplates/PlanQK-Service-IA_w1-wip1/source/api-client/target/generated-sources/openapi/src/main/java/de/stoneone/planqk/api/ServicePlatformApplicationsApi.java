package de.stoneone.planqk.api;

import de.stoneone.planqk.api.invoker.ApiClient;
import de.stoneone.planqk.api.invoker.EncodingUtils;
import de.stoneone.planqk.api.model.ApiResponse;

import de.stoneone.planqk.api.model.AccessTokenDto;
import de.stoneone.planqk.api.model.ApplicationDto;
import de.stoneone.planqk.api.model.CreateApplicationRequest;
import de.stoneone.planqk.api.model.CreateInternalSubscriptionRequest;
import de.stoneone.planqk.api.model.SubscriptionDto;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public interface ServicePlatformApplicationsApi extends ApiClient.Api {


  /**
   * 
   * Creates a new application within your organization or for yourself.
   * @param createApplicationRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ApplicationDto
   */
  @RequestLine("POST /v2/applications")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApplicationDto createApplication(CreateApplicationRequest createApplicationRequest, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>createApplication</code> but it also returns the http response headers .
   * Creates a new application within your organization or for yourself.
   * @param createApplicationRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /v2/applications")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ApplicationDto> createApplicationWithHttpInfo(CreateApplicationRequest createApplicationRequest, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Creates a subscription to internally published services.
   * @param id  (required)
   * @param createInternalSubscriptionRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return SubscriptionDto
   */
  @RequestLine("POST /v2/applications/{id}/subscriptions")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  SubscriptionDto createInternalSubscription(@Param("id") UUID id, CreateInternalSubscriptionRequest createInternalSubscriptionRequest, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>createInternalSubscription</code> but it also returns the http response headers .
   * Creates a subscription to internally published services.
   * @param id  (required)
   * @param createInternalSubscriptionRequest  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /v2/applications/{id}/subscriptions")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<SubscriptionDto> createInternalSubscriptionWithHttpInfo(@Param("id") UUID id, CreateInternalSubscriptionRequest createInternalSubscriptionRequest, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Deletes an application.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /v2/applications/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  void deleteApplication(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>deleteApplication</code> but it also returns the http response headers .
   * Deletes an application.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /v2/applications/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Void> deleteApplicationWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Deletes a specific subscription of an application.
   * @param id  (required)
   * @param subscriptionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /v2/applications/{id}/subscriptions/{subscriptionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  void deleteApplicationSubscription(@Param("id") UUID id, @Param("subscriptionId") String subscriptionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>deleteApplicationSubscription</code> but it also returns the http response headers .
   * Deletes a specific subscription of an application.
   * @param id  (required)
   * @param subscriptionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   */
  @RequestLine("DELETE /v2/applications/{id}/subscriptions/{subscriptionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<Void> deleteApplicationSubscriptionWithHttpInfo(@Param("id") UUID id, @Param("subscriptionId") String subscriptionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets the access token for your application. The access token must be used to authenticate execution requests to subscribed services.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return AccessTokenDto
   */
  @RequestLine("POST /v2/applications/{id}/access-token")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  AccessTokenDto getAccessToken(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getAccessToken</code> but it also returns the http response headers .
   * Gets the access token for your application. The access token must be used to authenticate execution requests to subscribed services.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /v2/applications/{id}/access-token")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<AccessTokenDto> getAccessTokenWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets an specific application and its basic properties.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return ApplicationDto
   */
  @RequestLine("GET /v2/applications/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApplicationDto getApplication(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getApplication</code> but it also returns the http response headers .
   * Gets an specific application and its basic properties.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/applications/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<ApplicationDto> getApplicationWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets the information about a specific subscription.
   * @param id  (required)
   * @param subscriptionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return SubscriptionDto
   */
  @RequestLine("GET /v2/applications/{id}/subscriptions/{subscriptionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  SubscriptionDto getApplicationSubscription(@Param("id") UUID id, @Param("subscriptionId") String subscriptionId, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getApplicationSubscription</code> but it also returns the http response headers .
   * Gets the information about a specific subscription.
   * @param id  (required)
   * @param subscriptionId  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/applications/{id}/subscriptions/{subscriptionId}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<SubscriptionDto> getApplicationSubscriptionWithHttpInfo(@Param("id") UUID id, @Param("subscriptionId") String subscriptionId, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all subscriptions of an application. This information concerns the services this application is subscribe to, either internal or public (through the marketplace API) subscriptions.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return List&lt;SubscriptionDto&gt;
   */
  @RequestLine("GET /v2/applications/{id}/subscriptions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  List<SubscriptionDto> getApplicationSubscriptions(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getApplicationSubscriptions</code> but it also returns the http response headers .
   * Gets a list of all subscriptions of an application. This information concerns the services this application is subscribe to, either internal or public (through the marketplace API) subscriptions.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/applications/{id}/subscriptions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<List<SubscriptionDto>> getApplicationSubscriptionsWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Gets a list of all applications.
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return List&lt;ApplicationDto&gt;
   */
  @RequestLine("GET /v2/applications")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  List<ApplicationDto> getApplications(@Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getApplications</code> but it also returns the http response headers .
   * Gets a list of all applications.
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/applications")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<List<ApplicationDto>> getApplicationsWithHttpInfo(@Param("xOrganizationId") String xOrganizationId);


}
