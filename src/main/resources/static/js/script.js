$(document).ready(function(){
	var base_url = 'http://clinic-calendar.herokuapp.com:8080/';

	if($('#txtIdCentro').val() == ''){
		window.location.href = 'https://clinic-calendar.herokuapp.com/';
	}

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
		
		window.location.href = 'https://clinic-calendar.herokuapp.com/ficha/'+rut_usu+'/'+idEvento;
	});

	//GUARDA FICHA
	$('#btnGuardaFicha').on('click', function(e){
		e.stopImmediatePropagation();

		var form_ficha = {
			"fecha"   : new Date(),
			"rutPac"  : $('#txtRutFicha').val(),
			"edad"    : $('#txtEdadFicha').val(),
			"peso"    : $('#txtPeso').val(),
			"estatura": $('#txtEstatura').val(),
			"motivo"  : $('#txtMotivo').val(),
			"antecedentes" : $('#txtAntecedentes').val(),
			"indicaciones" : $('#txtIndicaciones').val(),
			"examenFisico" : $('#txtExamen').val(),
			"diagnostico"  : $('#txtDiagnostico').val(),
			"idProfesional": $('#txtIdProf').val(),
			"solExamen"    : $('#txtSolEx').val()
		}

		$.ajax({
			type: 'post',
			url: 'https://clinic-calendar.herokuapp.com/ficha/save',
			contentType: "application/json; charset=utf-8",
            dataType: "json",
			data: JSON.stringify(form_ficha),
			success: function(){
				alert('Ficha guardada exitosamente!');
			},
			error: function(){
				alert('Error al guardar la ficha');
			}
		}).done(function(){
			var idEvento = $('#txtIdEventoFicha').val();
			var formData = {
				'estado' : 3
			}
			console.log('evento: '+idEvento);

			$.ajax({
				type: 'put',
				url: 'https://clinic-calendar.herokuapp.com/confirmar/'+idEvento,
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				data: JSON.stringify(formData),
				success: function(){
					console.log('Evento cerrado!');
				},
				error: function(){
					console.log('Error al guardar la ficha');
				}
			});
		});
	});

	//Historial de atenciones
	$('#tableFichasAnteriores td').on('click', function (e) {
		var a = $(this).attr('value');
			
		/*$.ajax({
			type: 'post',
			url: baseUrl+'Ficha/getFichabyId',
			data: {idficha: a},
			success: function(data){
				var obj = JSON.parse(data);
				$('#txtMotivo').val(obj.ficha.motivo);
				$('#txtObsFicha').val(obj.ficha.obsGenerales);
					
			},
			error: function(){
				console.log('error al buscar ficha by ID');
			}
		});*/
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
			url: "https://clinic-calendar.herokuapp.com/profesional/verificar/"+email,
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