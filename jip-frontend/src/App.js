import React from "react";
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import "antd/dist/antd.css";
import {Breadcrumb, Input, Layout, Menu} from "antd";
import Home from "./pages/Home";
import Users from "./pages/Users";
import ReadList from "./pages/ReadList";
import FavouriteList from "./pages/FavouriteList";
import SearchBarV2 from "./search-bar/SearchBarV2";
import BookDetails from "./pages/BookDetails";
import Signup from "./pages/Signup";
import AddBook from "./pages/AddBook";
import BookDetailsV2 from "./pages/BookDetailsV2";


const {Header, Content, Footer} = Layout;
const {Search} = Input;

export default function App() {
    return (
        <Router>
            <Layout style={{height: "100vh"}}>
                <Header style={{position: "fixed", zIndex: 1, width: "100%"}}>
                    <div className="logo"/>
                    <Menu theme="dark" mode="horizontal" defaultSelectedKeys={["1"]}>
                        <Menu.Item key="1">
                            <Link to="/">Home</Link>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <Link to="/users">Users</Link>
                        </Menu.Item>
                        <Menu.Item key="3">
                            <Link to="/about">About</Link>
                        </Menu.Item>
                        <Menu.Item key="4">
                            <Link to="/createBook">Create Book</Link>
                        </Menu.Item>
                    </Menu>

                </Header>
                <Content
                    className="site-layout"
                    style={{padding: "0 50px", marginTop: 64}}
                >
                    <Breadcrumb style={{margin: "16px 0"}}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>Users</Breadcrumb.Item>
                        <Breadcrumb.Item>About</Breadcrumb.Item>
                    </Breadcrumb>
                    <div
                        className="site-layout-background"
                        style={{padding: 24, minHeight: 380}}
                    >
                        <Switch>
                            <Route path="/readList">
                                <ReadList/>
                            </Route>
                            <Route path="/favouriteList">
                                <FavouriteList/>
                            </Route>
                            <Route path="/signup">
                                <Signup/>
                            </Route>
                            <Route path="/bookDetails">
                                <BookDetails/>
                            </Route>
                            <Route path="/createBook">
                                <AddBook/>
                            </Route>
                            <Route path="/searchBar">
                                <SearchBarV2/>
                            </Route>
                            <Route path="/about">
                                <About/>
                            </Route>
                            <Route path="/users">
                                <Users/>
                            </Route>
                            <Route path="/">
                                <Home/>
                            </Route>
                        </Switch>
                    </div>
                </Content>
                <Footer style={{textAlign: "center"}}>
                    Itap Design ©2022 Created by Furkan Can Itap
                </Footer>
            </Layout>
        </Router>
    );
}

function About() {
    return <h2>About</h2>;
}

