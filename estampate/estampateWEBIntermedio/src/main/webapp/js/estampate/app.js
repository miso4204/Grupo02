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
		templateUrl : '/estampateWEBIntermedio/html/inicio.html',
		controller : 'mainCtrl'
	}).when('/estampa', {
		templateUrl : '/estampateWEBIntermedio/html/estampa.html',
		controller : 'catalogoEstampaCtrl'
	}).when('/camisa', {
		templateUrl : '/estampateWEBIntermedio/html/camiseta.html',
		controller : 'camisaCtrl'
	}).when('/personalizar', {
		templateUrl : '/estampateWEBIntermedio/html/personalizar.html',
		controller : 'personalizarCtrl'
	}).when('/estampaAdmin', {
		templateUrl : '/estampateWEBIntermedio/html/estampaAdmin.html',
		controller : 'estampaAdminCtrl'
	}).when('/camisetasAdmin', {
		templateUrl : '/estampateWEBIntermedio/html/camisetasAdmin.html',
		controller : 'camisetasAdminCtrl'
	}).when('/perfil', {
		templateUrl : '/estampateWEBIntermedio/html/perfil.html',
		controller : 'perfilCtrl'
	}).when('/estampado', {
		templateUrl : '/estampateWEBIntermedio/html/catalogoEstampas.html',
		controller : 'estampaCtrl'
	}).when('/personalizarEstampa', {
		templateUrl : '/estampateWEBIntermedio/html/personalizarEstampa.html',
		controller : 'personalizarEstampaCtrl'
	}).when('/crearEstampa', {
		templateUrl : '/estampateWEBIntermedio/html/crearEstampa.html',
		controller : 'crearEstampaCtrl'
	}).when('/modificarEstampa/:estampa', {
		templateUrl : '/estampateWEBIntermedio/html/modificarEstampa.html',
		controller : 'modificarEstampaCtrl'
	}).when('/personaAdmin', {
		templateUrl : '/estampateWEBIntermedio/html/persona/personaAdmin.html',
		controller : 'personaAdminCtrl'
	}).when('/crearPersona', {
		templateUrl : '/estampateWEBIntermedio/html/persona/persona.html',
		controller : 'crearPersonaCtrl'
	}).when('/modificarPersona/:persona', {
		templateUrl : '/estampateWEBIntermedio/html/persona/modificarPersona.html',
		controller : 'modificarPersonaCtrl'
	}).when('/carrito', {
		templateUrl : '/estampateWEBIntermedio/html/carrito.html',
		controller : 'carritoCtrl'
	}).when('/checkout', {
		templateUrl : '/estampateWEBIntermedio/html/compras.html',
		controller : 'comprasCtrl'
	}).when('/reportesAdmin', {
		templateUrl : '/estampateWEBIntermedio/html/reportes.html',
		controller : 'reportesCtrl'
	}).when('/catalogo', {
		templateUrl : '/estampateWEBIntermedio/html/catalogo.html',
		controller : 'catalogoCtrl'
	}).when('/crearUsuario', {
		templateUrl : '/estampateWEBIntermedio/public/usuario.html',
		controller : 'usuarioCtrl'
	})
} ]);

