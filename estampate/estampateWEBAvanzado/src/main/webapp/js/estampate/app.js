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
		templateUrl : '/estampateWEBAvanzado/html/inicio.html',
		controller : 'mainCtrl'
	}).when('/estampa', {
		templateUrl : '/estampateWEBAvanzado/html/estampa.html',
		controller : 'catalogoEstampaCtrl'
	}).when('/camisa', {
		templateUrl : '/estampateWEBAvanzado/html/camiseta.html',
		controller : 'camisaCtrl'
	}).when('/personalizar', {
		templateUrl : '/estampateWEBAvanzado/html/personalizar.html',
		controller : 'personalizarCtrl'
	}).when('/estampaAdmin', {
		templateUrl : '/estampateWEBAvanzado/html/estampaAdmin.html',
		controller : 'estampaAdminCtrl'
	}).when('/camisetasAdmin', {
		templateUrl : '/estampateWEBAvanzado/html/camisetasAdmin.html',
		controller : 'camisetasAdminCtrl'
	}).when('/perfil', {
		templateUrl : '/estampateWEBAvanzado/html/perfil.html',
		controller : 'perfilCtrl'
	}).when('/estampado', {
		templateUrl : '/estampateWEBAvanzado/html/catalogoEstampas.html',
		controller : 'estampaCtrl'
	}).when('/personalizarEstampa', {
		templateUrl : '/estampateWEBAvanzado/html/personalizarEstampa.html',
		controller : 'personalizarEstampaCtrl'
	}).when('/crearEstampa', {
		templateUrl : '/estampateWEBAvanzado/html/crearEstampa.html',
		controller : 'crearEstampaCtrl'
	}).when('/modificarEstampa/:estampa', {
		templateUrl : '/estampateWEBAvanzado/html/modificarEstampa.html',
		controller : 'modificarEstampaCtrl'
	}).when('/personaAdmin', {
		templateUrl : '/estampateWEBAvanzado/html/persona/personaAdmin.html',
		controller : 'personaAdminCtrl'
	}).when('/crearPersona', {
		templateUrl : '/estampateWEBAvanzado/html/persona/persona.html',
		controller : 'crearPersonaCtrl'
	}).when('/modificarPersona/:persona', {
		templateUrl : '/estampateWEBAvanzado/html/persona/modificarPersona.html',
		controller : 'modificarPersonaCtrl'
	}).when('/carrito', {
		templateUrl : '/estampateWEBAvanzado/html/carrito.html',
		controller : 'carritoCtrl'
	}).when('/checkout', {
		templateUrl : '/estampateWEBAvanzado/html/compras.html',
		controller : 'comprasCtrl'
	}).when('/reportesAdmin', {
		templateUrl : '/estampateWEBAvanzado/html/reportes.html',
		controller : 'reportesCtrl'
	}).when('/catalogo', {
		templateUrl : '/estampateWEBAvanzado/html/catalogo.html',
		controller : 'catalogoCtrl'
	}).when('/crearUsuario', {
		templateUrl : '/estampateWEBAvanzado/public/usuario.html',
		controller : 'usuarioCtrl'
	})
} ]);

