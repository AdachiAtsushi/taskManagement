'use strict';

/**
    削除成功メッセージを非表示にする
 */
$(window).on('click', function() {
    $('#deleteSuccess').hide();
});

/** 
    入力欄クリア処理
 */
$(function() {
    $('#clear').on('click', function() {
        $('#keyword').val('');
    });
});

/**
    入力欄バリデーションチェック
 */
$(function() {
    $('#keyword').blur(function() {
        let keyword = $('#keyword').val();
        // 入力欄のバリデーションチェック
        let resultObj = keywordValidate(keyword);
        
        $('#keyword').removeClass('is-invalid');
        
        if (!resultObj.resultFlg) {
            $('.keyword-err-msg').text(resultObj.errorMsg);
            $('#keyword').addClass('is-invalid');
        }
    });
});

/**
    入力欄バリデーションチェック用関数
 */
function keywordValidate(keyword) {
    let resultObj = {
        resultFlg : true,
        errorMsg : ''
    }
    
    if (!keyword) {
        resultObj.resultFlg = false;
        resultObj.errorMsg = '検索キーワードを入力して下さい'
        return resultObj;
    } else if (keyword.length > 50) {
        resultObj.resultFlg = false;
        resultObj.errorMsg = '50文字以内で入力して下さい'
        return resultObj;
    } else {
        return resultObj;
    }
}





/**
    削除モーダルウインドウ
    削除ボタンに指定のIDを紐づける
 */
$(function() {
    $('#deleteModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let taskId = button.data('id');
        $('#delete').attr('href', `/task/delete?id=${taskId}`);
    });
});