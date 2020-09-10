$(document).ready(function(){
	var base_url = 'http://localhost:8080/';

	$('.input-number').on('input', function () { 
    	this.value = this.value.replace(/[^0-9]/g,'');
	});

	$('#myModal').on('shown.bs.modal', function (e) {
		var perfil = $('#txtNavUser').val();

		if(perfil == 0){
			$('#btnComenzar').prop('disabled',true);
		}
	});

	//- - - -SECCION FICHA  - - - - - 
	// INSERT NUEVA
	$('#btnComenzar').on('click',function(e){
		e.stopImmediatePropagation();
		var idEvento = $('#txtIdEvento').val();
		var rut_usu = $('#txtHiddenRut').val();
		
		window.location.href = base_url + 'ficha/'+rut_usu;
	});

	//GUARDA FICHA
	$('#btnGuardaFicha').on('click', function(e){
		e.stopImmediatePropagation();
		var form_ficha = {
			"fecha"   : new Date(),
			"rutPac"  : $('#txtRutFicha').val(),
			"peso"    : "",
			"estatura": "",
			"motivo"  : $('#txtMotivo').val(),
			"antecedentes" : $('#txtAntecedentes').val(),
			"indicaciones" : $('#txtIndicaciones').val(),
			"examenFisico" : $('#txtExamen').val(),
			"diagnostico"  : $('#txtDiagnostico').val(),
			"idProfesional": $('#txtIdProf').val()
		}

		$.ajax({
			type: 'post',
			url: base_url + 'ficha/save',
			contentType: "application/json; charset=utf-8",
            dataType: "json",
			data: JSON.stringify(form_ficha),
			success: function(){
				alert('Ficha guardada exitosamente!');
			},
			error: function(){
				alert('Error al guardar la ficha');
			}
		});
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