import { Routes, Route } from 'react-router-dom';
import Signup from './page/signup';
import Signin from './page/signin';
import Main from './page/main';
import Me from './page/me';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/signin" element={<Signin />} />
      <Route path="/me" element={<Me />} />
    </Routes>
  );
};

export default App;
