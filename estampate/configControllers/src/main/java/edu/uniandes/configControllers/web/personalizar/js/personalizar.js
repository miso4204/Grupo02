/**
 * 
 */
estampateControllers.controller('personalizarCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
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
