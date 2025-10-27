# OpenApiDefinition.TicTacToeControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**noOfGame**](TicTacToeControllerApi.md#noOfGame) | **POST** /no-of-game | 



## noOfGame

> Object noOfGame(requestBody)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.TicTacToeControllerApi();
let requestBody = {key: "null"}; // {String: String} | 
apiInstance.noOfGame(requestBody, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestBody** | [**{String: String}**](String.md)|  | 

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

