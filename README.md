
```
aws lambda list-functions --profile aws-federated --region us-west-2

aws cloudformation create-stack --stack-name inv-event-processor --template-body file://Infrastructure.json --profile aws-federated --region us-east-2

```

build artifact
----------------

as lambda server does not allow adding classpath with external java-archives, create
java-archive with deps which has to < 50MBs

```
mvn clean compile assembly:single
```

create lambda handler
----------------------

create a Identity Access Role with a policy allowing acccess to Lambda server.

```
$ aws lambda create-function --function-name inventory-event-processor --runtime java8 --role arn:aws:iam::033814027302:role/gregor-samsa-lambda-role --handler event.handlers.InventoryEventHandler::handleRequest --zip-file fileb://target/amz-wavelength-1.0-SNAPSHOT.jar --memory-size 512 --region us-west-2 --profile aws-federated
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

setup source as KStream.

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

emit events
-----------

```
$ aws kinesis put-record --stream-name InvStream --data "{"name": "ppd-3"}" --partition-key 3 --explicit-hash-key 3 --profile aws-federated --region us-east-2 
{
    "ShardId": "shardId-000000000000", 
    "SequenceNumber": "49574469025035173505113407932307675841737459605627207682"
}

$ aws kinesis put-record --stream-name InvStream --data "{"name": "ppd-6"}" --partition-key 6 --profile aws-federated --region us-east-2 
{
    "ShardId": "shardId-000000000000", 
    "SequenceNumber": "49574476939837253406015806857548040859250542097159159810"
}

$ aws kinesis put-record --stream-name InvStream --data "{"name": "ppd-8"}" --partition-key 8 --profile aws-federated --region us-east-2 
{
    "ShardId": "shardId-000000000003", 
    "SequenceNumber": "49574476831790142919134941539823359581397198178663530546"
}
```

the handler will be triggered as soon as event arrives stream.

run the event handler
-------------------------

```
aws lambda invoke --invocation-type RequestResponse --function-name inventory-event-processor --payload 100 --region us-west-2 --profile aws-federated output.text
{
    "StatusCode": 200
}
```


References
-----------

http://docs.aws.amazon.com/lambda/latest/dg/get-started-create-function.html

https://blog.symphonia.io/learning-lambda-part-5-743d8a99db53

https://hackernoon.com/processing-real-time-big-data-streams-using-kinesis-lambda-561a029ef305