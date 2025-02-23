minikube start --nodes=3 --driver=docker

kubectl label nodes minikube type=application
kubectl label nodes minikube-m02 type=database
kubectl label nodes minikube-m03 type=dependent_services