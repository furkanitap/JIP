import {Button, Form, Input} from "antd";
import {useHistory} from "react-router-dom";
import {useState} from "react";
import BookService from "../service/BookService";

const AddBook = () => {
    const history = useHistory();
    const [credentials, setCredentials] = useState({});

    const onFinish = async (values) => {
        console.log("Success:", values);
        debugger

        const response = await BookService.create(credentials);
        if (response) {
            history.push("/createBook");
        }

        //UserService.delete();
    };

    const onFinishFailed = (errorInfo) => {
        console.log("Failed:", errorInfo);
    };

    const handleChange = (event) => {
        debugger
        setCredentials({
            ...credentials,
            [event.target.name]: event.target.value
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
                label="Name"
                name="name"
                rules={[
                    {
                        required: true,
                        message: "Please input book name!"
                    }
                ]}
            >
                <Input
                    onChange={handleChange}
                    name="name"
                    value={credentials.name}
                />
            </Form.Item>

            <Form.Item
                label="Author"
                name="author"
                rules={[
                    {
                        required: true,
                        message: "Please input author name!"
                    }
                ]}
            >
                <Input
                    onChange={handleChange}
                    name="author"
                    value={credentials.author}
                />
            </Form.Item>

            <Form.Item
                label="ISBN"
                name="isbn"
                rules={[
                    {
                        required: true,
                        message: "Please input book ISBN!"
                    }
                ]}
            >
                <Input
                    onChange={handleChange}
                    name="isbn"
                    value={credentials.isbn}
                />
            </Form.Item>

            <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16
                }}
            >
                <Button type="primary" htmlType="submit">
                    Create
                </Button>
                <br/>
                <br/>

            </Form.Item>

        </Form>
    );
};

export default AddBook;
