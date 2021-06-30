
$('document').ready(function() {
	
	
	$('#auth-card').css({"display":"none"});
	
	$('#newApplicationBtn').on('click', function(event) {
		event.preventDefault();
		
		var formData = {
			       "mobileNumber" : $("#mobileNumber").val(),
			       "accountCurreny": $("#accountCurreny").val()
			       };
		
		newApplication(formData);
		

	});
	
	
//	$('#cifForm').submit(function(e){
//	    e.preventDefault();
//	    $.ajax({
//	        url: '/onboarding',
//	        type: 'post',
//	        data:$('#cifForm').serialize(),
//	        success:function(){
//	        	$('#collapseThree').toggleClass('show')
//	        	$('#collapseOne').removeClass('show')
//	        	
//	        },
//	    failure:function(){
//	    	console.log('failed');
//	    }
//	    });
//	});
	
	
//	$('#nextButton').on('click', function(event){
//		e.preventDefault();
//		$.ajax({
//	        url: '/onboarding',
//	        type: 'post',
//	        data:$('#cifForm').serialize(),
//	        success:function(){
//	        	$('#collapseThree').toggleClass('show')
//	        	$('#collapseOne').removeClass('show')
//	        	
//	        },
//	    failure:function(){
//	    	console.log('failed');
//	    }
//	    });
//	});
	
	
	$('#mobileQRBtn').on('click', function(event){
		event.preventDefault();
		loadImage();
	});
	
	$('#addAuth').on('click', function(event) {
		event.preventDefault();
		
		$('#auth-card').css({'display': 'block'});

	});
	
	
	$('#scanPhysicalCivilIdBtn').on('click', function (event){
		event.preventDefault();
		var civilId = $("#civilIdNumber").val();
		var json = {
			       "civilId" : civilId,
			       "authMode":"scan-physical"
			       };
		
		addMIDAuthentication(json)
	});
	
	$('#documentSaveBtn').on('click', function (event){
		event.preventDefault();
		var documentNumber = $("#documentNumber").val();
		var json = {
			       "documentNumber" : documentNumber,
			       "authMode":"other"
			       };
		
		addMIDAuthentication(json)
	});
	
	
	
	$('#sendPushNotificationBtn').on('click', function (event){
		event.preventDefault();
		var civilId = $("#civilIdNumber").val();
		var json = {
			       "customerCivilId" : civilId,
			       "authMode":"push"
			       };
		
		addMIDAuthentication(json)
	});
	
	
	$('#scanCustomerQRBtn').on('click', function (event){
		event.preventDefault();
		var customerQRData = $("#customerQRScanText").val();
		var json = {
			       "qrData" : customerQRData,
			       "authMode":"scan-qr"
			       };
		
		addMIDAuthentication(json)
	});
	
	$(document).on("click", "#authTable button.btn_delete", function() {
        let btn_id = (event.srcElement.id);
        civilId = btn_id.split("_")[2];

        $("div.modal-body").text("Do you want to delete The Authentication for Civil id = " + civilId + " ?");
        $("#model-delete-btn").css({"display": "inline"});
    });
	
	
	$(document).on("click", "#model-delete-btn", function() {
		
		deleteMIDAuthentication(civilId);
        
    });
	
	
});

	function newApplication(jsonData){
		$.ajax({
		       type : "POST",
		       contentType : "application/json",
		       url : "/newApplication",
		       data : JSON.stringify(jsonData),
		       dataType : 'json',
		       cache : false,
		       timeout : 600000,
		       success: function(response){
	              
		    	   $("#collapseFour").collapse('toggle');
		    	   $("#referenceNumber").html( response );
	            },
	            error : function(e) {
	              console.log("ERROR: ", e);
	            }
		});
	}

	function addMIDAuthentication(jsonData){
		authResponseRow='';
		$.ajax({
		       type : "POST",
		       contentType : "application/json",
		       url : "/addAuth",
		       data : JSON.stringify(jsonData),
		       dataType : 'json',
		       cache : false,
		       timeout : 600000,
		       success: function(response){
	              $.each(response, (i, authResponse) => {  

	                let deleteButton = '<button id='+'btn_delete_' +authResponse.customerCivilId +' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal">Delete</button>';
//	                let editButton = '<label class="c-switch c-switch-label c-switch-pill c-switch-success"><input id='+'btn_primary_' +authResponse.customerCivilId +' class="c-switch-input" type="checkbox"><span class="c-switch-slider" data-checked="✓" data-unchecked="✕"></span></label>'
	                
	                let tr_id = 'tr_' + authResponse.customerCivilId;
	                let authResponseRow = '<tr id=\"' + tr_id + "\"" + '>' +
	                			  '<td class=\"td_first_civilId\">' + authResponse.customerCivilId + '</td>' +
		                          '<td class=\"td_first_name\">' + authResponse.customerName.toUpperCase() + '</td>' +
		                          '<td class=\"td_expiry_date\">' + authResponse.civilIdExpiryDate + '</td>' +
		                          '<td class=\"td_expiry_auth\">' + authResponse.isAuthenticated + '</td>' +
//		                          '<td>' + editButton + '</td>'+
		                          '<td>' + deleteButton + '</td>' +
	                          '</tr>';                
	                $('#authTable tbody').append(authResponseRow);
	              });
	              $('#auth-card').css({"display":"none"});
	              $("#collapseFour").collapse('toggle');
	            },
	            error : function(e) {
	              alert("ERROR: ", e);
	              console.log("ERROR: ", e);
	            }
		});
	}
	
	function makePrimary(civilId){
		$.ajax({
            url: '/primaryAuth/' + civilId,
            type: 'POST',
            cache : false,
            contentType : "application/json",
            success: function(response) {
                $("div.modal-body").text("Marked as Primary to Civil id = " + civilId + "!");

                $("#model-primary-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the customer row on html page
                let row_id = "tr_" + civilId;
                //$("#" + row_id).remove();
                
                $('#auth-card').css({"display":"none"});
            },
            error: function(error){
                console.log("Error -> " + error);
            }
        });
	}
	
	function deleteMIDAuthentication(civilId){
		$.ajax({
            url: '/deleteAuth/' + civilId,
            type: 'POST',
            cache : false,
            contentType : "application/json",
            success: function(response) {
                $("div.modal-body").text("Delete successfully a Customer with id = " + civilId + "!");

                $("#model-delete-btn").css({"display": "none"});
                $("button.btn.btn-secondary").text("Close");

                // delete the customer row on html page
                let row_id = "tr_" + civilId;
                $("#" + row_id).remove();
                $('#auth-card').css({"display":"none"});
            },
            error: function(error){
                console.log("Error -> " + error);
            }
        });
	}
	
	function loadImage(){
		$.ajax({
			  url: "/mobileId/qr",
			  type: "GET",
			  success: function(response) {
				  mobileIdQR="data:image/png;base64," + response;
				  $("#mobileIdQR").attr("src", mobileIdQR);
			  },
			  error: function(xhr) {
			  }
			});
	}
