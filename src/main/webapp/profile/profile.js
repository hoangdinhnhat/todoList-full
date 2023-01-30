var uploadFile = document.querySelector('#uploadFile')
uploadFile.onchange = function ()
{
    var uploadForm = document.querySelector('.changeAvatar form')
    uploadForm.submit()
    setTimeout(() => {
        var confirm = document.querySelector('.layerChangeImg .containImg')
        var layerChangeImg = document.querySelector('.layerChangeImg')
        layerChangeImg.style.display = 'flex';
        confirm.style.backgroundImage = 'url(http://localhost:8080/ToDoList/storage?id=pending)'
    }, 2000);
}

var confirm = document.querySelector('.layerChangeImg .confirm')
confirm.onclick = function ()
{
    location.reload();
    var layerChangeImg = document.querySelector('.layerChangeImg')
    layerChangeImg.style.display = 'none';
    var changeAvatar = document.querySelector('.changeAvatar')
    changeAvatar.style.backgroundImage = 'url(http://localhost:8080/ToDoList/storage?id=0)'
}
