'use strict';

/**  
    更新ボタン押下時処理
*/
$(function() {
    $('#update').on('click', function() {
        // 各項目でバリデーションエラーが出ているなら、イベント中止
        if ($('.title-validate').hasClass('is-invalid') 
            || $('.textarea-validate').hasClass('is-invalid') 
            || $('.startTime-validate').hasClass('is-invalid') 
            || $('.endTime-validate').hasClass('is-invalid')) {
                return false;
            }
    });
});

/**
    削除モーダルウインドウ
    削除ボタンに指定のIDを紐づける
 */
$(function() {
    $('#deleteModal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let taskId = button.data('id');
        
        $('#delete').attr('href', `/task/detailDelete?id=${taskId}`);
    });
});

/** ============ キャンセル ============ */
$(function() {
    $('#cancel').on('click', function() {
        let transPage = $('#screen-type').val();
        
        // 遷移元画面に遷移する
        location = `/task/${transPage}`;
    });
});

