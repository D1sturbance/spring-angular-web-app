# Welcome to Spring-Angular web application

Web application example using spring and angular.

### Installation
I personally prefer using linux OS, but as far as I know most users who will start developing from scratch or users who are working in huge corporate organizations will likely use Windows OS. So instruction will be dedicated to Windows users as for linux there are a lot of good material in the internet.
#### Windows
There are two possible scenarios either you clone via Intellij or use CMD to clone repository and import it.
I prefer using CMD.
```sh
$ git clone https://github.com/D1sturbance/spring-angular-web-app.git
```
Import project to Inellij. If you are using gradle version lower than <5.6 comment line in build.gradle:

`
plugins {
    id 'java'
   // id "com.github.spotbugs" version "4.3.0"
}
`

The problem is that spotbugs cannot be used with lower gradle versions. If you want to use lower gradle version use findbugs it is deprecated version of spotbugs. However, if somehow you imported project with lower gradle version and want to upgrade to higher version and use spotbugs. You can do it like that: 

`
gradlew.bat wrapper --gradle-version 6.1
`

Now then your build.gradle is correct you cant start building anugalar part and for that we need to install node.js.
Link for installer is found here: https://nodejs.org/en/download/
After node.js is installed go to your angular project root and run those commands:

```sh
$npm install
$npm rebuild
```

After installation is complete we can now start with launching application.

### Launching
There are two ways of launching application. You can either go via CMD or you can go and set up Intellij angular client. How to setup Intellij you can find https://www.jetbrains.com/help/idea/developing-node-js-applications.html#
I'd like to cover CMD part.
First of all go to java part and launch application. After that go to CMD and launch command below being in your angular client directory.

```sh
$npm start
```

Now you can go to http://localhost:8000/ and check your application.

P.S. If you went with Intellij setup. To find node.js locations just type in CMD:

```sh
$where node
```
