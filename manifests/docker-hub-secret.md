kubectl create secret docker-registry docker-hub-secret \
  --docker-server=https://index.docker.io/v1/ \
  --docker-username=username \
  --docker-password=pwd \
  --docker-email=mail \
  --namespace=todok8s