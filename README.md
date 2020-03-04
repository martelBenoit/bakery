# SOA - Exposition de service -  Boulangerie

> **Auteurs :** Yoann Le Dréan, Benoît Martel et Loïc Travaillé

## Livrables
* Une archive **ws.war** contenant les trois services demandés.
* Un dossier **doc** contenant la JavaDoc.
* Un fichier **pdf** contenant notre analyse de WS-SECURITY.

## Utilisation

Pour lancer le fichier war sur un docker vous pouvez utiliser cette commande : 

```java 
sudo docker run --rm --name bakeryService -tomcat -v $(pwd)/ws.war:/usr/local/tomcat/webapps/ws.war -it -p 8080:8080 tomcat:9.0.12-jre10-slim
```

> **Attention : ** Cette commande peut varier en fonction de votre configuration !

En suivant cette commande les trois services seront publiés à ces endroits :
* Users management service : http://localhost:8080/ws/usersManagementService?wsdl
* Orders management service : http://localhost:8080/ws/ordersManagementService?wsdl
*  Payments management service : http://localhost:8080/ws/paymentsManagementService?wsdl