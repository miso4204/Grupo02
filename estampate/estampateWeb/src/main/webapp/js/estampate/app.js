/**
 * Config App
 */
var estampateApp = angular.module('estampateApp', [ 'ui.bootstrap','ngRoute', 'estampateControllers', 'ngSanitize', 'ngCookies' ], function($compileProvider) {
	$compileProvider.directive('compile', function($compile) {
		return function(scope, element, attrs) {
			scope.$watch(function(scope) {
				return scope.$eval(attrs.compile);
			}, function(value) {
				element.html(value);
				$compile(element.contents())(scope);
			});
		};
	});
});
estampateApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : '/estampateWEB/html/inicio.html',
		controller : 'mainCtrl'
	}).when('/estampa', {
		templateUrl : '/estampateWEB/html/estampa.html',
		controller : 'catalogoEstampaCtrl'
	}).when('/camisa', {
		templateUrl : '/estampateWEB/html/camiseta.html',
		controller : 'camisaCtrl'
	}).when('/personalizar', {
		templateUrl : '/estampateWEB/html/personalizar.html',
		controller : 'personalizarCtrl'
	}).when('/estampaAdmin', {
		templateUrl : '/estampateWEB/html/estampaAdmin.html',
		controller : 'estampaAdminCtrl'
	}).when('/camisetasAdmin', {
		templateUrl : '/estampateWEB/html/camisetasAdmin.html',
		controller : 'camisetasAdminCtrl'
	}).when('/perfil', {
		templateUrl : '/estampateWEB/html/perfil.html',
		controller : 'perfilCtrl'
	}).when('/estampado', {
		templateUrl : '/estampateWEB/html/catalogoEstampas.html',
		controller : 'estampaCtrl'
	}).when('/personalizarEstampa', {
		templateUrl : '/estampateWEB/html/personalizarEstampa.html',
		controller : 'personalizarEstampaCtrl'
	}).when('/crearEstampa', {
		templateUrl : '/estampateWEB/html/crearEstampa.html',
		controller : 'crearEstampaCtrl'
	}).when('/modificarEstampa/:estampa', {
		templateUrl : '/estampateWEB/html/modificarEstampa.html',
		controller : 'modificarEstampaCtrl'
	}).when('/personaAdmin', {
		templateUrl : '/estampateWEB/html/persona/personaAdmin.html',
		controller : 'personaAdminCtrl'
	}).when('/crearPersona', {
		templateUrl : '/estampateWEB/html/persona/persona.html',
		controller : 'crearPersonaCtrl'
	}).when('/modificarPersona/:persona', {
		templateUrl : '/estampateWEB/html/persona/modificarPersona.html',
		controller : 'modificarPersonaCtrl'
	}).when('/carrito', {
		templateUrl : '/estampateWEB/html/carrito.html',
		controller : 'carritoCtrl'
	}).when('/checkout', {
		templateUrl : '/estampateWEB/html/compras.html',
		controller : 'comprasCtrl'
	}).when('/reportesAdmin', {
		templateUrl : '/estampateWEB/html/reportes.html',
		controller : 'reportesCtrl'
	}).when('/catalogo', {
		templateUrl : '/estampateWEB/html/catalogo.html',
		controller : 'catalogoCtrl'
	})
} ]);

