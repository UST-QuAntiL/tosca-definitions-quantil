package de.stoneone.planqk.api;

import de.stoneone.planqk.api.invoker.ApiClient;
import de.stoneone.planqk.api.invoker.EncodingUtils;
import de.stoneone.planqk.api.model.ApiResponse;

import de.stoneone.planqk.api.model.AccountDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-12-12T17:23:29.757670+01:00[Europe/Berlin]")
public interface UserAccountControllerApi extends ApiClient.Api {


  /**
   * 
   * 
   * @return List&lt;AccountDto&gt;
   */
  @RequestLine("GET /my/accounts")
  @Headers({
    "Accept: application/json",
  })
  List<AccountDto> getAccounts();

  /**
   * 
   * Similar to <code>getAccounts</code> but it also returns the http response headers .
   * 
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /my/accounts")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<List<AccountDto>> getAccountsWithHttpInfo();


}
