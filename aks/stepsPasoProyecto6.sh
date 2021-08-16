az login
az ad sp create-for-rbac --name ucreativa_microservicios

#1 Logearse a su cuenta con el azure portal
az login

#2 Crearse un Service Principal
az ad sp create-for-rbac --name ucreativa_microservicios

#3 Deslogearse de su cuenta
az logout

#4 Connectarse con el service principal (REMPLACEN con sus datos)
az login --service-principal --username af06e853-28a4-4365-8a19-712a38b7cee4  --password "password"  --tenant 7d24c6c8-bde1-44ce-b93c-717379e23c7b

#6 Revisar lista de cuentas que tienen disponible
az account list

#7 Revisar lista disponible de locaciones
az account list-locations --query '[].name'

#8 Crear Resource Group
az group create -l brazilsouth -n dev-clase09-rg

#9 Crear cluster de k8s
az aks create -g dev-clase09-rg -n CoolCluster --node-count 2

#10 Ver lista de Clusters creados
az aks list -o table

#11 Ir al cluster en el Azure Portal
az aks browse -n CoolCluster -g dev-clase09-rg

#12 Actualizar su ~/.kube/config para conectarse con kubectl
az aks get-credentials -g dev-clase09-rg -n CoolCluster

#13 Ver lista de NodePools
az aks nodepool list  --cluster-name CoolCluster --resource-group dev-clase09-rg

#14 Escalar su cluster a 1 nodo
az aks nodepool scale --cluster-name CoolCluster --name nodepool1 --resource-group dev-clase09-rg --node-count 1

#15 Agregar otro Nodepool
az aks nodepool add --resource-group dev-clase09-rg --cluster-name CoolCluster --name otro --node-count 1
	
#16 Crear deployment con python-service
kubectl apply -f k8s/paso6-deployment.yml

#17 Crear service para poder accesar python-service
kubectl apply -f k8s/hola-svc.yml

#18 Obtener IP Publica del svc
kubectl get svc

#19 Ir al Servicio expuesto en la nube
http://20.201.32.206:2000/multiplicacion/7/por/6

#20 Eliminar todo
az aks delete -n CoolCluster -g dev-clase09-rg
az group delete -n dev-clase09-rg