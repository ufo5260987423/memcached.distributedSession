# memcached.distributedSession

------

Lastest Version:0.0.1-beta

------
## Introduce

This tool based on MemCached aims to act as a session-share-component for servers, which include Tomcat 6 and all the higher version.And what you really have to do is just write the configuration file and provide the necessary infomation of memcached servers .

## How to use

### Configuration
Add listed code in $Catalina_Home/conf/context.xml and surrounded it with context tag.

    <Manager className="com.ufo5260987423.memcached.distributedSession.manageProxy.TomcatProxy"
	addresses="192.168.0.104:11211"
	nodeName="Jerry" />

And the addresses attribute is the string of MemCached Servers' addresses blanked with spaces.Further more, nodeName is the identifier of tomcat server.

### References package
It's necessary to add slf4j-api-1.7.5.jar and xmemcached-2.0.0.jar to $Catalina_Home/lib. Of course you should add this tool to the folder. And please run command "mvn package" in order to compile this jar.

### Attention
When Compile your own jar,please modify the /src/test/resources/applicationContext.xml.Or your compile may fail.