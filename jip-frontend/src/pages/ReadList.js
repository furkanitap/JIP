import React from "react";
import "antd/dist/antd.css";
import {Table} from "antd";
import BookService from "../service/BookService";
import "./Users.css"
import {Link} from "react-router-dom";

const columns = [
    {
        title: "Name",
        dataIndex: "name",
        sorter: true,
        width: "20%"
    },
    {
        title: "Author",
        dataIndex: "author",
        sorter: true,
        width: "20%"
    },
    {
        title: "ISBN",
        dataIndex: "isbn"
    }
];

class ReadList extends React.Component {
    state = {
        data: [],
        pagination: {
            current: 1,
            pageSize: 5,
            defaultPageSize: 5
        },
        loading: false
    };

    componentDidMount() {
        const {pagination} = this.state;
        this.fetch(pagination);
    }

    handleTableChange = (pagination) => {

        this.fetch(
            pagination
        );
    };

    fetch = async (params = {}) => {
        this.setState({loading: true});

        const data = await BookService.fetchReadList(params);
        console.log(JSON.stringify(data));

        this.setState({
            loading: false,
            data: data,
            pagination: {
                ...params.pagination,
                total: 200 // Mock data
            }
        });
    };

    render() {
        const {data, pagination, loading} = this.state;

        return (
            <div className="container">
                <div className="left">
                    <Table
                        columns={columns}
                        rowKey={(record) => record.id}
                        dataSource={data}
                        pagination={pagination}
                        loading={loading}
                        onChange={this.handleTableChange}
                    />
                </div>
                <div className="right">
                    <div className="links">
                        <Link to="/favouriteList">Your Favourite List</Link>
                        <br/>
                        <Link to="/readList">Your Read List</Link>
                    </div>
                </div>
            </div>
        );
    }
}

export default ReadList;

