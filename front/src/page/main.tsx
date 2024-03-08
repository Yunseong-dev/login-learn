import { Link } from "react-router-dom";
import useToken from "../hooks/userToken";
import { useEffect, useState } from "react";
import { fetcherWithToken } from "../utils/axios";

const Main = () => {
  const { token, removeToken } = useToken();
  const [ name, setName] = useState("");

  useEffect(() => {
    if (token) {
      fetcherWithToken(token, '/user/userinfo')
        .then((data) => {
          setName(data.name);
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
          <a href="/article"></a>
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