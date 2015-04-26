/**
 * 
 */
estampateControllers.controller('camisetasAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/TipoCamiseta").success(function (response){
		$scope.camisetas= response;					
	} );
} ]);
