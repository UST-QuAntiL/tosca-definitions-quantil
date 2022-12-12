package de.stoneone.planqk.api;

import de.stoneone.planqk.api.invoker.ApiClient;
import de.stoneone.planqk.api.invoker.EncodingUtils;
import de.stoneone.planqk.api.model.ApiResponse;

import de.stoneone.planqk.api.model.ServiceExecutionDto;
import de.stoneone.planqk.api.model.SubscriptionDto;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public interface ServicePlatformSubscriptionsApi extends ApiClient.Api {


  /**
   * 
   * Retrieves a specific subscription.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return SubscriptionDto
   */
  @RequestLine("GET /subscriptions/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  SubscriptionDto getSubscription(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getSubscription</code> but it also returns the http response headers .
   * Retrieves a specific subscription.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /subscriptions/{id}")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<SubscriptionDto> getSubscriptionWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);



  /**
   * 
   * Retrieves the executions of a subscription.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return List&lt;ServiceExecutionDto&gt;
   */
  @RequestLine("GET /subscriptions/{id}/executions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  List<ServiceExecutionDto> getSubscriptionExecutions(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);

  /**
   * 
   * Similar to <code>getSubscriptionExecutions</code> but it also returns the http response headers .
   * Retrieves the executions of a subscription.
   * @param id  (required)
   * @param xOrganizationId The ID of your organization in case you want to perform operations in this context. Leave it empty to operate in your personal space. (optional)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /subscriptions/{id}/executions")
  @Headers({
    "Accept: application/json",
    "X-OrganizationId: {xOrganizationId}"
  })
  ApiResponse<List<ServiceExecutionDto>> getSubscriptionExecutionsWithHttpInfo(@Param("id") UUID id, @Param("xOrganizationId") String xOrganizationId);


}
