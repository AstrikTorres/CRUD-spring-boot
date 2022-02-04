var global = new Object();
//const btnEncryptData = document.getElementById('btnEncryptData');
//btnEncryptData.addEventListener('click', encryptData);
function numberRand(){
	return Math.floor(Math.random() * ((10000+1)-1)+1);
}

function affiliateMerchant(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var datos = "signed="+encodeURIComponent($("#data-encrypted").val()); 
	var token = $("#token").val();

	$.ajax({
		url: '/affiliateMerchant.do?random='+new Date(),
		type: 'POST', 
		data: datos,
		headers: {
			Authorization:
				'Bearer '+ token
		},
		success: function(result) {
			//$("#btnSendTx").button("reset");
			console.debug("Obteniendo el resultado");
			global = result;
			console.debug( result );
			$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
		},
		error: function(error) {
			console.debug("Obteniendo el error ");
			$("#btnSendTx").button("reset");
			console.debug( error );
			global = error;
			$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
		}
	});

}


function sendTxCode(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var datos = "signed="+encodeURIComponent($("#data-encrypted").val()); 
	var token = $("#token").val();

	$.ajax({
		url:
			'/sendTxCode.do?random='+new Date(),
			type: 'POST', 
			data: datos,
			headers: {
				Authorization:
					'Bearer '+ token
			},
			success: function(result) {
				$("#btnSendTx").button("reset");
				global = result;
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			},
			error: function(error) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			}
	});

}



function sendTx(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");
	var token = $("#token").val();

	var jsonData = {"auth": ("Bearer " + token),
			"rand": (new Date()),
			"data": ("signed="+encodeURIComponent($("#data-encrypted").val()))}

	$.ajax({
		url:'/sendTx/',
		type: 'POST', 
		data: jsonData,
		dataType : 'text',
		success: function(response) {
			$("#btnSendTx").button("reset");
			$("#txtResponse").val( response);
		},
		error: function(error) {
			global = error
			console.log("Error: " + global);
			$("#btnSendTx").button("reset");
			$("#txtResponse").val( global );
		}
	});

}


function verifyReference(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");
	var token = $("#token").val();

	var datos = {
			"data": ("signed="+encodeURIComponent($("#data-encrypted").val())),
			"auth": ('Bearer '+ token),
			"rand": (new Date())	
	}; 

	$.ajax({
		url:
			'/verifyReference/',
			type: 'POST', 
			data: datos,
			dataType : 'xml',
			success: function(result) {
				$("#btnSendTx").button("reset");
				global = result;
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			},
			error: function(error) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			}
	});

}


function getProductList(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var token = $("#token").val();

	var jsonData = {"rand" : (new Date()),
			"auth": ("Bearer " + token)};

	$.ajax({
		url:
			'/getProductList/',
			type: 'POST',
			dataType : 'text',
			data : jsonData,
			success: function(response) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( response);
			},
			error: function(error) {
				global = error
				console.log("Error: " + global);
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( global );
			}
	});

}


function getBalance(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var token = $("#token").val();
	var datos = {
			"auth": ('Bearer '+ token),
			"rand": (new Date())	
	}; 

	$.ajax({
		url:
			'/getBalance/',
			type: 'POST',
			data: datos,
			dataType : 'xml',
			success: function(result) {
				$("#btnSendTx").button("reset");
				global = result;
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			},
			error: function(error) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			}
	});

}



function sendEcho(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var token = $("#token").val();
	var datos = {
			"auth": ('Bearer '+ token),
			"rand": (new Date())	
	}; 

	$.ajax({
		url:
			'/sendEcho/',
			type: 'POST', 
			data: datos,
			dataType : 'xml',
			success: function(result) {
				$("#btnSendTx").button("reset");
				global = result;
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			},
			error: function(error) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			}
	});

}



function identifyMe(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var token = $("#token").val();

	var jsonData = {"rand" : (new Date()),
			"auth": ("Bearer " + token)};
	
	$.ajax({
		url:'/identifyMe/',
		type: 'POST',
		dataType : 'xml',
		data : jsonData,
		success: function(response) {
			$("#btnSendTx").button("reset");
			global = response;
			$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
		},
		error: function(error) {
			global = error
			$("#btnSendTx").button("reset");
			$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
		}
	});

}


function confirmTx(){
	var token = $("#token").val();
	$("#txtResponse").val( "");
	$("#btnConfirmTx").button("loading");
	var datos = {
			"data": ("signed="+encodeURIComponent($("#data-encrypted").val())),
			"auth": ('Bearer '+ token),
			"rand": (new Date())	
	}; 

	$.ajax({
		url:
			'/confirmTx/',
			type: 'POST', 
			data: datos,
			dataType : 'xml',
			success: function(result) {
				$("#btnConfirmTx").button("reset");	
				$("#txtResponse").val( new XMLSerializer().serializeToString(result) );
			},
			error: function(error) {
				$("#btnConfirmTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(result) );
			}
	});

}

function getJwtToken(){



	var jsonData = {"data" : ("idDistribuidor=" + $('#txtIdDistribuidor').val()
			+ "&codigoDispositivo=" + $("#txtCodigoDispositivo").val()			
			+ "&password=" + $('#txtPassword').val() + "&rand="+ numberRand())};

	$.ajax({
		async : true,
		url : "/getToken/",
		type : "POST",
		dataType : 'json',
		data : jsonData,
		success: function(response) {
			response.succes != "false" ? $('#token').val(response.token) : alert(response.succes);
		},
		error: function(error) {
			alert("failure");
		}
	});

}

function encryptData(){

	var jsonData = {"data" : ('data=' + $('#data-2-encrypt').val()),
			"idDistribuidor": $('#txtIdDistribuidor').val()
	};
	console.log($('#data-2-encrypt').val());

	$.ajax({
		async : true,
		url:'/encryptData/',
		type: 'POST',
		data: jsonData,
		success: function(response) {
			$("#data-encrypted").val(response);
		},
		error: function(error) {
			alert("failure");
		}
	});

}



function decryptData(){

	var data = "data="+encodeURIComponent($("#data-2-decrypt").val());

	$.ajax({
		url:
			'/decrypt/',
			type: 'POST', 
			data: data,

			success: function(result) {
				//	alert( "exito" );
				$("#data-decrypted").val( result );
			},
			error: function(error) {
				// alert("failure");
			}
	});  

}




function getEncryptionKeys(){
	$("#txtResponse").val( "");
	$("#btnSendTx").button("loading");

	var token = $("#token").val();

	$.ajax({
		url:
			'/sistema/service/getEncryptionKeys.do?random='+new Date(),
			type: 'POST', 
			headers: {
				Authorization:
					'Bearer '+ token
			},
			success: function(result) {
				$("#btnSendTx").button("reset");
				global = result;
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			},
			error: function(error) {
				$("#btnSendTx").button("reset");
				$("#txtResponse").val( new XMLSerializer().serializeToString(global) );
			}
	});

}