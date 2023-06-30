import {Button, Form, Input} from "antd";
import {useHistory} from "react-router-dom";
import {useState} from "react";
import AuthService from "../service/AuthService";

const Home = () => {
    const history = useHistory();
    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);

        const response = await AuthService.signin(credentials);
        if (response) {
            history.push("/users");
        }
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    const handleChange = (event) => {
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value
        });
    };

    const handleSignup = () => {
        history.push({
            pathname: '/signup'
        });
    };

    return (
        <Form
            name="basic"
            labelCol={{
                span: 8
            }}
            wrapperCol={{
                span: 16
            }}
            initialValues={{
                remember: true
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            style={{margin: "0 auto", width: 400}}
        >
            <Form.Item
                label="Username"
                name="username"
                rules={[
                    {
                        required: true,
                        message: "Please input your username!"
                    }
                ]}
            >
                <Input
                    onChange={handleChange}
                    name="username"
                    value={credentials.username}
                />
            </Form.Item>

            <Form.Item
                label="Password"
                name="password"
                rules={[
                    {
                        required: true,
                        message: "Please input your password!"
                    }
                ]}
            >
                <Input.Password
                    onChange={handleChange}
                    name="password"
                    value={credentials.password}
                />
            </Form.Item>

            <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16
                }}
            >
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
                <br/>
                <br/>
                <Button type={"primary"} onClick={handleSignup}>Signup</Button>

            </Form.Item>

        </Form>
    );
};

export default Home;
