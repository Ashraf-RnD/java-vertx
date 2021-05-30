= Java-vertx

image:https://img.shields.io/badge/vert.x-4.0.2-purple.svg[link="https://vertx.io"]

This application was generated using http://start.vertx.io

== Building

To launch your tests:
```
./gradlew clean test
```

To package your application:
```
./gradlew clean assemble
```

To run your application:
```
./gradlew clean run
```
To run your application without test:
```
gradle clean build run -x test
```

To see sns information in aws cli :

Topic List
```
aws sns --endpoint http://localhost:4575 --region ap-southeast-1 list-topics
```
Subscription List
```
aws sns --endpoint http://localhost:4575 --region ap-southeast-1 list-subscriptions
```

== Help

* https://vertx.io/docs/[Vert.x Documentation]
* https://stackoverflow.com/questions/tagged/vert.x?sort=newest&pageSize=15[Vert.x Stack Overflow]
* https://groups.google.com/forum/?fromgroups#!forum/vertx[Vert.x User Group]
* https://gitter.im/eclipse-vertx/vertx-users[Vert.x Gitter]


