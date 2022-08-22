container=( "$@" );
for cont in ${container[@]}
do
  ./ci/healthcheck.sh $cont;
  
  if [ $? -eq 0 ]
  then
    echo "$cont is running"
  else
    echo "$cont is not running. Please check the logs."
    exit 1
  fi 
done
