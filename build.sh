#!/bin/bash

#!/bin/sh
echo update source code...
git branch
git pull

echo stop application...

PID=$(ps -ef | grep tools-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo application is already stopped.
else
    echo killing $PID.
    kill $PID
    echo done.
fi

echo package application...

mvn -U -T 1C clean package -pl tools -am

echo start application...

cd target

nohup java -jar $APP_NAME > /dev/null 2>&1 &

tail -f nohup.out
