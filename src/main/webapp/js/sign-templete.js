function toSignUp()
{
    let signUp = document.querySelector('.sign-in-up-form.unselected')
    signUp.previousElementSibling.classList.add('unselected')
    signUp.classList.remove('unselected')
}

function toSignIn()
{
    let signIn = document.querySelector('.sign-in-up-form.unselected')
    signIn.nextElementSibling.classList.add('unselected')
    signIn.classList.remove('unselected')
}

function showUserFunc(element)
{
    element.classList.toggle('clicked')
}