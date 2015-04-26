/**
 * 
 */

estampateControllers.controller('estampaAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/Estampa/ByUser").success(function (response){
		$scope.estampas= response;					
	} );
} ]);