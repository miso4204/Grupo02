/**
 * Config Controllers
 */

var estampateControllers = angular.module('estampateControllers', ['ngCookies']);
function isEmptyJSON (obj) {
	for(var i in obj) { return false; }
	return true;
}
estampateControllers.controller('mainCtrl', [ '$scope', '$http','$cookieStore', function($scope, $http, $cookieStore) {
	$http.get("/estampateWEB/webresources/Funcionalidades").success(function(response) {
		$scope.funcionalidades = response;
		$scope.prueba=$cookieStore.get("estampaSelected")
	});
} ]);



estampateControllers.controller('catalogoEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.vista="Galeria";
	$http.get("/estampateWEB/webresources/Estampa").success(function (response){
		$scope.estampas= response;					
	} );
	$scope.selecionarEstampa=function(estampa){
		$cookieStore.put('estampaSelected',estampa);
	}		
} ]);

estampateControllers.controller('estampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.estampaSeleccionada=$cookieStore.get('estampaSelected');
	$http.get("/estampateWEB/webresources/Estampa").success(function (response){
		$scope.estampas= response;					
	} );
	$scope.selecionarEstampa=function(estampa){
		$cookieStore.put('estampaSelected',estampa);
	}		
} ]);

estampateControllers.controller('camisaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
		$scope.tiposCamisetas= response;					
	} );
	$scope.selecionarTipoCamiseta=function(tipoCamiseta){
		$cookieStore.put('tipoCamisetaSelected',tipoCamiseta);
	}		
} ]);



estampateControllers.controller('personalizarCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.holaTest="hola :)";
	$scope.estampaSelected=$cookieStore.get('estampaSelected');
	$scope.tipoCamisetaSeleccionado=$cookieStore.get('tipoCamisetaSelected');
		$http.get("/estampateWEB/webresources/Talla").success(function (response){
			$scope.tallas= response;					
		} );
		$http.get("/estampateWEB/webresources/Color").success(function (response){
			$scope.colores= response;					
		} );
		$http.get("/estampateWEB/webresources/Material").success(function (response){
			$scope.materiales= response;					
		} );
} ]);

estampateControllers.controller('personalizarEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.value= 0;
	$scope.estampaSelected=$cookieStore.get('estampaSelected');
	$scope.tipoCamisetaSeleccionado=$cookieStore.get('tipoCamisetaSelected');
		$http.get("/estampateWEB/webresources/Talla").success(function (response){
			$scope.tallas= response;
			
		} );
		$http.get("/estampateWEB/webresources/Color").success(function (response){
			$scope.colores= response;
		} );
		$http.get("/estampateWEB/webresources/Material").success(function (response){
			$scope.materiales= response;					
		} );
		$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
			
			$scope.tiposCamisetas= response;
			$scope.tipoCamisetaSeleccionado = response[0];
		} );
	    
	    $scope.newValue = function(value) {
	    	$scope.tipoCamisetaSeleccionado = $scope.tiposCamisetas[value-1];
	    }
	    $scope.setTalla=function (talla){
	    	$scope.tallaSelected=talla;
	    	
		};
		$scope.setColor=function (color){
	    	$scope.colorSelected=color;
	    	
		};
	    $scope.addCarrito=function (){
	    	if( typeof $scope.tallaSelected === 'undefined' )
	    	{
	    		$scope.tallaSelected=$scope.tallas[0];
	    	}
	    	if( typeof $scope.colorSelected === 'undefined' )
	    	{
	    		$scope.colorSelected=$scope.colores[0];
	    	}
	    	var precio = $scope.tipoCamisetaSeleccionado.valor + $scope.estampaSelected.precio;
	    	$scope.camiseta={"cantidad":1, "tipoCamiseta": $scope.tipoCamisetaSeleccionado, "estampaBean":$scope.estampaSelected, "tallaCamiseta":$scope.tallaSelected, "colorCamiseta":$scope.colorSelected, "precio":precio, "materialCamiseta":$scope.materiales[0]};
	    	
	    	
			$http.put("/estampateWEB/webresources/Carrito/",$scope.camiseta).success(function (){
				 alert('La camiseta ha sido agregada al carrito de compras');
			} ).error(function(data, status, headers, config){
				alert('Error al actualizar el carrito:'+data);
			});
		};
} ]);

estampateControllers.controller('modificarEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$http.get("/estampateWEB/webresources/Estampa/"+$routeParams.estampa).success(function (response){
		$scope.estampa= response;					
	} );
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$http.get("/estampateWEB/webresources/Tema").success(function (response){
		$scope.temas= response;					
	} );
	$scope.modificar=function(){
			$http.put("/estampateWEB/webresources/Estampa/",$scope.estampa).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al actualizar la estampa:'+data}];
			});
	}
}]);
estampateControllers.controller('crearEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$scope.estampa={"cantidadVotos":0, "imagen":"", "nombre":"", "informacion":"", "popularidad":0, "precio":0, "ratting":0, "temaBean":{}};
	$http.get("/estampateWEB/webresources/Tema").success(function (response){
		$scope.temas= response;					
	} );
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.crear=function(){
			$http.post("/estampateWEB/webresources/Estampa/",$scope.estampa).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Estampa Creada'}];
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al crear la estampa:'+data}];
			});
	}
}]);
estampateControllers.controller('estampaAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$scope.cargarEstampas=function (){
		$http.get("/estampateWEB/webresources/Estampa/User").success(function (response){
			$scope.estampas= response;					
		} );
	}
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.eliminar=function(estampa){
		
			if(confirm("Esta seguro de eliminar la estampa ?")){
				$http.delete("/estampateWEB/webresources/Estampa/",estampa).success(function (){
					 $scope.alerts=[{type: 'success',msg: 'Estampa Eliminada'}];
					 $scope.cargarEstampas();
				} ).error(function(data, status, headers, config){
					$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
					$scope.cargarEstampas();
				});
			}		
	}
	$scope.cargarEstampas();
} ]);

estampateControllers.controller('camisetasAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
		$scope.camisetas= response;					
	} );
	$scope.seleccionar=function (){
			
	}
} ]);

estampateControllers.controller('perfilCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[]
	$http.get("/estampateWEB/webresources/Persona").success(function (response){
		$scope.persona= response;					
	} );
	$http.get("/estampateWEB/webresources/Usuario").success(function (response){
		$scope.usuario= response;					
	} );
	$scope.guardarPerfil=function (){
		$http.put("/estampateWEB/webresources/Persona/",$scope.persona).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
		});
	};
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.guardarPassword=function(){
		$http.put("/estampateWEB/webresources/Usuario/",$scope.usuario).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
		});
	}  
} ]);