package de.stoneone.planqk.api;

import de.stoneone.planqk.api.invoker.ApiClient;
import de.stoneone.planqk.api.invoker.EncodingUtils;
import de.stoneone.planqk.api.model.ApiResponse;

import de.stoneone.planqk.api.model.ApiDto;
import de.stoneone.planqk.api.model.CreateSubscriptionRequest;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public interface ServicePlatformMarketplaceApi extends ApiClient.Api {


  /**
   * 
   * Creates a subscription to a publicly published service. To subscribe a service, you must supply the ID of an application as well as the ID of your selected pricing plan. The ID of a pricing plan can be found in the service details.
   * @param id  (required)
   * @param createSubscriptionRequest  (required)
   */
  @RequestLine("POST /v2/apis/{id}/subscriptions")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  void createSubscription(@Param("id") UUID id, CreateSubscriptionRequest createSubscriptionRequest);

  /**
   * 
   * Similar to <code>createSubscription</code> but it also returns the http response headers .
   * Creates a subscription to a publicly published service. To subscribe a service, you must supply the ID of an application as well as the ID of your selected pricing plan. The ID of a pricing plan can be found in the service details.
   * @param id  (required)
   * @param createSubscriptionRequest  (required)
   */
  @RequestLine("POST /v2/apis/{id}/subscriptions")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  ApiResponse<Void> createSubscriptionWithHttpInfo(@Param("id") UUID id, CreateSubscriptionRequest createSubscriptionRequest);



  /**
   * 
   * Gets a specific publicly published service and its basic properties.
   * @param id  (required)
   * @return ApiDto
   */
  @RequestLine("GET /v2/apis/{id}")
  @Headers({
    "Accept: application/json",
  })
  ApiDto findService(@Param("id") UUID id);

  /**
   * 
   * Similar to <code>findService</code> but it also returns the http response headers .
   * Gets a specific publicly published service and its basic properties.
   * @param id  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/apis/{id}")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<ApiDto> findServiceWithHttpInfo(@Param("id") UUID id);



  /**
   * 
   * Gets a list of all publicly published service on the PlanQK platform.
   * @return List&lt;ApiDto&gt;
   */
  @RequestLine("GET /v2/apis")
  @Headers({
    "Accept: application/json",
  })
  List<ApiDto> findServices();

  /**
   * 
   * Similar to <code>findServices</code> but it also returns the http response headers .
   * Gets a list of all publicly published service on the PlanQK platform.
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/apis")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<List<ApiDto>> findServicesWithHttpInfo();



  /**
   * 
   * Retrieves the logo of an API.
   * @param id  (required)
   * @return List&lt;byte[]&gt;
   */
  @RequestLine("GET /v2/apis/{id}/logo")
  @Headers({
    "Accept: application/json",
  })
  List<byte[]> getLogo(@Param("id") UUID id);

  /**
   * 
   * Similar to <code>getLogo</code> but it also returns the http response headers .
   * Retrieves the logo of an API.
   * @param id  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /v2/apis/{id}/logo")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<List<byte[]>> getLogoWithHttpInfo(@Param("id") UUID id);


}
