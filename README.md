
```
aws lambda list-functions --profile aws-federated --region us-west-2
```

http://docs.aws.amazon.com/lambda/latest/dg/get-started-create-function.html

build artifact
----------------

```
lein pom
mvn clean install
```

create lambda handler
----------------------

create a Identity Access Role with a policy allowing acccess to Lambda server.

```
$ aws lambda create-function --function-name order-event-processor --runtime java8 --role arn:aws:iam::033814027302:role/gregor-samsa-lambda-role --handler handler.OrderEventHandler::onEvent --zip-file fileb://target/aws-test-1.0-SNAPSHOT.jar --memory-size 512 --region us-west-2 --profile aws-federated
{
    "CodeSha256": "glQaUTO4Kybl5SkJoWOy5T330iaechhSdS3VVG5lC6g=", 
    "FunctionName": "order-event-processor", 
    "CodeSize": 2664, 
    "MemorySize": 512, 
    "FunctionArn": "arn:aws:lambda:us-west-2:033814027302:function:order-event-processor", 
    "Version": "$LATEST", 
    "Role": "arn:aws:iam::033814027302:role/gregor-samsa-lambda-role", 
    "Timeout": 3, 
    "LastModified": "2017-06-22T21:19:51.800+0000", 
    "Handler": "handler.OrderEventHandler::onEvent", 
    "Runtime": "java8", 
    "Description": ""
}
```

```
$ aws lambda list-functions --profile aws-federated --region us-west-2
{
    "Functions": [
        {
            "Version": "$LATEST", 
            "CodeSha256": "glQaUTO4Kybl5SkJoWOy5T330iaechhSdS3VVG5lC6g=", 
            "FunctionName": "order-event-processor", 
            "MemorySize": 512, 
            "CodeSize": 2664, 
            "FunctionArn": "arn:aws:lambda:us-west-2:033814027302:function:order-event-processor", 
            "Handler": "handler.OrderEventHandler::onEvent", 
            "Role": "arn:aws:iam::033814027302:role/gregor-samsa-lambda-role", 
            "Timeout": 3, 
            "LastModified": "2017-06-22T21:19:51.800+0000", 
            "Runtime": "java8", 
            "Description": ""
        }
    ]
}
```

run the event handler
-------------------------

```
aws lambda invoke --invocation-type RequestResponse --function-name order-event-processor --payload \"1\" --region us-west-2 --profile aws-federated output.text
{
    "FunctionError": "Unhandled", 
    "StatusCode": 200
}
```
