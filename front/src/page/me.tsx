import useToken from "../hooks/userToken";
import { useEffect, useState } from "react";
import axios from "axios";
import { customAxios } from "../utils/axios";

const me = () => {
  const { token, setToken } = useToken();
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState('');
  const [repassword, setRePassword] = useState('');

  useEffect(() => {
    if (token) {
      axios.get('http://localhost:8080/user/userinfo', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
        .then((response) => {
          setId(response.data.id);
          setName(response.data.name);
          setEmail(response.data.email);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [token]);

  const passwordChack = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();

    if (!repassword) {
      alert("현재 비밀번호를 입력해주세요.");
      return;
    }

    if (name === null || email === null) {
      alert("이름 또는 이메일을 입력하세요.")
      return;
    }

    try {
      const response = await customAxios.post('/user/Change', {
        id,
        name,
        email,
        password,
        repassword
      });

      const token = response.data.token;
      setToken(token);
      alert("회원정보가 수정되었습니다.");
      window.location.reload();


    } catch (error) {
      console.error("로그인 중 오류 발생", error);
      if (axios.isAxiosError(error)) {
        const response = error.response;
        if (response?.status === 400) {
          alert("현재 비밀번호가 일치하지 않습니다.");
        } else {
          alert("로그인 중 알 수 없는 오류가 발생했습니다.");
        }
      }
    }
  };

  return (
    <div>
      <form onSubmit={passwordChack}>
        <input type="text" value={id} disabled />
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
        <input type="password" placeholder="변경할 비밀번호" value={password} onChange={(e) => setPassword(e.target.value)} />
        <input type="password" placeholder="현재 비밀번호" value={repassword} onChange={(e) => setRePassword(e.target.value)} />
        <button type="submit">변경하기</button>
      </form>
      <a href="/">홈</a>
    </div>
  );
}

export default me;