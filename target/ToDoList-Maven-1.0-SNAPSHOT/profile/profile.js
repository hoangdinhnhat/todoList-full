var uploadFile = document.querySelector('#uploadFile')
uploadFile.onchange = function () {
    var uploadForm = document.querySelector('.changeAvatar form')
    uploadForm.submit()
    location.reload();
}

function edittingMode(ele) {
    let password = ele.querySelector('#password')
    let changePassword = ele.querySelector('#changePassword')
    password.style.display = 'none'
    changePassword.value = password.textContent
    changePassword.style.display = 'block'
    changePassword.focus()
}

function checkUTF8(text) {
    var utf8Text = text;
    try {
        utf8Text = decodeURIComponent(escape(text));
        return false
    } catch (e) {
        return true
    }
}

function handleEnter(ele) {
    let editValue = ele.value
    if (editValue.indexOf(' ') != -1 || checkUTF8(editValue)) {
        alert('Password cant include blank or utf8 character!')
        return;
    }
    let userInfor = document.querySelector('.userInfor')
    let username = userInfor.querySelector('#username').textContent.trim()
    console.log(username);
    let newInfo = username + ',' + editValue;
    let APIs = 'edited'
    let options = {
        method: 'POST',
        mode: "no-cors",
        headers: {
            'Content-Type': 'application/json',
        },
        body: newInfo
    }
    fetch(APIs, options)
        .then((response) => {
            let password = document.querySelector('#password')
            let changePassword = document.querySelector('#changePassword')
            password.style.display = 'block'
            changePassword.value = password.textContent
            changePassword.style.display = 'none'
            location.reload()
        })
}