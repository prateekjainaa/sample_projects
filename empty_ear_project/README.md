#This structure can be re-used to create JEE 7 based applications. It has a pre-configured REST service.
#Deployment is tested on wildfly 11
sample url : http://localhost:8080/v1/helloService/hello?name=Prateek

sample call : curl -X POST http://localhost:8080/v1/helloService/root/pojo --header "Content-Type:application/json" -d @/tmp/user.json


contents of user.json
{
"firstName": "Prateek",
"lastName" : "Jain"
}
