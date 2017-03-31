Escuela Colombiana de Ingenier�a

Construcci�n de Software - COSW

Taller - frameworks MVC Web - Elementos visuales.

 

Se quiere, en el proyecto trabajado en sesiones anteriores, agregar una vista en la que se muestren los usuarios inscritos en la aplicaci�n de TODOs, incluyendo sus datos b�sicos y su fotograf�a. El acceso a dicha informaci�n se har� a trav�s de una librer�a provista por un tercero.

Parte I. Backend.

En el pom.xml agregue el siguiente repositorio de dependencias (el cual contiene la librer�a requerida):

<repositories>
        <repository>
            <id>ECI internal repository</id>
            <url>http://profesores.is.escuelaing.edu.co/hcadavid/mvnmirror</url>
        </repository>
</repositories>
1.1. Si utiliza gradle agregue la dependencia al builg.gradle (app)

	```
	repositories {
		mavenCentral()
		maven { url "https://repo.spring.io/snapshot" }
		maven { url "https://repo.spring.io/milestone" }
		maven { url "http://profesores.is.escuelaing.edu.co/hcadavid/mvnmirror" }
	}
En el mismo archivo, agregue la siguiente dependencia:

    <dependency>
        <groupId>edu2.eci.cosw.stubs</groupId>
        <artifactId>FakeClientsLibrary</artifactId>
        <version>1.0</version>            
    </dependency>  
2.2. Si utiliza Gradle agregue la dependencia al builg.gradle (app) dependencies { compile('org.springframework.boot:spring-boot-starter-security') compile('org.springframework.boot:spring-boot-starter-web') compile('edu2.eci.cosw.stubs:FakeClientsLibrary:1.0') testCompile('org.springframework.boot:spring-boot-starter-test') } 3. Cree un API REST para el recurso 'clientes', de manera que maneje las URIs: * /clients <- obtiene todos los clientes * /clients/{id} <- obtiene un cliente en particular * /clients/{id}/picture <- obtiene la fotograf�a del cliente

Para lo anterior, tenga en cuenta:

*  Debe configurar la aplicaci�n para que las anteriores rutas no sean protegidas.
*  En la librer�a suministrada (como dependencia de Maven), la clase __ClientServicesStub__ tiene los siguientes m�todos:


```java
/**
 * Consultar el conjunto completo de clientes
 * @return el conjunto de clientes
 */
public Set<Client> getAllClients();

/**
 * Consultar un determiando cliente
 * @param id identificador del cliente
 * @return el cliente
 * @throws CliendLoadException si el identificador no existe
 */
public Client getClientById(int id) throws CliendLoadException; 

/**
 * Consultar la fotograf�a de un determinado cliente
 * @param id el identificador del cliente
 * @return el InputStream que permite consutar los bytes de la foto
 * del cliente
 * @throws CliendLoadException 
 */
public InputStream getClientPicture(int id) throws CliendLoadException;
```

* Para que un recurso REST tenga una representaci�n diferente a la est�ndar (JSON), en particular un archivo, el m�todo correspondiente del controlador REST debe retornar algo de tipo:

```
ResponseEntity<InputStreamResource>

```

Con lo anterior, el controlador puede construir la respuesta (en este caso con una imagen), usando la clase ResponseEntity:	
```		
ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpg")).body(new InputStreamResource(EL_INPUT_STREAM_DE_LA_IMAGEN));
```

Y en caso de error (por ejemplo si no se puede consultar la imagen), retornar un objeto ResponseEntity vac�o que tenga el c�digo NOT_FOUND de HTTP:

```				
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
```		
Parte II. Front-end.

Para el front-end se van a usar las siguientes librer�as de JavaScript (se est� actualizando la versi�n y se est� incluyendo angular-material). Haga la actualizaci�n correspondiente con bower:

"dependencies": { "angular": "^1.6.1", "angular-route": "^1.6.1", "angular-animate": "^1.6.1", "angular-aria": "^1.6.1", "angular-material": "^1.1.3" } ``` 2. Rectifique en en la p�gina html ra�z se est� importando el estilo de angular-material (esto va en la secci�n de la p�gina). Si hay referencias a otros estilos que no se usen (por ejemplo "html5-boilerplate") elim�nelos).

```	
```
Como hay una nueva librer�a (con una dependencia), la misma debe cargarse desde la p�gina principal. Agregue lo siguiente donde est�n las dem�s importaciones en la p�gina principal:

<script src="bower_components/angular-animate/angular-animate.js"></script> <script src="bower_components/angular-aria/angular-aria.js"></script> <script src="bower_components/angular-material/angular-material.js"></script>
```	
Modifique la configuraci�n del m�dulo principal (el definido en app.js), para que ahora importe 'ngMaterial'.

Cree una nueva vista (con su respectivo controlador) llamada 'Directorio de clientes', para el directorio de clientes de la aplicaci�n.

En el m�dulo de servicios creado en el ejercicio anterior, agregue uno de tipo 'f�brica' que retorne el $recurso asociado al recurso del API REST '/clients'. Como esto retorna un arreglo de objetos, es necesario definirlo de la siguiente forma:

.factory('Clients', function($resource){ return $resource('/clients',{},{ get: { method: 'GET', isArray: true
} });

    })
```	
En el controlador de su nueva vista inyecte el servicio antes creado ('Clients') y '$mdDialog' (un objeto de angular-material para crear ventanas emergentes).

En el controlador agregue un atributo (asociado a $scope) que contenga la lista de clientes. Haga que dicho atributo sea inicializado con el listado de los clientes obtenidos mediante en servicio 'Clients' inyectado.

En la vista cree una tabla que muestre todos los clientes, con las siguientes columnas:

Identificaci�n
Nombre
Fotograf�a
Bot�n 'detalles'
Use la directiva ng-repeat para que la tabla sea poblada.

Haga que el campo de foto (que deber�a ser un elemento de tipo 'image') tenga como fuente (src) un recurso '/clientes/{id}/picture' (del API REST), donde {id} es reemplazado por el valor que tenga la tabla en la fila correspondiente. Por ejemplo, si ng-repeat usa como variable 'cl':



```	12. Verifique que la aplicaci�n muestre el listado, incluyendo las fotos. 13. Revise en la documentaci�n de Angular-material c�mo abrir un di�logo: https://material.angularjs.org/latest/demo/dialog. Revise en el c�digo javascript del ejemplo c�mo se hizo la funci�n 'showAlert'. Use esta misma funci�n en su controlador, pero en lugar de pasar s�lo el objeto 'ev', agregue como par�metro un objeto 'client'. Con esto, haga que el di�logo muestre la descripci�n del perfil (profileDescription). 14. Asocie con ng-click los botones dibujados en la tabla con la funci�n antes creada (en este caso, pasando como par�metro el objeto $event y el objeto cliente -seg�n se llame en ng-repeat-).
Opcional

Cambie el di�logo por uno de tipo 'custom dialog', y haga una p�gina de detalle que muestre toda la informaci�n del cliente.