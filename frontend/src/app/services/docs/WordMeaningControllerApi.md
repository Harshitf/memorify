# OpenApiDefinition.WordMeaningControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAnswer**](WordMeaningControllerApi.md#getAnswer) | **GET** /ai | 
[**getWordMeaning**](WordMeaningControllerApi.md#getWordMeaning) | **GET** /get | 
[**saveWordMeaning**](WordMeaningControllerApi.md#saveWordMeaning) | **POST** /save | 
[**wordMeaning**](WordMeaningControllerApi.md#wordMeaning) | **GET** /word-meaning-home | 



## getAnswer

> String getAnswer(word)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.WordMeaningControllerApi();
let word = "word_example"; // String | 
apiInstance.getAnswer(word, (error, data, response) => {
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
 **word** | **String**|  | 

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## getWordMeaning

> Object getWordMeaning()



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.WordMeaningControllerApi();
apiInstance.getWordMeaning((error, data, response) => {
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

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## saveWordMeaning

> String saveWordMeaning(wordMeaningDto)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.WordMeaningControllerApi();
let wordMeaningDto = new OpenApiDefinition.WordMeaningDto(); // WordMeaningDto | 
apiInstance.saveWordMeaning(wordMeaningDto, (error, data, response) => {
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
 **wordMeaningDto** | [**WordMeaningDto**](WordMeaningDto.md)|  | 

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## wordMeaning

> WordMeaningDto wordMeaning()



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.WordMeaningControllerApi();
apiInstance.wordMeaning((error, data, response) => {
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

[**WordMeaningDto**](WordMeaningDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

