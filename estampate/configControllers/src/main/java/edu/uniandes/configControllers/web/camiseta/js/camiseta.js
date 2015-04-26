/**
 * 
 */
estampateControllers.controller('camisaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
		$scope.tiposCamisetas= response;					
	} );
	$scope.selecionarTipoCamiseta=function(tipoCamiseta){
		$cookieStore.put('tipoCamisetaSelected',tipoCamiseta);
	}		
} ]);



	