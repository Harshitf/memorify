# OpenApiDefinition.MessageApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMessages**](MessageApi.md#getMessages) | **GET** /api/v1/messages/chat/{chat-id} | 
[**saveMessage**](MessageApi.md#saveMessage) | **POST** /api/v1/messages | 
[**setMessageToSeen**](MessageApi.md#setMessageToSeen) | **PATCH** /api/v1/messages | 
[**uploadMedia**](MessageApi.md#uploadMedia) | **POST** /api/v1/messages/upload-media | 



## getMessages

> [MessageResponse] getMessages(chatId)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.MessageApi();
let chatId = "chatId_example"; // String | 
apiInstance.getMessages(chatId, (error, data, response) => {
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
 **chatId** | **String**|  | 

### Return type

[**[MessageResponse]**](MessageResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## saveMessage

> saveMessage(messageRequest)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.MessageApi();
let messageRequest = new OpenApiDefinition.MessageRequest(); // MessageRequest | 
apiInstance.saveMessage(messageRequest, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **messageRequest** | [**MessageRequest**](MessageRequest.md)|  | 

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


## setMessageToSeen

> setMessageToSeen(chatId)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.MessageApi();
let chatId = "chatId_example"; // String | 
apiInstance.setMessageToSeen(chatId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **chatId** | **String**|  | 

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## uploadMedia

> uploadMedia(chatId, file)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.MessageApi();
let chatId = "chatId_example"; // String | 
let file = "/path/to/file"; // File | 
apiInstance.uploadMedia(chatId, file, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **chatId** | **String**|  | 
 **file** | **File**|  | 

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: multipart/fprm-data
- **Accept**: Not defined

