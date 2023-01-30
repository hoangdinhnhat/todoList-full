import html from "../library/core.js";
import login from "./login.js";
import signup from "./signup.js"

function logLayer()
{
    return html`
    <div class="login-layer">
    ${login()}
    ${signup()}
</div>
    `
}

export default logLayer

