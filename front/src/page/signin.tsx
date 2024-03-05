import useToken from '../hooks/userToken.js';
import { customAxios } from "../utils/axios.js";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const SignIn = () => {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const { token, setToken } = useToken();
  const navigate = useNavigate();

  useEffect(() => {
    if (!!token) {
      alert("이미 로그인이 되어있습니다.");
      navigate("/");
    }
  }, [token, navigate]);

  const loginHandler = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();

    if (!id || !password) {
      alert("아이디와 비밀번호를 모두 입력해주세요.");
      return;
    }

    try {
      const response = await customAxios.post('/signin', {
        id,
        password
      });

      const token = response.data.token;
      setToken(token);
      navigate("/");
    } catch (error) {
      console.error("로그인 중 오류 발생", error);
      if (axios.isAxiosError(error)) {
        const response = error.response;
        if (response?.status === 400) {
          alert(response?.data.message);
        } else {
          alert("로그인 중 알 수 없는 오류가 발생했습니다.");
        }
      }
    }
  };

  return (
    <div>
      <form onSubmit={loginHandler}>
        <input
          type="text"
          value={id}
          onChange={(e) => setId(e.target.value)}
          placeholder="아이디"
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="비밀번호"
        />
        <button type="submit">로그인</button>
      </form>
      <a href="/signup">회원가입</a>
      <br />
      <a href="/">홈</a>
    </div>
  );
}

export default SignIn;
