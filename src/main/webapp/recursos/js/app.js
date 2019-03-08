var app = angular.module('controleservicos',['ngRoute', 'ngResource']);

app.config(function($routeProvider){

	$routeProvider
	.when("/servico/cadastro",{
		controller : "servicoController",
		templateUrl : "views/servicos/cadastro.html"
	})
	.when("/servico/cadastro/:id",{
		controller : "servicoController",
		templateUrl : "views/servicos/cadastro.html"
	})
	.when("/servico/listar",{
		controller : "servicoController",
		templateUrl : "views/servicos/list.html"
	})
	.when("/cliente/cadastro",{
		controller : "clienteController",
		templateUrl : "views/clientes/cadastro.html"
	})
	.when("/cliente/listar",{
		controller : "clienteController",
		templateUrl : "views/clientes/list.html"
	})
	.when("/ordem/cadastro",{
		controller : "ordemServicoController",
		templateUrl : "views/ordemservico/cadastro.html"
	})
	.when("/ordem/listar",{
		controller : "ordemServicoController",
		templateUrl : "views/ordemservico/list.html"
	})

});


app.controller('servicoController', function($scope, $http, $location, $routeParams){

	$scope.idSelectedRow = null;
	
	if ($routeParams.id != null && $routeParams.id != undefined	&& $routeParams.id != ''){		
		// se estiver editando 			
		$http.get("ws/servico/get/" + $routeParams.id).success(function(response) {
			$scope.servico = response;
			setTimeout(function () {
			}, 1000);
		}).error(function(data, status, headers, config) {
			erro("Error: " + status);
		});
	}else { 
		$scope.servico = {};
	}

	$scope.listarServicos = function() {		
		$http.get("ws/servico/listar/").success(function(response) {
			$scope.data = response;			
		}).error(function(response) {
			erro("Error: " + response);
		});
	};
	$scope.salvarServico = function(){
		$http.post("ws/servico/salvar", $scope.servico).success(function(response) {
			$scope.servico = {};
			sucesso("Gravado com sucesso!");
		}).error(function(response) {
			erro("Error: " + response);
		});
	}
			
	$scope.edit = function(){		 
		$location.url("servico/cadastro/"+$scope.idSelectedRow);
	}	
	
});

app.controller('clienteController', function($scope, $http, $location, $routeParams){

	$scope.listarClientes = function(){
		$http.get("ws/cliente/listar").success(function(response){
			$scope.data = response;
		}).error(function(response){
		   erro("Error: " + response);
		});
	}

	$scope.salvarCliente = function(){
		$scope.cliente.dataNasc = converteData($scope.cliente.dataNasc);
		$scope.cliente.sexo = parseInt($scope.cliente.sexo);
		$http.post("ws/cliente/salvar", $scope.cliente).success(function(){
			$scope.cliente = {};
			sucesso("Gravado com sucesso");
		}).error(function(response){
		   erro("Error: " + response);
		})
	}	

	$scope.goToLista = function(){
		$location.url("/cliente/listar"); 
	}

});

app.controller('ordemServicoController', function($scope, $http, $location, $routeParams){

	
	$scope.listarOrdens = function(){
		$http.get("ws/ordem/listar").success(function(response){
			$scope.data = response;
		}).error(function(response){
			erro('Error ' + response);
		});
	};

	$scope.listarClientes = function(){
		$http.get("ws/cliente/listar").success(function(response){
			$scope.clientesLista = response;
		}).error(function(response){
		   erro("Error: " + response);
		});
	}

	$scope.listarServicos = function(){
		$http.get("ws/servico/listar").success(function(response){
			$scope.servicosLista = response;
		}).error(function(response){
		   erro("Error: " + response);
		});
	}

	$scope.salvarOrdemServico = function(){
		$scope.ordemservico.dataAbertura = converteData($scope.ordemservico.dataAbertura);
		$http.post("ws/ordem/salvar", $scope.ordemservico).success(function(response){			
			$scope.ordemservico = {};
			sucesso("Gravado com sucesso");
		}).error(function(response){
			erro("Error: " + response);
		});
	}

});

app.directive('datepicker', function(){
	
	return function(scope, element, attrs) {
		element.datepicker({ numberOfMonths: 1, showButtonPanel: true, dateFormat: "dd/mm/yyyy"});
	}
});

app.directive('selectrecord', function(){
	
	return function(scope, element, attrs) {
		element.select2();
	}
});

app.directive('selectrow', function(){
	
	return function(scope, element, attrs) {
		element.click(function(){
			 $(this).addClass('selected').siblings().removeClass('selected');    
			 var value = $(this).find('td:first').html();
			 scope.$parent.idSelectedRow = value;
		});	   
	}
});

converteData = function($data){
	var ano = $data.substring(6,10);
	var mes = $data.substring(3,5);
	var dia = $data.substring(0,2);		
	return ano+"-"+mes+"-"+dia;
}


function sucesso(msg) {
    	$.notify({
        	message: msg
        },{
            type: 'success',
            timer: 1000
        });
}

//mostra msg de erro
function erro(msg) {
	$.notify({
    	message: msg
    },{
        type: 'danger',
        timer: 1000
    });
}
