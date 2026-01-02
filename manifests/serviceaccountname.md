kubectl create namespace todok8s
kubectl create serviceaccount todo -n todok8s
kubectl create rolebinding todok8s-binding \
  --clusterrole=admin \
  --serviceaccount=todok8s:todo \
  --namespace=todok8s