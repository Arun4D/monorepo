# Name of the target container to check
container_name="$1"
# Timeout in seconds. Default: 60
timeout=$((${2:-60}));

if [ -z $container_name ]; then
  echo "No container name specified";
  exit 1;
fi

echo "Container: $container_name";
echo "Timeout: $timeout sec";

#initalStatus=$(docker inspect --format='{{json .State}}' $container_name)
#echo "Inital Status: $initalStatus";

try=0;
is_healthy="false";
while [ $is_healthy != "true" ];
do
  try=$(($try + 1));
  printf "■";
  is_healthy=$(docker inspect --format='{{json .State}}' $container_name | jq '.Running == true');
  sleep 1;
  if [[ $try -eq $timeout ]]; then
    echo " Container did not boot within timeout";
    exit 1;
  fi
done
