$(document).ready(function(){
	var base_url = 'https://clinic-calendar.herokuapp.com/';
	//var base_url = 'http://localhost:8080/';
	if($('#txtIdCentro').val() == ''){
		window.location.href = base_url;
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
		
		window.location.href =  base_url + 'ficha/'+rut_usu+'/'+idEvento;
	});

	//GUARDA FICHA
	$('#btnGuardaFicha').on('click', function(e){
		e.stopImmediatePropagation();
		$('#modalCierraFicha').modal('show');

		if($('#btnConfirmFicha').on('click', function(){

			if($('#txtMotivo').val() == '' || $('#txtDiagnostico').val() == '' || $('#txtIndicaciones').val() == ''){
				alert('Debe copletar los datos del formulario');
			}else{
				var idEvento = $('#txtIdEventoFicha').val();

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
					url: base_url +  'ficha/save',
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					data: JSON.stringify(form_ficha),
					success: function(){
						$('#modalCierraFicha').modal('hide');
					},
					error: function(){
						alert('Error al guardar la ficha');
					}
				}).done(function(){
					var formData = {
						'estado' : 3
					}

					$.ajax({
						type: 'put',
						url: base_url + 'confirmar/'+idEvento,
						contentType: "application/json; charset=utf-8",
						dataType: "json",
						data: JSON.stringify(formData),
						success: function(){
							console.log('Evento cerrado!');
						},
						error: function(){
							alert('Ficha guardada exitosamente!'); // a pesar del error, aquí se guarda bien la ficha y se cierra el evento
							console.log('Error al cerrar evento, pero se cerró');
							//window.location.href = base_url + 'calendar';
						}
					});
				});
			}
		}));
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
			url: base_url + "profesional/verificar/"+email,
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

	//- - - - -USUARIOS
	//ELIMINA	
	$('#modalEliminaUser').on('shown.bs.modal', function(e){
		e.stopImmediatePropagation();
		var boton = e.relatedTarget;
		var idUser = $(boton).attr('data-id');
		console.log($(boton).attr('data-id'));
		$('#txtHiddenUserElimina').val(idUser);
	});

	$('#btnEliminaUserPerfil').on('click', function(e){
		e.stopImmediatePropagation();
		var idusuario = $('#txtHiddenUserElimina').val();
		
		$.ajax({
			type: 'put',
			url: 'https://clinic-calendar.herokuapp.com/' + 'user/delete/'+idusuario,
			success: function(){
				$('#modalEliminaUser').modal('hide');
				window.location.reload();
			},
			error: function(){
				console.log('error al eliminar usuario');
			}
		});
	});

	//EDITA
	$('#modalEditaUser').on('shown.bs.modal', function(e){
		e.stopImmediatePropagation();
		var boton = e.relatedTarget;
		var idUser = $(boton).attr('data-id');
		console.log($(boton).attr('data-id'));
		$('#txtHiddenUserEdita').val(idUser);
	});

	$('#btnEditaUserPerfil').on('click', function(e){
		e.stopImmediatePropagation();
		var idusuario = $('#txtHiddenUserEdita').val();
		
		$.ajax({
			type: 'put',
			url: base_url + 'user/'+idusuario,
			success: function(){
				$('#modalEditaUser').modal('hide');
				window.location.reload();
			},
			error: function(){
				console.log('error al editar usuario');
			}
		});
	});

});