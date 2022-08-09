'use strict';

/** ============ タイトル ============ */
$(function() {
    $('#title').blur(function() {
        let title = $('#title').val();
        let judgeObj = titleValidate(title);
        
        // 登録ボタン押下後の.was-validated クラスを削除
        formRemoveValidate();
        
        $('.title-validate').removeClass('is-invalid');
        
        // タイトルの入力値に異常があった場合
        if (!judgeObj.judgeFlg) {
            $('.title-err-msg').text(judgeObj.errorMsg);
            $('.title-validate').addClass('is-invalid');
        }
    });
});

/** タイトルの入力値検証 */
function titleValidate(title) {
    let judgeObj = {
        judgeFlg: true,
        errorMsg: ''
    }
    // タイトルの内容が空文字だった場合
    if (!title) {
        judgeObj.judgeFlg = false;
        judgeObj.errorMsg = 'タイトルを入力して下さい';
        return judgeObj;
        // 許容文字数を超えた場合
    } else if (title.length > 10) {
        judgeObj.judgeFlg = false;
        judgeObj.errorMsg = '10文字以内で入力して下さい';
        return judgeObj;
    } else {
        return judgeObj;
    }
}

/** ============ コメント ============ */
$(function() {
    $('#comment').blur(function() {
        let comment = $('#comment').val();
        let judgeObj = textAreaValidate(comment);
        
        // 登録ボタン押下後の.was-validated クラスを削除
        formRemoveValidate();
        
        $('.textarea-validate').removeClass('is-invalid');
        
        // コメントの入力値に異常があった場合
        if (!judgeObj.judgeFlg) {
            $('.textarea-err-msg').text(judgeObj.errorMsg);
            $('.textarea-validate').addClass('is-invalid');
        }
    });
});

/** コメントの入力値検証 */
function textAreaValidate(comment) {
    let judgeObj = {
        judgeFlg: true,
        errorMsg: ''
    }
    // コメントの内容が空文字だった場合
    if (!comment) {
        judgeObj.judgeFlg = false;
        judgeObj.errorMsg = 'コメントを入力して下さい';
        return judgeObj;
        // 許容文字数を超えた場合
    } else if (comment.length > 100) {
        judgeObj.judgeFlg = false;
        judgeObj.errorMsg = '100文字以内で入力して下さい';
        return judgeObj;
    } else {
        return judgeObj;
    }
}

/** ============ 開始日時 ============ */
$(function() {
    $('#startTime').blur(function() {
        let startTime = $('#startTime').val();
        
        // 登録ボタン押下後の.was-validated クラスを削除
        formRemoveValidate();
        
        $('.startTime-validate').removeClass('is-invalid');
        
        // 開始日時に適切な値が存在しない場合
        if (!startTime) {
            $('.startTime-err-msg').text('開始日時を正しく入力して下さい');
            $('.startTime-validate').addClass('is-invalid');
        }
        
        // 開始日時、終了日時の妥当性チェック
        if (!dateValidityCheck()) {
            $('.startTime-err-msg').text('終了日時より前の日時にして下さい');
            $('.startTime-validate').addClass('is-invalid');
        }
        $('.endTime-validate').removeClass('is-invalid');
        
    });
});

/** ============ 終了日時 ============ */
$(function() {
    $('#endTime').blur(function() {
        let endTime = $('#endTime').val();
        
        // 登録ボタン押下後の.was-validated クラスを削除
        formRemoveValidate();
        
        $('.endTime-validate').removeClass('is-invalid');
        
        // 終了日時に適切な値が存在しない場合
        if (!endTime) {
            $('.endTime-err-msg').text('終了日時を正しく入力して下さい');
            $('.endTime-validate').addClass('is-invalid');
            return;
        }
        
        // 開始日時、終了日時の妥当性チェック
        if (!dateValidityCheck()) {
            $('.endTime-err-msg').text('開始日時より後の日時にして下さい');
            $('.endTime-validate').addClass('is-invalid');
        }
        $('.startTime-validate').removeClass('is-invalid');
        
    });
});

/** 開始日時、終了日時の妥当性チェック */
function dateValidityCheck() {
    let startTime = $('#startTime').val();
    let endTime = $('#endTime').val();
    
    // 開始日時、終了日時のどちらかが未入力の場合
    if (!startTime || !endTime) {
        return true;
        // 開始日時が終了日時よりも後の日時の場合
    } else if (startTime > endTime) {
        return false;
    } else {
        return true;
    }
}

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

/** 登録ボタン押下後の画面スタイル削除 */
function formRemoveValidate() {
    if ($('.needs-validation').hasClass('was-validated')) {
        $('.needs-validation').removeClass('was-validated');
    }
}
