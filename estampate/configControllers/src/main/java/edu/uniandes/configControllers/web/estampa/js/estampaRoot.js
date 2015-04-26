/**
 * controller Estampa 
 */
estampateControllers.controller('estampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$http.get("/estampateWEB/webresources/Estampa").success(function (response){
		$scope.estampas= response;					
	} );
	$scope.selecionarEstampa=function(estampa){
		$cookieStore.put('estampaSelected',estampa);
	}
