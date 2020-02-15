#!/usr/bin/env bash

set -ex;

mvn clean install;

scp /home/tamerlan/disc/dev/bvg-hackaton/ua-guys/target/ua_guys-0.0.1-SNAPSHOT.jar \
tamerlan@cmlteam.com:./



ssh tamerlan@cmlteam.com '

PORT=$(sudo lsof -t -i:38080);

if [[ $PORT ]]
then
sudo kill -9 $PORT;
fi;

java -jar ua_guys-0.0.1-SNAPSHOT.jar &

'

