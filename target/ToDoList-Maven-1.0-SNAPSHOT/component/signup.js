import html from "../library/core.js";

function signup()
{
    return html`
    <form action="signup" method="post" class="sign-in-up-form unselected">
        <h1>SIGN UP</h1>
        <div class="input-ele">
            <label for="username">Username </label>
            <input type="text" name="username" id="username" placeholder="Username">
        </div>
        <br>
        <div class="input-ele">
            <label for="email">Email </label>
            <input type="email" name="email" id="email" placeholder="Email">
        </div>
        <br>
        <div class="input-ele">
            <label for="password">Password </label>
            <input type="password" name="password" id="password" placeholder="Password">
        </div>
        <br>
        <div class="input-ele">
            <label for="conf-password">Confirm Password </label>
            <input type="password" name="conf-password" id="conf-password" placeholder="Confirm Password">
        </div>
        <br>
        <div class="buttons">
            <input type="submit" value="SIGN UP">
            <div href="" onclick="toSignIn()">SIGN IN</div>
        </div>
    </form>
    `
}

export default signup