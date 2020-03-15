$(document).ready(function(){

	$('.input-number').on('input', function () { 
    	this.value = this.value.replace(/[^0-9]/g,'');
	});


	//BUSCAR PACIENTE POR RUT AL PRESIONAR ENTER
	$('#txtPaciente').keypress(function(e) {
    	var keycode = (e.keyCode ? e.keyCode : e.which);
    	if (keycode == '13') {
    		if($("#txtPaciente").val().length > 0){
    			var rut = $('#txtPaciente').val();
    			
	        	$.ajax({
	        		type: 'get',
	        		url: 'paciente/'+rut,
	        		data: {dni: rut},
	        		success: function(data){
	        			$('#txtNomPac').val(data.nombre+' '+data.a_pat+' '+data.a_mat);
	        			$('#txtobs').focus();

		        		if (data.rutnum > 1) {
		        			$('#btnOK').attr("disabled", false);
		        		}
	        		},
	        		error:function(){
	        			$('#modalPreguntaPac').modal('show');
	        			$('#txtNomPac').val('');
		        		$('#txtRutPac').val($('#txtPaciente').val());
	        		}
	        	});
    		}
	    	else{
	    		alert('Ingrese rut');
	    		$('#txtNomPac').val('');
	    	}
    	}
	});

	//NUEVO PACIENTE
	$('#btnGuardaPac').click(function(){

		var nom = $('#txtNomPac2').val();
		var apat = $('#txtApat').val();
		var amat = $('#txtAmat').val();
		var sexo = $('#cboSexo2').val(); 
		var fnac = $('#txtFnac').val();

		var formPac = {
			'dni'   : $('#txtRutPac').val(),
			'nombre'  : $('#txtNomPac2').val(),
			'a_pat'  : $('#txtApat').val(),
			'a_mat'  : $('#txtAmat').val(),
			'telefono'   : $('#txtTel').val(),
			'sexo'  : $('#cboSexo2').val(),
			'email' : $('#txtEmail2').val(),
			'fecha_nac'  : $('#txtFnac').val()
		};

		if(nom == "" || apat ==""){
			alert('Debe indicar nombre y apellido');
		}else{
			if(sexo == ""){
		    	alert('Debe seleccionar sexo');
			} else {
				if(fnac == ""){
					alert('Ingrese fecha de nacimiento');
				}else{
					$.ajax({
						type: 'post',
						url: "addpaciente",
						contentType: "application/json; charset=utf-8",
	        			dataType: "json",
						data: JSON.stringify(formPac),
						success: function(){
							//toastr.success('Paciente guardado!');
							$('#modalNewPaciente').modal('hide');
							$('#txtNomPac').val(nom+' '+apat+' '+amat);
				        	$('#modalNewPaciente').modal('hide');
							$('#btnOK').attr("disabled", false);
						},
						error: function(){
							console.log('error al guardar paciente');
						}
					});
				}
			}

		}

	});

	//limpia inputs cuando el modal se cierra
	$('#modalNew').on('hidden.bs.modal', function () {
    	$('#btnOK').attr("disabled", true);
    	$('#txtPaciente').val('');
    	$('#txtobs').val('');
    	$('#txtNomPac').val('');
	});

	$('#btnSi').click(function(){
		$('#modalPreguntaPac').modal('hide');
		$('#modalNewPaciente').modal('show');
	});
	
	//GUARDA EVENTO
	$('#btnOK').click(function(event) {
	    event.preventDefault();
	    var formData = {
	    	'title'       : $('#txtPaciente').val(),
	    	'start'       : $('#txtIni').val(),
	    	'end'	      : $('#txtFin').val(),
	    	'description' : $('#txtobs').val(),
	    	'rut_num'     : $('#txtPaciente').val()
	    };
	    
	    console.log(formData);
	    $.ajax({
	        type:'post',
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        url: "addevent",
	        data: JSON.stringify(formData),
	        success:function(data){
	        	console.log(data);
	        	$('#modalNew').modal('hide');
	          	window.location.reload();
	        },
	    });
	});
	
	//REGISTRA USUARIO
	$('#btnOK').click(function(event) {
	    event.preventDefault();
	    var formData = {
	    	'nombre'      : $('#txt_reg_nombre').val(),
	    	'a_pat'       : $('#txt_reg_apat').val(),
	    	'a_mat'	      : $('#txt_reg_amat').val(),
	    	'email' 	  : $('#txt_reg_email').val(),
	    	'password'    : $('#txt_reg_pass').val()
	    };
	    
	    console.log(formData);
	    $.ajax({
	        type:'post',
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        url: "addevent",
	        data: JSON.stringify(formData),
	        success:function(data){
	        	console.log(data);
	        	$('#modalNew').modal('hide');
	          	window.location.reload();
	        },
	    });
	});

	//INVITACION
	$('#btn-invitacion').click(function(){
		var recipient = $('#txt_mail_invita').val();
		var recipient_type = $('#cboPerfilInvita').val();

		$.ajax({
			type: 'post',
			contentType: "application/json; charset=utf-8",
	        dataType: "json",
		});
	})

});