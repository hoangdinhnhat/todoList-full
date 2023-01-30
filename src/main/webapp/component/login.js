import html from "../library/core.js";

function login()
{
    return html`
    <form action="login" method="post" class="sign-in-up-form">
        <h1>LOGIN</h1>
        <div class="input-ele">
            <label for="username">Username </label>
            <input type="text" name="username" id="username" placeholder="Username">
        </div>
        <br>
        <div class="input-ele">
            <label for="password">Password </label>
            <input type="password" name="password" id="password" placeholder="Password">
        </div>
        <br>
        <div id="html_element"></div>
        <br>
        <div class="buttons">
            <input type="submit" value="SIGN IN">
            <div href="" onclick="toSignUp()">SIGN UP</div>
        </div>
    </form>
    `
}

export default login