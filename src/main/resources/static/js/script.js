$(document).ready(function(){
	var base_url = 'http://localhost:8080/';

	$('.input-number').on('input', function () { 
    	this.value = this.value.replace(/[^0-9]/g,'');
	});

	//- - - -SECCION FICHA  - - - - - 
	// INSERT NUEVA
	$('#btnComenzar').on('click',function(e){
		e.stopImmediatePropagation();
		var idEvento = $('#txtIdEvento').val();
		window.location.href = base_url + 'ficha?idEvento='+idEvento;
	});


	// - - - - FIN SECCION  FICHA  - - - 

	//INVITACION
	$('#btn-invitacion').click(function(e){
		e.stopImmediatePropagation()
		var recipient = $('#txt_mail_invita').val();
		var recipient_type = $('#cboPerfilInvita').val();

		$.ajax({
			type: 'post',
			contentType: "application/json; charset=utf-8",
	        dataType: "json",
		});
	})

	//SECCION PROFESIONAL - - - - - - 

	$("#div-datosProf").hide();

	//BUSCA EMAIL
	$('#btnBuscaEmail').on('click',function(e){
		e.stopImmediatePropagation();
		var email = $('#txtBuscaProf').val();

		$.ajax({
			type: 'get',
			url: "/profesional/verificar/"+email,
			data: {email: email},
			dataType: "json",
			success: function(d){
				if(d != ''){
					$("#div-datosProf").show();
					console.log(d);
					$('#txtnombreProf').val(d.nombre);
					$('#txtapellidoProf').val(d.apat+' '+d.amat);
					$('#txtHiddenIdUsuario').val(d.idUsuario);
				}
			},
			error: function(jqXHR,xhr, status, text){
				if (jqXHR.status == 404){
					alert('Usuario no encontrado');
					$('#txtnombreProf').val('');
					$('#txtapellidoProf').val('');
				}
				
			}
		});
	});

	//guarda nuevo profesional
	$('#btnOkProf').on('click', function(){

	});

	$('#btnCancelProf').on('click',function(e){
		e.stopImmediatePropagation()
		$('#txtBuscaProf').val('');
		$('#txtnombreProf').val('');
		$('#txtapellidoProf').val('');
		$("#div-datosProf").hide();
	});

});