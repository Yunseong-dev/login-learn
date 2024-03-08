import { customAxios } from "../utils/axios";
import { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const SignUp = () => {
  const pk = 0;
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repassword, setRepassword] = useState("");

  const navigate = useNavigate();

  const joinHandler = async () => {
    try {
      if (!name || !id || !email || !password || !repassword) {
        alert('모든 필수 입력란을 작성해주세요.');
        return;
      }

      const response = await customAxios.post('/signup', {
        name,
        id,
        email,
        password
      });

      alert('회원가입이 완료되었습니다. 다시 로그인하여주세요.');
      navigate('/signin');
    } catch (error: any) {
      console.error('회원가입 중 오류 발생', error);
      if (error?.response?.status === 400) {
        alert(error.response.data.message);
      } else {
        alert(error.response.data.message);
      }
    }

  };

  const onSubmit = useCallback((e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (password !== repassword) {
      alert('비밀번호가 일치하지 않습니다.');
    } else {
      joinHandler();
    }
  }, [password, repassword, joinHandler]);

  return (
    <div>
      <form onSubmit={onSubmit}>
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="이름" />
        <input type="text" value={id} onChange={(e) => setId(e.target.value)} placeholder="아이디" />
        <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="이메일" />
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="비밀번호" />
        <input type="password" value={repassword} onChange={(e) => setRepassword(e.target.value)} placeholder="비밀번호확인" />
        <button type="submit">회원가입</button>
      </form>
      <a href="/signin">로그인</a>
    </div>
  );
}

export default SignUp;
