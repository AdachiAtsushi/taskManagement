'use strict';

/** ============ 登録 ============ */
$(function() {
    $('form').submit(function(event) {
        // 各項目でバリデーションエラーが出ているなら、イベント中止
        if ($('.title-validate').hasClass('is-invalid') 
            || $('.textarea-validate').hasClass('is-invalid') 
            || $('.startTime-validate').hasClass('is-invalid') 
            || $('.endTime-validate').hasClass('is-invalid')) {
                return false;
            }
        
        let formItems = $('.needs-validation');
        
        for (let form of formItems) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            $('.needs-validation').addClass('was-validated');
        }
    });
})
