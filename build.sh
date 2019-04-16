#!/bin/bash

APP_NAME=tools-0.0.1-SNAPSHOT.jar

echo start package

mvn install package -Dmaven.test.skip=true

#!/bin/sh
echo update source code...
git branch
git pull

echo stop application...

PID=$(ps -ef | grep $APP_NAME | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo application is already stopped.
else
    echo killing $PID.
    kill $PID
    echo done.
fi

cd target

echo start application...

nohup java -jar $APP_NAME &

tail -f /data0/www/apps/tools/nohup.out
