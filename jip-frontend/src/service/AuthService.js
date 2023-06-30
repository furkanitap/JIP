import axios from "axios";
import LocalStorageUtil from "../util/LocalStorageUtil";

const AuthService = (function () {
    const _signin = async (credentials) => {
        let token = null;

        try {
            const response = await axios.post(
                "http://localhost:8080/home/authenticate",
                credentials
            );
            if (response && response.data) {
                token = response.data.token;
                LocalStorageUtil.setToken(token);
            }
        } catch (error) {
            console.log(error);
        }

        return token;
    };

    const _signup = async (credentials) => {
        debugger

        try {
            const response = await axios.post(
                "http://localhost:8080/home/createUser",
                credentials
            );

        } catch (error) {
            console.log(error);
        }

        return "User created";
    };

    return {
        signin: _signin,
        signup: _signup
    };
})();

export default AuthService;
