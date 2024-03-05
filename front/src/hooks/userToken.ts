import { jwtDecode } from "jwt-decode";
import { useCookies } from "react-cookie";

interface TokenData {
   token: string | undefined;
   subject: string | undefined;
   setToken: (value: string) => void;
   removeToken: () => void;
}

const KEY = 'access_token';

export default function useToken(): TokenData {
   const [cookies, setCookies, removeCookies] = useCookies([KEY]);
   const token: string | undefined = cookies[KEY];

   const setToken = (value: string) => {
      setCookies(KEY, value);
   }

   const removeToken = () => {
      removeCookies(KEY);
   }

   let subject: string | undefined;

   if (!!token) {
      subject = jwtDecode(token).sub;
   }

   return { token, subject, setToken, removeToken };
}
