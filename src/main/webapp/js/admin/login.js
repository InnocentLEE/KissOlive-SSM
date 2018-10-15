$('#mpanel2').codeVerify({
        type : 1,
        width : '400px',
        height : '50px',
        fontSize : '30px',
        codeLength : 6,
        btnId : 'check-btn',
        ready : function() {
        },
        success : function() {
            $('#myModal').modal('hide');
        },
        error : function() {
            alert('验证码不匹配！');
        }
    });
