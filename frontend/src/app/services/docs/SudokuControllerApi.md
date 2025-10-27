# OpenApiDefinition.SudokuControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**submitSudoku**](SudokuControllerApi.md#submitSudoku) | **POST** /submit | 



## submitSudoku

> Boolean submitSudoku(requestBody)



### Example

```javascript
import OpenApiDefinition from 'open_api_definition';

let apiInstance = new OpenApiDefinition.SudokuControllerApi();
let requestBody = [null]; // [[Number]] | 
apiInstance.submitSudoku(requestBody, (error, data, response) => {
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
 **requestBody** | [**[[Number]]**](Array.md)|  | 

### Return type

**Boolean**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

