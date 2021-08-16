#1 Logearse a su cuenta con el azure portal
az login

#2 Crearse un Service Principal
az ad sp create-for-rbac --name ucreativa_microservicios

#4 Deslogearse de su cuenta
az logout

#5 Connectarse con el service principal (REMPLACEN con sus datos)
az login --service-principal --username af06e853-28a4-4365-8a19-712a38b7cee4  --password "password"  --tenant 7d24c6c8-bde1-44ce-b93c-717379e23c7b

#6 Crear Resource Group
az group create -l brazilsouth -n dev-clase10-rg

#7 Crear el Azure Container Registry
az acr create --resource-group dev-clase10-rg --name ucreativaregistryclase10 --sku Basic

#8 Cree la imagen que va a hacer deployment, recuerde que tiene que estar ubicado donde esta el código y el dockerfile
docker build --tag ucreativaregistryclase10.azurecr.io/pythonmultiplicacion:1.0.0 .

#9 Haga Login al Container Registry
az acr login --name ucreativaregistryclase10

#10 Haga push de la imagen al registry
docker push ucreativaregistryclase10.azurecr.io/pythonmultiplicacion:1.0.0

#11 Cree el cluster
# Recuerde aquí hay que proveer su username y password (Use el Service Principal)
# Note que estamos poniendo un label para estos nodos
az aks create -g dev-clase10-rg -n Clase10Cluster --node-count 1 --nodepool-labels "processing=aqui"  --service-principal "af06e853-28a4-4365-8a19-712a38b7cee4"  --client-secret "password" 
			  
#12 Loggese con Kubectl
az aks get-credentials -g dev-clase10-rg -n Clase10Cluster

#13 Confirme que se logeo bien ejecutando cualquier comando de Kubectl
kubectl get no -o wide

#14 Instale el hola-deployment.yml
kubectl apply -f paso7-deployment.yml

#15 Recuerde que puede monitorear el estado de su cluster en otra terminal asi:
while true; do kubectl get po -o wide; sleep 1; done

#16 Agregue otro Nodepool
az aks nodepool add --resource-group dev-clase10-rg --cluster-name Clase10Cluster --name otro --node-count 1

#17 Confirme que ambos nodos fueron creados, recuerde uno tiene un label el otro no
kubectl get no -o wide

#18 Cambie la cantidad de replicas en hola-deployment.yml en la linea 8 por 20 y note que todos los pods se mantienen en el mismo nodo
kubectl apply -f paso7-deployment.yml
kubectl get po -o wide

#19 Si crea otro deployment con bastantes replicas veras que estos van al nodo que les da la gana
kubectl apply -f otropaso7-deployment.yml
kubectl get po -o wide

#20 Borre el resource group y con esto TODO para que no le sigan cobrando ;)
az group delete -n dev-clase10-rg