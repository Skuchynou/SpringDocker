Pour les commandes curl, on peut utiliser (sur le cmd windows pas sur le powershell) les commandes suivantes : 

curl -X POST http://localhost:8085/api/employee -H "Content-type:application/json" -d "{\"id\":\"89\",\"firstName\":\"Chan\",\"lastName\":\"222222\"}"
Pour le post d'un employé que l'on transformera en hash du valeur plus tard 

curl -X GET http://localhost:8085/api/employee/89

Pour le get d'un employé