@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  java-vertx startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and JAVA_VERTX_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\java-vertx-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-client-4.0.2.jar;%APP_HOME%\lib\vertx-web-4.0.2.jar;%APP_HOME%\lib\vertx-maven-service-factory-4.0.2.jar;%APP_HOME%\lib\vertx-json-schema-4.0.2.jar;%APP_HOME%\lib\vertx-service-factory-4.0.2.jar;%APP_HOME%\lib\vertx-web-common-4.0.2.jar;%APP_HOME%\lib\vertx-auth-common-4.0.2.jar;%APP_HOME%\lib\vertx-core-4.0.2.jar;%APP_HOME%\lib\jackson-core-2.12.2.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\lombok-1.18.16.jar;%APP_HOME%\lib\vertx-bridge-common-4.0.2.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.52.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.52.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.52.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.52.Final.jar;%APP_HOME%\lib\netty-handler-4.1.52.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.52.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.52.Final.jar;%APP_HOME%\lib\netty-codec-4.1.52.Final.jar;%APP_HOME%\lib\netty-transport-4.1.52.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.52.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.52.Final.jar;%APP_HOME%\lib\netty-common-4.1.52.Final.jar;%APP_HOME%\lib\maven-aether-provider-3.3.1.jar;%APP_HOME%\lib\aether-connector-basic-1.1.0.jar;%APP_HOME%\lib\aether-transport-file-1.1.0.jar;%APP_HOME%\lib\aether-transport-http-1.1.0.jar;%APP_HOME%\lib\aether-impl-1.1.0.jar;%APP_HOME%\lib\aether-spi-1.1.0.jar;%APP_HOME%\lib\aether-util-1.1.0.jar;%APP_HOME%\lib\aether-api-1.1.0.jar;%APP_HOME%\lib\httpclient-4.5.3.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\guava-25.1-jre.jar;%APP_HOME%\lib\maven-model-builder-3.3.1.jar;%APP_HOME%\lib\maven-model-3.3.1.jar;%APP_HOME%\lib\maven-repository-metadata-3.3.1.jar;%APP_HOME%\lib\maven-builder-support-3.3.1.jar;%APP_HOME%\lib\plexus-utils-3.0.20.jar;%APP_HOME%\lib\httpcore-4.4.6.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-2.0.0.jar;%APP_HOME%\lib\error_prone_annotations-2.1.3.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\plexus-interpolation-1.21.jar


@rem Execute java-vertx
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JAVA_VERTX_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable JAVA_VERTX_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%JAVA_VERTX_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
