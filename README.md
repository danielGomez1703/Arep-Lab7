# Aplicación distribuida segura en todos sus frentes

este programa es un ejemplo del manejo de seguridad dentro de una aplicacion mediante certificados y autenticacion, por medio de solicitudes https. 
para el manejo de este tipo de aplicaciones se utilizo Spark como framework.

las credenciales de la aplicacion son:
	-usuario : admins
	-clave : admin



## requsites
    -java version 1.7+
    -maven installed
	-docker installed

## Installation
 ```sh
$ git pull https://github.com/danielGomez1703/Arep-Lab7
$ cd Arep-Lab7
$ cd secureApp
$ mvn package
```

# MANUAL
  estos programas estan desplegados en AWS para acceder a ellos localmente debe ejecutarlos en puertos difentes
  para esto utilizamos docker como herrmienta para este tipo de pruebas, so lo va a ejecutar local 
 
 ```sh
$ docker-compose up -d
```
	aca ya poda acceder de forma local con el siguiente link:
	
	https://localhost:45000
	

    
## Modelo
![Modelo](https://github.com/danielGomez1703/Arep-Lab7/blob/master/resources/Model.JPG)

    Este modelo describe la interaccion del programa a nivel de sus componentes. en el modelo destacan **calculator** y el **LinkedList**
    el contendor es un implementacion propia de una lista en base a la implementacion LIST de java.
    CalculatorMain tiene la clase anonima de calculator, en el codigo podemos ver como se hace mediante una funcion lambda y tambien mediante una clase anonima.
    
    

## Descripion
	Dentro de este proyecto encontrara dos aplicaciones diferentes, estas aplicaciones se montan en docker de forma local y funcion. 
	con la ayuda de docker hub montamos las imagenes en repositorios diferentes y por ende en contenedores diferentea. con el fin de mostrar la comunicacion segura entre 2 servidores.
	
![evidencia](https://github.com/danielGomez1703/Arep-Lab7/blob/master/resources/ev1.JPG)

	una vez habilitados los puertos y las imagenes corriendo obtenemos dos links diferentes para estos despliegues, el principal corre en el peurto 45000
	y nos dejara en un login 
	
![evidencia2](https://github.com/danielGomez1703/Arep-Lab7/blob/master/resources/ev2.JPG)
	
	por otro lado el servicio que locita este principal sera el siguiente:
	
![evidencia3](https://github.com/danielGomez1703/Arep-Lab7/blob/master/resources/ev3.JPG)
## Reporte

para consultar el reporte debe acceder al link 

[reporte de laboratorio](https://github.com/danielGomez1703/Arep-Lab7/blob/master/resources/Design_OO.pdf)

## Author

Daniel Felipe Gomez Suarez [danielgomez1703](https://github.com/danielGomez1703)
    
## BUILT IN

Proyecto construido en [Maven](https://maven.apache.org/)
   
## License
----
para consultar su licencia vaya al link 
[leer aqui](https://github.com/danielGomez1703/Arep-Lab7/blob/master/LICENSE)