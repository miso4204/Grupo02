/**
 *  Perfil Controller
 */

estampateControllers.controller('perfilCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEB/webresources/Persona/ByUser").success(function (response){
		$scope.persona= response;					
	} );
} ]);