import {Select} from 'antd';
import React, {useState} from 'react';
import BookService from "../service/BookService";
import {useHistory} from "react-router-dom";

const {Option} = Select;
let timeout;
let currentValue;

const fetch = (value, callback) => {
    if (timeout) {
        clearTimeout(timeout);
        timeout = null;
    }
    currentValue = value;

    const fake = async (params = {}) => {

        const data = await BookService.doSearch(currentValue);

        console.log(JSON.stringify(data));
        callback(data)

    };
    timeout = setTimeout(fake, 600);
};

const SearchInput = (props) => {
    const history = useHistory();
    const [data, setData] = useState([]);
    const [value, setValue] = useState();

    const handleSearch = (newValue) => {


        if (newValue) {

            fetch(newValue, setData);
        } else {

            setData([]);
        }
    };

    const handleChange = (newValue) => {

        setValue(newValue);
        history.push({
            pathname: '/bookDetails',
            state: {bookId: newValue}
        });

    };


    const options = data.map((d) => <Option key={d.id}>{d.name} </Option>);

    return (
        <Select
            showSearch
            value={value}
            placeholder={props.placeholder}
            style={props.style}
            defaultActiveFirstOption={false}
            showArrow={false}
            filterOption={false}
            onSearch={handleSearch}
            onChange={handleChange}
            notFoundContent={null}
        >
            {options}
        </Select>
    );
};

const SearchBarV2 = () => (
    <SearchInput
        placeholder="input search text"
        style={{
            width: 300,
        }}
    />
);

export default SearchBarV2;