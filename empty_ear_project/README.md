#This structure can be re-used to create JEE 7 based applications. It has a pre-configured REST service.
#Deployment is tested on wildfly 11
sample url : http://localhost:8080/v1/helloService/hello?name=Prateek

#Install miroprofile config on WildFly

To add it to an existing WildFly server installation:

 download and unzip the distribution wildfly-microprofile-health-dist zip.

    Copy the relevant modules to your WildFly installation:

    cd modules/system/layers/base
    cp -R ./org/eclipse/microprofile $WILDFLY_HOME/modules/system/layers/base/org/eclipse
    cp -R ./org/wildfly/extension/microprofile $WILDFLY_HOME/modules/system/layers/base/org/wildfly/extension

    Add the extension and subsystem to your standalone.xml:

    a. In the <extensions> add <extension module="org.wildfly.extension.microprofile.health"/>

    b. In the <profile> add 
    <subsystem xmlns="urn:wildfly:microprofile-health:1.0">
            <http path="/health"/>
        </subsystem>


 Access health data like, http://<jboss_IP>:<PORT>/health
 