/**
 * Config Controllers
 */

var estampateControllers = angular.module('estampateControllers', ['ngCookies']);

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
} ]);

estampateControllers.controller('estampaAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/Estampa/ByUser").success(function (response){
		$scope.estampas= response;					
	} );
} ]);

estampateControllers.controller('camisetasAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
		$scope.camisetas= response;					
	} );
} ]);

estampateControllers.controller('perfilCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/Persona/ByUser").success(function (response){
		$scope.persona= response;					
	} );
} ]);