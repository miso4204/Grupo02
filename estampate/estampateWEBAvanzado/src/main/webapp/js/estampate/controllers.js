/**
 * Config Controllers
 */

var estampateControllers = angular.module('estampateControllers', ['ngCookies']);
function isEmptyJSON (obj) {
	for(var i in obj) { return false; }
	return true;
}
estampateControllers.controller('mainCtrl', [ '$scope', '$http','$cookieStore', function($scope, $http, $cookieStore) {
	$http.get("/estampateWEBAvanzado/webresources/Funcionalidades").success(function(response) {
		$scope.funcionalidades = response;
		$scope.prueba=$cookieStore.get("estampaSelected")
	});
} ]);



estampateControllers.controller('catalogoEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.vista="Galeria";
	$http.get("/estampateWEBAvanzado/webresources/Estampa").success(function (response){
		$scope.estampas= response;					
	} );
	$scope.selecionarEstampa=function(estampa){
		$cookieStore.put('estampaSelected',estampa);
	}		
} ]);
estampateControllers.controller('catalogoCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.vista="Galeria";
	$http.get("/estampateWEBAvanzado/webresources/TipoCamiseta").success(function (response){
		$scope.tiposCamisetas= response;					
	} );
	$scope.selecionarTipoCamiseta=function(tipoCamiseta){
		$cookieStore.put('tipoCamisetaSelected',tipoCamiseta);
		$location.path("#/personalizar");
	}
} ]);

estampateControllers.controller('estampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.estampaSeleccionada=$cookieStore.get('estampaSelected');
	if($scope.estampaSeleccionada.ratting>=1){
		document.getElementById("star1").className = "fa fa-star gold";
		if($scope.estampaSeleccionada.ratting>=2){
			document.getElementById("star2").className = "fa fa-star gold";
			if($scope.estampaSeleccionada.ratting>=3){
				document.getElementById("star3").className = "fa fa-star gold";
				if($scope.estampaSeleccionada.ratting>=4){
					document.getElementById("star4").className = "fa fa-star gold";
					if($scope.estampaSeleccionada.ratting=5){
						document.getElementById("star5").className = "fa fa-star gold";
					}
				}
				
			}
		}
	}
	
	$scope.ratingDesign=function(){
		$scope.estampaSeleccionada.ratting = $scope.estampaSeleccionada.cantidadVotos*$scope.estampaSeleccionada.ratting+parseInt($scope.rating);
		$scope.estampaSeleccionada.cantidadVotos= $scope.estampaSeleccionada.cantidadVotos+1;
		$scope.estampaSeleccionada.ratting = $scope.estampaSeleccionada.ratting/$scope.estampaSeleccionada.cantidadVotos;
		$http.put("/estampateWEBAvanzado/webresources/Estampa/",$scope.estampaSeleccionada).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar la estampa:'+data}];
		});
	}
	$http.get("/estampateWEBAvanzado/webresources/Estampa").success(function (response){
		$scope.estampas= response;					
	} );
	$scope.selecionarEstampa=function(estampa){
		$cookieStore.put('estampaSelected',estampa);
	}		
	
	$http.get("/estampateWEBAvanzado/webresources/facebook/habilitado").success(function(response) {
		$scope.facebookHabilitado = response;
	});
} ]);

estampateControllers.controller('camisaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEBAvanzado/webresources/TipoCamiseta").success(function (response){
		$scope.tiposCamisetas= response;					
	} );
	$scope.selecionarTipoCamiseta=function(tipoCamiseta){
		$cookieStore.put('tipoCamisetaSelected',tipoCamiseta);
	}		
} ]);

estampateControllers.controller('reportesCtrl', [ '$scope', '$routeParams','$http','$cookieStore','$sce', function($scope, $routeParams, $http,$cookieStore,$sce) {	
	$scope.reporte = {"idReporte":3,"nombre":"Ventas por periodo"};
	$scope.reportes = [{"idReporte":1,"nombre":"Rating por diseño"},{"idReporte":2,"nombre":"Rating por artista"},{"idReporte":3,"nombre":"Ventas por periodo"},{"idReporte":4,"nombre":"Ventas por artista"}];
	/*
	$http.get("/estampateWEBAvanzado/webresources/reportes/getReporte/3").success(function (response){
		$scope.reporte= response;					
	} );*/
	$scope.changeContent = function(reporte) {
		//alert("ingreso");
		//alert(reporte.idReporte);
		$http.get("/estampateWEBAvanzado/webresources/reportes/getReporte/"+reporte.idReporte).success(function (response){
			$scope.reporteGen= response;					
		} );
		//alert("datos: " + $scope.reporteGen);
		if(reporte.idReporte == "1")
		{
			$scope.content = 'http://localhost:8080/estampateWEBAvanzado/public/reporterRatingDisenos.pdf';
		}
		else if(reporte.idReporte == "2")
		{
			$scope.content = 'http://localhost:8080/estampateWEBAvanzado/public/reporterRatingDisenosArtistas.pdf';
		}
		else if(reporte.idReporte == "3")
		{
			//$scope.content = 'http://localhost:8080/estampateWEBAvanzado/public/reporteVentasPeriodo.pdf';
			//alert("ingreso");
			//$window.location.href = 'http://localhost:8080/estampateWEBAvanzado/public/reporteVentasPeriodo.pdf';
		}
		else if(reporte.idReporte == "3")
		{
			$scope.content = 'http://localhost:8080/estampateWEBAvanzado/public/reporteVentasArtista.pdf';
		}
	}
	$scope.selectAction = function(reporte) {
	    //alert("seleccion: " + reporte.idReporte);
	    $scope.reporteSelected = reporte;
	};
} ]);

estampateControllers.controller('personalizarCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.holaTest="hola :)";
	$scope.estampaSelected=$cookieStore.get('estampaSelected');
	$scope.tipoCamisetaSeleccionado=$cookieStore.get('tipoCamisetaSelected');
		$http.get("/estampateWEBAvanzado/webresources/Talla").success(function (response){
			$scope.tallas= response;					
		} );
		$http.get("/estampateWEBAvanzado/webresources/Color").success(function (response){
			$scope.colores= response;					
		} );
		$http.get("/estampateWEBAvanzado/webresources/Material").success(function (response){
			$scope.materiales= response;					
		} );
		$http.get("/estampateWEBAvanzado/webresources/Estampa").success(function (response){
			$scope.estampas= response;					
		} );
		$scope.setEstampa = function(estampa) {
	    	$scope.estampaSelected = estampa;
	    }
		$scope.addCarrito=function (){
	    	if( typeof $scope.tallaSelected === 'undefined' )
	    	{
	    		$scope.tallaSelected=$scope.tallas[0];
	    	}
	    	if( typeof $scope.colorSelected === 'undefined' )
	    	{
	    		$scope.colorSelected=$scope.colores[0];
	    	}
	    	var precio = $scope.tipoCamisetaSeleccionado.valor + $scope.estampaSelected.precio;
	    	$scope.camiseta={"cantidad":1, "tipoCamiseta": $scope.tipoCamisetaSeleccionado, "estampaBean":$scope.estampaSelected, "tallaCamiseta":$scope.tallaSelected, "colorCamiseta":$scope.colorSelected, "precio":precio, "materialCamiseta":$scope.materiales[0]};
	    	
	    	
			$http.put("/estampateWEBAvanzado/webresources/Carrito/",$scope.camiseta).success(function (){
				 alert('La camiseta ha sido agregada al carrito de compras');
			} ).error(function(data, status, headers, config){
				alert('Error al actualizar el carrito:'+data);
			});
		};
} ]);

estampateControllers.controller('personalizarEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.value= 0;
	$scope.estampaSelected=$cookieStore.get('estampaSelected');
	$scope.tipoCamisetaSeleccionado=$cookieStore.get('tipoCamisetaSelected');
		$http.get("/estampateWEBAvanzado/webresources/Talla").success(function (response){
			$scope.tallas= response;
			
		} );
		$http.get("/estampateWEBAvanzado/webresources/Color").success(function (response){
			$scope.colores= response;
		} );
		$http.get("/estampateWEBAvanzado/webresources/Material").success(function (response){
			$scope.materiales= response;					
		} );
		$http.get("/estampateWEBAvanzado/webresources/TipoCamiseta").success(function (response){
			
			$scope.tiposCamisetas= response;
			$scope.tipoCamisetaSeleccionado = response[0];
		} );
	    
	    $scope.newValue = function(value) {
	    	$scope.tipoCamisetaSeleccionado = $scope.tiposCamisetas[value-1];
	    }
	    $scope.setTalla=function (talla){
	    	$scope.tallaSelected=talla;
	    	
		};
		$scope.setColor=function (color){
	    	$scope.colorSelected=color;
	    	
		};
	    $scope.addCarrito=function (){
	    	if( typeof $scope.tallaSelected === 'undefined' )
	    	{
	    		$scope.tallaSelected=$scope.tallas[0];
	    	}
	    	if( typeof $scope.colorSelected === 'undefined' )
	    	{
	    		$scope.colorSelected=$scope.colores[0];
	    	}
	    	var precio = $scope.tipoCamisetaSeleccionado.valor + $scope.estampaSelected.precio;
	    	$scope.camiseta={"cantidad":1, "tipoCamiseta": $scope.tipoCamisetaSeleccionado, "estampaBean":$scope.estampaSelected, "tallaCamiseta":$scope.tallaSelected, "colorCamiseta":$scope.colorSelected, "precio":precio, "materialCamiseta":$scope.materiales[0]};
	    	
	    	
			$http.put("/estampateWEBAvanzado/webresources/Carrito/",$scope.camiseta).success(function (){
				 alert('La camiseta ha sido agregada al carrito de compras');
			} ).error(function(data, status, headers, config){
				alert('Error al actualizar el carrito:'+data);
			});
		};
} ]);

estampateControllers.controller('modificarEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$http.get("/estampateWEBAvanzado/webresources/Estampa/"+$routeParams.estampa).success(function (response){
		$scope.estampa= response;					
	} );
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$http.get("/estampateWEBAvanzado/webresources/Tema").success(function (response){
		$scope.temas= response;					
	} );
	$scope.modificar=function(){
			$http.put("/estampateWEBAvanzado/webresources/Estampa/",$scope.estampa).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al actualizar la estampa:'+data}];
			});
	}
}]);
estampateControllers.controller('crearEstampaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$scope.estampa={"cantidadVotos":0, "imagen":"", "nombre":"", "informacion":"", "popularidad":0, "precio":0, "ratting":0, "temaBean":{}};
	$http.get("/estampateWEBAvanzado/webresources/Tema").success(function (response){
		$scope.temas= response;					
	} );
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.crear=function(){
			$http.post("/estampateWEBAvanzado/webresources/Estampa/",$scope.estampa).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Estampa Creada'}];
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al crear la estampa:'+data}];
			});
	}
}]);
estampateControllers.controller('estampaAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[];	
	$scope.cargarEstampas=function (){
		$http.get("/estampateWEBAvanzado/webresources/Estampa/User").success(function (response){
			$scope.estampas= response;					
		} );
	}
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.eliminar=function(estampa){
		
			if(confirm("Esta seguro de eliminar la estampa ?")){
				$http.delete("/estampateWEBAvanzado/webresources/Estampa/"+estampa.id).success(function (){
					 $scope.alerts=[{type: 'success',msg: 'Estampa Eliminada'}];
					 $scope.cargarEstampas();
				} ).error(function(data, status, headers, config){
					$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
					$scope.cargarEstampas();
				});
			}		
	}
	$scope.cargarEstampas();
} ]);

estampateControllers.controller('camisetasAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$http.get("/estampateWEBAvanzado/webresources/TipoCamiseta").success(function (response){
		$scope.camisetas= response;					
	} );
	$scope.seleccionar=function (){
			
	}
} ]);
estampateControllers.controller('carritoCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	
	$http.get("/estampateWEBAvanzado/webresources/MetodoEnvio").success(function (response){
		$scope.metodosDeEnvio= response;					
	} );
	$scope.setMetodoEnvio=function (metodoEnvio){
    	$scope.metodoEnvioSelected=metodoEnvio;
    	$scope.totalConEnvio();
	};
	$http.get("/estampateWEBAvanzado/webresources/Carrito/").success(function (response){
		 $scope.camisasEnCarrito = response;
		 
	} ).error(function(data, status, headers, config){
		alert('Error al actualizar el carrito:'+data);
	});	
	$scope.updateItem=function(item)
	{
		$http.put("/estampateWEBAvanzado/webresources/Carrito/Update/",item).success(function (response){
			 
		});	
	}
	$scope.getTotal = function(){
	    var total = 0;
	    for(var i = 0; i < $scope.camisasEnCarrito.length; i++){
	        var product = $scope.camisasEnCarrito[i];
	        total += (product.precio * product.cantidad);
	    }
	    return total;
	}
	$scope.totalConEnvio = function(){
	    if($scope.metodoEnvioSelected === undefined || $scope.metodoEnvioSelected === null)
	    	return $scope.getTotal();
	    else
	    	return $scope.getTotal()+$scope.metodoEnvioSelected.valor;
	}
	$scope.removeItem = function(item){
		
		if(confirm("Esta seguro de eliminar la camiseta del carrito?")){
			$http.put("/estampateWEBAvanzado/webresources/Carrito/Delete/",item).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Camiseta Eliminada'}];
				 $http.get("/estampateWEBAvanzado/webresources/Carrito/").success(function (response){
					 $scope.camisasEnCarrito = response;
					 
				} ).error(function(data, status, headers, config){
					alert('Error al actualizar el carrito:'+data);
				});	
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al eliminar la camiseta, por favor intente de nuevo: '+data}];
			});
		}
	}
	$scope.enviarDatos = function(){
		$cookieStore.put('metodoEnvioSelected',$scope.metodoEnvioSelected);
		$location.path("#/checkout");
	}
} ]);
estampateControllers.controller('comprasCtrl', [ '$scope', '$routeParams','$http','$cookieStore','$location', function($scope, $routeParams, $http,$cookieStore,$location) {	
	$scope.alerts=[];
    $scope.checkboxModel = {value1:false,value2:false,value3:false};
	$http.get("/estampateWEBAvanzado/webresources/Persona").success(function (response){
		$scope.persona= response;					
	} );
	$scope.consultarCarrito=function (){
		$http.get("/estampateWEBAvanzado/webresources/Carrito/ByUser/").success(function (response){
			 $scope.carrito = response;
			 $scope.metodoEnvio=$cookieStore.get("metodoEnvioSelected");
		});
	}
	$scope.realizarPago=function(mediopago){
		//alert(angular.toJson(carrito));
		$scope.rand = 5465898989 - Math.random();
		$scope.met = $scope.metodoEnvio.id.toString()+"|"+mediopago;
		$http.post("/estampateWEBAvanzado/webresources/Venta/Pagar/",$scope.met).success(function (){
			alert("El pago fue exitoso con código de referencia:" + $scope.rand.toString().replace(".", ""));
			$scope.alerts=[{type: 'success',msg: 'El pago fue exitoso con código de referencia: ' + $scope.rand.toString().replace(".", "")}];
		} ).error(function(data, status, headers, config){
			if(data.indexOf("Error CAR1543:") !=-1)
			{
			  alert("Error CAR1543: La característica seleccionada no esta disponible en la versión actual.");
			  $scope.alerts=[{type: 'danger',msg: 'Error al realizar el pago: ' + 'Error CAR1543: La característica seleccionada no esta disponible en la versión actual.'}];	
			}
			else
			{
				$scope.alerts=[{type: 'danger',msg: 'Error al realizar el pago:' + data}];				
			}
		});
	}
	$scope.cambiarCheck=function(id){
		if(id==1){
		    $scope.checkboxModel = {value1:true,value2:false,value2:false};
		}
		if(id==2){
		    $scope.checkboxModel = {value1:false,value2:true,value2:false};
		}
		if(id==3){
		    $scope.checkboxModel = {value1:false,value2:false,value2:true};
		}
	}
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	}
	$scope.consultarCarrito();
} ]);

estampateControllers.controller('perfilCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {
	$scope.alerts=[]
	$http.get("/estampateWEBAvanzado/webresources/Persona").success(function (response){
		$scope.persona= response;					
	} );
	$http.get("/estampateWEBAvanzado/webresources/Usuario").success(function (response){
		$scope.usuario= response;					
	} );
	$scope.guardarPerfil=function (){
		$http.put("/estampateWEBAvanzado/webresources/Persona/",$scope.persona).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
		});
	};
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.guardarPassword=function(){
		$http.put("/estampateWEBAvanzado/webresources/Usuario/",$scope.usuario).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
		});
	}  
} ]);

estampateControllers.controller('personaAdminCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.alerts=[];	
	$scope.persona={"id":0,"apellidos":"", "departamento":"", "direccion":"", "email":"", "fechaNacimiento":null, "identificacion":"", "municipio":"", "nombres":"","pais":"", "telefono":""};
	$scope.cargarPersonas=function (){
		$http.get("/estampateWEBAvanzado/webresources/Persona/All").success(function (response){
			$scope.personas = response;					
		} );
	}
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	$scope.eliminarPersona=function eliminarPersona(persona){
		if(confirm("Esta seguro de eliminar la persona ?")){
				$http.put("/estampateWEBAvanzado/webresources/Persona/Delete",persona).success(function (){
					 $scope.alerts=[{type: 'success',msg: 'Persona Eliminada'}];
					 $scope.cargarPersonas();
				} ).error(function(data, status, headers, config){
					$scope.alerts=[{type: 'danger',msg: 'Error al eliminar la Persona:'+data}];
					$scope.cargarPersonas();
				});
		}		
	}
	$scope.cargarPersonas();

} ]);
estampateControllers.controller('crearPersonaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.alerts=[];	
	$scope.persona={"id":0,"apellidos":"", "departamento":"", "direccion":"", "email":"", "fechaNacimiento":null, "identificacion":"", "municipio":"", "nombres":"","pais":"", "telefono":""};
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	/*Crea una persona*/
	$scope.crearPersona=function(){
		$http.post("/estampateWEBAvanzado/webresources/Persona/",$scope.persona).success(function (){
			$scope.alerts=[{type: 'success',msg: 'Persona Creada'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al crear la persona:'+data}];
		});
	}
	/*Crea una persona*/
	$scope.crearUsuario=function crearUsuario(usuario){
		$scope.usuarioCrear={"username":usuario.username, "password": usuario.password, "personaBean": {"identificacion":$scope.persona.identificacion}};
		$http.post("/estampateWEBAvanzado/webresources/Usuario/",$scope.usuarioCrear).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Usuario Creado'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al crear el usuario:'+data}];
		});
	}
	// ****** DatePicker
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MM-yyyy'];
	  $scope.format = $scope.formats[0];
} ]);

estampateControllers.controller('modificarPersonaCtrl', [ '$scope', '$routeParams','$http','$cookieStore', function($scope, $routeParams, $http,$cookieStore) {	
	$scope.alerts=[];
	/*Obtiene persona por id*/
	$http.get("/estampateWEBAvanzado/webresources/Persona/ById/"+$routeParams.persona).success(function (response){
		$scope.persona = response;
	} );
	/*Obtiene Usuario por persona id*/
	$http.get("/estampateWEBAvanzado/webresources/Usuario/ByPersona/"+$routeParams.persona).success(function (response){
		$scope.usuario= response;
	} );
	
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	/*Actualiza una persona*/
	$scope.actualizarPersona=function(){
			$http.put("/estampateWEBAvanzado/webresources/Persona",$scope.persona).success(function (){
				 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
			} ).error(function(data, status, headers, config){
				$scope.alerts=[{type: 'danger',msg: 'Error al actualizar la persona:'+data}];
			});
	}
	
	/*Actualiza una usuario*/
	$scope.actualizarUsuario=function(){
		$http.put("/estampateWEBAvanzado/webresources/Usuario/",$scope.usuario).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Datos Actualizados'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al actualizar el perfil:'+data}];
		});
	}
	
	// ****** DatePicker
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MM-yyyy'];
	  $scope.format = $scope.formats[0];
} ]);
estampateControllers.controller('usuarioCtrl', [ '$scope', '$routeParams','$http','$cookieStore','$location', function($scope, $routeParams, $http,$cookieStore,$location) {	
	$scope.alerts=[];	
	$scope.persona={"id":0,"apellidos":"", "departamento":"", "direccion":"", "email":"", "fechaNacimiento":null, "identificacion":"", "municipio":"", "nombres":"","pais":"", "telefono":""};
	$scope.closeAlert=function() {
	    $scope.alerts=[];
	};
	/*Crea una persona*/
	$scope.crearPersona=function(){
		$http.post("/estampateWEBAvanzado/webresources/Persona/",$scope.persona).success(function (){
			$scope.alerts=[{type: 'success',msg: 'Persona Creada'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al crear la persona:'+data}];
		});
	}
	/*Crea una persona*/
	$scope.crearUsuario=function crearUsuario(usuario){
		$scope.tipoUsuario = $location.search().dat;
		$scope.tipoR=0;
		if($scope.tipoUsuario = 'a')
		{
			$scope.tipoR = 2;
		}else
			{
				$scope.tipoR = 3;
			}
		$scope.usuarioCrear={"username":usuario.username, "password": usuario.password, "personaBean":{"identificacion":$scope.persona.identificacion},"rolBean":{"id":$scope.tipoR}};
		$http.post("/estampateWEBAvanzado/webresources/Usuario/UsuarioRol",$scope.usuarioCrear).success(function (){
			 $scope.alerts=[{type: 'success',msg: 'Usuario Creado'}];
		} ).error(function(data, status, headers, config){
			$scope.alerts=[{type: 'danger',msg: 'Error al crear el usuario:'+data}];
		});
	}
	// ****** DatePicker
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MM-yyyy'];
	  $scope.format = $scope.formats[0];
} ]);
