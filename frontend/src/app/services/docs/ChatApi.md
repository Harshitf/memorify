# OpenApiDefinition.ChatApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createChat**](ChatApi.md#createChat) | **POST** /api/v1/chats | 
[**getChatsByReciever**](ChatApi.md#getChatsByReciever) | **GET** /api/v1/chats | 



## createChat

> String createChat(senderId, recieverId)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ChatApi();
let senderId = "senderId_example"; // String | 
let recieverId = "recieverId_example"; // String | 
apiInstance.createChat(senderId, recieverId, (error, data, response) => {
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
 **senderId** | **String**|  | 
 **recieverId** | **String**|  | 

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getChatsByReciever

> [ChatResponse] getChatsByReciever()



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.ChatApi();
apiInstance.getChatsByReciever((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**[ChatResponse]**](ChatResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

