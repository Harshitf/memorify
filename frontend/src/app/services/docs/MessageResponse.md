# OpenApiDefinition.MessageResponse

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Number** |  | [optional] 
**content** | **String** |  | [optional] 
**senderId** | **String** |  | [optional] 
**receiverId** | **String** |  | [optional] 
**messageType** | **String** |  | [optional] 
**messageState** | **String** |  | [optional] 
**createdAt** | **Date** |  | [optional] 
**media** | **[Blob]** |  | [optional] 



## Enum: MessageTypeEnum


* `TEXT` (value: `"TEXT"`)

* `IMAGE` (value: `"IMAGE"`)

* `AUDIO` (value: `"AUDIO"`)

* `VIDEO` (value: `"VIDEO"`)





## Enum: MessageStateEnum


* `SENT` (value: `"SENT"`)

* `SEEN` (value: `"SEEN"`)




