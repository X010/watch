/**
 * 删除提示
 * @param url
 */
function deleteModel(url) {
    if (confirm("确认删除该项吗？")) {
        window.location.href = url;
    }
}