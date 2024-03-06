import { Link } from "react-router-dom";
import useToken from "../hooks/userToken";
import { useEffect, useState } from "react";
import axios from "axios";

const Main = () => {
  const { token, removeToken } = useToken();
  const [ name, setName] = useState("");

  useEffect(() => {
    if (token) {
      axios.get('http://localhost:8080/user/userinfo', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
      .then((response) => {
        setName(response.data.name);
      })
      .catch((error) => {
        console.error(error);
      });
    }
  }, [token]);

  return (
    <div>
      {!!token ? (
        <>
          <div>반갑습니다, {name}님!</div>
          <Link to={"/"}><div onClick={removeToken}>로그아웃</div></Link>
          <a href="/me">내정보</a>
        </>
      ): (
        <>
          <a href="/signin">로그인하기</a>
        </>
        )}
    </div>
  );
};

export default Main;